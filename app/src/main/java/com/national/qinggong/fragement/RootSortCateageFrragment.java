package com.national.qinggong.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.national.qinggong.bean.CarBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.bean.ShopDetailBean;
import com.national.qinggong.contract.SearchShopListContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.SearchShopListPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * tab分类
 * */
public class RootSortCateageFrragment extends BaseFragment implements SearchShopListContract.View{
    public static final String ISSHOWIING = "ISSHOWIING";

    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    @BindView(R.id.left_recycle)
    RecyclerView left_recycle;
    @BindView(R.id.right_recycle)
    RecyclerView right_recycle;
    @BindView(R.id.tab_bar_recycleview)
    RecyclerView tab_bar_recycleview;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private ProgressLayout mProgressLayout;

    private JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean.ChildBean> mshopLeftlistAdapter;
    private JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean> tab_barDataAdapter;
    private JoneBaseAdapter<SearchShopListBean.DataBeanX.ListBean.DataBean> rightDataAdapter;
    private int getCategory_id;

    int currentTabPage;

    public static RootSortCateageFrragment newInstance(boolean isShow) {
        Bundle args = new Bundle();
        args.putBoolean(ISSHOWIING, isShow);
        RootSortCateageFrragment fragment = new RootSortCateageFrragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {

        topTabBar();
        shopListRecycleview();
        shopRightRecycleview();
        loadCategory();
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
                getSearchListInfo(mIndex, "", getCategory_id);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getSearchListInfo(mIndex, "", getCategory_id);
            }
        });


    }
    @Override
    protected SearchShopListPresenter getPresenter() {
        return new SearchShopListPresenter(_mActivity, RootSortCateageFrragment.this);
    }

    public void loadCategory() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        getPresenter().getSearchCategory(map);
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


/*
* 跨境电商列表只展示图片
* */
    public void shopRightRecycleview() {
        rightDataAdapter = new JoneBaseAdapter<SearchShopListBean.DataBeanX.ListBean.DataBean>(right_recycle, R.layout.item_sort_right) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchShopListBean.DataBeanX.ListBean.DataBean model) {
                helper.setText(R.id.shop_name, model.getGoods_name() + "");
                helper.setText(R.id.maidian_tv, model.getNumber() + "");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(true, true, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.image));
                if (currentTabPage==1){
                    helper.getView(R.id.commant_lin).setVisibility(View.GONE);
                    helper.getView(R.id.image_kuajing).setVisibility(View.VISIBLE);
                    Glide.with(_mActivity).load(model.getFile_path())
                            .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.image_kuajing));
                }else {
                    helper.getView(R.id.commant_lin).setVisibility(View.VISIBLE);
                    helper.getView(R.id.image_kuajing).setVisibility(View.GONE);
                }

                helper.setItemChildClickListener(R.id.chat_tv);
                helper.setItemChildClickListener(R.id.iv_shop_car);

            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        right_recycle.setLayoutManager(layoutManager);
        right_recycle.setAdapter(rightDataAdapter);
     /*   List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
        rightDataAdapter.setData(datas);*/


        rightDataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                SearchShopListBean.DataBeanX.ListBean.DataBean currentbean = rightDataAdapter.getItem(position);
                switch (childView.getId()) {
                    case R.id.chat_tv:
                        if (rightDataAdapter!=null){
                            Bundle Bundle_about = new Bundle();
                            Bundle_about.putInt("type", 34);
                            Bundle_about.putString("user_id",currentbean.getUser_id()+"");
                            PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                        }
                        break;
                    case R.id.iv_shop_car:
                        //ToastUtilMsg.showToast(getActivity(),"购物车");
                        getadd_car(currentbean.getGoods_id()+"");
                        break;

                }
            }
        });

        rightDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (rightDataAdapter!=null){
                    SearchShopListBean.DataBeanX.ListBean.DataBean currentbean = rightDataAdapter.getItem(position);
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

    public void topTabBar() {
        tab_barDataAdapter = new JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean>(tab_bar_recycleview, R.layout.item_analy_first_tab) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchCategoryBean.DataBean.ListBean model) {
                if (model.getName().contains(" ")){
                    String tab_name = model.getName().replace(" ", "\n");
                    helper.setText(R.id.textview_first_tab, tab_name + "");
                }else {
                    helper.setText(R.id.textview_first_tab, model.getName() + "");
                }

                View view1 = helper.getView(R.id.view1_sort);
                LinearLayout item_tab_lin = helper.getView(R.id.item_tab_lin);
                if (model.isSeleted()) {
                    view1.setVisibility(View.VISIBLE);
                    helper.getTextView(R.id.textview_first_tab).setTextColor(Color.parseColor("#D20B17"));
                    item_tab_lin.setBackgroundResource(R.drawable.vault_out_vauleseleted_shape);
                } else {
                    view1.setVisibility(View.INVISIBLE);
                    helper.getTextView(R.id.textview_first_tab).setTextColor(Color.parseColor("#ffffff"));
                    item_tab_lin.setBackgroundResource(R.drawable.vault_out_vaule_shape);
                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false);
        tab_bar_recycleview.setLayoutManager(mJobLinearlayoutManager);
        tab_bar_recycleview.setAdapter(tab_barDataAdapter);
        tab_barDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                if (tab_barDataAdapter.getData().get(position).isSeleted()) {

                } else {
                    for (int i = 0; i < tab_barDataAdapter.getData().size(); i++) {
                        if (i == position) {
                            tab_barDataAdapter.getData().get(i).setSeleted(true);

                            if (tab_barDataAdapter.getData().get(i).getName().contains("Cross Border")){
                                currentTabPage=1;//  1代表跨境
                            }else {
                                currentTabPage=0;
                            }

                        /*
                        * 左侧列表
                        * */
                            List<SearchCategoryBean.DataBean.ListBean.ChildBean> getChild = tab_barDataAdapter.getData().get(i).getChild();
                            int is_select=0;
                            if (getChild!=null&getChild.size()>0){
                                for (int j = 0; j <getChild.size() ; j++) {
                                    if (getChild.get(j).isSeleted()){
                                        getCategory_id=  getChild.get(j).getCategory_id();
                                        Log.i("category——id 111",getCategory_id+"");
                                        is_select=1;
                                        break;
                                    }
                                }
                                if (is_select == 0) {
                                    getChild.get(0).setSeleted(true);
                                    getCategory_id= getChild.get(0).getCategory_id();
                                    Log.i("category——id  000",getCategory_id+"");
                                }

                                //
                                twinkling_refreshlayout.startRefresh();
                                mshopLeftlistAdapter.setData(getChild);
                            }

//                                chooseName(tab_barDataAdapter.getData().get(i).getBlock());
//                            Toast.makeText(_mActivity, "" + mJobDataAdapter.getData().get(position).getMsg(), Toast.LENGTH_LONG).show();
                        } else {
                            tab_barDataAdapter.getData().get(i).setSeleted(false);
                        }
                    }

                    tab_barDataAdapter.notifyDataSetChanged();
                }
            }
        });


   /*     ArrayList<String> tab_content = new ArrayList<>(Arrays.asList(_mActivity.getResources().getStringArray(R.array.tab_content)));
        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < tab_content.size(); i++) {
            HomeBean homeBean = new HomeBean();
            if (i == 0) {
                homeBean.setSeleted(true);
            }
            homeBean.setMsg(tab_content.get(i).toString());
            datas.add(homeBean);
        }*/
//        tab_barDataAdapter.setData(datas);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.root_fragment_sort_cateage;
    }

    public void shopListRecycleview() {
        mshopLeftlistAdapter = new JoneBaseAdapter<SearchCategoryBean.DataBean.ListBean.ChildBean>(left_recycle, R.layout.item_root_left) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchCategoryBean.DataBean.ListBean.ChildBean model) {
                if (model.getName().contains("&amp;")){
                    String leftname = model.getName().replace("&amp;", "\n");
                    helper.setText(R.id.title, leftname+ "");
                }else {
                    helper.setText(R.id.title, model.getName() + "");
                }
                TextView left_bg = helper.getView(R.id.title);
                View view1 = helper.getView(R.id.view1_sort);
                if (model.isSeleted()) {
                    left_bg.setTextColor(Color.parseColor("#D20B17"));
                    view1.setVisibility(View.VISIBLE);
                    left_bg.setBackgroundColor(_mActivity.getResources().getColor(R.color.white));
                } else {
                    view1.setVisibility(View.INVISIBLE);
                    left_bg.setTextColor(Color.parseColor("#727C8F"));
                    left_bg.setBackgroundColor(_mActivity.getResources().getColor(R.color.color_F5F5F5));
                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        left_recycle.setLayoutManager(mJobLinearlayoutManager);
        left_recycle.setAdapter(mshopLeftlistAdapter);
        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("Raw Materials" + i);
            datas.add(homeBean);
        }
        datas.get(0).setSeleted(true);
//        mshopLeftlistAdapter.setData(datas);
        mshopLeftlistAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                List<SearchCategoryBean.DataBean.ListBean.ChildBean> list = mshopLeftlistAdapter.getData();
                for (int i = 0; i < list.size(); i++) {
                    if (position == i) {
                        list.get(position).setSeleted(true);
                        getCategory_id = list.get(i).getCategory_id();
                        Log.i("category——id  999",getCategory_id+"");
                        twinkling_refreshlayout.startRefresh();
                    } else {
                        list.get(i).setSeleted(false);
                    }
                }
                mshopLeftlistAdapter.notifyDataSetChanged();

               /* List<ClassListAndChildBan.DataBean.ListBean.ChildBean> rightChild = list.get(position).getChild();
                if (rightChild != null & rightChild.size() > 0) {
                    rightTypeAdatpter.setData(rightChild);
                }
                rightTypeAdatpter.notifyDataSetChanged();*/
            }
        });
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
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
                            rightDataAdapter.getData().clear();
                            List<SearchShopListBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                rightDataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<SearchShopListBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            rightDataAdapter.getData().addAll(getList);
                        }


                        int getTotal = getData.getList().getTotal();
                        if (getTotal == 0) {
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyLayout.showEmpty();
                            right_recycle.setVisibility(View.GONE);
                        } else {
                            right_recycle.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        }
                        rightDataAdapter.notifyDataSetChanged();
                        twinkling_refreshlayout.setEnableLoadmore(true);

                    } else {
                        if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            right_recycle.setVisibility(View.GONE);
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
    public void refreshCategory(SearchCategoryBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                SearchCategoryBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    List<SearchCategoryBean.DataBean.ListBean> getList = getData.getList();
                    if (getList != null & getList.size() > 0) {
                        getList.get(0).setSeleted(true);
                        tab_barDataAdapter.setData(getList);
                        tab_barDataAdapter.notifyDataSetChanged();
                        List<SearchCategoryBean.DataBean.ListBean.ChildBean> getChild = getList.get(0).getChild();
                        if (getChild!=null&getChild.size()>0){
                            getChild.get(0).setSeleted(true);
                            getCategory_id= getChild.get(0).getCategory_id();
                            mshopLeftlistAdapter.setData(getChild);
                            twinkling_refreshlayout.startRefresh();
                        }
                    }

                }
            }
        }
    }

    @Override
    public void add_addCar(DeleteCarBean userInfo) {

    }


    private void getadd_car(String goods_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("goods_id", goods_id + "");
        map.put("token", getToken);
        getPresenter().submit_addcar(map);
    }

    @Override
    public void showToast(String content) {

    }
}
