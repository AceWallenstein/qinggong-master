package com.national.qinggong.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.national.qinggong.bean.MultipleHomeBean;

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder{

    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindHolder(MultipleHomeBean model);
}
