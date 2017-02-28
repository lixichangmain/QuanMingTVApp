package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.bean.UserCare;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class MyCareGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<UserCare> list;
    private LayoutInflater inflater;

    public MyCareGridViewAdapter(Context context, List<UserCare> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.livefragment_recycleview_item, parent, false);
            //直播画面
            viewHolder.thumb = (ImageView) convertView.findViewById(R.id.liveFG_item_bofang);
            //观看人数
            viewHolder.view = (TextView) convertView.findViewById(R.id.LiveFGshumu);
            //主播名
            viewHolder.nick = (TextView) convertView.findViewById(R.id.liveFG_item_textUp);
            //直播主题
            viewHolder.title = (TextView) convertView.findViewById(R.id.liveFG_item_textDown);
            //主播头像
            viewHolder.avatar = (SimpleDraweeView) convertView.findViewById(R.id.liveFG_item_touxiang);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        return convertView;
    }

    static class ViewHolder {
        ImageView thumb;
        TextView nick, title, view;
        SimpleDraweeView avatar;
    }
}
