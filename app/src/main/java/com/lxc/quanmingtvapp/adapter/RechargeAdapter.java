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
 * Created by Administrator on 2016/10/20.
 */
public class RechargeAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public RechargeAdapter(Context context, List<String> list) {
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

    public List<String> getList() {
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.recharge_listview_item, parent, false);
            viewHolder.textcoll = (TextView) convertView.findViewById(R.id.textcoll_item);
            viewHolder.textmoney = (TextView) convertView.findViewById(R.id.money_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String str = list.get(position);
        viewHolder.textmoney.setText(str + "元");
        viewHolder.textcoll.setText(Integer.parseInt(str) * 10 + "");
        return convertView;
    }

    static class ViewHolder {
        TextView textcoll, textmoney;
    }
}
