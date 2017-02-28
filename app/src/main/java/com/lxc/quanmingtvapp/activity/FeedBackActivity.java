package com.lxc.quanmingtvapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;

public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.imageback_feedbackactivity:

                break;
            case R.id.btn_tijiao_feedback:
                Toast.makeText(FeedBackActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                break;
        }
        finish();

    }
}
