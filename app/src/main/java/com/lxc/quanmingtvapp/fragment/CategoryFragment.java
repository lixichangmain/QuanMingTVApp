package com.lxc.quanmingtvapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxc.quanmingtvapp.InterFace.CallbackLiveFGItemClick;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.activity.LiveFGactivity.SearchActivity;
import com.lxc.quanmingtvapp.activity.LiveList;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener, Callback.CommonCallback<String>, CallbackLiveFGItemClick {
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private ImageView img;
    private List<CategoryBean> list;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_category, container, false);
        initView(inflate);
        getCateGoryData();
        return inflate;
    }

    private void getCateGoryData() {
        RequestParams params = new RequestParams(UrlAPIConfig.getCategoryFragmentUrl());
        x.http().get(params, this);


    }

    private void initView(View inflate) {
        recyclerView = (RecyclerView) inflate.findViewById(R.id.category_fg_recyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        list = new ArrayList<>();

        img = (ImageView) inflate.findViewById(R.id.category_fg_sousuo);
        img.setOnClickListener(this);

    }

    //搜索跳转
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    CategoryBean categoryBean = new CategoryBean();
                    categoryBean.setJSONObject(jsonObject);
                    list.add(categoryBean);
                }
                adapter = new CategoryAdapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
                setLisenter();

            } catch (JSONException e) {
                e.printStackTrace();
            }
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
        Toast.makeText(getActivity(), "position" + position, Toast.LENGTH_SHORT).show();
        String slug = list.get(position).getSlug();
//        Log.e("++++++++++++++++++++++",slug+"");

        String name = list.get(position).getName();
//        Log.e("++++++++++++++++++++++",name+"");
//
//
        String url = UrlAPIConfig.getCategroyJumpUrl(slug);

        Intent intent = new Intent(getActivity(), LiveList.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("url",url);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
