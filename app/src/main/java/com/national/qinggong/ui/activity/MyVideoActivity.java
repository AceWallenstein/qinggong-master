package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.national.qinggong.bean.LiveVideoListBean;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyVideoActivity extends BaseActivity {

    public static void open(Context context) {
        Intent intent = new Intent(context, MyVideoActivity.class);
        context.startActivity(intent);
    }

    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter mJobDataAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_ok)
    TextView tv_ok;
    @BindView(R.id.tv_video_count)
    TextView tv_video_count;
    @BindView(R.id.rl_back)
    LinearLayout rl_back;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_my_video;
    }

    @Override
    protected void initdata() {
        loadDataAnn();
        initRefresh();
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddVideoActivity.open(MyVideoActivity.this);
            }
        });
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void getLiveListBack() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveVideoList(map)
                .compose(RequestManager.<LiveVideoListBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveVideoListBean>() {
                               @Override
                               public void accept(LiveVideoListBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 && userInfo.getData().getList().getData().size() > 0) {
                                       tv_video_count.setText("My Video（" + userInfo.getData().getList().getTotal() + "）");
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           List<LiveVideoListBean.DataBeanX.ListBean.DataBean> getList = userInfo.getData().getList().getData();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveVideoListBean.DataBeanX.ListBean.DataBean> getList = userInfo.getData().getList().getData();
                                           mJobDataAdapter.getData().addAll(getList);
                                       }
                                       List<LiveVideoListBean.DataBeanX.ListBean.DataBean> getList = userInfo.getData().getList().getData();
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


    private void getLiveListRemove(String id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("id", id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveVideoDelete(map)
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
                                   if (userInfo.getCode() == 1) {
                                       ToastUtilMsg.showToast(MyVideoActivity.this, userInfo.getMsg());
                                       if (twinkling_refreshlayout != null) {
                                           twinkling_refreshlayout.startRefresh();
                                       }
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
        mJobDataAdapter = new JoneBaseAdapter<LiveVideoListBean.DataBeanX.ListBean.DataBean>(recyclerView, R.layout.item_live_video_list) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveVideoListBean.DataBeanX.ListBean.DataBean model) {

                helper.setText(R.id.tv_name, model.getTitle());
                helper.setText(R.id.tv_sku, model.getUpdate_time());
                helper.setText(R.id.tv_seconds, model.getTimes() + "Seconds");
                Glide.with(MyVideoActivity.this).load(model.getImage().getFile_path()).into(helper.getImageView(R.id.image));
                helper.setItemChildClickListener(R.id.btn_del);
                if (model.getTopic_id() > 0) {
                    helper.setText(R.id.tv_liv_room, "Cancel Live");
                    helper.setBackgroundRes(R.id.tv_liv_room,R.drawable.bg_home_live_black_5);
                } else {
                    helper.setText(R.id.tv_liv_room, "Upload Live Room");
                    helper.setBackgroundRes(R.id.tv_liv_room,R.drawable.bg_home_live_red_5);
                }
                helper.setItemChildClickListener(R.id.tv_liv_room);
            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                LiveVideoListBean.DataBeanX.ListBean.DataBean dataBean = (LiveVideoListBean.DataBeanX.ListBean.DataBean) mJobDataAdapter.getData().get(position);
                PlayVideoActivity.open(MyVideoActivity.this, dataBean.getFile_path());
            }
        });
        mJobDataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                LiveVideoListBean.DataBeanX.ListBean.DataBean dataBean = (LiveVideoListBean.DataBeanX.ListBean.DataBean) mJobDataAdapter.getData().get(position);

                switch (childView.getId()) {
                    case R.id.btn_del:
                        getLiveListRemove(dataBean.getId() + "");
                        break;
                    case R.id.tv_liv_room:
                        videoId = dataBean.getId() + "";
                        if (dataBean.getTopic_id() > 0) {
                            cancelVideo(videoId);
                        } else {
                            MyBroadcastActivity2.open(MyVideoActivity.this);
                        }
                        break;
                }
            }
        });
        recyclerView.setAdapter(mJobDataAdapter);
    }

    String videoId = "";

    private void tv_liv_room(String id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("topic_id", id);
        map.put("id", videoId);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .choseVideo(map)
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
                                   if (userInfo.getCode() == 1) {
                                       ToastUtilMsg.showToast(MyVideoActivity.this, userInfo.getMsg());
                                       if (twinkling_refreshlayout != null) {
                                           twinkling_refreshlayout.startRefresh();
                                       }
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void cancelVideo(String id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("id", videoId);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .cancelVideo(map)
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
                                   if (userInfo.getCode() == 1) {
                                       ToastUtilMsg.showToast(MyVideoActivity.this, userInfo.getMsg());
                                       if (twinkling_refreshlayout != null) {
                                           twinkling_refreshlayout.startRefresh();
                                       }
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 200 && data != null) {
            tv_liv_room(data.getStringExtra("room_id"));
        }
    }
}
