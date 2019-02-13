package com.example.butterknife.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.butterknife.fragment.BaseFragment;
import com.example.butterknife.fragment.HotSaleFragment;

public class HomePageAdapter extends FragmentPagerAdapter {

    private String[] pageNames=new String[]{"热销", "招牌", "主食", "小吃", "饮品"};

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HotSaleFragment();
                default:
                    return new BaseFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageNames[position];
    }

    @Override
    public int getCount() {
        return pageNames.length;
    }
}
