package com.lxc.quanmingtvapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<String> titleList;
    private List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm, List<String> titleList, List<Fragment> list) {
        super(fm);
        this.titleList = titleList;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
