package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lxc.quanmingtvapp.activity.PlayActivity;
import com.lxc.quanmingtvapp.bean.HomeHanderItem;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class HomeViewPagerAdapter extends PagerAdapter {

    private List<ImageView> list;



    public HomeViewPagerAdapter(List<ImageView> list){
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }


}
