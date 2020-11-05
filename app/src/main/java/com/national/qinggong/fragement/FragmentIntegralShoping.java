package com.national.qinggong.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.IndexGetBannerBean;
import com.national.qinggong.bean.IntegralShopBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.GridSpacingItemDecoration;
import com.national.qinggong.presenter.IntegralShopingPresenter;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.ui.activity.AppLoginActivity;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;
import com.national.qinggong.util.ToastUtilMsg;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.bean.RectBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.RectIndicator;
import com.zhengsr.viewpagerlib.type.BannerTransType;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*积分商城
 * */
public class FragmentIntegralShoping extends BaseFragment implements IntegralShopingContract.View {
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    @BindView(R.id.home_integral_recyclerView)
    RecyclerView home_integral_recyclerView;
    @BindView(R.id.rl_back)
    LinearLayout rl_back;

    @BindView(R.id.jifen_num)
    TextView jifen_num;

    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loop_viewpager_mz;
    @BindView(R.id.normal_indicator)
    RectIndicator rect_indicator;
    JoneBaseAdapter<IntegralShopBean.DataBeanX.ListBean.DataBean> recommend_DataAdapter;
    @BindView(R.id.jifen_mingxi)
    LinearLayout jifenMingxi;
    @BindView(R.id.duihuan_jilu)
    LinearLayout duihuanJilu;

    private boolean mIsRefresh = true;
    private int mIndex = 1;

    private ProgressLayout mProgressLayout;

    public static FragmentIntegralShoping newInstance() {
        Bundle args = new Bundle();
        FragmentIntegralShoping fragment = new FragmentIntegralShoping();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected IntegralShopingPresenter getPresenter() {
        return new IntegralShopingPresenter(_mActivity, FragmentIntegralShoping.this);
    }

    @Override
    protected void initdata() {
        initBanner();
        getCount();
        initRefresh();
        loadRecommend();
        String integral_points = CacheHelper.getAlias(_mActivity, "integral_points");
        jifen_num.setText(StringUtils.isEmpty(integral_points) ? "0" : integral_points);
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
                String getToken = CacheHelper.getAlias(_mActivity, "getToken");

                getIntegralShoping(mIndex, 1, getToken);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                String getToken = CacheHelper.getAlias(_mActivity, "getToken");

                getIntegralShoping(mIndex, 1, getToken);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    public void initBanner() {
        loop_viewpager_mz.addIndicator(rect_indicator);
        RectBean rectBean = new RectBean();
        rectBean.horizonMargin = 30;
//       rectBean.normalColor = Color.GRAY;
        rectBean.normalColor = _mActivity.getResources().getColor(R.color.color_no_choose);
//       rectBean.selectedColor =_mActivity.getResources().getColor(R.color.color_choose);
        rectBean.selectedColor = Color.RED;
        rectBean.width = 36;
        rectBean.height = 5;
        rectBean.roundRadius = 5;

        /**
         * 配置 CircleIndicator 的自定义属性
         */
        rect_indicator.addRectBean(rectBean);


        /**
         * 配置 BannerViewPager 的数据
         */
        PageBean bean = new PageBean();
        bean.isAutoLoop = true;
        bean.smoothScrollTime = 400;
        bean.loopTime = 5000;
        bean.transFormer = BannerTransType.DEPATH;

        loop_viewpager_mz.addPageBean(bean)
                .addIndicator(rect_indicator)
                .setCurrentPosition(1);

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


    private void getCount() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("type", "1");
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
                                       showBanner(loop_viewpager_mz, userInfo.getData().getBanner());
                                   } else {
                                       ToastUtilMsg.showToast(_mActivity, userInfo.getMsg());
                                   }

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }


    private void getIntegralShoping(int page, int is_points_exchange, String token) {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("page", page + "");
        map.put("is_points_exchange", is_points_exchange + "");
        map.put("token", token + "");
        getPresenter().IntegralInfo(map);

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
    protected int getLayoutId() {
        return R.layout.fragment_integral_shoping;
    }

    public void loadRecommend() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        home_integral_recyclerView.setLayoutManager(gridLayoutManager);

        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 5), true);
        home_integral_recyclerView.addItemDecoration(gridSpacingItemDecoration);
        home_integral_recyclerView.setHasFixedSize(true);
        recommend_DataAdapter = new JoneBaseAdapter<IntegralShopBean.DataBeanX.ListBean.DataBean>(home_integral_recyclerView, R.layout.item_integral_shop) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, IntegralShopBean.DataBeanX.ListBean.DataBean model) {
                LinearLayout lin_root = helper.getView(R.id.lin_root);
//               DensityUtil.setViewMargin(_mActivity,lin_root, true, 10, 5, 10, 0);
                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNeed_points_num() + "Credits");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getGoods_image())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));

            }
        };
        home_integral_recyclerView.setAdapter(recommend_DataAdapter);
        recommend_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (recommend_DataAdapter == null) return;
                IntegralShopBean.DataBeanX.ListBean.DataBean currentbean = recommend_DataAdapter.getItem(position);
                Bundle messageBundle = new Bundle();
                messageBundle.putInt("type", 21);
                messageBundle.putString("good_id", currentbean.getGoods_id() + "");
                PlatformForFragmentActivity.newInstance(_mActivity, messageBundle);
            }
        });


    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    //    IntegralShopBean
    @Override
    public void IntegralTask(Object userInfo) {
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        JSONObject jsonObject = null;
        IntegralShopBean homeBean = new Gson().fromJson(jsoncontent, new TypeToken<IntegralShopBean>() {
        }.getType());
        if (homeBean != null) {
            if (homeBean.getData() != null) {
                IntegralShopBean.DataBeanX getData = homeBean.getData();
                if (getData != null) {
                    if (mIsRefresh) {
                        recommend_DataAdapter.getData().clear();
                        List<IntegralShopBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                        if (getList != null && getList.size() > 0) {
                            recommend_DataAdapter.getData().addAll(getList);
                        }
                    } else {
                        List<IntegralShopBean.DataBeanX.ListBean.DataBean> getList = getData.getList().getData();
                        recommend_DataAdapter.getData().addAll(getList);
                    }


                    int getTotal = getData.getList().getTotal();
                    if (getTotal == 0) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                        home_integral_recyclerView.setVisibility(View.GONE);
                    } else {
                        home_integral_recyclerView.setVisibility(View.VISIBLE);
//                        rel_nodate.setVisibility(View.GONE);
                    }
                    recommend_DataAdapter.notifyDataSetChanged();
                    twinkling_refreshlayout.setEnableLoadmore(true);

                } else {
                    if (homeBean == null) {
//                        rel_nodate.setVisibility(View.VISIBLE);
                        home_integral_recyclerView.setVisibility(View.GONE);
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

    @OnClick({R.id.jifen_mingxi, R.id.duihuan_jilu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jifen_mingxi:

                break;
            case R.id.duihuan_jilu:
                Bundle duihuan_jilu_Bundle = new Bundle();
                duihuan_jilu_Bundle.putInt("type", 24);
                PlatformForFragmentActivity.newInstance(_mActivity, duihuan_jilu_Bundle);
                break;
        }
    }
}
