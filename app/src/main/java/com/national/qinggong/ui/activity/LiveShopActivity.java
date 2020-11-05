package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.LiveRoomGoodsListBean;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class LiveShopActivity extends BaseActivity {

    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter mJobDataAdapter;

    public static void open(Context context) {
        Intent intent = new Intent(context, LiveShopActivity.class);
        context.startActivity(intent);
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_back)
    LinearLayout rl_back;

    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_live_shop;
    }

    private boolean mIsRefresh = true;
    private int mIndex = 1;

    @Override
    protected void initdata() {
        loadDataAnn();
        initRefresh();
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getLiveListBack() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .live_roomGoodsList(map)
                .compose(RequestManager.<LiveRoomGoodsListBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (twinkling_refreshlayout == null) {
                            return;
                        }
                        if (mIsRefresh) {
                            twinkling_refreshlayout.finishRefreshing();
                        } else {
                            twinkling_refreshlayout.finishLoadmore();
                        }
                    }
                })
                .subscribe(new Consumer<LiveRoomGoodsListBean>() {
                               @Override
                               public void accept(LiveRoomGoodsListBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 && userInfo.getData().size() > 0) {
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           List<LiveRoomGoodsListBean.DataBean> getList = userInfo.getData();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveRoomGoodsListBean.DataBean> getList = userInfo.getData();
                                           mJobDataAdapter.getData().addAll(getList);
                                       }
                                       List<LiveRoomGoodsListBean.DataBean> getList = userInfo.getData();
                                       if (getList.size() == 0) {
                                           emptyLayout.setVisibility(View.VISIBLE);
                                           emptyLayout.showEmpty();
                                           recyclerView.setVisibility(View.GONE);
                                       } else {
                                           recyclerView.setVisibility(View.VISIBLE);
                                           emptyLayout.setVisibility(View.GONE);
                                       }
                                       mJobDataAdapter.notifyDataSetChanged();
                                       twinkling_refreshlayout.setEnableLoadmore(true);
                                   } else {
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           mJobDataAdapter.notifyDataSetChanged();
                                           emptyLayout.setVisibility(View.VISIBLE);
                                           emptyLayout.showEmpty();
                                       }
                                       twinkling_refreshlayout.setEnableLoadmore(true);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
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
        twinkling_refreshlayout.setTargetView(null);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mIsRefresh = true;
                mIndex = 1;
                getLiveListBack();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                twinkling_refreshlayout.finishLoadmore();
                twinkling_refreshlayout.setEnableLoadmore(true);

            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    public void loadDataAnn() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter = new JoneBaseAdapter<LiveRoomGoodsListBean.DataBean>(recyclerView, R.layout.item_live_select_shop) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveRoomGoodsListBean.DataBean model) {

                helper.setText(R.id.tv_name, model.getGoods_name());
                helper.setText(R.id.tv_sku, model.getNumber());
                helper.setVisibility(R.id.tv_select, View.GONE);
                Glide.with(LiveShopActivity.this).load(model.getFile_path()).into(helper.getImageView(R.id.image));

            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

            }
        });
        recyclerView.setAdapter(mJobDataAdapter);
    }

}
