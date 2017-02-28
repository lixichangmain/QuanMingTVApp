package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxc.quanmingtvapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/16.
 */
public class MyMineAdapter extends BaseAdapter {
    private List<Integer> imageIdList;
    private List<String> textList;
    private Context context;

    public MyMineAdapter(Context context, List<Integer> imageIdList, List<String> textList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.textList = textList;
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int position) {
        return textList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_mine, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textview_item_mine);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(textList.get(position));
        viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(imageIdList.get(position),0,0,0);
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}
