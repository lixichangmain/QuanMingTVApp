package com.lxc.quanmingtvapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lxc.quanmingtvapp.InterFace.CallbackLiveFGItemClick;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.activity.LiveFGactivity.SearchActivity;
import com.lxc.quanmingtvapp.adapter.LiveAdapter;
import com.lxc.quanmingtvapp.bean.LiveFGBean;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment implements CallbackLiveFGItemClick, Callback.CommonCallback<String>, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private LiveAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private LiveFGBean liveFGBean;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView img;
//    private SimpleDraweeView img;

    public LiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_live, container, false);
        inintView(inflate);
        getLiveData();
        return inflate;
    }

    private void inintView(View inflate) {

        recyclerView = (RecyclerView) inflate.findViewById(R.id.LiveFGrecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.LiveFgFresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        img = (ImageView) inflate.findViewById(R.id.livefg_sousuo);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "1234567890-", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }

        });

    }

    private void setLisenter() {
        adapter.setCallbackLiveFGItemClick(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "position" + position, Toast.LENGTH_SHORT).show();

    }

    private void getLiveData() {
        RequestParams params = new RequestParams(UrlAPIConfig.getLiveFragmentUrl());
        x.http().get(params, this);

    }


    @Override
    public void onSuccess(String result) {
//        Log.i("--------------", result + "");
        Gson gson = new Gson();
        liveFGBean = gson.fromJson(result, LiveFGBean.class);
        adapter = new LiveAdapter(getActivity(), liveFGBean.getData());
        recyclerView.setAdapter(adapter);
        setLisenter();
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//        Log.i("--------------", "失败");
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

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

                getLiveData();
                swipeRefreshLayout.setRefreshing(false);
            }

        }
    };


}
