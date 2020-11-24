package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.national.qinggong.bean.CarBean;
import com.national.qinggong.bean.IndexGetBannerBean;
import com.national.qinggong.bean.LiveListBackBean;
import com.national.qinggong.bean.LiveRoomListBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CommonUtils;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.ToastUtilMsg;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import org.greenrobot.eventbus.Subscribe;

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

public class LivePlayListActivity extends AppCompatActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loop_viewpager_mz;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.lin_live)
    LinearLayout linLive;
    @BindView(R.id.lin_live_ann)
    LinearLayout linLiveAnn;
    @BindView(R.id.lin_live_back)
    LinearLayout linLiveBack;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.iv_top)
    ImageView iv_top;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    private ProgressLayout mProgressLayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private int postion = 1;
    private JoneBaseAdapter mJobDataAdapter;
    private JoneBaseAdapter mJobDataAdapter1;
    private JoneBaseAdapter mJobDataAdapter2;

    public static void open(Context context) {
        Intent intent = new Intent(context, LivePlayListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_play_list);
        ButterKnife.bind(this);
        loadData();

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#ffd20b17"));
                tv2.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#333333"));
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                loadData();
                postion = 1;
                twinkling_refreshlayout.startRefresh();
            }
        });
        linLiveAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setTextColor(Color.parseColor("#ffd20b17"));
                tv1.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#333333"));
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                loadDataAnn();
                postion = 2;
                twinkling_refreshlayout.startRefresh();
            }
        });
        linLiveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setTextColor(Color.parseColor("#ffd20b17"));
                tv1.setTextColor(Color.parseColor("#333333"));
                tv2.setTextColor(Color.parseColor("#333333"));
                v3.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                postion = 3;
                loadDataBack();
                twinkling_refreshlayout.startRefresh();
            }
        });
        getCount();
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
                    getLiveList20();
                } else if (postion == 2) {
                    getLiveList();
                } else if (postion == 3) {
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
                  //  getLiveList20();
                } else if (postion == 2) {
                    twinkling_refreshlayout.finishLoadmore();
                    twinkling_refreshlayout.setEnableLoadmore(true);
                 //   getLiveList();
                } else if (postion == 3) {
                    getLiveListBack();
                }
            }
        });
        twinkling_refreshlayout.startRefresh();

    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    private void getCount() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("type", "3");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .indexGetBanner(map)
                .compose(RequestManager.<IndexGetBannerBean>applyIoSchedulers())
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
                .subscribe(new Consumer<IndexGetBannerBean>() {
                               @Override
                               public void accept(IndexGetBannerBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1) {

                                       Glide.with(LivePlayListActivity.this).load(userInfo.getData().getBanner().get(0).getImage().getFile_path()).into((ImageView) iv_top);
                                       showBanner(loop_viewpager_mz, userInfo.getData().getBanner());
                                   } else {
                                       ToastUtilMsg.showToast(LivePlayListActivity.this, userInfo.getMsg());
                                   }

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void showBanner(BannerViewPager bannerViewPager, List<IndexGetBannerBean.DataBean.BannerBean> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout, getBanner, new PageHelperListener<IndexGetBannerBean.DataBean.BannerBean>() {

            @Override
            public void bindView(View view, final IndexGetBannerBean.DataBean.BannerBean data, int position) {
//                setText(view, R.id.loop_text, data.text);
                ImageView imageView = view.findViewById(R.id.loop_icon);
                GlideUtils.loadImageByUrl(data.getImage().getFile_path(), imageView);
            }

            @Override
            public void onItemClick(View view, IndexGetBannerBean.DataBean.BannerBean data, int position) {
                super.onItemClick(view, data, position);
//                Toast.makeText(_mActivity, data.url + " " + position, Toast.LENGTH_SHORT).show();
             /*   Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);*/
            }
        });
    }

    private void getLiveListBack() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", mIndex+"");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .live_roomBack(map)
                .compose(RequestManager.<LiveListBackBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveListBackBean>() {
                               @Override
                               public void accept(LiveListBackBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 && userInfo.getData().getData().size() > 0) {
                                       if (mIsRefresh) {
                                           mJobDataAdapter2.getData().clear();
                                           List<LiveListBackBean.DataBeanX.DataBean> getList = userInfo.getData().getData();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter2.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveListBackBean.DataBeanX.DataBean> getList = userInfo.getData().getData();
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
                                           mJobDataAdapter2.notifyDataSetChanged();emptyLayout.setVisibility(View.VISIBLE);
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

    private void getLiveList() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("status", "10");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveRoomList(map)
                .compose(RequestManager.<LiveRoomListBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveRoomListBean>() {
                               @Override
                               public void accept(final LiveRoomListBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 && userInfo.getData().getList().size() > 0) {
                                       if (mIsRefresh) {
                                           mJobDataAdapter1.getData().clear();
                                           List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter1.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           mJobDataAdapter1.getData().addAll(getList);
                                       }

                                      runOnUiThread(new Runnable() {
                                           @Override
                                           public void run() {
                                               List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                               if (getList.size() == 0) {
                                                   emptyLayout.setVisibility(View.VISIBLE);
                                                   emptyLayout.showEmpty();
                                                   recyclerView.setVisibility(View.GONE);
                                               } else {
                                                   recyclerView.setVisibility(View.VISIBLE);
                                                   emptyLayout.setVisibility(View.GONE);
                                               }

                                               mJobDataAdapter1.notifyDataSetChanged();
                                               twinkling_refreshlayout.setEnableLoadmore(true);
                                           }
                                       });


                                   } else {
                                       runOnUiThread(new Runnable() {
                                           @Override
                                           public void run() {
                                               if (mIsRefresh) {
                                                   mJobDataAdapter1.getData().clear();
                                                   mJobDataAdapter1.notifyDataSetChanged();
                                                   emptyLayout.setVisibility(View.VISIBLE);
                                                   emptyLayout.showEmpty();
                                               }
                                               twinkling_refreshlayout.setEnableLoadmore(true);
                                           }
                                       });
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void getLiveList20() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("status", "20");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveRoomList(map)
                .compose(RequestManager.<LiveRoomListBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveRoomListBean>() {
                               @Override
                               public void accept(LiveRoomListBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1 && userInfo.getData().getList().size() > 0) {
                                       if (mIsRefresh) {
                                           mJobDataAdapter.getData().clear();
                                           List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           if (getList != null && getList.size() > 0) {
                                               mJobDataAdapter.getData().addAll(getList);
                                           }
                                       } else {
                                           List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();
                                           mJobDataAdapter.getData().addAll(getList);
                                       }
                                       List<LiveRoomListBean.DataBean.ListBean> getList = userInfo.getData().getList();

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

    public void loadData() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter = new JoneBaseAdapter<LiveRoomListBean.DataBean.ListBean>(recyclerView, R.layout.item_live_list_play) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveRoomListBean.DataBean.ListBean model) {
                helper.setText(R.id.tv_content, model.getName() + "");
                helper.setText(R.id.tv_name, model.getAnchor().getUser().getNickName() + "");


                Glide.with(LivePlayListActivity.this).load(model.getFile_path()).into((ImageView) helper.getView(R.id.image));
                Glide.with(LivePlayListActivity.this).load(model.getAnchor().getUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.image_head));
            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter != null) {
                    LiveRoomListBean.DataBean.ListBean currentbean = (LiveRoomListBean.DataBean.ListBean) mJobDataAdapter.getItem(position);
                    LivePalyActivity.open(LivePlayListActivity.this, currentbean.getId() + "", currentbean.getLive_url()
                            , currentbean.getAnchor().getUser().getAvatarUrl(), "0", currentbean.getLike_num() + "", currentbean.getAnchor().getUser().getNickName(), currentbean.getAnchor().getId() + "");
                }

            }
        });
        recyclerView.setAdapter(mJobDataAdapter);

    }


    public void loadDataAnn() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter1 = new JoneBaseAdapter<LiveRoomListBean.DataBean.ListBean>(recyclerView, R.layout.item_live_list_play_ann) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveRoomListBean.DataBean.ListBean model) {
                //2020-10-17 17:22
                String times1[]=model.getCreate_time().split(" ");
                String times[]=times1[0].split("-");
                helper.setText(R.id.tv_time, times[2] + "-"+times[1]+"-"+times[0]+" "+times1[1]);
                //helper.setText(R.id.tv_time, model.getStart_time().getText() + "");
                helper.setText(R.id.tv_name, model.getName() + "");
                helper.setText(R.id.tv_user_name, model.getAnchor().getUser().getNickName() + "");
                ViewGroup.LayoutParams layoutParams = helper.getView(R.id.image).getLayoutParams();
                layoutParams.width= (DensityUtil.getScreenWidth(LivePlayListActivity.this));
                layoutParams.height= (DensityUtil.getScreenWidth(LivePlayListActivity.this));
                helper.getView(R.id.image).setLayoutParams(layoutParams);

                Glide.with(LivePlayListActivity.this).load(model.getFile_path()).into((ImageView) helper.getView(R.id.image));
                Glide.with(LivePlayListActivity.this).load(model.getAnchor().getUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.image_head));
            }
        };
        mJobDataAdapter1.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter1 != null) {
                    LiveRoomListBean.DataBean.ListBean currentbean = (LiveRoomListBean.DataBean.ListBean) mJobDataAdapter1.getItem(position);
                    LiveAnchorDetailActivity.open(LivePlayListActivity.this, currentbean.getAnchor_id() + "");
                }

            }
        });
        recyclerView.setAdapter(mJobDataAdapter1);
    }

    public void loadDataBack() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mJobDataAdapter2 = new JoneBaseAdapter<LiveListBackBean.DataBeanX.DataBean>(recyclerView, R.layout.item_live_list_play_back) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveListBackBean.DataBeanX.DataBean model) {
                //Log.i("test","时间1="+model.getStart_time());
                //Log.i("test","时间2="+CommonUtils.time2(model.getStart_time()) + "");

                //2020-10-17 17:22
                String times1[]=model.getCreate_time().split(" ");
                String times[]=times1[0].split("-");
                helper.setText(R.id.tv_time, times[2] + "-"+times[1]+"-"+times[0]+" "+times1[1]);

                //helper.setText(R.id.tv_time, CommonUtils.time2(model.getStart_time()) + "");
                helper.setText(R.id.tv_content, model.getName() + "");
                // helper.setText(R.id.tv_user_name, model.getAnchor().getUser().getNickName() + "");
                Glide.with(LivePlayListActivity.this).load(model.getFile_path()).into((ImageView) helper.getView(R.id.image));
                //   Glide.with(LivePlayListActivity.this).load(model.getAnchor().getUser().getAvatarUrl()).into((ImageView) helper.getView(R.id.image_head));
            }
        };
        mJobDataAdapter2.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter2 != null) {
                    LiveListBackBean.DataBeanX.DataBean currentbean = (LiveListBackBean.DataBeanX.DataBean) mJobDataAdapter2.getItem(position);
                    PlayVideoActivity.open(LivePlayListActivity.this, currentbean.getVideo_url());
                }

            }
        });

        recyclerView.setAdapter(mJobDataAdapter2);
    }
}
