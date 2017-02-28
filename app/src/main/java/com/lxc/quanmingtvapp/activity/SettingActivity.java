package com.lxc.quanmingtvapp.activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.CompoundButton;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;


import java.io.File;
import java.text.DecimalFormat;


public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch wifiSwitch;
    private TextView showCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        //wifi设置按钮
        wifiSwitch = (Switch) findViewById(R.id.switchbutton_settingactivity);
        wifiSwitch.setOnCheckedChangeListener(this);
        //找到清除缓存显示textView
        showCache = (TextView) findViewById(R.id.textshowcache_settingactivity);
        //显示当前缓存的大小
        File dir = this.getExternalCacheDir();
        double usedSpace = 1.0 * seacheCashe(dir) / 1024 / 1024;
        showCache.setText(formatNum(usedSpace) + "M");//格式化数字并显示

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageback_settingactivity:
                //返回按钮
                finish();
                break;
            case R.id.clearCache_setting:
                //清除缓存,这里首先要获取缓存内容的大小
                File dir = this.getExternalCacheDir();
                deleteFilesByDirectory(dir.getAbsolutePath());
                float usedSpace = 1.0f * seacheCashe(dir) / 1024 / 1024;
                showCache.setText(formatNum(usedSpace) + "M");//格式化数字并显示
                Toast.makeText(SettingActivity.this, "清理成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.guanyuwe_setting:
                //关于我们，跳转到关于我们界面**************************************
                Intent intent = new Intent(this,AboutOurActivity.class);
                startActivity(intent);
                break;
            case R.id.pingfen_setting:
                //给我们评分
                Toast.makeText(SettingActivity.this, "未找到应用市场", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //wifi设置的监听事件**********************************
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //打开wifi提醒，将按钮的track设置上图片

        } else {

        }
    }

    //删除缓存文件
    private static void deleteFilesByDirectory(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFilesByDirectory(files[i].getAbsolutePath());
                }
            }
            if (file.isFile()) {
                file.delete();
            }
        }
    }


    //查询Cache目录已用内存
    private long seacheCashe(File file) {
        long usedSpace = 0;
        File[] filesList = file.listFiles();
        if (filesList.length != 0) {
            for (int i = 0; i < filesList.length; i++) {
                if (filesList[i].isDirectory()) {
                    usedSpace = usedSpace + seacheCashe(filesList[i]);
                } else {
                    usedSpace = usedSpace + filesList[i].length();
                }
            }
            return usedSpace;
        } else {
            return 0;
        }
    }

    //格式化数字
    private String formatNum(double num){
        DecimalFormat df = new DecimalFormat("###0.0");
        String str = df.format(num);
        return str;
    }
}
