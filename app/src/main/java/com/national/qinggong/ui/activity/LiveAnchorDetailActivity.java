package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.LiveAnchorDetailBean;
import com.national.qinggong.bean.LiveGetVideobyAnchorBean;
import com.national.qinggong.bean.LiveListBackBean;
import com.national.qinggong.bean.LiveListBackBean2;
import com.national.qinggong.bean.LiveRoomListBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.CommonUtils;
import com.national.qinggong.util.DensityUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class LiveAnchorDetailActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_search_lin)
    LinearLayout titleSearchLin;
    @BindView(R.id.target_tv)
    TextView targetTv;
    @BindView(R.id.target_rela)
    RelativeLayout targetRela;
    @BindView(R.id.lin_content)
    LinearLayout linContent;
    @BindView(R.id.lin_title)
    LinearLayout linTitle;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nickName)
    TextView nickName;
    @BindView(R.id.Follow)
    TextView Follow;
    @BindView(R.id.Chat)
    TextView Chat;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.follow_count)
    TextView followCount;
    @BindView(R.id.fans_count)
    TextView fansCount;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.lin_live)
    LinearLayout linLive;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.lin_live_ann)
    LinearLayout linLiveAnn;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    private ProgressLayout mProgressLayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private int postion = 1;
    private JoneBaseAdapter mJobDataAdapter2;

    public static void open(Context context, String anchor_id) {
        Intent intent = new Intent(context, LiveAnchorDetailActivity.class);
        intent.putExtra("anchor_id", anchor_id);
        context.startActivity(intent);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_live_anchor_detail;
    }

    @Override
    protected void initdata() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Live_AnchorDetail(getIntent().getStringExtra("anchor_id"));
        linLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#ffd20b17"));
                tv2.setTextColor(Color.parseColor("#333333"));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                postion=1;
                loadData(listGoods);
                twinkling_refreshlayout.startRefresh();
            }
        });
        linLiveAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setTextColor(Color.parseColor("#ffd20b17"));
                tv1.setTextColor(Color.parseColor("#333333"));
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                postion=2;
                loadDataBack();
                twinkling_refreshlayout.startRefresh();
            }
        });
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 34);
                Bundle_about.putString("user_id", user_id);
                PlatformForFragmentActivity.newInstance(LiveAnchorDetailActivity.this, Bundle_about);
            }
        });
        Follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Is_fans=!Is_fans;
                if(Is_fans){
                    livefocus();
                }else{
                    liveCancelFocus();
                }
            }
        });
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
                if (postion == 1) {
                    loadData(listGoods);
                } else if (postion == 2) {
                    getLiveListBack();
                }
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                if (postion == 1) {
                    twinkling_refreshlayout.finishLoadmore();
                    twinkling_refreshlayout.setEnableLoadmore(true);
                } else if (postion == 2) {
                    getLiveListBack();
                }
            }
        });
        twinkling_refreshlayout.startRefresh();

    }


    String user_id="";
    boolean Is_fans=false;
    private void Live_AnchorDetail(String anchor_id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("anchor_id", anchor_id);
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_AnchorDetail(map)
                .compose(RequestManager.<LiveAnchorDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveAnchorDetailBean>() {
                               @Override
                               public void accept(LiveAnchorDetailBean userInfo) throws Exception {
                                listGoods= userInfo.getData().getGoods();
                                   loadData(listGoods);
                                   followCount.setText(userInfo.getData().getFans()+"");
                                   fansCount.setText(userInfo.getData().getVisits()+"");
                                   nickName.setText(userInfo.getData().getUser().getNickName());
                                   user_id=  userInfo.getData().getUser().getUser_id()+"";
                                   Is_fans= userInfo.getData().isIs_fans();
                                   if(Is_fans){
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg_2);
                                   }else{
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg);
                                   }
                                   Follow.setPadding(DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4),DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4));
                                   Glide.with(LiveAnchorDetailActivity.this).load(userInfo.getData().getUser().getAvatarUrl()).into(image);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }
    List<LiveAnchorDetailBean.DataBean.GoodsBean> listGoods= new ArrayList<>();


    public void loadData(List<LiveAnchorDetailBean.DataBean.GoodsBean> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final JoneBaseAdapter mJobDataAdapter = new JoneBaseAdapter<LiveAnchorDetailBean.DataBean.GoodsBean>(recyclerView, R.layout.item_hot_products) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveAnchorDetailBean.DataBean.GoodsBean model) {

                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNumber() + "");
                CornerTransform transformation = new CornerTransform(LiveAnchorDetailActivity.this, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(LiveAnchorDetailActivity.this).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));
            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter != null) {
                    LiveAnchorDetailBean.DataBean.GoodsBean currentbean = (LiveAnchorDetailBean.DataBean.GoodsBean) mJobDataAdapter.getItem(position);
                    if (currentbean != null) {
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id", getGoods_id + "");
                        PlatformForFragmentActivity.newInstance(LiveAnchorDetailActivity.this, Bundle_about);
                    }
                }
            }
        });
        mJobDataAdapter.setData(list);
        recyclerView.setAdapter(mJobDataAdapter);
        if (twinkling_refreshlayout == null) {
            return;
        }
        if (mIsRefresh) {
            twinkling_refreshlayout.finishRefreshing();
        } else {
            twinkling_refreshlayout.finishLoadmore();
        }
    }
    private void getLiveListBack() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("anchor_id", getIntent().getStringExtra("anchor_id"));
        map.put("page",mIndex+"");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_getVideobyAnchor(map)
                .compose(RequestManager.<LiveListBackBean2>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveListBackBean2>() {
                               @Override
                               public void accept(LiveListBackBean2 userInfo) throws Exception {

                                   if (userInfo.getCode() == 1 && userInfo.getData().getData().size() > 0) {
                                       if (mIsRefresh) {
                                           mJobDataAdapter2.getData().clear();
                                           List<LiveListBackBean2.DataBeanX.DataBean> getList = userInfo.getData().getData();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter2.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveListBackBean2.DataBeanX.DataBean> getList = userInfo.getData().getData();
                                           mJobDataAdapter2.getData().addAll(getList);
                                       }

                                       if (userInfo.getData().getTotal() == 0) {
                                           emptyLayout.setVisibility(View.VISIBLE);
                                           emptyLayout.showEmpty();
                                           recyclerView.setVisibility(View.GONE);
                                       } else {
                                           recyclerView.setVisibility(View.VISIBLE);
                                           emptyLayout.setVisibility(View.GONE);
                                       }
                                       mJobDataAdapter2.notifyDataSetChanged();
                                       twinkling_refreshlayout.setEnableLoadmore(true);
                                   } else {
                                       if (mIsRefresh) {
                                           mJobDataAdapter2.getData().clear();
                                           mJobDataAdapter2.notifyDataSetChanged();
                                           emptyLayout.setVisibility(View.VISIBLE);
                                           emptyLayout.showEmpty();
                                       }
                                       twinkling_refreshlayout.setEnableLoadmore(true);
                                   }

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   throwable.getMessage();
                               }
                           }
                );
    }


    private void livefocus() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("anchor_id", getIntent().getStringExtra("anchor_id"));
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .livefocus(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object userInfo) throws Exception {
                                   if(Is_fans){
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg_2);
                                   }else{
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg);
                                   }

                                   Follow.setPadding(DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4),DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void liveCancelFocus() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("anchor_id", getIntent().getStringExtra("anchor_id"));
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveCancelFocus(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object userInfo) throws Exception {
                                   if(Is_fans){
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg_2);
                                   }else{
                                       Follow.setBackgroundResource(R.drawable.botton_login_bg);
                                   }
                                   Follow.setPadding(DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4),DensityUtil.dip2px(LiveAnchorDetailActivity.this,15),DensityUtil.dip2px(LiveAnchorDetailActivity.this,4));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    public void loadDataBack() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter2 = new JoneBaseAdapter<LiveListBackBean2.DataBeanX.DataBean>(recyclerView, R.layout.item_live_list_play_back) {
           @Override
           public void fillItemData(BGAViewHolderHelper helper, int position, LiveListBackBean2.DataBeanX.DataBean model) {
               helper.setText(R.id.tv_time, model.getStart_time().getText() );
               helper.setText(R.id.tv_content, model.getName() + "");
               // helper.setText(R.id.tv_user_name, model.getAnchor().getUser().getNickName() + "");
               Glide.with(LiveAnchorDetailActivity.this).load(model.getFile_path()).into((ImageView) helper.getView(R.id.image));
               //   Glide.with(LivePlayListActivity.this).load(model.getAnchor().getUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.image_head));
           }
       };
        mJobDataAdapter2.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter2 != null) {
                    LiveListBackBean.DataBeanX.DataBean currentbean = (LiveListBackBean.DataBeanX.DataBean) mJobDataAdapter2.getItem(position);
                    PlayVideoActivity.open(LiveAnchorDetailActivity.this,currentbean.getVideo_url());
                }

            }
        });
        recyclerView.setAdapter(mJobDataAdapter2);
    }
}
