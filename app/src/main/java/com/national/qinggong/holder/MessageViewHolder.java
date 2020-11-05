package com.national.qinggong.holder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.bean.CateageBean;
import com.national.qinggong.bean.MultipleHomeBean;
import com.national.qinggong.ui.activity.MessageActivity;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.ActivityUtils;

public class MessageViewHolder  extends TypeAbstractViewHolder{
    private final TextView sale_count;
    private final TextView right_now_open_account;
    private final TextView activity_info;
    private TextView home_cateage_recyclerView;
    Context context;
    private JoneBaseAdapter<CateageBean> mJobDataAdapter;
    public MessageViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
//        this.layoutInflater = LayoutInflater.from(context);
        sale_count = (TextView) itemView.findViewById(R.id.sale_count);
        right_now_open_account = (TextView) itemView.findViewById(R.id.right_now_open_account);
        activity_info = (TextView) itemView.findViewById(R.id.activity_info);
    }

    @Override
    public void bindHolder(MultipleHomeBean model) {
        sale_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(context,MessageActivity.class);
            }
        });
        right_now_open_account.setOnClickListener(new View.OnClickListener() {//开户
            @Override
            public void onClick(View v) {
                Bundle weekSalaryBundle = new Bundle();
                weekSalaryBundle.putInt("type", 4);
                PlatformForFragmentActivity.newInstance(context, weekSalaryBundle);
            }
        });
        activity_info.setOnClickListener(new View.OnClickListener() {//活动
            @Override
            public void onClick(View v) {
                Bundle weekSalaryBundle = new Bundle();
                weekSalaryBundle.putInt("type", 5);
                PlatformForFragmentActivity.newInstance(context, weekSalaryBundle);
            }
        });

    }
}
