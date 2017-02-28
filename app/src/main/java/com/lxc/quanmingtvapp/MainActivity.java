package com.lxc.quanmingtvapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lxc.quanmingtvapp.fragment.CategoryFragment;
import com.lxc.quanmingtvapp.fragment.HomeFragment;
import com.lxc.quanmingtvapp.fragment.LiveFragment;
import com.lxc.quanmingtvapp.fragment.MineFragment;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment showFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        showCurrentFragment(HomeFragment.class);
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton radioButton = (RadioButton) findViewById(R.id.rb_home_main);
        radioButton.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home_main:
                showCurrentFragment(HomeFragment.class);
                break;
            case R.id.rb_categroy_main:
                showCurrentFragment(CategoryFragment.class);
                break;
            case R.id.rb_live_main:
                showCurrentFragment(LiveFragment.class);
                break;
            case R.id.rb_mine_main:
                showCurrentFragment(MineFragment.class);
                break;

        }
    }


    private void showCurrentFragment(Class<? extends Fragment> clazz) {
        fragmentTransaction = fragmentManager.beginTransaction();
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        showFragment = fragmentManager.findFragmentByTag(clazz.getName());
        if (showFragment != null) {
            fragmentTransaction.show(showFragment);
        } else {
            try {
                showFragment = clazz.getConstructor().newInstance();
                fragmentTransaction.add(R.id.framelayout_main, showFragment, clazz.getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
//        Toast.makeText(MainActivity.this, "当前activity被销毁", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    //     public void showCategrayFragment(){
//
//         showCurrentFragment(CategoryFragment.class);
//     }
}
