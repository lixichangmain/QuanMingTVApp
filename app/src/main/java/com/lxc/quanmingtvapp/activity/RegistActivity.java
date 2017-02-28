package com.lxc.quanmingtvapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.application.MyApplication;
import com.lxc.quanmingtvapp.bean.User;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

public class RegistActivity extends AppCompatActivity {


    private EditText inputname;
    private EditText inputpassword;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        inputname = (EditText) findViewById(R.id.et_name_registactivity);
        inputpassword = (EditText) findViewById(R.id.et_password_registactivity);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageback_registactivity:
                finish();
                break;
            case R.id.btn_registactivity:
                //注册逻辑
                String name = inputname.getText().toString().trim();
                String password = inputpassword.getText().toString().trim();
                registUser(name, password);
                finish();
                break;
        }
    }

    public void registUser(String name, String password) {
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(password)) {
            dbManager = x.getDb(MyApplication.getMyApplication().getDaoConfig());
            try {
                List<User> list = dbManager.selector(User.class).where("username", "=", name).findAll();
                if (list == null || list.size() == 0) {
                    dbManager.save(new User(name, password,"0","0"));
                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
            } catch (DbException e) {
                e.printStackTrace();
                Toast.makeText(RegistActivity.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegistActivity.this, "用户名、密码为空", Toast.LENGTH_SHORT).show();
        }

    }
}
