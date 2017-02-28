package com.lxc.quanmingtvapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxc.quanmingtvapp.MainActivity;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.comment.UrlAPIConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class AdActivity extends AppCompatActivity implements Handler.Callback,View.OnClickListener, Callback.CommonCallback<String> {

    private Handler handler;
    private ImageView imageView;
    private TextView textView;
    private ImageOptions options;
    private int time = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        initView();
    }

    private void initView() {

        handler = new Handler(this);
        imageView = (ImageView) findViewById(R.id.advertimage_adactivity);
        textView = (TextView) findViewById(R.id.textdump_adactivity);

        //加载图片数据
        RequestParams params = new RequestParams(UrlAPIConfig.getStartAdvertUrl());
        //创建图片修饰参数
       options = new ImageOptions.Builder()
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setUseMemCache(true)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
        x.http().get(params,this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        time--;
        if (time>0){
            textView.setText(time +" 跳过");
            handler.sendEmptyMessageDelayed(1,1000);
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }



    @Override
    public void onClick(View v) {
        time = 0;
    }

    @Override
    public void onSuccess(String result) {
        if (!TextUtils.isEmpty(result)){
            try {
                JSONObject object = new JSONObject(result);
                JSONArray jsonArray = object.getJSONArray("androidstart");
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                x.image().bind(imageView,jsonObject.getString("thumb"),options);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        handler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
        textView.setText(time+" 跳过");
        handler.sendEmptyMessageDelayed(1,1000);
    }
}
