package com.lxc.quanmingtvapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.application.MyApplication;
import com.lxc.quanmingtvapp.bean.User;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity implements PlatformActionListener {

    private EditText inputName;
    private EditText inputPassword;
    private Platform platform;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String userIcon;
    private String userName;
    private DbManager dbManager;
    private ImageView btn_passwordhide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //找到用户名和密码的输入控件
        inputName = (EditText) findViewById(R.id.usename_loginactivity);
        inputPassword = (EditText) findViewById(R.id.password_loginactivity);
        btn_passwordhide = (ImageView) findViewById(R.id.btn_passwordhide);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.backImage_loginactivity:
                //返回上一个页面
                finish();
                break;
            case R.id.regist_loginactivity:
                //点击注册按钮的逻辑
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login_loginactivity:
                //点击登录按钮的逻辑
                // 判断用户是否是我们的用户如果是将他的状态全部存储到sharedPrederence中
                String name = inputName.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                loginUser(name, password);
                break;
            case R.id.btn_passwordhide:
                if (btn_passwordhide.isSelected()){
                    //设置editText隐藏
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btn_passwordhide.setSelected(false);
                }else {
                    //设置editText显示
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btn_passwordhide.setSelected(true);
                }
                //是否隐藏密码的按钮逻辑
//                if (inputPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
//                    inputPassword.setInputType(InputType.TYPE_CLASS_TEXT);
//                    btn_passwordhide.setImageResource(R.mipmap.btn_code_hide);
//                } else if (inputPassword.getInputType() == InputType.TYPE_CLASS_TEXT) {
//                    inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                    btn_passwordhide.setImageResource(R.mipmap.btn_code_show);
//                }

                break;
            case R.id.tv_wangjipass_loginactivity:
                //忘记密码的逻辑处理
                remindPassword();
                Toast.makeText(LoginActivity.this, "有待开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_qqlogin:
                //第三方qq登录的逻辑
                Log.e("===================", "onClick: qq");
                platform = ShareSDK.getPlatform(this, QQ.NAME);
                if (platform.isAuthValid()) {
                    platform.removeAccount(true);
                }
                platform.setPlatformActionListener(this);
                //authorize与showUser单独调用一个即可

                platform.showUser(null);//授权并获取用户信息
                //isValid和removeAccount不开启线程，会直接返回。

                break;
            case R.id.image_webologin:
                Log.e("===================", "onClick: weibo");
                platform = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                platform.setPlatformActionListener(this);
                platform.showUser(null);//执行登录，登录后在回调里面获取用户资料

                break;
            case R.id.imagewx_weixinlogin:
                //第三方微信登录的逻辑
                Log.e("===================", "onClick: Wechat");
                platform = ShareSDK.getPlatform(this, Wechat.NAME);
                platform.setPlatformActionListener(this);
                platform.showUser(null);//执行登录，登录后在回调里面获取用户资料
                break;
        }
    }

    private void remindPassword() {

    }

    private void loginUser(String name, String password) {
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(password)) {
            dbManager = x.getDb(MyApplication.getMyApplication().getDaoConfig());
            try {
                List<User> userList = dbManager.selector(User.class).where("username", "=", name).findAll();
                if (userList != null) {
                    boolean flag = false;
                    for (int i = 0; i < userList.size(); i++) {
                        if (password.equals(userList.get(i).getPassword())) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        editor.putString("username", name);
                        editor.putInt("loginState", 1).commit();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                }

            } catch (DbException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(LoginActivity.this, "用户名、密码为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

        //第三方登录成功之后，获取用户信息将其写到xml中
        if (platform != null) {
            userIcon = platform.getDb().getUserIcon();
            userName = platform.getDb().getUserName();
            editor.putString("icon", userIcon);
            editor.putString("username", userName);
            editor.putInt("loginState", 1).commit();
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "第三方登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

}
