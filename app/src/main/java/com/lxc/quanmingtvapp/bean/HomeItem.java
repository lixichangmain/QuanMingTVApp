package com.lxc.quanmingtvapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class HomeItem implements Parcelable{


    /**
     * recommend_image : http://image.quanmin.tv/94c7901bce10d0636e704bba43a01045jpg
     * announcement : 每天早上10点半直播
     * title : 国服最强猴王
     * create_at : 2016-10-17 10:33:46
     * intro : 新浪微博：孙悟空kingking百度贴吧：lol猴王悟空
     * video : http://thumb.quanmin.tv/333.mp4?t=1476686400
     * screen : 0
     * love_cover :
     * category_id : 1
     * video_quality : 234
     * like : 16
     * default_image :
     * slug :
     * weight : 131028920
     * status : 1
     * avatar : http://image.quanmin.tv/avatar/2ef14b476e146062065af2857d953be0?imageView2/2/w/300/
     * level : 0
     * uid : 333
     * play_at : 2016-10-17 10:33:46
     * view : 434729
     * category_slug : lol
     * nick : 孙悟空zzzz
     * beauty_cover :
     * app_shuffling_image : http://image.quanmin.tv/d6808580cd0c897366c76df33ed95902jpg
     * start_time : 2016-10-17 10:33:46
     * follow : 385253
     * category_name : 英雄联盟
     * grade :
     * thumb : http://snap.quanmin.tv/333-1476686522-747.jpg?imageView2/2/w/390/
     * hidden : false
     */

    private String recommend_image;
    private String announcement;
    private String title;
    private String create_at;
    private String intro;
    private String video;
    private int screen;
    private String love_cover;
    private String category_id;
    private String video_quality;
    private String like;
    private String default_image;
    private String slug;
    private String weight;
    private String status;
    private String avatar;
    private String level;
    private String uid;
    private String play_at;
    private String view;
    private String category_slug;
    private String nick;
    private String beauty_cover;
    private String app_shuffling_image;
    private String start_time;
    private String follow;
    private String category_name;
    private String grade;
    private String thumb;
    private boolean hidden;

    public void setObject(JSONObject jsonObject){
        this.nick=jsonObject.optString("nick");
        this.title=jsonObject.optString("title");
        this.uid=jsonObject.optString("uid");
        this.thumb=jsonObject.optString("thumb");
        this.avatar=jsonObject.optString("avatar");
        this.status=jsonObject.optString("status");
        this.view=jsonObject.optString("view");
    }


    public String getRecommend_image() {
        return recommend_image;
    }

    public void setRecommend_image(String recommend_image) {
        this.recommend_image = recommend_image;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public String getLove_cover() {
        return love_cover;
    }

    public void setLove_cover(String love_cover) {
        this.love_cover = love_cover;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlay_at() {
        return play_at;
    }

    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBeauty_cover() {
        return beauty_cover;
    }

    public void setBeauty_cover(String beauty_cover) {
        this.beauty_cover = beauty_cover;
    }

    public String getApp_shuffling_image() {
        return app_shuffling_image;
    }

    public void setApp_shuffling_image(String app_shuffling_image) {
        this.app_shuffling_image = app_shuffling_image;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
////////////实现序列化的方法
    @Override
    public int describeContents() {
        return 0;
    }
//写对象到parcel的方法

    /**
     * parcel 是一个容器 用来放置对象
     * @param dest 这是一个容器对象
     * @param flags 你想网parcel里面放入parcelable的一个标记
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(nick);
        dest.writeString(avatar);
        dest.writeString(status);
        dest.writeString(thumb);
        dest.writeString(uid);
        dest.writeString(title);
        dest.writeString(view);
    }

    public static final Parcelable.Creator<HomeItem> CREATOR
            =new Creator<HomeItem>(){

        @Override
        public HomeItem createFromParcel(Parcel source) {
            HomeItem homeItem=new HomeItem();
            homeItem.setNick(source.readString());
            homeItem.setAvatar(source.readString());
            homeItem.setStatus(source.readString());
            homeItem.setThumb(source.readString());
            homeItem.setUid(source.readString());
            homeItem.setTitle(source.readString());
            homeItem.setView(source.readString());
            return homeItem;
        }

        @Override
        public HomeItem[] newArray(int size) {
            return new HomeItem[size];
        }
    };
}
