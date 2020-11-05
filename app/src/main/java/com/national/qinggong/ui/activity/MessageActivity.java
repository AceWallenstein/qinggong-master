package com.national.qinggong.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class MessageActivity extends BaseActivity {
    @BindView(R.id.message_recyclerView)
    RecyclerView message_recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<HomeBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;

    @Override
    protected void initdata() {
        initRefresh();
        mJobDataAdapter = new JoneBaseAdapter<HomeBean>(message_recyclerView, R.layout.home_message_item) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomeBean model) {
                helper.setText(R.id.message_content, model.getMsg() + "");
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        message_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        message_recyclerView.setAdapter(mJobDataAdapter);
        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
        mJobDataAdapter.setData(datas);
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Log.i("=position=", position+ "===");
//                Bundle weekSalaryBundle = new Bundle();
//                weekSalaryBundle.putInt("type", 2);
//                PlatformForFragmentActivity.newInstance(MessageActivity.this, weekSalaryBundle);

                ActivityUtils.startActivity(MessageActivity.this, WebviewActivity.class);


//                Toast.makeText(context,"当前"+position,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRefresh() {
        mProgressLayout = new ProgressLayout(this);
        twinkling_refreshlayout.setHeaderView(mProgressLayout);
        twinkling_refreshlayout.setFloatRefresh(true);
        twinkling_refreshlayout.setEnableOverScroll(false);
        twinkling_refreshlayout.setHeaderHeight(100);
        twinkling_refreshlayout.setMaxHeadHeight(120);
        twinkling_refreshlayout.setBottomHeight(70);
        twinkling_refreshlayout.setMaxBottomHeight(90);
        twinkling_refreshlayout.setTargetView(message_recyclerView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                initAdapter();
                twinkling_refreshlayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

//                twinkling_refreshlayout.setEnableLoadmore(true);
            }
        });


    }

    @Override
    protected int initResourceLayout() {
        return R.layout.message_recycle;
    }


    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

        }
    }
}
