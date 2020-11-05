package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.contract.CarContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.CarPresenter;
import com.national.qinggong.presenter.MyMessagePresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 *
 * 购物车*/
public class RootCartFrragment extends BaseFragment implements CarContract.View {
    @BindView(R.id.car_recycleview)
    RecyclerView car_recycleview;

    @BindView(R.id.total_num)
    TextView total_num;
    public static final String ISSHOWIING = "ISSHOWIING";
    private JoneBaseAdapter<HomeBean> mJobDataAdapter;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;
    private ProgressLayout mProgressLayout;
    private JoneBaseAdapter<CarBean.DataBeanX.ListBean.DataBean> rightDataAdapter;

    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    public static RootCartFrragment newInstance(boolean isShow) {
        Bundle args = new Bundle();
        args.putBoolean(ISSHOWIING, isShow);
        RootCartFrragment fragment = new RootCartFrragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        newArrivData();
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
    protected CarPresenter getPresenter() {
        return new CarPresenter(_mActivity, RootCartFrragment.this);
    }

    private void getMessageListInfo(int page) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", page + "");
        map.put("token", getToken);
        getPresenter().getMessageList(map);
    }
    private void deleteCarinfo(int cart_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("cart_id", cart_id + "");
        map.put("token", getToken);
        getPresenter().getDeleteCar(map);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.root_car_fragment;
    }
    public void newArrivData() {
        rightDataAdapter = new JoneBaseAdapter<CarBean.DataBeanX.ListBean.DataBean>(car_recycleview, R.layout.item_car_root) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, CarBean.DataBeanX.ListBean.DataBean model) {

                helper.setText(R.id.car_name, model.getGoods_name() + "");

                helper.setText(R.id.car_number, model.getNumber() + "");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(true, true, true, true);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.car_image));



                helper.setItemChildClickListener(R.id.delete_car);
                helper.setItemChildClickListener(R.id.chat_tv);



            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        car_recycleview.setLayoutManager(layoutManager);
        car_recycleview.setAdapter(rightDataAdapter);


        rightDataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                switch (childView.getId()) {
                    case R.id.delete_car:
                        int getUser_id = rightDataAdapter.getData().get(position).getId();
                        deleteCarinfo(getUser_id );
                        break;
                    case R.id.chat_tv:
                                if (rightDataAdapter!=null){
                                    CarBean.DataBeanX.ListBean.DataBean currentbean = rightDataAdapter.getItem(position);
                                    Bundle Bundle_about = new Bundle();
                                    Bundle_about.putInt("type", 34);
                                    Bundle_about.putString("user_id",currentbean.getUser_id()+"");
                                    PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                                }
                        break;

                }
            }
        });
        rightDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (rightDataAdapter!=null){
                    CarBean.DataBeanX.ListBean.DataBean currentbean = rightDataAdapter.getItem(position);
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

    @Override
    public void deleteCar(DeleteCarBean userInfo) {
        if (userInfo!=null){
            if (userInfo.getCode()==1){
                ToastUtilMsg.showToast(_mActivity,userInfo.getMsg()+"");
                twinkling_refreshlayout.startRefresh();
            }
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            twinkling_refreshlayout.startRefresh();
        }
    }
    @Override
    public void refreshMessagePage(CarBean homeBean) {

        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    CarBean.DataBeanX getData = homeBean.getData();
                    if (getData != null) {
                        if (mIsRefresh) {
                            rightDataAdapter.getData().clear();
                            List<CarBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            if (getList != null && getList.size() > 0) {
                                rightDataAdapter.getData().addAll(getList);
                            }
                        } else {
                            List<CarBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                            rightDataAdapter.getData().addAll(getList);
                        }


                        int getTotal = getData.getList().getTotal();
                        if (getTotal==0){
                            total_num.setVisibility(View.INVISIBLE);
                        }else{
                            total_num.setVisibility(View.VISIBLE);
                            if (getTotal==1){
                                total_num.setText(getTotal+" item in total");
                            }else{
                                total_num.setText(getTotal+" items in total");
                            }
                        }

                        if (getTotal == 0) {
                            emptyLayout.setVisibility(View.VISIBLE);
                            emptyLayout.showEmpty();
                            car_recycleview.setVisibility(View.GONE);
                        } else {
                            car_recycleview.setVisibility(View.VISIBLE);
                            emptyLayout.setVisibility(View.GONE);
                        }
                        rightDataAdapter.notifyDataSetChanged();
                        twinkling_refreshlayout.setEnableLoadmore(true);

                    } else {
                        if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                            car_recycleview.setVisibility(View.GONE);
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
}
