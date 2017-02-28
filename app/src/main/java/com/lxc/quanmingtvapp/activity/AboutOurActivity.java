package com.lxc.quanmingtvapp.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxc.quanmingtvapp.R;

public class AboutOurActivity extends AppCompatActivity {

    private ImageView imageViewback;
    private TextView textViewVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_our);
        init();
    }

    private void init() {
        textViewVersion = (TextView) findViewById(R.id.textversion_aboutour);
        //获取App的应用名和版本号
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(),0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            String appName = getResources().getString(labelRes);
            String version = packageInfo.versionName;
            textViewVersion.setText(appName+" "+ version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view){
        if (view.getId() == R.id.imageback_aboutour){
            finish();
        }
    }


}
