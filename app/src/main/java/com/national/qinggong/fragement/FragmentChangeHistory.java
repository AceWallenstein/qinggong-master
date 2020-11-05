package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.national.qinggong.bean.IntegralShopBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.UserOrderListsBean;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.GridSpacingItemDecoration;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*兑换记录
 * item_duihuan_jilu.xml
 * */
public class FragmentChangeHistory extends BaseFragment implements IntegralShopingContract.View {
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.recyclerView)
    RecyclerView home_integral_recyclerView;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.lin_no_null)
    TextView lin_no_null;
    private JoneBaseAdapter recommend_dataAdapter;
    private ProgressLayout mProgressLayout;

    public static FragmentChangeHistory newInstance() {
        Bundle args = new Bundle();
        FragmentChangeHistory fragment = new FragmentChangeHistory();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });
        initRefresh();
        getCount();
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_history;
    }

    public void loadRecommend() {
        home_integral_recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 5), true);
        home_integral_recyclerView.addItemDecoration(gridSpacingItemDecoration);
        home_integral_recyclerView.setHasFixedSize(true);
        recommend_dataAdapter = new JoneBaseAdapter<IntegralShopBean.DataBeanX.ListBean.DataBean>(home_integral_recyclerView, R.layout.item_integral_shop_2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, IntegralShopBean.DataBeanX.ListBean.DataBean model) {
                LinearLayout lin_root = helper.getView(R.id.lin_root);
//               DensityUtil.setViewMargin(_mActivity,lin_root, true, 10, 5, 10, 0);
                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNeed_points_num() + "Credits");
                helper.setText(R.id.tv_april, "April" + "Credits");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getGoods_image())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));

            }
        };
        home_integral_recyclerView.setAdapter(recommend_dataAdapter);
        recommend_dataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (recommend_dataAdapter == null) return;

            }
        });


    }

    private boolean mIsRefresh = true;
    private int mIndex = 1;

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
                getCount();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getCount();
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    private void getCount() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", mIndex + "");
        map.put("dataType", "all");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .userOrderLists(map)
                .compose(RequestManager.<IntegralShopBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadRecommend();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        refreshPostfinally();
                    }
                })
                .subscribe(new Consumer<IntegralShopBean>() {
                               @Override
                               public void accept(IntegralShopBean homeBean) throws Exception {
                                   if (homeBean != null) {
                                       if (homeBean.getData() != null) {
                                           IntegralShopBean.DataBeanX getData = homeBean.getData();
                                           if (getData != null) {
                                               if (mIsRefresh) {
                                                   recommend_dataAdapter.getData().clear();
                                                   List<IntegralShopBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                                                   if (getList != null && getList.size() > 0) {
                                                       recommend_dataAdapter.getData().addAll(getList);
                                                   }
                                               } else {
                                                   List<IntegralShopBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                                                   recommend_dataAdapter.getData().addAll(getList);
                                               }


                                               int getTotal = getData.getList().getTotal();
                                               tv_count.setText("Total "+getTotal+" items");
                                               if (getTotal == 0) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                                                   home_integral_recyclerView.setVisibility(View.GONE);
                                                   lin_no_null.setVisibility(View.VISIBLE);
                                               } else {
                                                   home_integral_recyclerView.setVisibility(View.VISIBLE);
                                                   lin_no_null.setVisibility(View.GONE);
//                        rel_nodate.setVisibility(View.GONE);
                                               }
                                               recommend_dataAdapter.notifyDataSetChanged();
                                               twinkling_refreshlayout.setEnableLoadmore(true);

                                           } else {
                                               if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                                                   home_integral_recyclerView.setVisibility(View.GONE);
                                               }
                                               if (mIsRefresh) {
                                                   recommend_dataAdapter.getData().clear();
                                                   recommend_dataAdapter.notifyDataSetChanged();
                                               }
                                               twinkling_refreshlayout.setEnableLoadmore(true);
                                           }
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
    public void IntegralTask(Object userInfo) {

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

    @Override
    public void showToast(String content) {

    }
}
