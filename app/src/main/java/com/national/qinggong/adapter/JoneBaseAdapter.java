package com.national.qinggong.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 *
 * @Description: TODO(){通用的适配器  只是数据需要回调到书写页面}
 *
 */
public abstract class JoneBaseAdapter<T> extends BGARecyclerViewAdapter<T> {


    public JoneBaseAdapter(RecyclerView recyclerView, @LayoutRes int resource) {
        super(recyclerView, resource);
    }
    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, T model) {
        fillItemData(helper,position,model);
    }
    public abstract void fillItemData(BGAViewHolderHelper helper, int position, T model);
}
