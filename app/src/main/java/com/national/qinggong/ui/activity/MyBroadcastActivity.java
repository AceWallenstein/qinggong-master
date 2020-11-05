package com.national.qinggong.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.national.qinggong.bean.BaseBean;
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
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyBroadcastActivity extends BaseActivity {
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
        Intent intent = new Intent(context, MyBroadcastActivity.class);
        context.startActivity(intent);
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
                                       tv_live.setText("Live Broadcast（"+userInfo.getData().getList().size()+"）");
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           List<LiveRoomTopicListBean.DataBean.ListBean> getList =userInfo.getData().getList();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveRoomTopicListBean.DataBean.ListBean> getList =userInfo.getData().getList();
                                           mJobDataAdapter.getData().addAll(getList);
                                       }
                                       List<LiveRoomTopicListBean.DataBean.ListBean> getList =userInfo.getData().getList();
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
    private void live_roomdelTopic(String topic_id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("topic_id", topic_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .live_roomdelTopic(map)
                .compose(RequestManager.<BaseBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Consumer<BaseBean>() {
                               @Override
                               public void accept(BaseBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 ) {
                                       twinkling_refreshlayout.startRefresh();
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

                //2020-10-17 17:22
                String times1[]=model.getUpdate_time().split(" ");
                String times[]=times1[0].split("-");
                helper.setText(R.id.tv_sku, times[2] + "-"+times[1]+"-"+times[0]+" "+times1[1]);

                //helper.setText(R.id.tv_sku, model.getUpdate_time());

                helper.setVisibility(R.id.image_del, View.VISIBLE);
                if(model.getIs_check()==1){
                    helper.setText(R.id.tv_btn, "Start");

                    helper.setBackgroundRes(R.id.tv_btn,R.drawable.bg_home_live_red_5);
                }else{
                    helper.setText(R.id.tv_btn, "Under review");
                    helper.setBackgroundRes(R.id.tv_btn,R.drawable.bg_home_live_black_5);
                }
                helper.setItemChildClickListener(R.id.image_del);
                Glide.with(MyBroadcastActivity.this).load(model.getFile_path()).into(helper.getImageView(R.id.image));

            }
        };
        mJobDataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {

                switch (childView.getId()){
                    case R.id.image_del:
                        LiveRoomTopicListBean.DataBean.ListBean bean= (LiveRoomTopicListBean.DataBean.ListBean) mJobDataAdapter.getData().get(position);
                        live_roomdelTopic(bean.getId()+"");
                        break;
                }
            }
        });
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, final int position) {

               new RxPermissions(MyBroadcastActivity.this)
                .request(
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                ).subscribe(new Consumer<Boolean>() {
                   @Override
                   public void accept(Boolean aBoolean) throws Exception {

                       LiveRoomTopicListBean.DataBean.ListBean listBean = (LiveRoomTopicListBean.DataBean.ListBean) mJobDataAdapter.getData().get(position);
                       if(listBean.getIs_check()==1){
                           LiveActivity.open(MyBroadcastActivity.this,listBean.getId()+"",listBean.getAnchor().getUser().getAvatarUrl(),"0",listBean.getLike_num()+"",listBean.getAnchor().getUser().getNickName());
                       }else{
                           ToastUtilMsg.showToast(MyBroadcastActivity.this,"Under review");
                       }

                   }
               });
            }
        });

        recyclerView.setAdapter(mJobDataAdapter);
    }
}
