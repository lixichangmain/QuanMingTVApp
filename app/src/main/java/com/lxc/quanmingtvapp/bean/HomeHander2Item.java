package com.lxc.quanmingtvapp.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class HomeHander2Item {

    private String title;
    private String nick;
    private String thumb;
    private String avatar;
    public void setJSONObject(JSONObject jsonObject){
        JSONObject jsonObject1=jsonObject.optJSONObject("link_object");

        this.title=jsonObject1.optString("title");
        this.nick=jsonObject1.optString("nick");
        this.thumb=jsonObject1.optString("thumb");
        this.avatar=jsonObject1.optString("avatar");
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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
