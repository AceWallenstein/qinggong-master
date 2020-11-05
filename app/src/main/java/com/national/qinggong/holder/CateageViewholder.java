package com.national.qinggong.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.utils.DensityUtil;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.bean.CateageBean;
import com.national.qinggong.bean.MultipleHomeBean;
import com.national.qinggong.customview.GridSpacingItemDecoration;

import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;


public class CateageViewholder extends TypeAbstractViewHolder {
    //    private final LinearLayout lin;
    private RecyclerView home_cateage_recyclerView;
    //    private final LayoutInflater layoutInflater;
    Context context;
    private JoneBaseAdapter<CateageBean> mJobDataAdapter;

    public CateageViewholder(View itemView, Context context) {
        super(itemView);
        this.context = context;
//        this.layoutInflater = LayoutInflater.from(context);
        home_cateage_recyclerView = (RecyclerView) itemView.findViewById(R.id.home_cateage_recyclerView);
    }

    @Override
    public void bindHolder(MultipleHomeBean model) {
//        for (int i = 0; i < model.getListBeans().size(); i++) {
//        View list_item = LayoutInflater.from(context).inflate(R.layout.item_main_list_item, null);
//        TextView title = (TextView) list_item.findViewById(R.id.title);
//        title.setText(model.getListBeans().get(i).getMsg());
//            lin.addView(list_item);
//        }
        mJobDataAdapter = new JoneBaseAdapter<CateageBean>(home_cateage_recyclerView, R.layout.item_grid) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, CateageBean model) {
//                helper.setText(R.id.title, model.getMsg() + "");
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 5);
        home_cateage_recyclerView.setLayoutManager(gridLayoutManager);
        home_cateage_recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, DensityUtil.dp2px(context, 10), true));
        home_cateage_recyclerView.setAdapter(mJobDataAdapter);
        mJobDataAdapter.setData(model.getCateageBeanList());
//        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
//            @Override
//            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//                Log.i("=position=", position+ "===");
//                Toast.makeText(context,"当前"+position,Toast.LENGTH_LONG).show();
//            }
//        });

    }
}
