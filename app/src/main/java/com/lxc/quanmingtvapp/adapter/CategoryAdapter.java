package com.lxc.quanmingtvapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxc.quanmingtvapp.InterFace.CallbackLiveFGItemClick;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.bean.CategoryBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<CategoryBean> list;
    private Context context;


    public CategoryAdapter(Context context, List<CategoryBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_categoryfg_recycleview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            x.image().bind(((MyViewHolder) holder).img,list.get(position).getImage());
            ((MyViewHolder) holder).textView.setText(list.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.category_item_img);
            textView = (TextView) itemView.findViewById(R.id.category_item_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callbackLiveFGItemClick.onItemClick(v,getLayoutPosition());
        }
    }
    private CallbackLiveFGItemClick callbackLiveFGItemClick;

    public void setCallbackLiveFGItemClick(CallbackLiveFGItemClick callbackLiveFGItemClick) {
        this.callbackLiveFGItemClick = callbackLiveFGItemClick;
    }


}
