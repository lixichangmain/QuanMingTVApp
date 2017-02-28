package com.lxc.quanmingtvapp;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class StartAnimation {
    private static StartAnimation startAnimation;
    private StartAnimation(){

    }
    public static StartAnimation getStartAnimation(){
        if (startAnimation==null){
        startAnimation=new StartAnimation();
        }
        return startAnimation;
    }



    private ImageView imageView;

    private AnimationDrawable animationDrawable;
    public void start(Resources resources,ImageView imageView){

        this.imageView=imageView;
        animationDrawable=new AnimationDrawable();
        animationDrawable.addFrame(new BitmapDrawable(resources, BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_1)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_2)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_3)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_4)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_5)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_6)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_7)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_8)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_9)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_10)),80);
        animationDrawable.addFrame(new BitmapDrawable(resources,BitmapFactory.
                decodeResource(resources,R.mipmap.ani_popover_loading_yz_11)),80);
        //设置这个帧动画循环播放
        animationDrawable.setOneShot(false);
        imageView.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }

    public void stop(){
        animationDrawable.stop();
        imageView.clearAnimation();
        imageView.setVisibility(View.GONE);
    }
}
