package com.national.qinggong.customview.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.national.qinggong.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2019/8/1
 * 编写人：czy_yangxudong
 * 功能描述：首页商品适配器
 */
public class AutoPollAdapter extends RecyclerView.Adapter {

    private Context context;
    private final List<String> mData;

    public AutoPollAdapter(Context context, List<String> list) {
        this.context = context;
        this.mData = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_test, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        String data = mData.get(position%mData.size());
        viewHolder.tv_name.setText(data);

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

   /* public interface OnClickItemListener {
        void onClick(int position);

        void onClickListener(String name, int position);

        void onClickSelectListener(AddressBean.Datas bean);
    }

    public OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
