package com.lxc.quanmingtvapp.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class HomeHorscItem {


    /**
     * id : 405
     * title : 英雄联盟
     * thumb : http://image.quanmin.tv/2afdbd142d816ad0507046535d764f6apng
     * link :
     * create_at : 2016-03-12 19:53:47
     * status : 1
     * fk : null
     * subtitle : 英雄联盟
     * ext : {"classification":"lol"}
     * slot_id : 106
     * priority : 1
     */

    private int id;
    private String title;
    private String thumb;
    private String link;
    private String create_at;
    private int status;
    private Object fk;
    private String subtitle;
    /**
     * classification : lol
     */

    private ExtBean ext;
    private int slot_id;
    private int priority;


    public void setJSONObject(JSONObject jsonObject){
        this.subtitle=jsonObject.optString("subtitle");
        this.thumb=jsonObject.optString("thumb");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getFk() {
        return fk;
    }

    public void setFk(Object fk) {
        this.fk = fk;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static class ExtBean {
        private String classification;

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }
    }
}
