package com.lxc.quanmingtvapp.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/10/20.
 */
@Table(name = "watchhistory")
public class WatchHistory {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "nick")
    private String nick;

    @Column(name = "avatar")
    private String avatar;

    @Column(name="status")
    private String status;

    @Column(name = "thumb")
    private String thumb;

    @Column(name = "uid")
    private String uid;

    @Column(name = "title")
    private String title;

    @Column(name = "view")
    private String view;


    public WatchHistory(String username,String nick, String avatar,  String status, String thumb, String uid,String title,  String view) {
        this.username = username;
        this.nick = nick;
        this.avatar = avatar;
        this.status = status;
        this.thumb = thumb;
        this.uid = uid;
        this.title = title;
        this.view = view;
    }

    @Override
    public String toString() {
        return "WatchHistory{" +
                "avatar='" + avatar + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", nick='" + nick + '\'' +
                ", status='" + status + '\'' +
                ", thumb='" + thumb + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", view='" + view + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
