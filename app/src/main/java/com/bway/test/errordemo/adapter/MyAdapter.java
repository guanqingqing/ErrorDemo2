package com.bway.test.errordemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.test.errordemo.R;
import com.bway.test.errordemo.bean.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<Data.TopStoriesBean> mlist;
    Context context;


    public MyAdapter(Context context, List<Data.TopStoriesBean> mlist) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Data.TopStoriesBean bean = mlist.get(position);
        holder.itemName.setText(bean.getTitle());
        Glide.with(context).load(bean.getImage()).into(holder.itemImage);
        holder.itemView.setTag(position);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemimageClick.setimageClick(v, position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                setLongItemClick.setimageClick(v,position);
                return false;
            }
        });


    }
    public interface setItemimageClick{
        void setimageClick(View view ,int position);
    }

    setItemimageClick setItemimageClick;

    public void setSetItemimageClick(MyAdapter.setItemimageClick setItemimageClick) {
        this.setItemimageClick = setItemimageClick;
    }

    public interface setLongItemClick{
        void setimageClick(View view ,int position);
    }

    setLongItemClick setLongItemClick;

    public void setSetLongItemClick(MyAdapter.setLongItemClick setLongItemClick) {
        this.setLongItemClick = setLongItemClick;
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_name)
        TextView itemName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
