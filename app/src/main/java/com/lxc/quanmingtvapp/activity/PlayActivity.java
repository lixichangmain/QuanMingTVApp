package com.lxc.quanmingtvapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.facebook.common.media.MediaUtils;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.adapter.PlayRoomFragmentPagerAdapter;
import com.lxc.quanmingtvapp.adapter.PlayRoomPopuwindowViewPagerAdapter;
import com.lxc.quanmingtvapp.adapter.PupuWindowGridViewAdapter;
import com.lxc.quanmingtvapp.application.MyApplication;
import com.lxc.quanmingtvapp.bean.HomeItem;
import com.lxc.quanmingtvapp.bean.HomePlayItem;
import com.lxc.quanmingtvapp.bean.WatchHistory;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;
import com.lxc.quanmingtvapp.fragment.PlayRoomChatFragment;
import com.lxc.quanmingtvapp.fragment.PlayRoomPaiHangFragment;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, ViewPager.OnPageChangeListener, Callback.CommonCallback<String> {
    private PlayerView playerView;
    private String url;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private CheckBox radioButtonHot;
    private ImageView imageViewSendMessage;
    private CheckBox imageViewGift;
    private EditText editText;
    private SharedPreferences sharedPreferences;
    ////获取editText的内容
    private String contentOfEditText;
    ////点击赠送礼物的popuwindow
    private PopupWindow popupWindow;
    ///POPUWINDOW的viewpager
    private ViewPager popuwindowVP;
    //popuwindow中的两个小点
    private LinearLayout linearLayout_popu;
    private DbManager dbManager;

    //直播流flv
    private String flv;
    //图片的url
    private String img_actor;
    //两个title标题
    private String title1;
    private String title2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
        initPopuWindow();
        saveData();
        //获取房间的内容
        getRoomDate();
        setViewPager();
        setViewOnclick();
    }



    //获取房间内容数据
    private void getRoomDate() {

        RequestParams requestParams=new RequestParams(url);
        x.http().get(requestParams,this);

    }

    //获取序列化对象  以及房间uid 以及存储到本地数据库 显示观看历史
    private void saveData() {

        Intent intent = getIntent();
        ///由头部的imageView跳转   或者直播见跳转
        Bundle bundle = intent.getExtras();
        HomeItem homeItem = bundle.getParcelable("key");
        url = UrlAPIConfig.getPlayRoomUrl(homeItem.getUid());
        String username = sharedPreferences.getString("username", null);
        if (username==null) {
            Toast.makeText(PlayActivity.this, "当前用户未登录", Toast.LENGTH_SHORT).show();
        }else {
            WatchHistory history = new WatchHistory(username
                    , homeItem.getNick()
                    , homeItem.getAvatar()
                    , homeItem.getStatus()
                    , homeItem.getThumb()
                    , homeItem.getUid()
                    , homeItem.getTitle()
                    , homeItem.getView());
            dbManager = x.getDb(MyApplication.getMyApplication().getDaoConfig());

            List<WatchHistory> historyList = null;
            try {
                historyList = dbManager.selector(WatchHistory.class).where("uid", "=", homeItem.getUid()).findAll();

                if (historyList == null || historyList.size() == 0) {
                    dbManager.save(history);

                }
            } catch (DbException e) {
                e.printStackTrace();
            }

        }
    }

    /////////////播放视频
    private void playRadio() {

        playerView = new PlayerView(this);
        playerView.setTitle("视频播放");
        playerView.setScaleType(PlayStateParams.fillparent);
        playerView.hideMenu(true);
        playerView.showThumbnail(new OnShowThumbnailListener() {
            @Override
            public void onShowThumbnail(ImageView ivThumbnail) {

            }
        });
        playerView.setPlaySource(flv);
        playerView.startPlay();
//隐藏中间播放按钮,ture为隐藏，false为不做隐藏处理，但不是显示
//        playerView.hideCenterPlayer(true);
//        playerView.operatorPanl();


    }

//////////////////////////////////////////////////////////////////////////
    ////////设置全屏和半屏之间的切换点击事件  影藏和显示其他的布局

    //设置viewPager
    private void setViewPager() {

        List<Fragment> list = new ArrayList<>();
        List<String> listTabStr = new ArrayList<>();
        list.add(new PlayRoomChatFragment());
        list.add(new PlayRoomPaiHangFragment());
        listTabStr.add("聊天");
        listTabStr.add("排行");
        tabLayout.newTab().setText("聊天");
        tabLayout.newTab().setText("排行");
        PlayRoomFragmentPagerAdapter playRoomFragmentPagerAdapter = new PlayRoomFragmentPagerAdapter(getSupportFragmentManager(), list, listTabStr);
        viewPager.setAdapter(playRoomFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        viewPager = (ViewPager) findViewById(R.id.viewpager_playroom);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_playroom);
        imageView = (ImageView) findViewById(R.id.img1_playroom);
        textView1 = (TextView) findViewById(R.id.tx1_playroom);
        textView2 = (TextView) findViewById(R.id.tx2_playroom);
        radioButtonHot = (CheckBox) findViewById(R.id.img_playroom_hot);
        imageViewSendMessage = (ImageView) findViewById(R.id.img_playroom_sendmessage);
        imageViewGift = (CheckBox) findViewById(R.id.img_playroom_gift);
        editText = (EditText) findViewById(R.id.editText_playroom);

    }

    //专门设置页面控件监听的方法
    private void setViewOnclick() {
//      editText的点击事件
        editText.addTextChangedListener(this);
        //具体发送消息的点击事件
        imageViewSendMessage.setOnClickListener(this);
        //礼物图片设置点击事件
        imageViewGift.setOnClickListener(this);

    }

    //
    //////这只edittext的监听   当editText里有内容  发送消息的图片状态为true
    //显示为红色
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 1) {
            imageViewSendMessage.setClickable(true);
            imageViewSendMessage.setSelected(true);
        } else {
            imageViewSendMessage.setClickable(false);
            imageViewSendMessage.setSelected(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    ///////////////////////////////////////////
    /////////当发送消息照片按钮点击之后
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_playroom_sendmessage:
                contentOfEditText = editText.getText().toString();
                editText.setText("");
                break;
            case R.id.img_playroom_gift:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                }
                break;
        }

    }

    //////在popuwindow里找到滑动的点   以及viewpager
    public void initPopuWindow() {
        List<View> list_view = new ArrayList<>();
        View popuView = LayoutInflater.from(this).inflate(R.layout.playroom_popuwindow, null);
        popupWindow = new PopupWindow(popuView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout_popu = (LinearLayout) popuView.findViewById(R.id.linearLayout_playroom_popuwindow);
        linearLayout_popu.getChildAt(0).setSelected(true);
        popuwindowVP = (ViewPager) popuView.findViewById(R.id.viewpager_playroom_popuWindow);
        //获取填充viewpager的gridview
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_playroom_popuwindow_gridview, null);
        GridView gridView1 = (GridView) view1.findViewById(R.id.gridview_item_playroom_popuwindow);
        //设置第一页gridview的六个数据
        List<String> listSmall = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            listSmall.add(i + "");
        }
        ///给gridview  设置adapter
        PupuWindowGridViewAdapter pupuWindowGridViewAdapter1 = new PupuWindowGridViewAdapter(listSmall, this);
        gridView1.setAdapter(pupuWindowGridViewAdapter1);
        list_view.add(view1);
        //popuwindow  vp的第二个页面  同样也是gridview

        List<String> listBig = new ArrayList<>();
        for (int i = 7; i < 13; i++) {
            listBig.add(i + "");
        }
        PupuWindowGridViewAdapter pupuWindowGridViewAdapter2 = new PupuWindowGridViewAdapter(listBig, this);
        View view2 = LayoutInflater.from(this).inflate(R.layout.gridview_playroom_popuwindow, null);
        GridView gridView2 = (GridView) view2.findViewById(R.id.gridview2_playroom_window);
        gridView2.setAdapter(pupuWindowGridViewAdapter2);
        list_view.add(view2);



        //给viewPager设置adapter需要list<view>


        PlayRoomPopuwindowViewPagerAdapter playRoomPopuwindowViewPagerAdapter = new PlayRoomPopuwindowViewPagerAdapter(list_view);
        popuwindowVP.setAdapter(playRoomPopuwindowViewPagerAdapter);
        popuwindowVP.addOnPageChangeListener(this);
    }


    ////////////////////////
    //viewpager的监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < linearLayout_popu.getChildCount(); i++) {
            linearLayout_popu.getChildAt(i).setSelected(true);
        }
        linearLayout_popu.getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

///////////////////////////////////////////////////////////
    ////xutils访问网络的callback

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject jsonObject=new JSONObject(result);
            HomePlayItem homePlayItem=new HomePlayItem();
            homePlayItem.setJSONObject(jsonObject);
            img_actor=homePlayItem.getAvatar();
            title1=homePlayItem.getNick();
            title2=homePlayItem.getTitle();
            flv=homePlayItem.getBiaoqin();
            textView1.setText(title1);
            textView2.setText(title2);
            playRadio();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerView != null) {
            playerView.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerView != null) {
            playerView.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
//        MediaUtils.muteAudioFocus(this, false);
        /**demo的内容，激活设备常亮状态*/
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerView != null) {
            playerView.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (playerView != null) {
            playerView.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (playerView != null && playerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}
    }


}
