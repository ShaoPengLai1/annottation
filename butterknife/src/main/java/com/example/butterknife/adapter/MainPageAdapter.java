package com.example.butterknife.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.butterknife.fragment.HomeFragment;
import com.example.butterknife.fragment.MineFragment;

public class MainPageAdapter extends FragmentPagerAdapter {

    private String[] menus=new String[]{"预约","我的"};
    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new MineFragment();
            default:
                return new Fragment();
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return menus[position];
    }

    @Override
    public int getCount() {
        return menus.length;
    }

}
