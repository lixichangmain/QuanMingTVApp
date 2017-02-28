package com.lxc.quanmingtvapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lxc.quanmingtvapp.PayResult;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.adapter.RechargeAdapter;
import com.lxc.quanmingtvapp.server.ServerDo;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private TextView textView;
    private List<String> list;
    private RechargeAdapter rechargeAdapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String coll;
    private PopupWindow popupWindow;
    private View popuView;
    private TextView textpaymoney;
    private ServerDo serverDo;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        int num = Integer.parseInt(sharedPreferences.getString("coll","0"));
                        int rechargenum = Integer.parseInt(collstr);
                        int total = num + rechargenum;
                        textView.setText(total+"");
                        editor.putString("coll",total+"").commit();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(RechargeActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private String collstr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        init();

    }

    private void init() {
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //找到listView
        listView = (ListView) findViewById(R.id.listview_rechargeactivity);
        //找到账户余额对应的textView
        textView = (TextView) findViewById(R.id.textcoll_rechargeactivity);
        coll = sharedPreferences.getString("coll", "0");
        textView.setText(coll);
        //添加item数据
        initList();

        rechargeAdapter = new RechargeAdapter(this, list);
        listView.setAdapter(rechargeAdapter);

        //设置listview的点击事件
        listView.setOnItemClickListener(this);

        popuView = LayoutInflater.from(this).inflate(R.layout.popuwindow_recharge, null);
        popupWindow = new PopupWindow(popuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        textpaymoney = (TextView) popuView.findViewById(R.id.text_paymoeny_recharge);
    }

    private void initList() {
        list = new ArrayList<>();
        list.add(10 + "");
        list.add(25 + "");
        list.add(50 + "");
        list.add(100 + "");
        list.add(1000 + "");
        list.add(10000 + "");
    }

    public void showPopuWindow(){
        if (popupWindow.isShowing()){
            popupWindow.dismiss();
        }else {
            popupWindow.showAtLocation(popuView, Gravity.BOTTOM,0,0);
        }
    }
    //设置返回按钮的点击事件
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageback_rechareactivity:
                finish();
                break;
            case R.id.zhifubao_recharge:
                //支付宝支付
//                Toast.makeText(RechargeActivity.this, "支付宝支付", Toast.LENGTH_SHORT).show();
                serverDo = new ServerDo();
                final String payInfo = serverDo.doPay(collstr);
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(RechargeActivity.this);
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(payInfo, true);

                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
            case R.id.quxiao_recharge:
                //取消支付
                Toast.makeText(RechargeActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
        }

    }

    //listView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        collstr = rechargeAdapter.getList().get(position);
        textpaymoney.setText("支付"+collstr+"元");
        showPopuWindow();
    }


}
