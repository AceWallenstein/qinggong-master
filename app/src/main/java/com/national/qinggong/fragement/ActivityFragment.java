package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.PlatformActivityArticleContract;
import com.national.qinggong.presenter.ActivityArticlePresenter;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * 活动列表
 * */
public class ActivityFragment extends BaseFragment implements PlatformActivityArticleContract.View {
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
    Unbinder unbinder;

    public static ActivityFragment newInstance() {
        Bundle args = new Bundle();
        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private int mIndex = 1;
    private boolean mIsRefresh = true;
    @BindView(R.id.message_recyclerView)
    RecyclerView activity_recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<ActivityArticleBean.DataBean.ListBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;

    @Override
    protected void initdata() {
        tvTitle.setText("活动列表");
        initRefresh();
        mJobDataAdapter = new JoneBaseAdapter<ActivityArticleBean.DataBean.ListBean>(activity_recyclerView, R.layout.activity_fragment) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ActivityArticleBean.DataBean.ListBean model) {
//                helper.setText(R.id.message_content, model.getMsg() + "");
                if (model.getPoster() != null) {
                    GlideUtils.loadImageByUrl(model.getPoster(), (ImageView) helper.getView(R.id.image));
                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        activity_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        activity_recyclerView.setAdapter(mJobDataAdapter);
        List<HomeBean> datas = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            HomeBean homeBean = new HomeBean();
//            homeBean.setMsg("测试" + i);
//            datas.add(homeBean);
//        }
//        mJobDataAdapter.setData(datas);
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//                ActivityArticleBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
//
//                WebviewActivity.newIntance(_mActivity, currentbean, "活动");
                String getParam = mJobDataAdapter.getItem(position).getId() + "";
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "活动详情接口");
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        getuserInfo("1", "20");
    }

    /*
     * ?page=1&limit=20
     * */
    private void getuserInfo(int page, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page+"");
        map.put("limit", limit);
        getPresenter().getArticleInfo(map);
    }

    @Override
    protected ActivityArticlePresenter getPresenter() {
        return new ActivityArticlePresenter(_mActivity, ActivityFragment.this);
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
        twinkling_refreshlayout.setTargetView(activity_recyclerView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                initAdapter();
                twinkling_refreshlayout.finishRefreshing();
                mIsRefresh = true;
                mIndex = 1;
                getuserInfo(mIndex, "20");
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getuserInfo(mIndex, "20");
//                twinkling_refreshlayout.setEnableLoadmore(true);
            }
        });
        twinkling_refreshlayout.startRefresh();


    }


    @Override
    protected int getLayoutId() {
        return R.layout.message_recycle;
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;

        }
    }


    @Override
    public void showToast(String content) {

    }

    @Override
    public void refreshArticleInfo(ActivityArticleBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                ActivityArticleBean.DataBean getData = userInfo.getData();
                  if (getData!=null){
                      if (mIsRefresh) {
                          mJobDataAdapter.getData().clear();
                          List<ActivityArticleBean.DataBean.ListBean> getList = getData.getList();
                          if (getList != null && getList.size() > 0) {
                              mJobDataAdapter.getData().addAll(getList);
                          }
                      } else {
                          List<ActivityArticleBean.DataBean.ListBean> getList = getData.getList();
                          mJobDataAdapter.getData().addAll(getList);
                      }
                      mJobDataAdapter.notifyDataSetChanged();
                      twinkling_refreshlayout.setEnableLoadmore(true);
                  }else {
                      if (mIsRefresh) {
//                        setViewViable(false);
                          mJobDataAdapter.getData().clear();
                          mJobDataAdapter.notifyDataSetChanged();
                      }
                      twinkling_refreshlayout.setEnableLoadmore(true);
                  }

            }
        }
    }

    @Override
    public void refreshPostfinally() {
        if (twinkling_refreshlayout == null) {
            return;
        }
        if (mIsRefresh) {
            twinkling_refreshlayout.finishRefreshing();
        } else {
            twinkling_refreshlayout.finishLoadmore();
        }
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

}
