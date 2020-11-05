package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.contract.SalemanListContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.SalemanListPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*Salesman*/
public class FragmentSalesmanList extends BaseFragment implements SalemanListContract.View {

    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.new_recycleview)
    RecyclerView newRecycleview;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;


    private boolean mIsRefresh = true;
    private int mIndex = 1;

    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter<SaleManBean.DataBeanX.ListBean.DataBean> newArrival_DataAdapter;
    private String search_name = "";

    public static FragmentSalesmanList newInstance(String into_type) {
        Bundle args = new Bundle();
        FragmentSalesmanList fragment = new FragmentSalesmanList();
        args.putString("search_name", into_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SalemanListPresenter getPresenter() {
        return new SalemanListPresenter(_mActivity, FragmentSalesmanList.this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search_name = getArguments().getString("search_name");
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected void initdata() {
        newsaleData();
        initRefresh();
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
                getSaleListInfo(mIndex, search_name);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getSaleListInfo(mIndex, search_name);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    private void getSaleListInfo(int page, String search) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("search", search);
        map.put("page", page + "");
        map.put("token", getToken);
        getPresenter().getNewList(map);
    }


    public void newsaleData() {
        newArrival_DataAdapter = new JoneBaseAdapter<SaleManBean.DataBeanX.ListBean.DataBean>(newRecycleview, R.layout.item_search_salesman) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SaleManBean.DataBeanX.ListBean.DataBean model) {
                helper.setText(R.id.user_name, model.getNickName() + "");
                helper.setText(R.id.user_email, model.getAccount() + "");
//                helper.setText(R.id.new_content,  model.getArticle_title() +"");


                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getAvatarUrl())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.user_head));
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
                SaleManBean.DataBeanX.ListBean.DataBean currentbean = newArrival_DataAdapter.getItem(position);
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 34);
                Bundle_about.putString("user_id",currentbean.getUser_id()+"");
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_saleman;
    }


    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;

        }
    }

    @Override
    public void refreshSaleMan(SaleManBean homeBean) {
        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    SaleManBean.DataBeanX getData = homeBean.getData();
                    if (getData != null) {
                        if (mIsRefresh) {
                            newArrival_DataAdapter.getData().clear();
                            List<SaleManBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                newArrival_DataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<SaleManBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
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
}
