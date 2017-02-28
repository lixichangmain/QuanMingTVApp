package com.lxc.quanmingtvapp.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class HomeHanderItem {
    private String thumb;
    private String uid;
    public void setJSONObjec(JSONObject jsonObject){
        JSONObject jsonObject1=jsonObject.optJSONObject("link_object");

        this.thumb=jsonObject1.optString("thumb");
        this.uid=jsonObject1.optString("uid");
    }
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
