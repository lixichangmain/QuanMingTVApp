package com.lxc.quanmingtvapp.adapter;

import android.content.Context;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.activity.PlayActivity;
import com.lxc.quanmingtvapp.bean.HomeItem;
import com.lxc.quanmingtvapp.fragment.HomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class HomeListViewAdapter extends BaseAdapter implements View.OnClickListener, HomeFragment.OnItemClick {

    private List<JSONArray> list;
    private Context context;
    private List<HomeItem> listBen;
    private String content;
    private List<List> lists=new ArrayList<>();
    private HomeFragment homeFragment=new HomeFragment();
    private int position;


//    public void setOnItemClick(OnItemClick onItemClick){
//        this.onItemClick=onItemClick;
//    }
//
//    public interface OnItemClick {
//        public void getPosition(int psition);
//    }


    public HomeListViewAdapter(List<JSONArray> list, Context context) {
        this.list = list;
        this.context = context;
        homeFragment.setOnItemClick(this);
    }

    @Override
    public int getCount() {
        return list.size() - 1;
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
        JSONArray jsonArray = list.get(position);
        listBen = new ArrayList<>();
        HomeItem homeItem = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            homeItem = new HomeItem();
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            JSONObject jsonObject1 = jsonObject.optJSONObject("link_object");
            homeItem.setObject(jsonObject1);
            listBen.add(homeItem);
        }
        lists.add(listBen);
        ViewHolder viewHolder = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_home2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageViewBig1 = (ImageView) convertView.findViewById(R.id.img_home_item2BigZuoShang);
            viewHolder.imageViewBig2 = (ImageView) convertView.findViewById(R.id.img_home_item2BigYouShang);
            viewHolder.imageViewBig3 = (ImageView) convertView.findViewById(R.id.img_home_item2BigZuoXia);
            viewHolder.imageViewBig4 = (ImageView) convertView.findViewById(R.id.img_home_item2BigYouXia);

            viewHolder.imageViewSmall1 = (SimpleDraweeView) convertView.findViewById(R.id.img_home_item2SmallZuoShang);
            viewHolder.imageViewSmall2 = (SimpleDraweeView) convertView.findViewById(R.id.img_home_item2SmallYouShang);
            viewHolder.imageViewSmall3 = (SimpleDraweeView) convertView.findViewById(R.id.img_home_item2SmallZuoXia);
            viewHolder.imageViewSmall4 = (SimpleDraweeView) convertView.findViewById(R.id.img_home_item2SmallYouXia);
            viewHolder.tx1 = (TextView) convertView.findViewById(R.id.tx_home_item2ZuoShang1);
            viewHolder.tx2 = (TextView) convertView.findViewById(R.id.tx_home_item2ZuoShang2);
            viewHolder.tx3 = (TextView) convertView.findViewById(R.id.tx_home_item2YouShang1);
            viewHolder.tx4 = (TextView) convertView.findViewById(R.id.tx_home_item2YouShang2);
            viewHolder.tx5 = (TextView) convertView.findViewById(R.id.tx_home_item2ZuoXia1);
            viewHolder.tx6 = (TextView) convertView.findViewById(R.id.tx_home_item2ZuoXia2);
            viewHolder.tx7 = (TextView) convertView.findViewById(R.id.tx_home_item2YouXia1);
            viewHolder.tx8 = (TextView) convertView.findViewById(R.id.tx_home_item2YouXia2);
            viewHolder.txTitle = (TextView) convertView.findViewById(R.id.tx_home_item2Title);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.txTitle.setText(list.get(list.size() - 1).optJSONObject(position + 3).optString("name"));
        content = list.get(list.size() - 1).optJSONObject(position + 3).optString("name");

        //左上
        if (listBen.size() > 1) {
            HomeItem homeItem1 = listBen.get(0);
            x.image().bind(viewHolder.imageViewBig1, homeItem1.getThumb());
            viewHolder.imageViewBig1.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuanjiao));

            viewHolder.imageViewBig1.setOnClickListener(this);

            getCircleImage(viewHolder.imageViewSmall1, homeItem1.getAvatar());
//        x.image().bind(viewHolder.imageViewSmall1, homeItem1.getAvatar());
//        viewHolder.imageViewSmall1.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuanxing));
            viewHolder.tx1.setText(homeItem1.getNick());
            viewHolder.tx2.setText(homeItem1.getTitle());
        }

        //右上
        if (listBen.size() > 2) {
            HomeItem homeItem2 = listBen.get(1);
            viewHolder.imageViewBig2.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuanjiao));
            x.image().bind(viewHolder.imageViewBig2, homeItem2.getThumb());

            viewHolder.imageViewBig2.setOnClickListener(this);

//        viewHolder.imageViewSmall2.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.yuanxing));
//        x.image().bind(viewHolder.imageViewSmall2, homeItem2.getAvatar());
            getCircleImage(viewHolder.imageViewSmall2, homeItem2.getAvatar());
            viewHolder.tx3.setText(homeItem2.getNick());
            viewHolder.tx4.setText(homeItem2.getTitle());
        } else {
            return convertView;
        }

        //左下

        HomeItem homeItem3 = listBen.get(2);
        x.image().bind(viewHolder.imageViewBig3, homeItem3.getThumb());
        viewHolder.imageViewBig3.setBackground(context.getResources().getDrawable(R.drawable.yuanjiao));

        viewHolder.imageViewBig3.setOnClickListener(this);

//        x.image().bind(viewHolder.imageViewSmall3, homeItem3.getAvatar());
//        viewHolder.imageViewSmall3.setBackground(context.getResources().getDrawable(R.drawable.yuanxing));
        getCircleImage(viewHolder.imageViewSmall3, homeItem3.getAvatar());
        viewHolder.tx5.setText(homeItem3.getNick());
        viewHolder.tx6.setText(homeItem3.getTitle());


        //右下

        HomeItem homeItem4 = listBen.get(3);
        x.image().bind(viewHolder.imageViewBig4, homeItem4.getThumb());
        viewHolder.imageViewBig4.setBackground(context.getResources().getDrawable(R.drawable.yuanjiao));

        viewHolder.imageViewBig4.setOnClickListener(this);
//        x.image().bind(viewHolder.imageViewSmall4, homeItem4.getAvatar());
//        viewHolder.imageViewSmall4.setBackground(context.getResources().getDrawable(R.drawable.yuanxing));
        getCircleImage(viewHolder.imageViewSmall4, homeItem4.getAvatar());
        viewHolder.tx7.setText(homeItem4.getNick());
        viewHolder.tx8.setText(homeItem4.getTitle());


        return convertView;
    }

    //获取圆形图像
    public void getCircleImage(SimpleDraweeView imageView, String path) {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(path))
                .setAutoPlayAnimations(true)
                .build();
        imageView.setController(draweeController);
    }

    @Override
    public void onClick(View v) {
//     onItemClick.getPosition();
        Toast.makeText(context, ""+lists.size()+position, Toast.LENGTH_SHORT).show();
        List<HomeItem> list1=lists.get(position);
        switch (v.getId()) {
            case R.id.img_home_item2BigZuoShang:
                Toast.makeText(context, "" + content, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "BigZuoShang", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(context, PlayActivity.class);
                Bundle bundle1 = new Bundle();

                bundle1.putParcelable("key", list1.get(0));
                intent1.putExtras(bundle1);
                context.startActivity(intent1);

                break;
            case R.id.img_home_item2BigYouShang:
                Toast.makeText(context, "" + content, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(context, PlayActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("key", list1.get(1));
                intent2.putExtras(bundle2);
                context.startActivity(intent2);
                break;
            case R.id.img_home_item2BigZuoXia:
                Toast.makeText(context, "" + content, Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(context, PlayActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putParcelable("key", list1.get(2));
                intent3.putExtras(bundle3);
                context.startActivity(intent3);
                break;
            case R.id.img_home_item2BigYouXia:
                Toast.makeText(context, "" + content, Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(context, PlayActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putParcelable("key", list1.get(3));
                intent4.putExtras(bundle4);
                context.startActivity(intent4);
                break;
        }
    }

    @Override
    public void getPosition(int position) {
        this.position=position;
    }

    static class ViewHolder {
        ImageView imageViewBig1, imageViewBig2, imageViewBig3, imageViewBig4;
        SimpleDraweeView imageViewSmall1, imageViewSmall2, imageViewSmall3, imageViewSmall4;
        TextView tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8, txTitle;
    }

}
