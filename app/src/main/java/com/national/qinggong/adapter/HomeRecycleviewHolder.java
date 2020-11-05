package com.national.qinggong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.national.qinggong.R;
import com.national.qinggong.bean.MultipleHomeBean;
import com.national.qinggong.holder.CateageViewholder;
import com.national.qinggong.holder.MessageViewHolder;
import com.national.qinggong.holder.TypeAbstractViewHolder;
import com.national.qinggong.holder.TypeAdvViewholder;
import com.national.qinggong.holder.TypeRecycleHolder;

import java.util.List;

/*
 * 多种布局显示
 * */
public class HomeRecycleviewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MultipleHomeBean> homeEntities;
    private LayoutInflater layoutInflater;

    public HomeRecycleviewHolder(Context context, List<MultipleHomeBean> multipleHomeBeanList) {
        this.context = context;
        this.homeEntities = multipleHomeBeanList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return homeEntities.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new TypeAdvViewholder(layoutInflater.inflate(R.layout.test_bottom, parent, false), context);
            case 1:
                return new MessageViewHolder(layoutInflater.inflate(R.layout.home_message, parent, false), context);
            case 2:
                return new CateageViewholder(layoutInflater.inflate(R.layout.cate_recycle, parent, false), context);
            case 3:
                return new TypeRecycleHolder(layoutInflater.inflate(R.layout.home_recy_recycle, parent, false), context);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TypeAbstractViewHolder) holder).bindHolder(homeEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return homeEntities == null ? 0 : homeEntities.size();
    }
}
