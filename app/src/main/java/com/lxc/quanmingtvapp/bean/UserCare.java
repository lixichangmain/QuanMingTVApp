package com.lxc.quanmingtvapp.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/10/20.
 */
@Table(name="usercare")
public class UserCare {
    @Column(name="id",isId = true,autoGen = true)
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="nick")
    private String nick;

    @Column(name="avatar")
    private String avatar;

    @Column(name="status")
    private int status;

    @Column(name="thumb")
    private String thumb;

    @Column(name="uid")
    private String uid;

    @Column(name="title")
    private String title;

    @Column(name="view")
    private String view;

    public UserCare(){}
    public UserCare(String username,String nick, String avatar,int status, String thumb, String uid,String title,String view) {
        this.username = username;
        this.nick = nick;
        this.avatar = avatar;
        this.status = status;
        this.thumb = thumb;
        this.uid = uid;
        this.title = title;
        this.view = view;
    }
}
