package com.lxc.quanmingtvapp.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.StartAnimation;
import com.lxc.quanmingtvapp.activity.CategoryJumpAcitivity;
import com.lxc.quanmingtvapp.adapter.HomeFillGridViewAdapter;
import com.lxc.quanmingtvapp.adapter.HomeListViewAdapter;
import com.lxc.quanmingtvapp.adapter.HomeViewPagerAdapter;
import com.lxc.quanmingtvapp.bean.HomeHander2Item;
import com.lxc.quanmingtvapp.bean.HomeHanderItem;
import com.lxc.quanmingtvapp.bean.HomeHanferItem;
import com.lxc.quanmingtvapp.bean.HomeItem;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements Callback.CommonCallback<String>, AdapterView.OnItemClickListener {


    private ViewPager viewPager;
    private LinearLayout linearLayout_dian;
    private Handler handler;
    private static final int DELAY = 1;
    private int dianPosition = 0;
    private ListView listView;
    private View handerView2;
    private View handerView1;
    private String result;
    private List<JSONArray> list_jsonArray;
    private GridView gridView;
    private List<HomeHanferItem> list;
    private String guanggaoData;
    //animationDrawable显示在textView的背景之上
    private ImageView animator;




    private boolean flag=true;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
       //开始动画
        StartAnimation.getStartAnimation().start(getResources(),animator);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case DELAY:
                        dianPosition++;
                        setDianState();
                        viewPager.setCurrentItem(dianPosition % 5, true);
                        handler.sendEmptyMessageDelayed(1, 3000);
                        break;
                }
                return false;
            }
        });
        getData();


        return view;
    }

    /////////////////////////////
    //设置点的状态
    private void setDianState() {
        for (int i = 0; i < linearLayout_dian.getChildCount(); i++) {
            linearLayout_dian.getChildAt(i).setSelected(false);
        }
        linearLayout_dian.getChildAt(dianPosition % 5).setSelected(true);
    }
    ///////////////////
    //解析数据
    private void parseDate() {
        list_jsonArray = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray2 = jsonObject.optJSONArray("app-classification");
            setHanderDate(jsonArray2);
            JSONArray jsonArray3 = jsonObject.optJSONArray("app-recommendation");

            /////////////////////////////////////////////////////////
            ///////设置hander数据
            addHanderView2(jsonArray3);
            ////////////
            //设置推荐部分数据  不做数据填充  以及页面跳转


            //////////////////////////////////////////////////////
            JSONArray jsonArray4 = jsonObject.optJSONArray("app-lol");
            JSONArray jsonArray5 = jsonObject.optJSONArray("app-beauty");
            JSONArray jsonArray6 = jsonObject.optJSONArray("app-heartstone");
            JSONArray jsonArray7 = jsonObject.optJSONArray("app-huwai");
            JSONArray jsonArray8 = jsonObject.optJSONArray("app-overwatch");
            JSONArray jsonArray9 = jsonObject.optJSONArray("app-blizzard");
            JSONArray jsonArray10 = jsonObject.optJSONArray("app-qqfeiche");
            JSONArray jsonArray11 = jsonObject.optJSONArray("app-mobilegame");
            JSONArray jsonArray12 = jsonObject.optJSONArray("app-wangzhe");
            JSONArray jsonArray13 = jsonObject.optJSONArray("app-dota2");
            JSONArray jsonArray14 = jsonObject.optJSONArray("app-tvgame");
            JSONArray jsonArray15 = jsonObject.optJSONArray("app-webgame");
            JSONArray jsonArray16 = jsonObject.optJSONArray("app-dnf");
            JSONArray jsonArray17 = jsonObject.optJSONArray("app-minecraft");
            JSONArray jsonArray18 = jsonObject.optJSONArray("list");


            list_jsonArray.add(jsonArray4);
            list_jsonArray.add(jsonArray5);
            list_jsonArray.add(jsonArray6);
            list_jsonArray.add(jsonArray7);
            list_jsonArray.add(jsonArray8);
            list_jsonArray.add(jsonArray9);
            list_jsonArray.add(jsonArray10);
            list_jsonArray.add(jsonArray11);
            list_jsonArray.add(jsonArray12);
            list_jsonArray.add(jsonArray13);
            list_jsonArray.add(jsonArray14);
            list_jsonArray.add(jsonArray15);
            list_jsonArray.add(jsonArray16);
            list_jsonArray.add(jsonArray17);
            list_jsonArray.add(jsonArray18);
            setListView();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    HomeListViewAdapter homeListViewAdapter;
    ///////////////////////////////////////////////
    //设置listView的adapter
    private void setListView() {
        homeListViewAdapter = new HomeListViewAdapter(list_jsonArray, getActivity());

        listView.setAdapter(homeListViewAdapter);
        listView.setOnItemClickListener(this);

    }
    ////////////////////////////////////////
    //设置第二个handerview
    private void addHanderView2(JSONArray jsonArray){
        List<HomeHander2Item> list=new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject=jsonArray.optJSONObject(i);
            HomeHander2Item homeHander2Item=new HomeHander2Item();
            homeHander2Item.setJSONObject(jsonObject);
            list.add(homeHander2Item);
        }
        ImageView img_JCTJ1= (ImageView) handerView2.findViewById(R.id.img_home_jingcaiTJ1);
        ImageView img_JCTJLeft= (ImageView) handerView2.findViewById(R.id.img_home_jingcaiTJLeft);
        ImageView img_JCTJ2= (ImageView) handerView2.findViewById(R.id.img_home_jingcaiTJ2);
        ImageView img_JCTJRight= (ImageView) handerView2.findViewById(R.id.img_home_jingcaiTJRight);
        TextView tx1_Left= (TextView) handerView2.findViewById(R.id.tx_home_jingcaiTJLeft1);
        TextView tx2_Left= (TextView) handerView2.findViewById(R.id.tx_home_jingcaiTJLeft2);
        TextView tx1_Right= (TextView) handerView2.findViewById(R.id.tx_home_jingcaiTJRight1);
        TextView tx2_Right= (TextView) handerView2.findViewById(R.id.tx_home_jingcaiTJRight2);
        HomeHander2Item homeHander2Item1=list.get(0);
        HomeHander2Item homeHander2Item2=list.get(1);
        x.image().bind(img_JCTJ1,homeHander2Item1.getThumb());
        x.image().bind(img_JCTJLeft,homeHander2Item1.getAvatar());
        tx1_Left.setText(homeHander2Item1.getNick());
        tx2_Left.setText(homeHander2Item1.getTitle());

        x.image().bind(img_JCTJ2,homeHander2Item2.getThumb());
        x.image().bind(img_JCTJRight,homeHander2Item2.getAvatar());
        tx1_Right.setText(homeHander2Item2.getNick());
        tx2_Right.setText(homeHander2Item2.getTitle());


    }

/////////////////////////////////////////////
    //添加头布局
    private void addHanderView1() {

        List<ImageView> list = new ArrayList<>();
        List<HomeHanderItem> listHanderItem=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(guanggaoData);
            JSONArray jsonArray=jsonObject.optJSONArray("app-focus");
            for (int i = 0; i < jsonArray.length(); i++) {
                HomeHanderItem homeHanderItem=new HomeHanderItem();
                homeHanderItem.setJSONObjec(jsonArray.optJSONObject(i));
                listHanderItem.add(homeHanderItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(getContext());
            x.image().bind(imageView,listHanderItem.get(i).getThumb());
            list.add(imageView);
        }
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(list);
        viewPager.setAdapter(homeViewPagerAdapter);
        handler.sendEmptyMessageDelayed(DELAY, 3000);

    }

/////////////////////////////////////////////////////////
    //头部每个圆形条目的数据设置
    private void setHanderDate(JSONArray jsonArray) {
        list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            HomeHanferItem homeHanferItem = new HomeHanferItem();
            homeHanferItem.setJSONObject(jsonObject);
            list.add(homeHanferItem);
        }
//        float dp = getActivity().getResources().getDisplayMetrics().density;


        float dp = getActivity().getResources().getDisplayMetrics().density;
        HomeFillGridViewAdapter homeFillGridViewAdapter = new HomeFillGridViewAdapter(list, getContext());
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.width = (int) (list.size() * 81 * dp);
        gridView.setLayoutParams(params);
        gridView.setNumColumns(list.size());
        gridView.setGravity(Gravity.CENTER);

//        ViewGroup.LayoutParams params=gridView.getLayoutParams();
//        params.width= (int) ((list.size()+1)*180*dp);
//        params.height= (int) (80*dp);
//        gridView.setNumColumns(list.size()+1);
//        gridView.setLayoutParams(params);
        gridView.setAdapter(homeFillGridViewAdapter);
        /////////////////////////////
        //全部分类没有数据  所以减一


    }

    ///////////////////////////////////////////////////////
    /////获取网络数据
    private void getData() {
        RequestParams requestParams = new RequestParams(UrlAPIConfig.getHomeFragmentUrl());
        RequestParams requestParams1=new RequestParams(UrlAPIConfig.getHomeWelcomeUrl());
        x.http().get(requestParams1,this);
        x.http().get(requestParams, this);
    }

    /////////////////////////////////////////
    //initView
    private void initView(View view) {
        animator = (ImageView) view.findViewById(R.id.animator);
        handerView1 = LayoutInflater.from(getContext()).inflate(R.layout.home_handerview, null);
        gridView = (GridView) handerView1.findViewById(R.id.gridView_home_hander);
        handerView2 = LayoutInflater.from(getContext()).inflate(R.layout.item_home1, null);
        viewPager = (ViewPager) handerView1.findViewById(R.id.vp_home_gundong);
        linearLayout_dian = (LinearLayout) handerView1.findViewById(R.id.ll_dian);
        linearLayout_dian.getChildAt(0).setSelected(true);
        listView = (ListView) view.findViewById(R.id.listView_home);
        listView.addHeaderView(handerView1);
        listView.addHeaderView(handerView2);
    }

///////////////////////////////////////////////////////////////////
    //网络请求获取数据的callback

    @Override
    public void onSuccess(String result) {
        try {
            if(flag){
                flag=false;
            StartAnimation.getStartAnimation().stop();
            }
            JSONObject jsonObject=new JSONObject(result);
            if(jsonObject.optJSONArray("app-focus")!=null){
                this.guanggaoData=result;
                Log.i("<<<<<<<<",""+guanggaoData);
                addHanderView1();
            }else {
                this.result = result;
                parseDate();
            }
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
//////////////////////////////////////////
    //listView 的点击监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "item的监听执行了", Toast.LENGTH_SHORT).show();
        if(onItemClick!=null){
            onItemClick.getPosition(position);
        }
    }
private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }

    public interface OnItemClick{
        void getPosition(int position);
    }
}
