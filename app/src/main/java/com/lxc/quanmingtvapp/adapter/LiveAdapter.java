package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.InterFace.CallbackLiveFGItemClick;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.bean.LiveFGBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.XMLFormatter;

/**
 * Created by Administrator on 2016/10/16.
 */
public class LiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LiveFGBean.DataBean> list;
    private Context context;

    public LiveAdapter(Context context, List<LiveFGBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.livefragment_recycleview_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            ((MyViewHolder) holder).textup.setText(list.get(position).getNick());
            ((MyViewHolder) holder).textdown.setText(list.get(position).getTitle());
            String s = list.get(position).getView();
            float f2 = 0;
            if(Integer.parseInt(s)>10000){
                float f1 = (Float.parseFloat(s))/10000;
                BigDecimal b = new BigDecimal(f1);
                f2 = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
            }else{
                float f1 = Float.parseFloat(s);
                BigDecimal b = new BigDecimal(f1);
                f2 = b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
            }

            ((MyViewHolder) holder).shumu.setText(f2+"W");
//            ImageOptions options = new ImageOptions.Builder().setCrop(true).setSize(150,150).setUseMemCache(true).build();
//            x.image().bind(((MyViewHolder) holder).bofang,list.get(position).getThumb());
//            x.image().bind(((MyViewHolder) holder).touxiang,list.get(position).getAvatar(),options);



            Uri uri =  Uri.parse(list.get(position).getAvatar());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .build();
            ((MyViewHolder) holder).touxiang.setController(controller);


            Uri uri1 =  Uri.parse(list.get(position).getThumb());
            DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                    .setUri(uri1)
                    .build();
            ((MyViewHolder) holder).bofang.setController(controller1);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private CallbackLiveFGItemClick callbackLiveFGItemClick;

    public void setCallbackLiveFGItemClick(CallbackLiveFGItemClick callbackLiveFGItemClick) {
        this.callbackLiveFGItemClick = callbackLiveFGItemClick;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        SimpleDraweeView bofang,touxiang;

        TextView textup, textdown,shumu;

        public MyViewHolder(View itemView) {
            super(itemView);

            bofang = (SimpleDraweeView) itemView.findViewById(R.id.liveFG_item_bofang);
            touxiang = (SimpleDraweeView) itemView.findViewById(R.id.liveFG_item_touxiang);
            textup = (TextView) itemView.findViewById(R.id.liveFG_item_textUp);
            textdown = (TextView) itemView.findViewById(R.id.liveFG_item_textDown);
            shumu = (TextView) itemView.findViewById(R.id.LiveFGshumu);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            callbackLiveFGItemClick.onItemClick(v,this.getLayoutPosition());
        }
    }
}
