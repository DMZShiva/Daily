package com.example.watersystem.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.watersystem.R;
import com.example.watersystem.home.dialog.HomeAboutDialogFragment;
import com.example.watersystem.home.dialog.HomePhoneDialogFragment;
import com.example.watersystem.view.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomePhoneDialogFragment.EditTextDialogListener {

    private BannerViewPager bannerViewPager;

    private LinearLayout homeTest;
    private LinearLayout homeSetting;
    private LinearLayout homeAnalysis;
    private LinearLayout homePhone;
    private LinearLayout homeAbout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        bannerViewPager = view.findViewById(R.id.home_banner);
        homeTest = view.findViewById(R.id.home_test);
        homeSetting = view.findViewById(R.id.home_setting);
        homeAnalysis = view.findViewById(R.id.home_analysis);
        homePhone = view.findViewById(R.id.home_phone);
        homeAbout = view.findViewById(R.id.home_about);

        initBannerViewPager();
        initLinearListener();

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

    private void initLinearListener() {
        homeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.homeTestFragment);
            }
        });

        homeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.homeSettingFragment);
            }
        });

        homeAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.homeAnalysisFragment);
            }
        });

        homePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomePhoneDialogFragment dialogFragment = new HomePhoneDialogFragment();
                dialogFragment.show(getChildFragmentManager(), "dialog");
            }
        });

        homeAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeAboutDialogFragment dialog = new HomeAboutDialogFragment();
                dialog.show(getChildFragmentManager(), "confirm_dialog");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(String text) {

    }
}
