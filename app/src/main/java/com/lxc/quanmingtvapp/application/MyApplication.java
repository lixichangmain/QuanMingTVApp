package com.lxc.quanmingtvapp.application;

import android.app.Application;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lxc.quanmingtvapp.adapter.MyMineAdapter;

import org.xutils.DbManager;
import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/10/17.
 */
public class MyApplication extends Application implements DbManager.DbOpenListener, DbManager.DbUpgradeListener {

    DbManager.DaoConfig daoConfig;
    private static MyApplication myApplication;

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        myApplication = this;
        ShareSDK.initSDK(this);
        //初始化fresco
        Fresco.initialize(this);
        //初始化Xutils
        x.Ext.init(this);
        x.Ext.setDebug(true);
        //设置数据库的参数
        daoConfig = new DbManager.DaoConfig()
                .setDbVersion(1)
                .setDbName("userdb.db")
                .setAllowTransaction(true)//开启事务
                .setDbDir(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS))
                .setDbOpenListener(this)
                .setDbUpgradeListener(this);


    }

    @Override
    public void onDbOpened(DbManager db) {
        //开启WAL,对写入加速提升巨大
        db.getDatabase().enableWriteAheadLogging();
    }

    @Override
    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
        //数据库的更新操作
    }
}
