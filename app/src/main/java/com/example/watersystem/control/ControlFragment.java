package com.example.watersystem.control;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watersystem.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import cn.nodemedia.NodePlayer;
import cn.nodemedia.NodePlayerDelegate;
import cn.nodemedia.NodePlayerView;

public class ControlFragment extends Fragment implements NodePlayerDelegate{

    private static final String TAG = "ControlFragment";

    // IP地址
    private static final String serverIp = "127.0.0.1";;

    // 端口号
    private static final int serverPort = 8080;

    // 客户端Socket
    private Socket socketClient;

    // 初始化输入流对象，用于从Socket中读取数据。
    private BufferedReader stockIn;

    // 初始化输出流对象，用于向Socket中写入数据。
    private PrintWriter stockOut;

    // 接收服务端发送的数据
    private String stockContent = "";

    // 初始化一个Handler对象，用于处理与Socket相关的操作。
    private Handler handlerStock;

    // 向左移动
    private Button leftButton;

    // 向右移动
    private Button rightButton;

    // 向前移动
    private Button upButton;

    // 向后移动
    private Button downButton;

    // 视频播放器
    private NodePlayerView nodePlayerView;

    private NodePlayer nodePlayer;
    private String url = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_control, container, false);

        leftButton = view.findViewById(R.id.btn_ctrl_left);
        rightButton = view.findViewById(R.id.btn_ctrl_right);
        upButton = view.findViewById(R.id.btn_ctrl_up);
        downButton = view.findViewById(R.id.btn_ctrl_down);
        nodePlayerView = view.findViewById(R.id.rtmpNpv);

        initView();
//        initSocketThread();
//        initSocketHandler();

        return view;
    }

    private void initView() {

        nodePlayerView.setRenderType(NodePlayerView.RenderType.SURFACEVIEW);
        nodePlayerView.setUIViewContentMode(NodePlayerView.UIViewContentMode.ScaleAspectFit);

        nodePlayer = new NodePlayer(requireContext());
        nodePlayer.setPlayerView(nodePlayerView);
        nodePlayer.setNodePlayerDelegate(this);
        nodePlayer.setHWEnable(true);

        nodePlayer.start();

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "left", Toast.LENGTH_SHORT).show();
                SocketSendMsg("control#10000001#left#");
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "right", Toast.LENGTH_SHORT).show();
                SocketSendMsg("control#10000001#right#");
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "up", Toast.LENGTH_SHORT).show();
                SocketSendMsg("control#10000001#up#");
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "down", Toast.LENGTH_SHORT).show();
                SocketSendMsg("control#10000001#down#");
            }
        });
    }


    //  socket线程初始化，连接服务器
    private void initSocketThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    // 创建socket并连接服务器
                    socketClient = new Socket(serverIp, serverPort);
                    stockIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                    stockOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
                    while (true) {
                        // 输入流未关闭
                        if (!socketClient.isInputShutdown()) {
                            try {
                                if ((stockContent = stockIn.readLine()) != null) {
                                    Log.i(TAG, "stock read: " + stockContent);
                                    Message message = Message.obtain();
                                    message.what = 0x00;
                                    message.obj = stockContent;
                                    handlerStock.sendMessage(message);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    // 处理socket的handler
    private void initSocketHandler() {
        handlerStock = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch (message.what) {
                    case 0x01:
                    case 0x02:
                    case 0x03:
                        break;
                    default:
                        Log.i(TAG, "Unkown msg: " + message.obj.toString());
                }
                return false;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        nodePlayer.stop();
        nodePlayer.release();
//        try {
//            socket_client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void SocketSendMsg(String data) {
        new Thread() {
            @Override
            public void run() {
                if (socketClient.isConnected()) {
                    if (!socketClient.isOutputShutdown()) {
                        Log.i(TAG, "stock send: " + data);
                        stockOut.println(data);
                    }
                }
            }
        }.start();
    }



    /**
     * NodePlayer事件触发回调
     * @param player
     * @param event
     * @param msg
     */
    @Override
    public void onEventCallback(NodePlayer player, int event, String msg) {
        switch(event){
            case 1000:
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "连接中", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1001:
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "连接成功", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1002:
                // 视频连接失败 流地址不存在，或者本地网络无法和服务端通信，回调这里。5秒后重连， 可停止
                Log.e(TAG, msg);
                break;
            case 1003:
                // 视频开始重连,自动重连总开关
                //                nodePlayer.stopPlay();
                Log.e(TAG, msg);
                break;
            case 1004:
                // 视频播放结束
                Log.e(TAG, msg);
                break;
            case 1005:
                // 网络异常,播放中断,播放中途网络异常，回调这里。1秒后重连，如不需要，可停止
                Log.e(TAG, msg);
                break;
            case 1006:
                //RTMP连接播放超时
                Log.e(TAG, msg);
                break;
            case 1100:
                // 播放缓冲区为空
                Log.e(TAG, msg);
                break;
            case 1101:
                // 播放缓冲区正在缓冲数据,但还没达到设定的bufferTime时长
                Log.e(TAG, msg);
                break;
            case 1102:
                // 播放缓冲区达到bufferTime时长,开始播放.
                // 如果视频关键帧间隔比bufferTime长,并且服务端没有在缓冲区间内返回视频关键帧,会先开始播放音频.直到视频关键帧到来开始显示画面.
                Log.e(TAG, msg);
                break;
            case 1103:
                // 客户端明确收到服务端发送来的 StreamEOF 和 NetStream.Play.UnpublishNotify时回调这里
                // 注意:不是所有云cdn会发送该指令,使用前请先测试
                // 收到本事件，说明：此流的发布者明确停止了发布，或者因其网络异常，被服务端明确关闭了流
                // 本sdk仍然会继续在1秒后重连，如不需要，可停止
                Log.e(TAG, msg);
                break;
            case 1104:
                //解码后得到的视频高宽值 格式为:{width}x{height}
                Log.e(TAG, msg);
                break;
            default:
                break;
        }
    }
}
