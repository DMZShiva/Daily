package com.example.watersystem.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.watersystem.R;
import com.example.watersystem.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BannerViewPager bannerViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        bannerViewPager = view.findViewById(R.id.home_banner);

        initBannerViewPager();

        return view;
    }

    private void initBannerViewPager() {
        List<Integer> imageUrl=new ArrayList<>();
        // 当用户在第一页时，向右拖拽左滑时新增一个最后一页，让用户以为滑到了最后一页
        imageUrl.add(R.drawable.business_data_is_selected);
        // 1 用户看到的第一页
        imageUrl.add(R.drawable.business_home_is_selected);
        // 2 用户看到的第二页
        imageUrl.add(R.drawable.business_control_is_selected);
        // 3 用户看到的第三页
        imageUrl.add(R.drawable.business_data_is_selected);
        // 当用户在第三页时，向左拖拽右滑时新增一个第一页，让用户以为滑到了第一页
        imageUrl.add(R.drawable.business_home_is_selected);

        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("控制");
        titles.add("数据");

        bannerViewPager.setData(imageUrl);
        bannerViewPager.setTitles(titles);
    }

}
