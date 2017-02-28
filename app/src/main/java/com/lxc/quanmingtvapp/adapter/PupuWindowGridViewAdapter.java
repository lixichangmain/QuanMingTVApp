package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lxc.quanmingtvapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class PupuWindowGridViewAdapter extends BaseAdapter implements View.OnClickListener {
    ViewHolder viewHolder;
    private List<String> list;
    private Context context;
    public PupuWindowGridViewAdapter(List<String> list,Context context){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_playroom_fiipopuwindow,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.checkBox= (CheckBox) convertView.findViewById(R.id.checkbox_item_playroom_fillpopuwindow);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.text_playroom_popuwindow_fillgridview);
            viewHolder.textView.setOnClickListener(this);
            convertView.setTag(viewHolder);

        }
        else
            viewHolder= (ViewHolder) convertView.getTag();
            viewHolder.textView.setText(list.get(position));

        return convertView;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(context
                , "点击了"+viewHolder.checkBox.isChecked(), Toast.LENGTH_SHORT).show();
        boolean checked=!viewHolder.checkBox.isChecked();
        viewHolder.checkBox.setChecked(checked);
    }

    class ViewHolder{
      CheckBox checkBox;
      TextView textView;
  }

}
