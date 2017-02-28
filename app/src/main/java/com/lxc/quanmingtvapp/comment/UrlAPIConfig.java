package com.lxc.quanmingtvapp.comment;

/**
 * Created by Administrator on 2016/10/16.
 */
public class UrlAPIConfig {
    public static String getStartAdvertUrl(){
        return "http://www.quanmin.tv/json/page/app-data/info.json?device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }
    public static String getLiveFragmentUrl(){
        return "http://www.quanmin.tv/json/play/list.json?10151738&device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }
    public static String getHomeFragmentUrl(){
        return "http://www.quanmin.tv/json/page/appv2-index/info.json?10151706&device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
//        return "http://www.quanmin.tv/json/app/index/recommend/list-android.json?02271526&v=3.0.1&os=1&ver=4&toid=1509931114&token=fg9vSYa4c1390a&sid=cc17a9584d602bf0182a3b7b48660ba3";
    }
    public static String getCategroyJumpUrl(String categories){
        return "http://www.quanmin.tv/json/categories/"+categories+"/list.json?10151732&device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }
    public static String getCategoryFragmentUrl(){
        return "http://www.quanmin.tv/json/categories/list.json?10151731&device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }

    public static String getPlayRoomUrl(String uid){
        return "http://www.quanmin.tv/json/rooms/"+uid+"/info.json?10151724&device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }
    public static String getHomeWelcomeUrl(){
        return "http://www.quanmin.tv/json/page/app-data/info.json?device=860138035287728&v=2.1.3&screen=2&ch=baiduzhushou&sh=960&sw=540&uid=16cb4d8&net=0&ver=4&os=1";
    }
}
