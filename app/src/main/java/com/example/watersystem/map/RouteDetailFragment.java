package com.example.watersystem.map;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.WalkPath;
import com.example.watersystem.R;
import com.example.watersystem.map.adapter.BusSegmentListAdapter;
import com.example.watersystem.map.adapter.DriveSegmentListAdapter;
import com.example.watersystem.map.adapter.RideSegmentListAdapter;
import com.example.watersystem.map.adapter.WalkSegmentListAdapter;
import com.example.watersystem.map.utils.MapUtil;
import com.example.watersystem.map.utils.SchemeBusStep;

import java.util.ArrayList;
import java.util.List;

/**
 * 路线规划详情Fragment
 */
public class RouteDetailFragment extends Fragment {

    private Toolbar toolbar;
    private TextView tvTitle, tvTime;
    private RecyclerView rv;

    public static RouteDetailFragment newInstance() {
        return new RouteDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_route_detail, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化视图
     *
     * @param view
     */
    private void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTime = view.findViewById(R.id.tv_time);
        rv = view.findViewById(R.id.rv);
        //高亮状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RouteDetailFragment.this).navigate(R.id.mapFragment);
            }
        });

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }

        switch (bundle.getInt("type", 0)) {
            case 0: // 步行
                walkDetail(bundle);
                break;
            case 1: // 骑行
                rideDetail(bundle);
                break;
            case 2: // 驾车
                driveDetail(bundle);
                break;
            case 3: // 公交
                busDetail(bundle);
                break;
            default:
                break;
        }
    }

    /**
     * 步行详情
     *
     * @param bundle
     */
    private void walkDetail(Bundle bundle) {
        tvTitle.setText("步行路线规划");
        WalkPath walkPath = bundle.getParcelable("path");
        String dur = MapUtil.getFriendlyTime((int) walkPath.getDuration());
        String dis = MapUtil.getFriendlyLength((int) walkPath.getDistance());
        tvTime.setText(dur + "(" + dis + ")");
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new WalkSegmentListAdapter(R.layout.map_item_segment, walkPath.getSteps()));
    }

    /**
     * 骑行详情
     *
     * @param bundle
     */
    private void rideDetail(Bundle bundle) {
            tvTitle.setText("骑行路线规划");
            RidePath ridePath = bundle.getParcelable("path");
            String dur = MapUtil.getFriendlyTime((int) ridePath.getDuration());
            String dis = MapUtil.getFriendlyLength((int) ridePath.getDistance());
            tvTime.setText(dur + "(" + dis + ")");
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv.setAdapter(new RideSegmentListAdapter(R.layout.map_item_segment, ridePath.getSteps()));
    }

    /**
     * 驾车详情
     *
     * @param bundle
     */
    private void driveDetail(Bundle bundle) {
        tvTitle.setText("驾车路线规划");
        DrivePath drivePath = bundle.getParcelable("path");
        String dur = MapUtil.getFriendlyTime((int) drivePath.getDuration());
        String dis = MapUtil.getFriendlyLength((int) drivePath.getDistance());
        tvTime.setText(dur + "(" + dis + ")");
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new DriveSegmentListAdapter(R.layout.map_item_segment, drivePath.getSteps()));
    }

    /**
     * 公交详情
     *
     * @param bundle
     */
    private void busDetail(Bundle bundle) {
        tvTitle.setText("公交路线规划");
        BusPath busPath = bundle.getParcelable("path");
        String dur = MapUtil.getFriendlyTime((int) busPath.getDuration());
        String dis = MapUtil.getFriendlyLength((int) busPath.getDistance());
        tvTime.setText(dur + "(" + dis + ")");
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new BusSegmentListAdapter(R.layout.map_item_segment, getBusSteps(busPath.getSteps())));
    }

    /**
     * 公交方案数据组装
     *
     * @param list
     * @return
     */
    private List<SchemeBusStep> getBusSteps(List<BusStep> list) {
        List<SchemeBusStep> busStepList = new ArrayList<>();
        SchemeBusStep start = new SchemeBusStep(null);
        start.setStart(true);
        busStepList.add(start);
        for (BusStep busStep : list) {
            if (busStep.getWalk() != null && busStep.getWalk().getDistance() > 0) {
                SchemeBusStep walk = new SchemeBusStep(busStep);
                walk.setWalk(true);
                busStepList.add(walk);
            }
            if (busStep.getBusLine() != null) {
                SchemeBusStep bus = new SchemeBusStep(busStep);
                bus.setBus(true);
                busStepList.add(bus);
            }
            if (busStep.getRailway() != null) {
                SchemeBusStep railway = new SchemeBusStep(busStep);
                railway.setRailway(true);
                busStepList.add(railway);
            }

            if (busStep.getTaxi() != null) {
                SchemeBusStep taxi = new SchemeBusStep(busStep);
                taxi.setTaxi(true);
                busStepList.add(taxi);
            }
        }
        SchemeBusStep end = new SchemeBusStep(null);
        end.setEnd(true);
        busStepList.add(end);
        return busStepList;
    }
}
