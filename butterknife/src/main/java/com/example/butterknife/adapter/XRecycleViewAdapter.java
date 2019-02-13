package com.example.butterknife.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.butterknife.R;
import com.example.butterknife.bean.ShoppingBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class XRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShoppingBean.DataBean.TuijianBean.ListBeanX> mData;
    private Context mContext;

    public XRecycleViewAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList<>();
    }

    public void setData(List<ShoppingBean.DataBean.TuijianBean.ListBeanX> datas) {
        mData.clear();
        if (datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public List<ShoppingBean.DataBean.TuijianBean.ListBeanX> getmData() {
        return mData;
    }

    public void addData(List<ShoppingBean.DataBean.TuijianBean.ListBeanX> datas) {
        mData.clear();
        if (datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_recycle_xrvadapter,viewGroup,false);
        return new XRecyclerViewHplder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String images = mData.get(i).getImages();
        String[] split = images.split("\\|");
        //String[] split = mData.get(i).getList().get(i).getImages().split("\\|");
        XRecyclerViewHplder holder= (XRecyclerViewHplder) viewHolder;
        Glide.with(mContext).load(split[0]).into(holder.simple_img);
        holder.simple_title.setText(mData.get(i).getTitle());
        holder.simple_price.setText(mData.get(i).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class XRecyclerViewHplder extends RecyclerView.ViewHolder{

        private SimpleDraweeView simple_img;
        private TextView simple_title,simple_price;
        public XRecyclerViewHplder(@NonNull View itemView) {
            super(itemView);
            simple_img=itemView.findViewById(R.id.simple_img);
            simple_title=itemView.findViewById(R.id.simple_title);
            simple_price=itemView.findViewById(R.id.simple_price);
        }
    }
}
