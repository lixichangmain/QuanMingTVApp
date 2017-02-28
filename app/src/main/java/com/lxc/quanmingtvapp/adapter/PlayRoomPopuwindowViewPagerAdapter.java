package com.lxc.quanmingtvapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.mob.tools.gui.ViewPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class PlayRoomPopuwindowViewPagerAdapter extends PagerAdapter {

  private List<View> list;
    public PlayRoomPopuwindowViewPagerAdapter (List<View> list){
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
        View view=list.get(position);
        if(view.getParent()!=null){

            ((ViewGroup) view.getParent()).removeView(view);
        }
        container.addView(view);
        return view;
    }

}
