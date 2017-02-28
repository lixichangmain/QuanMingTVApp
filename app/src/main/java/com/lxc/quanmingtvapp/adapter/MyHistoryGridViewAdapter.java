package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.bean.WatchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class MyHistoryGridViewAdapter extends BaseAdapter{
    private List<WatchHistory> list;
    private Context context;
    private LayoutInflater inflater;
    public MyHistoryGridViewAdapter(Context context,List<WatchHistory> list){
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.livefragment_recycleview_item,parent,false);
            viewHolder.thumb = (SimpleDraweeView) convertView.findViewById(R.id.liveFG_item_bofang);
            viewHolder.avator = (SimpleDraweeView) convertView.findViewById(R.id.liveFG_item_touxiang);
            viewHolder.nick = (TextView) convertView.findViewById(R.id.liveFG_item_textUp);
            viewHolder.title = (TextView) convertView.findViewById(R.id.liveFG_item_textDown);
            viewHolder.view = (TextView) convertView.findViewById(R.id.LiveFGshumu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WatchHistory watchHistory = list.get(position);
        //设置数据
        viewHolder.nick.setText(watchHistory.getNick());
        viewHolder.title.setText(watchHistory.getTitle());
        viewHolder.view.setText(Integer.parseInt(watchHistory.getView())/10000+"W");

        //设置图片信息
        Uri uri =  Uri.parse(watchHistory.getAvatar());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .build();
        viewHolder.avator.setController(controller);


        Uri uri1 =  Uri.parse(watchHistory.getThumb());
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .build();
        viewHolder.thumb.setController(controller1);
        return convertView;
    }

    static class ViewHolder{

        TextView title,view,nick;
        SimpleDraweeView avator,thumb;
    }
}
