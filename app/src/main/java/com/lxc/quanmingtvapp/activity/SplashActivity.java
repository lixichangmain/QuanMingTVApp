package com.lxc.quanmingtvapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class SplashActivity extends AppCompatActivity implements Handler.Callback, Callback.CommonCallback<String> {

    public static final int WHAT = 1;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        x.http().get(new RequestParams(UrlAPIConfig.getHomeWelcomeUrl()),this);
        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(WHAT,2000);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what==WHAT){
            Intent intent = new Intent(this,AdActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }


    @Override
    public void onSuccess(String result) {
        if (!TextUtils.isEmpty(result)){
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("androidstart");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String imageUrl = jsonObject.getString("thumb");

            } catch (JSONException e) {
                e.printStackTrace();
            }
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
}
