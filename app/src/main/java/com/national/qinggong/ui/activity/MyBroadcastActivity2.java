package com.national.qinggong.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.LiveRoomTopicListBean;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyBroadcastActivity2 extends BaseActivity {
    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter mJobDataAdapter;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.tv_live)
    TextView tv_live;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;

    public static void open(Context context) {
        Intent intent = new Intent(context, MyBroadcastActivity2.class);
        ((Activity) context).startActivityForResult(intent, 200);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_my_broadcast;
    }

    @Override
    protected void initdata() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadDataAnn();

        initRefresh();
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
                getLiveList();
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


    private void getLiveList() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .live_roomTopicList(map)
                .compose(RequestManager.<LiveRoomTopicListBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
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
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Consumer<LiveRoomTopicListBean>() {
                               @Override
                               public void accept(LiveRoomTopicListBean userInfo) throws Exception {

                                   if (userInfo.getCode() == 1 && userInfo.getData().getList().size() > 0) {
                                       tv_live.setText("Live Broadcast（" + userInfo.getData().getList().size() + "）");
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           List<LiveRoomTopicListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveRoomTopicListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           mJobDataAdapter.getData().addAll(getList);
                                       }
                                       List<LiveRoomTopicListBean.DataBean.ListBean> getList = userInfo.getData().getList();
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

    public void loadDataAnn() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter = new JoneBaseAdapter<LiveRoomTopicListBean.DataBean.ListBean>(recyclerView, R.layout.item_live_list_start) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveRoomTopicListBean.DataBean.ListBean model) {

                helper.setText(R.id.tv_name, model.getName());
                helper.setText(R.id.tv_sku, model.getUpdate_time());
                helper.setText(R.id.tv_btn, "Choose");
                Glide.with(MyBroadcastActivity2.this).load(model.getFile_path()).into(helper.getImageView(R.id.image));

            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, final int position) {
                LiveRoomTopicListBean.DataBean.ListBean listBean = (LiveRoomTopicListBean.DataBean.ListBean) mJobDataAdapter.getData().get(position);
                int id = listBean.getId();
                Intent intent = new Intent();
                intent.putExtra("room_id", id+"");
                setResult(200, intent);
                finish();
            /*   new RxPermissions(MyBroadcastActivity2.this)
                .request(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                ).subscribe(new Consumer<Boolean>() {
                   @Override
                   public void accept(Boolean aBoolean) throws Exception {

                       LiveRoomTopicListBean.DataBean.ListBean listBean = (LiveRoomTopicListBean.DataBean.ListBean) mJobDataAdapter.getData().get(position);
                       if(listBean.getIs_check()==1){
                           LiveActivity.open(MyBroadcastActivity2.this,listBean.getId()+"",listBean.getAnchor().getUser().getAvatarUrl(),"0",listBean.getLike_num()+"",listBean.getAnchor().getUser().getNickName());
                       }else{
                           ToastUtilMsg.showToast(MyBroadcastActivity2.this,"Under review");
                       }

                   }
               });*/
            }
        });

        recyclerView.setAdapter(mJobDataAdapter);
    }
}
