package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.IntegralMingXiBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.IntegralMingxiContract;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.IntegralMingxiPresenter;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*item_integral_mingxi.xml
 * 积分明细*/
public class FragmentIntegralMingxi extends BaseFragment implements IntegralMingxiContract.View {
    @BindView(R.id.new_recycleview)
    RecyclerView newRecycleview;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;

    @BindView(R.id.mingxi_jife)
    TextView mingxi_jife;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;


    private boolean mIsRefresh = true;
    private int mIndex = 1;

    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter<IntegralMingXiBean.DataBeanX.ListBean.DataBean> newArrival_DataAdapter;
    private String search_name = "";

    public static FragmentIntegralMingxi newInstance() {
        Bundle args = new Bundle();
        FragmentIntegralMingxi fragment = new FragmentIntegralMingxi();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IntegralMingxiPresenter getPresenter() {
        return new IntegralMingxiPresenter(_mActivity, FragmentIntegralMingxi.this);
    }

    @Override
    protected void initdata() {
        newsaleData();
        initRefresh();
        String integral_points = CacheHelper.getAlias(_mActivity, "integral_points");
        mingxi_jife.setText(StringUtils.isEmpty(integral_points) ? "0" : integral_points);
    }

    public void newsaleData() {
        newArrival_DataAdapter = new JoneBaseAdapter<IntegralMingXiBean.DataBeanX.ListBean.DataBean>(newRecycleview, R.layout.item_integral_mingxi) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, IntegralMingXiBean.DataBeanX.ListBean.DataBean model) {
                helper.setText(R.id.jifen_num, model.getValue() > 0 ? "+" + model.getValue() : "-" + model.getValue());
                helper.setText(R.id.liulan_huodedianshu_tv, model.getDescribe() + "");
                helper.setText(R.id.jifen_jiaoyi_tima, model.getCreate_time() + "");
                helper.setText(R.id.shi_fen_miao_tv, model.getCreate_time() + "");
//                helper.setText(R.id.new_content,  model.getArticle_title() +"");


            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        newRecycleview.setLayoutManager(layoutManager);
        newRecycleview.setHasFixedSize(true);
        newRecycleview.setAdapter(newArrival_DataAdapter);
        List<HomeBean> datas = new ArrayList<>();
     /*   for (int i = 0; i < 3; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }*/
//        mJobDataAdapter.setData(datas);

        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//                AboutOursNewsBean.DataBeanX.ListBean.DataBean currentbean = newArrival_DataAdapter.getItem(position);
//
//                String getParam = currentbean.getArticle_id()+"";
//                if (!StringUtils.isEmpty(getParam)) {
//                    WebviewActivity.newIntance(_mActivity, getParam, "新闻详情");
//                }
            }
        });
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
                getMingxiInfo(mIndex, search_name);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getMingxiInfo(mIndex, search_name);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    private void getMingxiInfo(int page, String search) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", page + "");
        map.put("token", getToken);
        getPresenter().getNewList(map);
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral_mingxi;
    }

    @Override
    public void refreshNews(IntegralMingXiBean homeBean) {
        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    IntegralMingXiBean.DataBeanX getData = homeBean.getData();
                    if (getData != null) {
                        if (mIsRefresh) {
                            newArrival_DataAdapter.getData().clear();
                            List<IntegralMingXiBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                newArrival_DataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<IntegralMingXiBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            newArrival_DataAdapter.getData().addAll(getList);
                        }


                        int getTotal = getData.getList().getTotal();
                        if (getTotal == 0) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyLayout.showEmpty();
                            newRecycleview.setVisibility(View.GONE);
                        } else {
                            newRecycleview.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        }
                        newArrival_DataAdapter.notifyDataSetChanged();
                        twinkling_refreshlayout.setEnableLoadmore(true);

                    } else {
                        if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            newRecycleview.setVisibility(View.GONE);
                        }
                        if (mIsRefresh) {
                            newArrival_DataAdapter.getData().clear();
                            newArrival_DataAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.rl_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
        }
    }
}
