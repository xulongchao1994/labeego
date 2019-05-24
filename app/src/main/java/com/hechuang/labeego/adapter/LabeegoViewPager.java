package com.hechuang.labeego.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class LabeegoViewPager extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private FragmentManager fm;

    public LabeegoViewPager(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
