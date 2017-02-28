package com.lxc.quanmingtvapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxc.quanmingtvapp.InterFace.CallbackLiveFGItemClick;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.adapter.CategoryAdapter;
import com.lxc.quanmingtvapp.adapter.LiveAdapter;
import com.lxc.quanmingtvapp.bean.CategoryBean;
import com.lxc.quanmingtvapp.bean.LiveFGBean;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class LiveList extends AppCompatActivity implements Callback.CommonCallback<String>,CallbackLiveFGItemClick, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private LiveAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private LiveFGBean liveFGBean;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView backImg;
    private TextView text;


    private String url;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        url  =bundle.getString("url");
//        Log.e("------------------",url+"");
        name  =bundle.getString("name");
        initView();
        getData();
    }

    private void getData() {
        RequestParams params = new RequestParams(url);
        x.http().get(params, this);

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.liveList_recyclerView);
        gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        backImg = (ImageView) findViewById(R.id.liveList_back);
        text = (TextView) findViewById(R.id.liveList_title);
        text.setText(name);

        //退出监听
        backImg.setOnClickListener(this);

        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.liveList_fresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onSuccess(String result) {
        if (!TextUtils.isEmpty(result)) {
            Gson gson = new Gson();
            liveFGBean = gson.fromJson(result, LiveFGBean.class);
            adapter = new LiveAdapter(this, liveFGBean.getData());
            recyclerView.setAdapter(adapter);
            setLisenter();
        }
    }

    private void setLisenter() {
        adapter.setCallbackLiveFGItemClick(this);

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
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "position" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                getData();
                swipeRefreshLayout.setRefreshing(false);
            }

        }
    };
}
