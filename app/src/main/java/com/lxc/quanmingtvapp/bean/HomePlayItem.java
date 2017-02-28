package com.lxc.quanmingtvapp.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class HomePlayItem {

    private String uid;
    private String title;
    private String nick;
    private String avatar;
    private String biaoqin;
    private String gaoqin;
    private String chaoqin;
    private int view;


    public void setJSONObject(JSONObject jsonObject){
        this.uid=jsonObject.optString("uid");
        this.title=jsonObject.optString("title");
        this.nick=jsonObject.optString("nick");
        this.avatar=jsonObject.optString("avatar");
        this.view=jsonObject.optInt("view");
        JSONObject jsonObject1=jsonObject.optJSONObject("live");
        JSONObject jsonObject2=jsonObject1.optJSONObject("ws");
        JSONObject jsonObject3=jsonObject2.optJSONObject("flv");
        JSONObject jsonObject4=jsonObject3.optJSONObject("3");
        JSONObject jsonObject5=jsonObject3.optJSONObject("4");
        JSONObject jsonObject6=jsonObject3.optJSONObject("5");
        this.biaoqin=jsonObject4.optString("src");
        this.gaoqin=jsonObject5.optString("src");
        this.chaoqin=jsonObject6.optString("src");

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBiaoqin() {
        return biaoqin;
    }

    public void setBiaoqin(String biaoqin) {
        this.biaoqin = biaoqin;
    }

    public String getGaoqin() {
        return gaoqin;
    }

    public void setGaoqin(String gaoqin) {
        this.gaoqin = gaoqin;
    }

    public String getChaoqin() {
        return chaoqin;
    }

    public void setChaoqin(String chaoqin) {
        this.chaoqin = chaoqin;
    }
}
