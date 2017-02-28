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
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.bean.HomeHanferItem;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class HomeFillGridViewAdapter extends BaseAdapter {

    private List<HomeHanferItem> list;
    private Context context;
  public HomeFillGridViewAdapter(List<HomeHanferItem> list,Context context){
      this.list=list;
      this.context=context;
  }

    @Override
    public int getCount() {
        return list.size()+1;
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

        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.home_fill_gridview,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (SimpleDraweeView) convertView.findViewById(R.id.img_home_fill_gridview);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.tx_home_fill_gridview);
            convertView.setTag(viewHolder);
        }
            viewHolder= (ViewHolder) convertView.getTag();
        if(position<list.size()-1){
            HomeHanferItem homeHanferItem=list.get(position);
//            x.image().bind(viewHolder.imageView,homeHanferItem.getThumb());
            getCircleImage(viewHolder.imageView,homeHanferItem.getThumb());
            viewHolder.textView.setText(homeHanferItem.getSubtitle());
        }else {
            viewHolder.imageView.setImageResource(R.mipmap.all_live);
            viewHolder.textView.setText("查看更多");
        }


        return convertView;
    }


    //获取圆形图像
    public void getCircleImage(SimpleDraweeView imageView,String path){
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(path))
                .setAutoPlayAnimations(true)
                .build();
        imageView.setController(draweeController);
    }
    class ViewHolder{
        SimpleDraweeView imageView;
        TextView textView;
    }
}
