package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.bean.ShopDetailBean;
import com.national.qinggong.contract.SearchShopListContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.CustomPopWindow;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.customview.GridSpacingItemDecoration;
import com.national.qinggong.presenter.SearchShopListPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*搜索商品结果列表*/
public class FragmentSearchShopResult extends BaseFragment implements SearchShopListContract.View {
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.user_ecit)
    TextView userEcit;
    @BindView(R.id.go_home)
    ImageView goHome;
    @BindView(R.id.shop_recyclerview)
    RecyclerView shopRecyclerview;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;


    private JoneBaseAdapter<SearchShopListBean.DataBeanX.ListBean.DataBean> recommend_DataAdapter;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private ProgressLayout mProgressLayout;
    private CustomPopWindow mListPopWindow;
    private JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean> newArrival_DataAdapter;
    private int getCategory_id;
    private String search_name = "";

    public static FragmentSearchShopResult newInstance(String into_type) {
        Bundle args = new Bundle();
        FragmentSearchShopResult fragment = new FragmentSearchShopResult();
        args.putString("search_name", into_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search_name = getArguments().getString("search_name");
    }

    @Override
    protected void initdata() {
        loadRecommend();
        initRefresh();
    }

    private void getSearchListInfo(int page, String searchName, int category_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", page + "");
        map.put("search_name", searchName);
        map.put("category_id", category_id + "");
        map.put("token", getToken);
        getPresenter().getSearchList(map);
    }


    public void loadCategory() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        getPresenter().getSearchCategory(map);
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
                getSearchListInfo(mIndex, search_name, getCategory_id);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getSearchListInfo(mIndex, search_name, getCategory_id);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    @Override
    protected SearchShopListPresenter getPresenter() {
        return new SearchShopListPresenter(_mActivity, FragmentSearchShopResult.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_shop_result;
    }

    public void loadRecommend() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        shopRecyclerview.setLayoutManager(gridLayoutManager);

        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 5), true);
        shopRecyclerview.addItemDecoration(gridSpacingItemDecoration);
        shopRecyclerview.setHasFixedSize(true);
        recommend_DataAdapter = new JoneBaseAdapter<SearchShopListBean.DataBeanX.ListBean.DataBean>(shopRecyclerview, R.layout.item_hot_products) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchShopListBean.DataBeanX.ListBean.DataBean model) {
                LinearLayout lin_root = helper.getView(R.id.lin_root);
//               DensityUtil.setViewMargin(_mActivity,lin_root, true, 10, 5, 10, 0);
                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNumber() + "");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));

            }
        };
        shopRecyclerview.setAdapter(recommend_DataAdapter);
       /* List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }*/
        recommend_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (recommend_DataAdapter!=null){
                    SearchShopListBean.DataBeanX.ListBean.DataBean currentbean = recommend_DataAdapter.getItem(position);
                    if (currentbean!=null){
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id",getGoods_id+"");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }
                }
            }
        });

    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @OnClick({R.id.rl_back, R.id.go_home, R.id.user_ecit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.go_home:
                break;
            case R.id.user_ecit:
                loadCategory();
                break;
        }
    }

    @Override
    public void refreshSearchPage(SearchShopListBean homeBean) {
        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    SearchShopListBean.DataBeanX getData = homeBean.getData();
                    if (getData != null) {
                        if (mIsRefresh) {
                            recommend_DataAdapter.getData().clear();
                            List<SearchShopListBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                recommend_DataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<SearchShopListBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            recommend_DataAdapter.getData().addAll(getList);
                        }


                        int getTotal = getData.getList().getTotal();
                        if (getTotal == 0) {
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyLayout.showEmpty();
                            shopRecyclerview.setVisibility(View.GONE);
                        } else {
                            shopRecyclerview.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        }
                        recommend_DataAdapter.notifyDataSetChanged();
                        twinkling_refreshlayout.setEnableLoadmore(true);

                    } else {
                        if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            shopRecyclerview.setVisibility(View.GONE);
                        }
                        if (mIsRefresh) {
                            recommend_DataAdapter.getData().clear();
                            recommend_DataAdapter.notifyDataSetChanged();
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
    public void refreshCategory(SearchCategoryBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                SearchCategoryBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    List<SearchCategoryBean.DataBean.ListBean> getList = getData.getList();
                    if (getList != null & getList.size() > 0) {
//                       getList
                        showPopListView();
                        SearchCategoryBean.DataBean.ListBean firstBean = new SearchCategoryBean.DataBean.ListBean();
                        firstBean.setName("All Categories");
                        firstBean.setCategory_id(0);
                        getList.add(0, firstBean);
                        newArrival_DataAdapter.setData(getList);
                        Log.i("cateage====", getList.size() + "===");
                    }

                }
            }
        }
    }

    @Override
    public void add_addCar(DeleteCarBean userInfo) {

    }

    private void showPopListView() {
        View contentView = LayoutInflater.from(_mActivity).inflate(R.layout.search_category_recycleview, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(_mActivity)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)//显示大小
                .create()
                .showAsDropDown(userEcit, 0, 20);
    }

    private void handleListView(View contentView) {
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recyclerView);
        newArrival_DataAdapter = new JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean>(recyclerView, R.layout.item_category_pp) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchCategoryBean.DataBean.ListBean model) {
                helper.setText(R.id.tv_name, model.getName() + "");
//                helper.setText(R.id.user_email, model.getAccount()+ "");

            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newArrival_DataAdapter);
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                SearchCategoryBean.DataBean.ListBean currentBean = newArrival_DataAdapter.getItem(position);
                String getName = currentBean.getName();
                userEcit.setText(getName);
                getCategory_id = currentBean.getCategory_id();
                mListPopWindow.dissmiss();
                twinkling_refreshlayout.startRefresh();
            }
        });
    }

    @Override
    public void showToast(String content) {

    }
}
