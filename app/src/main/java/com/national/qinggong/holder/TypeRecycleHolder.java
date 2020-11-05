package com.national.qinggong.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.bean.ListBean;
import com.national.qinggong.bean.MultipleHomeBean;
import com.national.qinggong.customview.CustomRecycleview;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class TypeRecycleHolder extends TypeAbstractViewHolder {
    //    private final LinearLayout lin;
    private CustomRecycleview home_recycle_recyclerView;
    //    private final LayoutInflater layoutInflater;
    Context context;
    private JoneBaseAdapter<ListBean> mJobDataAdapter;

    public TypeRecycleHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
//        mViewPager = itemView.findViewById(R.id.viewpager);
//        this.layoutInflater = LayoutInflater.from(context);
        home_recycle_recyclerView = (CustomRecycleview) itemView.findViewById(R.id.home_recycle_recyclerView);
//        lin=(LinearLayout)  itemView.findViewById(R.id.lin);
    }

    @Override
    public void bindHolder(MultipleHomeBean model) {
        Log.i("=bindHolder=", model.getListBeans().size() + "===");
//        for (int i = 0; i < model.getListBeans().size(); i++) {
//        View list_item = LayoutInflater.from(context).inflate(R.layout.item_main_list_item, null);
//        TextView title = (TextView) list_item.findViewById(R.id.title);
//        title.setText(model.getListBeans().get(i).getMsg());
//            lin.addView(list_item);
//        }
        mJobDataAdapter = new JoneBaseAdapter<ListBean>(home_recycle_recyclerView, R.layout.item_main_list_item) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ListBean model) {
                helper.setText(R.id.title, model.getMsg() + "");
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        home_recycle_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        home_recycle_recyclerView.setAdapter(mJobDataAdapter);
        mJobDataAdapter.setData(model.getListBeans());
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Log.i("=position=", position+ "===");
//                Toast.makeText(context,"当前"+position,Toast.LENGTH_LONG).show();
                Bundle weekSalaryBundle = new Bundle();
                weekSalaryBundle.putInt("type", 3);
                PlatformForFragmentActivity.newInstance(context, weekSalaryBundle);
            }
        });

    }
}
