package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ChatMessageEvent;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.MyMessageContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.MyMessagePresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

//MyMessagePresenter
public class FragmentRootMesage extends BaseFragment implements MyMessageContract.View {
    @BindView(R.id.message_recycleview)
    RecyclerView messageRecycleview;
    Unbinder unbinder;
    private JoneBaseAdapter<HomeBean> mJobDataAdapter;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter<MyMessageBean.DataBeanX.ListBean.DataBean> rightDataAdapter;

    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;

    public static FragmentRootMesage newInstance() {
        Bundle args = new Bundle();
//        args.putBoolean(ISSHOWIING, isShow);
        FragmentRootMesage fragment = new FragmentRootMesage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected MyMessagePresenter getPresenter() {
        return new MyMessagePresenter(_mActivity, FragmentRootMesage.this);
    }


    private void getMessageListInfo(int page) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", page + "");
        map.put("token", getToken);
        getPresenter().getMessageList(map);
    }

    private void initRefresh() {
        mProgressLayout = new ProgressLayout(_mActivity);
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
                getMessageListInfo(mIndex);


            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getMessageListInfo(mIndex);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (twinkling_refreshlayout != null) {
                twinkling_refreshlayout.startRefresh();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (twinkling_refreshlayout != null) {
            twinkling_refreshlayout.startRefresh();
        }
    }

    @Override
    protected void initdata() {
        newMessageData();
        initRefresh();
    }

    public void newMessageData() {
        rightDataAdapter = new JoneBaseAdapter<MyMessageBean.DataBeanX.ListBean.DataBean>(messageRecycleview, R.layout.item_root_message) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, MyMessageBean.DataBeanX.ListBean.DataBean model) {
                helper.setText(R.id.username, model.getNickName() + "");
                helper.setText(R.id.content, model.getContent() + "");
                if (model.getNew_num() > 0) {
                    helper.setVisibility(R.id.xiaoxi_num, View.VISIBLE);
                } else {
                    helper.setVisibility(R.id.xiaoxi_num, View.GONE);
                }
                helper.setText(R.id.xiaoxi_num, model.getNew_num() + "");


                //Today  Yseterday
                Log.i("test", "聊天列表时间：=" + time(model.getCreate_time() * 1000));
                //2020-10-17 17:22
                //String times1[]=(time(model.getCreate_time()*1000)).split(" ");
                //String times[]=times1[0].split("-");
                // helper.setText(R.id.time_create, times[2] + "-"+times[1]+"-"+times[0]+" "+times1[1]);

                if (time(model.getCreate_time() * 1000).contains("Today") || time(model.getCreate_time() * 1000).contains("Yseterday")) {
                    helper.setText(R.id.time_create, time(model.getCreate_time() * 1000));
                } else {
                    String times1[] = (time(model.getCreate_time() * 1000)).split(" ");
                    String times[] = times1[0].split("-");
                    helper.setText(R.id.time_create, times[2] + "-" + times[1] + "-" + times[0] + " " + times1[1]);
                }


                //helper.setText(R.id.time_create, time(model.getCreate_time()*1000));

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(true, true, true, true);
                Glide.with(_mActivity).load(model.getAvatarUrl())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.user_head_image));
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        messageRecycleview.setLayoutManager(layoutManager);
        messageRecycleview.setAdapter(rightDataAdapter);
        rightDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                MyMessageBean.DataBeanX.ListBean.DataBean currentbean = rightDataAdapter.getItem(position);
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 34);
                Bundle_about.putString("user_id", currentbean.getTo_user_id() + "");
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
            }
        });

    }

    public static String time(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat hose = new SimpleDateFormat("HH:mm");
        String res = "";
        try {
            Date date = new Date(time);
            if (isToday(time)) {
                res = "Today " + hose.format(date);
            } else if (isYesterday(time)) {
                res = "Yseterday " + hose.format(date);
            } else {
                res = simpleDateFormat.format(date);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 是否为今天
     */
    public static boolean isToday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 0;
        }
        return false;
    }

    /**
     * 是否为昨天
     */
    public static boolean isYesterday(Long timeStamp) {
        Calendar todayCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        if (calendar.get(Calendar.YEAR) == (todayCalendar.get(Calendar.YEAR))) {
            int diffDay = todayCalendar.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
            return diffDay == 1;
        }
        return false;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_root_message;
    }


    @Override
    public void refreshMessagePage(MyMessageBean homeBean) {
        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    EventBus.getDefault().post(new ChatMessageEvent(homeBean.getData().getNew_message_num()));
                    MyMessageBean.DataBeanX getData = homeBean.getData();
                    if (getData != null) {
                        if (mIsRefresh) {
                            rightDataAdapter.getData().clear();
                            List<MyMessageBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                rightDataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<MyMessageBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            rightDataAdapter.getData().addAll(getList);
                        }


                        int getTotal = getData.getList().getTotal();
                        if (getTotal == 0) {
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyLayout.showEmpty();
                            messageRecycleview.setVisibility(View.GONE);
                        } else {
                            messageRecycleview.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        }
                        rightDataAdapter.notifyDataSetChanged();
                        twinkling_refreshlayout.setEnableLoadmore(true);

                    } else {
                        if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            messageRecycleview.setVisibility(View.GONE);
                        }
                        if (mIsRefresh) {
                            rightDataAdapter.getData().clear();
                            rightDataAdapter.notifyDataSetChanged();
                        }
                        twinkling_refreshlayout.setEnableLoadmore(true);
                    }
                }

            }
        }
    }

    @Override
    public void stockFinish() {
        if (twinkling_refreshlayout == null) {
            return;
        }
        if (mIsRefresh) {
            twinkling_refreshlayout.finishRefreshing();
        } else {
            twinkling_refreshlayout.finishLoadmore();
        }
    }

    @Override
    public void showToast(String content) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {
        getActivity().finish();
    }
}
