package com.lxc.quanmingtvapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.adapter.MyCareGridViewAdapter;
import com.lxc.quanmingtvapp.application.MyApplication;
import com.lxc.quanmingtvapp.bean.UserCare;
import com.lxc.quanmingtvapp.bean.WatchHistory;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

public class MyCareActivity extends AppCompatActivity {

    private GridView gridview;
    private DbManager dbManager;
    private SharedPreferences sharedPreferences;
    private String username;
    private MyCareGridViewAdapter myCareGridViewAdapter;
    private ImageView nullCareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_care);
        init();

    }

    private void init() {
        //初始化sharedPreferences获取当前登录的用户名
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);
        gridview = (GridView) findViewById(R.id.griaview_mycareactivity);
        nullCareImage = (ImageView) findViewById(R.id.nullcaseimage_mycaseactivity);
        //取出数据库中的数据
        dbManager = x.getDb(MyApplication.getMyApplication().getDaoConfig());
        try {
            List<WatchHistory> list = dbManager.selector(WatchHistory.class).where("username", "=", username).findAll();

            if (list == null || list.size() == 0) {
                nullCareImage.setVisibility(View.VISIBLE);
//                gridview.set

            }else {
                //如果没有关注就显示图片
                nullCareImage.setVisibility(View.GONE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        finish();
    }
}
