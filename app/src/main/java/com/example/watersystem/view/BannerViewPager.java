package com.example.watersystem.view;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.watersystem.R;

import java.util.ArrayList;
import java.util.List;

public class BannerViewPager extends FrameLayout {

    private static final String TAG = "BannerViewPager";

    private ViewPager viewPager;

    //标题
    private TextView tvTitle;

    //圆点集合
    private LinearLayout indicatorGroup;

    //适配器
    private BannerAdapter adapter;

    //标题集合
    private List<String> titles;

    //图片数据
    private List imageUrls;

    //轮播图显示
    private List<View> views;

    //保存显示的小圆点
    private ImageView[] tips;

    //保存imageUrls的总数
    private int count;

    //轮播图的间隔时间
    private int bannerTime = 2500;

    //轮播图的当前选中项
    private int currentItem = 0;

    //保存触发时手动滑动的时间 进行判断防止滑动之后立即轮播
    private long releaseTime = 0;

    //开始
    private final int START = 10;

    //结束
    private final int STOP = 20;

    private Context context;
    private Handler handler;


    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            if (now - releaseTime > bannerTime - 500) {
                handler.sendEmptyMessage(START);
            } else {
                handler.sendEmptyMessage(STOP);
            }
        }
    };


    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        titles = new ArrayList<>();
        titles.add("轮播图展示");
        titles.add("标题2");
        titles.add("标题3");
        imageUrls = new ArrayList();
        views = new ArrayList<>();
        init(context, attrs);
    }


    private void init(final Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_banner_view, this);
        viewPager = (ViewPager) view.findViewById(R.id.banner_view_pager);
        tvTitle = (TextView) view.findViewById(R.id.banner_title);
        indicatorGroup = (LinearLayout) view.findViewById(R.id.banner_indicator);
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case START:
                        viewPager.setCurrentItem(currentItem + 1);
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable, bannerTime);
                        break;
                    case STOP:
                        releaseTime = 0;
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable, bannerTime);
                        break;
                }
            }
        };
    }

    /**
     * 初始化数据 以及拿到数据后的各种设置
     * 可以是网络地址 也可是项目图片数据
     *
     * @param imageUrls
     */
    public void setData(List<?> imageUrls) {
        this.imageUrls.clear();
        this.count = imageUrls.size();

        this.imageUrls.addAll(imageUrls);

        initIndicator();
        getShowView();
        setUI();
    }

    /**
     * 设置标题
     *
     * @param titles
     */

    public void setTitles(List<String> titles) {
        this.titles.clear();
        this.titles.addAll(titles);
    }

    /**
     * 设置小圆点指示器
     */
    private void initIndicator() {
        tips = new ImageView[count-2];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // 设置点点点view的高度
        layoutParams.height = Utils.dip2px(context,10);
        // 设置点点点view的宽度
        layoutParams.width = Utils.dip2px(context,10);
        // 设置点点点view的左边距
        layoutParams.leftMargin = Utils.dip2px(context,5);
        // 设置点点点view的右边距
        layoutParams.rightMargin = Utils.dip2px(context,5);
        for (int i = 0; i < count-2; i++) {
            ImageView imageView = new ImageView(context);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.shape_circle_red);
            } else {
                imageView.setBackgroundResource(R.drawable.shape_circle_white);
            }

            tips[i] = imageView;
            indicatorGroup.addView(imageView, layoutParams);
        }
    }

    /**
     * 获取显示图片view
     */
    private void getShowView() {
        for (int i = 0; i < imageUrls.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (imageUrls.get(i) instanceof String) {

            } else {
                imageView.setImageResource((Integer) imageUrls.get(i));
            }
            views.add(imageView);
        }
    }

    /**
     * 设置UI
     */
    private void setUI() {
        adapter = new BannerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(onPageChangeLis);
        viewPager.setCurrentItem(1);
        handler.postDelayed(runnable, bannerTime);
    }

    /**
     * viewPage改变监听
     * 用于响应所选页面
     */
    private ViewPager.OnPageChangeListener onPageChangeLis = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onPageScrolled: position "+position+" positionOffset "+positionOffset+" positionOffsetPixels "+positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, "onPageSelected: position "+position);
            // 每滑动一次都只打印一次方法，position打印最终选定页面的下标

            //计算当前页的下标，用于指示器改变
            int max = views.size() - 1;
            int temp = position;
            currentItem = position;
            if (position == 0) {
                // 当滑动到下标为0时，其实对于用户来说看到的是最后一个，所以需要将下标替换成倒数第二个
                currentItem = max - 1;
            } else if (position == max) {
                // 当滑动到最后一个时，其实对于用户来说看到的是第一个，所以需要将下标替换成正数第二个
                currentItem = 1;
            }
            temp = currentItem - 1;
            setIndicatorAndTitle(temp);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            currentItem = viewPager.getCurrentItem();
            Log.d(TAG, "onPageScrollStateChanged: state "+state+" currentItem "+currentItem);
            // 0 静止  1 正在拖拽   2 松手后自动滑动
            // 每次拖动的状态变化都是 1->2->0  每次拖动一页，都会调用3次方法，依次打印1->2->0
            switch (state) {
                // 静止状态
                case 0:
                    if (currentItem == 0) {
                        viewPager.setCurrentItem(count-2, false);
                    } else if (currentItem == count - 1) {
                        viewPager.setCurrentItem(1, false);
                    }
                    break;
                // 拖拽状态
                case 1:
                    releaseTime = System.currentTimeMillis();
                    if (currentItem == count - 1) {
                        viewPager.setCurrentItem(1, false);
                    } else if (currentItem == 0) {
                        viewPager.setCurrentItem(count-2, false);
                    }
                    break;
                // 松手自动滑动状态
                case 2:
                    break;
            }
        }
    };


    /**
     * 设置指示器和标题切换
     *
     * @param position
     */
    private void setIndicatorAndTitle(int position) {
        tvTitle.setText(titles.get(position));

        for (int i = 0; i < tips.length; i++) {
            if (i == position) {
                tips[i].setBackgroundResource(R.drawable.shape_circle_red);
            } else {
                tips[i].setBackgroundResource(R.drawable.shape_circle_white);
            }
        }
    }

    /**
     * 适配器
     */
    class BannerAdapter extends PagerAdapter {

        /**
         * 返回可用的视图数量
         * @return
         */
        @Override
        public int getCount() {
            return views.size();
        }


        /**
         * 判断当前View是否是来自于object
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        /**
         * 创建初始化View，如果总共有三个View，刚开始会创建好第一二两个，当从第一个滑动到第二个时，会创建第三个view
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        /**
         * 销毁view，当滑动到第三个时会销毁第一个，当滑动到第一个时会销毁第三个
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

    }

}