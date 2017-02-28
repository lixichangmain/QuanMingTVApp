package com.lxc.quanmingtvapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.adapter.MyFragmentAdapter;
import com.lxc.quanmingtvapp.fragment.fragmentliveroom.LivingFragment;
import com.lxc.quanmingtvapp.fragment.fragmentliveroom.UnLivingFragment;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private List<String> titleList;
    private List<Fragment> list;
    private ViewPager viewPager;
    private MyFragmentAdapter myFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        init();
    }

    private void init() {

        //设置标题数据
        titleList = new ArrayList<>();
        titleList.add("正在直播");
        titleList.add("不在直播");
        //设置fragment的list
        list = new ArrayList<>();
        list.add(new LivingFragment());
        list.add(new UnLivingFragment());

        tabLayout = (TabLayout) findViewById(R.id.title_roomactivity);
        viewPager = (ViewPager) findViewById(R.id.viewpager_roomactivity);
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),titleList,list);
        viewPager.setAdapter(myFragmentAdapter);
        for (int i = 0; i < titleList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(i)));
        }
        tabLayout.setupWithViewPager(viewPager);

    }

    //设置返回按钮的监听
    public void onClick(View view){
        finish();
    }
}
