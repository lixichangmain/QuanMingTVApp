package com.lxc.quanmingtvapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.activity.FeedBackActivity;
import com.lxc.quanmingtvapp.activity.LoginActivity;
import com.lxc.quanmingtvapp.activity.MyCareActivity;
import com.lxc.quanmingtvapp.activity.RechargeActivity;
import com.lxc.quanmingtvapp.activity.RoomActivity;
import com.lxc.quanmingtvapp.activity.SettingActivity;
import com.lxc.quanmingtvapp.activity.TaskActivity;
import com.lxc.quanmingtvapp.activity.UserInfoActivity;
import com.lxc.quanmingtvapp.adapter.MyMineAdapter;

import org.xutils.DbManager;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    public static final int CODE = 100;
    private ImageView imageSetting;
    private ImageView imageMessage;
    private ListView listView;
    private SimpleDraweeView imageViewTuXiang;
    private List<Integer> imageIdList;
    private List<String> textList;
    private MyMineAdapter myMineAdapter;
    private DbManager dbManager;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    private String username;
    private int loginState;
    private TextView textView;
    private String icon;
    private String iconname;
    private String email;
    private String phone;
    private RelativeLayout relativeLayout;
    private TextView textseed;
    private TextView textcoll;
    private String seed;
    private String coll;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        //初始化sharedPrefaceerence
        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
        //初始化数据库管理员
        imageMessage = (ImageView) view.findViewById(R.id.imageView_message_mine);
        imageSetting = (ImageView) view.findViewById(R.id.imageView_setting_mine);
        imageViewTuXiang = (SimpleDraweeView) view.findViewById(R.id.image_mine_tuxiang);
        textView = (TextView) view.findViewById(R.id.textView_login_mine);

        //找到充值相关的控件
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_recharge_minefragment);
        //种子的控件
        textseed = (TextView) view.findViewById(R.id.textseed_minefragment);
        //牛币控件
        textcoll = (TextView) view.findViewById(R.id.colltext_minefragment);
        Button button = (Button) view.findViewById(R.id.btn_racharge_minefragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginState == 0) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), RechargeActivity.class);
                    startActivity(intent);
                }
            }
        });
        //设置图片的点击事件
        imageMessage.setOnClickListener(this);
        imageSetting.setOnClickListener(this);
        imageViewTuXiang.setOnClickListener(this);
        //初始化listView
        listView = (ListView) view.findViewById(R.id.listView_mine);
        initlistView(listView);
        listView.setOnItemClickListener(this);
    }

    //设置图像、信息、设置等按钮的点击事件
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.imageView_message_mine:
                //信息的点击事件
                //判断用户是否登录,如果没有登录则跳转到登录页面，如果登录了则跳到反馈页面
                if (loginState == 0) {
                    intent.setClass(getActivity(), LoginActivity.class);

                } else {
                    intent.setClass(getActivity(), FeedBackActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.imageView_setting_mine:
                //设置的点击事件，跳转到设置页面
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;

            case R.id.image_mine_tuxiang:
                //图像的点击事件跟信息判断一样
                if (loginState == 0) {
                    intent.setClass(getActivity(), LoginActivity.class);

                } else {
                    //跳转到个人信息页面
                    intent.setClass(getActivity(), UserInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("icon", icon);
                    bundle.putString("username", username);
                    bundle.putString("email", email);
                    bundle.putString("phone", phone);
                    intent.putExtras(bundle);
                }
                startActivity(intent);
                break;

        }


    }

    private void initlistView(ListView listView) {
        imageIdList = new ArrayList<>();
        textList = new ArrayList<>();
        imageIdList.add(R.mipmap.ic_profile_host_room);
        textList.add("房间管理");
        imageIdList.add(R.mipmap.ic_profile_concern);
        textList.add("我的关注");
        imageIdList.add(R.mipmap.ic_profile_histoy);
        textList.add("观看历史");
        imageIdList.add(R.mipmap.icon_profile_remind);
        textList.add("开播提醒");
        imageIdList.add(R.mipmap.ic_profile_task);
        textList.add("种子任务");
        imageIdList.add(R.mipmap.ic_profile_game);
        textList.add("游戏中心");
        myMineAdapter = new MyMineAdapter(getActivity(), imageIdList, textList);
        listView.setAdapter(myMineAdapter);
    }

    //设置listView的点击事件


    //画面开始的时候加载用户信息
    @Override
    public void onResume() {
        super.onResume();


        loginState = preferences.getInt("loginState", 0);
        //加载用户名
        username = preferences.getString("username", "点击登录");
        textView.setText(username);

        //加载邮箱
        email = preferences.getString("email", null);
        //加载电话
        phone = preferences.getString("phone", null);

        if (loginState == 0) {
            relativeLayout.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        //加载种子数量
        seed = preferences.getString("seed", "0");
        //加载牛币的数量
        coll = preferences.getString("coll", "0");
        textseed.setText(seed);
        textcoll.setText(coll);
        //加载图像
        //现在内存中查找是否用户设置了图片，如果设置了则直接读取出来显示，没有就去网络上下载
        icon = preferences.getString("icon", null);
        iconname = preferences.getString("iconname",null);
        Bitmap bitmap = BitmapFactory.decodeFile(getActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname);
        if (bitmap != null) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse("file://"+getActivity().getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname))
                    .build();
            imageViewTuXiang.setController(controller);
        } else if (icon != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(icon))
                    .setAutoPlayAnimations(true)
                    .build();
            imageViewTuXiang.setController(draweeController);

        } else {
            imageViewTuXiang.setImageResource(R.mipmap.img_touxiang_default);
        }
    }

    //设置listView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if (loginState == 0) {
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
            return;
        }
        switch (position) {
            case 0:
                //跳转房间管理
                intent.setClass(getActivity(), RoomActivity.class);
                startActivity(intent);
                break;
            case 1:
                //跳转到我的关注页面
                intent.setClass(getActivity(), MyCareActivity.class);
                startActivity(intent);
                break;
            case 2:
                //跳转到观看历史页面
                intent.setClass(getActivity(), MyCareActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(getActivity(), MyCareActivity.class);
                startActivity(intent);
                break;
            case 4:

                //开播提醒
                intent.setClass(getActivity(), MyCareActivity.class);
                startActivity(intent);

                intent.setClass(getActivity(), TaskActivity.class);
                startActivity(intent);

                break;
            case 5:
                break;
            case 6:
                break;

        }
    }
}
