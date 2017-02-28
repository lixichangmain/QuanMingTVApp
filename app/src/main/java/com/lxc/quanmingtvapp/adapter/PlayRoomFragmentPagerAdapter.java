package com.lxc.quanmingtvapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19 0019.
 */
public class PlayRoomFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> listStr;

    public PlayRoomFragmentPagerAdapter(FragmentManager fm,List<Fragment> list,List<String> listStr) {
        super(fm);
        this.list=list;
        this.listStr=listStr;
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
        return listStr.get(position);
    }
}
