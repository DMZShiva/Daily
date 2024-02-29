package com.example.watersystem.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.watersystem.R;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginFragment extends Fragment {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<CharSequence> tabList = Arrays.asList("密码登陆", "验证码登陆");
    private LoginByPsdFragment loginByPsdFragment;
    private LoginByCodeFragment loginByFaceFragment;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        viewPager = view.findViewById(R.id.login_view_pager);
        tabLayout = view.findViewById(R.id.login_tab_layout);
        button = view.findViewById(R.id.login_button);

        initTableLayout();
        initLogin();
        initRegister();

        return view;
    }

    private void initTableLayout() {

        loginByPsdFragment = new LoginByPsdFragment();
        fragmentList.add(loginByPsdFragment);

        loginByFaceFragment = new LoginByCodeFragment();
        fragmentList.add(loginByFaceFragment);

        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    @NonNull
                    @Override
                    public Fragment getItem(int position) {
                        return fragmentList.get(position);
                    }

                    @Override
                    public int getCount() {
                        return tabList.size();
                    }
                };
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    Object view = tab.view;
                    Class clz = view.getClass();
                    Field f = clz.getDeclaredField("textView");
                    f.setAccessible(true);
                    TextView textViw = (TextView) f.get(view);
                    textViw.setTypeface(Typeface.DEFAULT_BOLD);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                try {
                    Object view = tab.view;
                    Class clz = view.getClass();
                    Field f = clz.getDeclaredField("textView");
                    f.setAccessible(true);
                    TextView textViw = (TextView) f.get(view);
                    textViw.setTypeface(Typeface.DEFAULT);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.getTabAt(i).setText(tabList.get(i).toString());
        }
    }

    private void initLogin() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initRegister() {

    }
}
