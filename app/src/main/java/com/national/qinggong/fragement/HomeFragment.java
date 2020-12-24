package com.national.qinggong.fragement;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.HomeBannerBean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.LoopBean;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.PersonCenterBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.HomePageContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.HorizontalItemDecoration;
import com.national.qinggong.presenter.HomePagePresenter;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.ui.activity.AppLoginActivity;
import com.national.qinggong.ui.activity.LiveAnchorDetailActivity;
import com.national.qinggong.ui.activity.LivePalyActivity;
import com.national.qinggong.ui.activity.LivePlayListActivity;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.PlayVideoActivity;
import com.national.qinggong.ui.activity.TestActivity;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.Constant;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.bean.RectBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.RectIndicator;
import com.zhengsr.viewpagerlib.type.BannerTransType;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
 * 首页
 *
 * https://github.com/LillteZheng/ViewPagerHelper
 * */
public class HomeFragment extends BaseFragment implements HomePageContract.View {
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;


    @BindView(R.id.message_rel)
    RelativeLayout message_rel;


    @BindView(R.id.home_product_recyclerView)
    RecyclerView home_product_recyclerView;

    @BindView(R.id.recommend_recyclerview)
    RecyclerView recommend_recyclerview;
    @BindView(R.id.new_arrive_lin)
    LinearLayout new_arrive_lin;


    @BindView(R.id.message_view)
    ImageView message_view;
    private boolean isRefreshing = true;

    private int mIndex = 1;


    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loop_viewpager_mz;
    @BindView(R.id.recyclerView_live)
    RecyclerView recyclerView_live;
    @BindView(R.id.loop_viewpager_shop)
    BannerViewPager loop_viewpager_shop;
    @BindView(R.id.normal_indicator)
    RectIndicator rect_indicator;
    @BindView(R.id.tb_more)
    TextView tb_more;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.lin_live)
    LinearLayout linLive;
    @BindView(R.id.lin_live_ann)
    LinearLayout linLiveAnn;
    @BindView(R.id.lin_live_back)
    LinearLayout linLiveBack;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.progress_bar2)
    ProgressBar progress_bar2;
    /*@BindView(R.id.banner)
    Banner banner;*/

    int flag;
    private List<String> mToplist = new ArrayList<>(); //头部广告链接集合
    List<HomeBannerBean.DataBean.SwipersBean> getSwipers;
    List<View> awardviews = new ArrayList<>();

    HomeBannerBean.DataBean.AdBean getAd;
    ActivityArticleBean.DataBean.ListBean getActivity;
    private JoneBaseAdapter<ClassTypeBean.DataBean.ListBean> classroom_JobDataAdapter;

    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<HomePageBean.DataBean.HotGoodsBean> mJobDataAdapter;
    private JoneBaseAdapter<HomePageBean.DataBean.RecommendGoodsBean> recommend_DataAdapter;
    private ProgressLayout mProgressLayout;
    private View awardmarqueeViewContent;

    private static final int[] RESID = {
            R.mipmap.banner_default,
            R.mipmap.banner_default,
            R.mipmap.banner_default,
    };
    private static final String[] TEXT = {"图像处理", "LSB开发", "游戏开发"};
    private List<LoopBean> mDatas;
    private JoneBaseAdapter<MultiMarketBean> marketDataAdapter;

    List<MultiMarketBean> currentInfo;
    private List<HomePageBean.DataBean.LiveBean> live;
    private List<HomePageBean.DataBean.AppOBean> appo;
    private List<HomePageBean.DataBean.PlayBackBean> playback;
    //new arrivals 的viewpage的总页数-1
    private int pageSize;
    private List<HomePageBean.DataBean.NewGoodsBean> getNew_goods;
    private List<BannerShopBean> listBanner;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter(_mActivity, HomeFragment.this);
    }


    @Override
    protected void initdata() {
        initRefresh();
        loadData();
        initBanner();
        loadRecommend();
        getHeader();
        tb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LivePlayListActivity.open(_mActivity);
            }
        });
        linLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setTextColor(Color.parseColor("#FFFFFF"));
                tv2.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#333333"));
                tv1.setBackgroundResource(R.drawable.bg_home_live_red);
                tv2.setBackgroundResource(R.drawable.bg_home_live_g);
                tv3.setBackgroundResource(R.drawable.bg_home_live_g);

                newArrivDataLive(recyclerView_live, live);
            }
        });
        linLiveAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv2.setTextColor(Color.parseColor("#FFFFFF"));
                tv1.setTextColor(Color.parseColor("#333333"));
                tv3.setTextColor(Color.parseColor("#333333"));
                tv2.setBackgroundResource(R.drawable.bg_home_live_red);
                tv1.setBackgroundResource(R.drawable.bg_home_live_g);
                tv3.setBackgroundResource(R.drawable.bg_home_live_g);
                newArrivAnno(recyclerView_live, appo);

            }
        });
        linLiveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv3.setTextColor(Color.parseColor("#FFFFFF"));
                tv1.setTextColor(Color.parseColor("#333333"));
                tv2.setTextColor(Color.parseColor("#333333"));
                tv3.setBackgroundResource(R.drawable.bg_home_live_red);
                tv2.setBackgroundResource(R.drawable.bg_home_live_g);
                tv1.setBackgroundResource(R.drawable.bg_home_live_g);
                newArrivDataPlayBack(recyclerView_live, playback);

            }
        });

        //home_product_recyclerView.setons
        home_product_recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int offsetX = recyclerView.computeHorizontalScrollOffset();
                int range = recyclerView.computeHorizontalScrollRange();
                int extend = recyclerView.computeHorizontalScrollExtent();
                Float progress = offsetX * 1.0f / (range - extend)*100;
                //Log.i("test", "progress 百分比=" + progress);
                progress_bar.setProgress(Math.round(progress));
            }
        });


        loop_viewpager_shop.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset viewpage当前页滑动的距离的百分比
                //positionOffsetPixels 页面滚动的实际距离
                Log.i("test","loop_viewpager_shop百分比="+positionOffset+"+++position="+position);



                //总页数-1
                //int totalPage=loop_viewpager_shop.getChildCount();
                //每一页的宽度
                int with=loop_viewpager_shop.getWidth();
                //viewpage的总宽度
                int totalWith=pageSize*with;
                //已经滑动的距离
                //float distance = (with * positionOffset)  + (with * position);
                //int bbb=position%pageSize;
                //Log.i("test","bbb="+bbb);

                //float distance = (with * positionOffset)  + (with * (position%pageSize));
                float distance = (with * positionOffset)  + (with * position);

                Log.i("test","positionOffsetPixels="+positionOffsetPixels);
                Log.i("test","总页数 pageSize="+pageSize);
                Log.i("test","单页宽度with="+with);
                Log.i("test","viewpage的总宽度totalWith="+totalWith);
                Log.i("test","已经滑动的距离distance="+distance);

                float p=distance/totalWith*100;

                Log.i("test","进度p="+p);
                Log.i("test","进度int p="+Math.round(p));
                Log.i("test","==============================================================");
                progress_bar2.setProgress(Math.round(p));
            }

            @Override
            public void onPageSelected(int i) { }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        /*loop_viewpager_shop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP){
                    loop_viewpager_shop.startAnim();
                }

                return false;
            }
        });*/
    }




    public void initBanner() {
      /*  mDatas = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            LoopBean bean = new LoopBean();
            if (i == 0) {
                bean.url = "https://www.chanel.com/i18n/zh_CN/slides/1600_Collection_M%C3%A9tiers_d'art_201920_FSH_0720_CN.jpg";
            } else {
                bean.url = "http://articleimg.xbiao.com/2020/0610/735_480_202006101591788026991.jpg";
            }

            bean.text = "545454";
            mDatas.add(bean);
        }*/
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
                .setCurrentPosition(0);




        PageBean bean2 = new PageBean();
        bean2.isAutoLoop = false;
        bean2.smoothScrollTime = 400;
        bean2.loopTime = 5000;
        bean2.transFormer = BannerTransType.DEPATH;
        loop_viewpager_shop.addPageBean(bean2)
                .setCurrentPosition(0);


//        showBanner(loop_viewpager_mz);
    }

    @Override
    public void onStart() {
        super.onStart();
        loop_viewpager_mz.startAnim();
        //loop_viewpager_shop.startAnim();
    }

    private void showBanner(BannerViewPager bannerViewPager, List<HomePageBean.DataBean.BannerBean> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout, getBanner, new PageHelperListener<HomePageBean.DataBean.BannerBean>() {


            @Override
            public void bindView(View view, final HomePageBean.DataBean.BannerBean data, int position) {
//                setText(view, R.id.loop_text, data.text);
                ImageView imageView = view.findViewById(R.id.loop_icon);
              /*  GlideApp.with(view).load(data.resId)
                        .into(imageView);*/
                GlideUtils.loadImageByUrl(data.getImage().getFile_path(), imageView);

            }

            @Override
            public void onItemClick(View view, HomePageBean.DataBean.BannerBean data, int position) {
                super.onItemClick(view, data, position);
//                Toast.makeText(_mActivity, data.url + " " + position, Toast.LENGTH_SHORT).show();
                Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
            }
        });
        loop_viewpager_mz.startAnim();
    }

    private void showBannerShop(BannerViewPager bannerViewPager,final List<BannerShopBean> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout_2, getBanner, new PageHelperListener<BannerShopBean>() {

            @Override
            public void bindView(View view, final BannerShopBean data, int position) {
                RecyclerView viewById = view.findViewById(R.id.new_arrive_recyclerView);
                newArrivData(viewById, data.getNew_goods);
                Log.d("TAG", position + "===========================banner");
            }

            @Override
            public void onItemClick(View view, BannerShopBean data, int position) {
                //super.onItemClick(view, data, position);
                getNew_goods = getBanner.get(position).getNew_goods;

                Log.i("test", position + "===========================banner");
            }
        });
        //loop_viewpager_shop.startAnim();
    }

    class BannerShopBean {
        List<HomePageBean.DataBean.NewGoodsBean> getNew_goods = new ArrayList<>();

        public List<HomePageBean.DataBean.NewGoodsBean> getGetNew_goods() {
            return getNew_goods;
        }

        public void setGetNew_goods(List<HomePageBean.DataBean.NewGoodsBean> getNew_goods) {
            this.getNew_goods = getNew_goods;
        }
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
        twinkling_refreshlayout.setTargetView(scrollView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                getHomePage();
                twinkling_refreshlayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

                twinkling_refreshlayout.finishLoadmore();
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    private void getHomePage() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        getPresenter().homeInfo(map);

    }

    public void newArrivData(RecyclerView new_arrive_recyclerView, final List<HomePageBean.DataBean.NewGoodsBean> getNew_goods) {
        final JoneBaseAdapter newArrival_DataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.NewGoodsBean>(new_arrive_recyclerView, R.layout.item_hot_products2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, final int position, final HomePageBean.DataBean.NewGoodsBean model) {
//                helper.setText(R.id.title, model.getMsg() + "");
             /*   helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNumber() + "");*/
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));


               /* helper.getView(R.id.rrl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int getGoods_id = model.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id", getGoods_id + "");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }
                });*/

            }

        };
        //LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        new_arrive_recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        new_arrive_recyclerView.setAdapter(newArrival_DataAdapter);




        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                //HomePageBean.DataBean.NewGoodsBean currentbean = (HomePageBean.DataBean.NewGoodsBean) newArrival_DataAdapter.getItem(position);
                if (getNew_goods!=null&&getNew_goods.size()>0){
                    HomePageBean.DataBean.NewGoodsBean currentbean = getNew_goods.get(position);

                    if (currentbean != null) {
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id", getGoods_id + "");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }


                }



            }
        });
        newArrival_DataAdapter.setData(getNew_goods);


//        mJobDataAdapter.setData(datas);
    }

    public void newArrivDataLive(RecyclerView new_arrive_recyclerView, List<HomePageBean.DataBean.LiveBean> getNew_goods) {
        final JoneBaseAdapter newArrival_DataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.LiveBean>(new_arrive_recyclerView, R.layout.item_hot_products2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomePageBean.DataBean.LiveBean model) {

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));
            }
        };
        //LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        new_arrive_recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        newArrival_DataAdapter.setData(getNew_goods);
        new_arrive_recyclerView.setAdapter(newArrival_DataAdapter);
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                //LivePlayListActivity.open(_mActivity);
                HomePageBean.DataBean.LiveBean currentbean = (HomePageBean.DataBean.LiveBean) newArrival_DataAdapter.getItem(position);
                LivePalyActivity.open(getActivity(), currentbean.getId() + "", currentbean.getLive_url()
                        , "", "0", currentbean.getLike_num() + "",
                        "",currentbean.getAnchor_id() + "");
            }
        });

//        mJobDataAdapter.setData(datas);
    }

    public void newArrivAnno(RecyclerView new_arrive_recyclerView, List<HomePageBean.DataBean.AppOBean> getNew_goods) {
        final JoneBaseAdapter newArrival_DataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.AppOBean>(new_arrive_recyclerView, R.layout.item_hot_products2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomePageBean.DataBean.AppOBean model) {

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));
            }
        };
        //LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        new_arrive_recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        newArrival_DataAdapter.setData(getNew_goods);
        new_arrive_recyclerView.setAdapter(newArrival_DataAdapter);

        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                //LivePlayListActivity.open(_mActivity);
                HomePageBean.DataBean.AppOBean currentbean = (HomePageBean.DataBean.AppOBean) newArrival_DataAdapter.getItem(position);
                LiveAnchorDetailActivity.open(getActivity(), currentbean.getAnchor_id() + "");
            }
        });

//        mJobDataAdapter.setData(datas);
    }

    public void newArrivDataPlayBack(RecyclerView new_arrive_recyclerView, List<HomePageBean.DataBean.PlayBackBean> getNew_goods) {
        final JoneBaseAdapter newArrival_DataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.PlayBackBean>(new_arrive_recyclerView, R.layout.item_hot_products2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomePageBean.DataBean.PlayBackBean model) {

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));
            }
        };
        new_arrive_recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        newArrival_DataAdapter.setData(getNew_goods);
        new_arrive_recyclerView.setAdapter(newArrival_DataAdapter);
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                //LivePlayListActivity.open(_mActivity);
                HomePageBean.DataBean.PlayBackBean currentbean = (HomePageBean.DataBean.PlayBackBean) newArrival_DataAdapter.getItem(position);
                PlayVideoActivity.open(getActivity(), currentbean.getVideo_url());
            }
        });

//        mJobDataAdapter.setData(datas);
    }

    public void getHeader() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
//        token=6900314512ee92d52ff0e3e59c550a13
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);

        RetrofitClient.getApiService(API.APP_QING_GONG)
                .personCenter(map)
                .compose(RequestManager.<PersonCenterBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
//                        mView.stockLoginFinish();
                    }
                })
                .subscribe(new Consumer<PersonCenterBean>() {
                               @Override
                               public void accept(PersonCenterBean userInfo) throws Exception {
                                   if(userInfo.getCode()!=1){
                                      ActivityUtils.startActivity(getContext(), AppLoginActivity.class);       //token过期跳转。
                                   }
                                   PersonCenterBean.DataBean.UserInfoBean getUserInfo = userInfo.getData().getUserInfo();
                                   if (!StringUtils.isEmpty(getUserInfo.getAvatarUrl())) {
                                       Glide.with(_mActivity).load(getUserInfo.getAvatarUrl()).into((ImageView) message_view);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );

    }


    public void loadRecommend() {
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recommend_recyclerview.setLayoutManager(gridLayoutManager);

/*        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 5), true);
        recommend_recyclerview.addItemDecoration(gridSpacingItemDecoration);*/
        //   recommend_recyclerview.setHasFixedSize(true);
        recommend_DataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.RecommendGoodsBean>(recommend_recyclerview, R.layout.item_hot_products) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomePageBean.DataBean.RecommendGoodsBean model) {
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



                helper.setItemChildClickListener(R.id.chat_tv);
                helper.setItemChildClickListener(R.id.iv_shop_car);
            }
        };
        recommend_recyclerview.setAdapter(recommend_DataAdapter);
       /* List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }*/

        recommend_DataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                HomePageBean.DataBean.RecommendGoodsBean currentbean = recommend_DataAdapter.getItem(position);
                switch (childView.getId()) {
                    case R.id.chat_tv:
                        if (recommend_DataAdapter!=null){
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


        recommend_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (recommend_DataAdapter != null) {
                    HomePageBean.DataBean.RecommendGoodsBean currentbean = recommend_DataAdapter.getItem(position);
                    if (currentbean != null) {
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id", getGoods_id + "");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }
                }

            }
        });
    }

    @OnClick({
            R.id.message_rel, R.id.search_bg})
    public void onViewClicked(View view) {
        switch (view.getId()) {



           /* case R.id.class_recommend://课程推荐
                Bundle classBundle = new Bundle();
                classBundle.putInt("type", 3);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
                break;*/
            case R.id.message_rel://个人红心
                Bundle messageBundle = new Bundle();
                messageBundle.putInt("type", 25);
                PlatformForFragmentActivity.newInstance(_mActivity, messageBundle);

                //TestActivity.open(getActivity());
                break;

         /*   case R.id.activity_image://营销活动
                if (getActivity != null) {
                    ActivityArticleBean.DataBean.ListBean currentbean = getActivity;

                    String getParam = currentbean.getId() + "";
                    if (!StringUtils.isEmpty(getParam)) {
                        WebviewActivity.newIntance(_mActivity, getParam, "活动详情接口");
                    }

                }

                break;*/


            case R.id.search_bg:
                Bundle searchBundle = new Bundle();
                searchBundle.putInt("type", 30);
                PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                break;
            /*     case R.id.fenxi_lin:
             *//*进入k线*//*
                // + "=="+
                if (getFx != null) {
                    if (!StringUtils.isEmpty(getFx.getMarket()) & !StringUtils.isEmpty(getFx.getCode())) {
                        Bundle Bundle = new Bundle();
                        Bundle.putInt("type", 7);
                        Bundle.putString(Constant.mark, getFx.getMarket());
                        Bundle.putString(Constant.code, getFx.getCode());
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
                    }
                }

                break;*/
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }


    HomeBannerBean.DataBean.Market.FxBean getFx;


    @Override
    public void HomeTask(HomePageBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                HomePageBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    List<HomePageBean.DataBean.BannerBean> getBanner = getData.getBanner();
                    if (getBanner != null && getBanner.size() > 0) {
                        showBanner(loop_viewpager_mz, getBanner);
                    }

                    List<HomePageBean.DataBean.HotGoodsBean> getHot_goods = getData.getHot_goods();
                    if (getHot_goods != null && getHot_goods.size() > 0) {
                        mJobDataAdapter.setData(getHot_goods);
                    }

                    live = getData.getLive();
                    appo = getData.getAppo();
                    playback = getData.getPlayback();

                    if (live != null && live.size() > 0) {
                        newArrivDataLive(recyclerView_live, live);
                    }

                    List<HomePageBean.DataBean.RecommendGoodsBean> getRecommend_goods = getData.getRecommend_goods();
                    if (getRecommend_goods != null && getRecommend_goods.size() > 0) {
                        recommend_DataAdapter.setData(getRecommend_goods);
                    }

                    List<HomePageBean.DataBean.NewGoodsBean> getNew_goods = getData.getNew_goods();
                    Log.i("test","getNew_goods="+getNew_goods.size());

                    //向上取整
                    pageSize = (int)Math.ceil((double)getNew_goods.size()/8)-1;
                    //向下取整
                    //pageSize = (int)Math.ceil((double)getNew_goods.size()/8);

                    listBanner = new ArrayList<>();
                    listBanner.clear();

                    for (int i = 0; i < getNew_goods.size(); i++) {
                        int position = i / 8;
                        if (listBanner.size() - 1 < position) {
                            BannerShopBean bannerShopBean = new BannerShopBean();
                            bannerShopBean.getNew_goods.add(getNew_goods.get(i));
                            listBanner.add(position, bannerShopBean);
                        } else {
                            listBanner.get(position).getGetNew_goods().add(getNew_goods.get(i));
                        }
                    }
                    showBannerShop(loop_viewpager_shop, listBanner);
                }
            }
        }
    }

    @Override
    public void add_addCar(DeleteCarBean userInfo) {

    }

    @Override
    public void showToast(String content) {

    }

   /* public void loadMarketData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 3, LinearLayoutManager.VERTICAL, false);
        home_market_recyclerView.setLayoutManager(gridLayoutManager);
//       recycler_result.addItemDecoration(new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 10), true));
        marketDataAdapter = new JoneBaseAdapter<MultiMarketBean>(home_market_recyclerView, R.layout.item_market_index) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, MultiMarketBean model) {
                int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                View hangqing = helper.getView(R.id.hangqing);
//                hangqing.setLayoutParams(new ViewGroup.LayoutParams(
//                        width / 3, ViewGroup.LayoutParams.WRAP_CONTENT));
                if (model==null)return;
                if (model.getMarket()==null)return;
                helper.setText(R.id.hangqing_name, model.getMarket().getName() + "");
                helper.setText(R.id.new_price, model.getClose() + "");
//                helper.setText(R.id.left_pre,  "");
                try {
                    String zhangfu = dfone.format((model.getClose() - model.getPrev_close()) / model.getPrev_close() * 100);
                    helper.setText(R.id.right_pre, zhangfu + "%");
                    double zhangdiezhi = model.getClose() - model.getPrev_close();
                    String onepoint = dfone.format(zhangdiezhi);
                    if (zhangdiezhi > 0) {
                        helper.setText(R.id.left_pre, "+" + onepoint + "%");
                        helper.setTextColor(R.id.left_pre, Color.parseColor("#ff0000"));
                    } else if (zhangdiezhi < 0) {
                        helper.setText(R.id.left_pre, "" + onepoint + "%");
                        helper.setTextColor(R.id.left_pre, Color.parseColor("#00ff00"));
                    } else {
                        helper.setText(R.id.left_pre, "" + onepoint + "%");
                        helper.setTextColor(R.id.left_pre, Color.parseColor("#00ff00"));
                    }

                } catch (Exception throwable) {

                }
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onAttachedToRecyclerView(recyclerView);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (manager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return 1;
                        }
                    });
                }
            }
        };
        marketDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                *//*进入k线*//*
                Bundle Bundle = new Bundle();
                Bundle.putInt("type", 7);
                 if (marketDataAdapter.getItem(position)==null)return;
                Bundle.putString(Constant.mark, marketDataAdapter.getItem(position).getMark());
                Bundle.putString(Constant.code, marketDataAdapter.getItem(position).getCode());
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
            }
        });

        home_market_recyclerView.setHasFixedSize(true);
        home_market_recyclerView.setAdapter(marketDataAdapter);
    }*/







    /*
     * 处理历史k线
     *
     * */


    class articleOnclick implements View.OnClickListener {
        String id;

        public articleOnclick(String articleid) {
            this.id = articleid;
        }

        @Override
        public void onClick(View v) {
            WebviewActivity.newIntance(_mActivity, id, "文章");
        }
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        userisconnect();


    }

    @Override
    public void onResume() {
        super.onResume();
        flag = 1;
        String username = (String) CacheHelper.get(_mActivity, "username", "");

    }


    //view的显示和隐藏
    public static void setViewVisable(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
    }


    public static void setViewGone(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
        }
    }


    public void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false);
        home_product_recyclerView.setLayoutManager(layoutManager);
        home_product_recyclerView.setHasFixedSize(true);
        home_product_recyclerView.addItemDecoration(new HorizontalItemDecoration(10, mContext));//10表示10dp

        //home_product_recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, DensityUtil.dip2px(mContext, 10), true));
        mJobDataAdapter = new JoneBaseAdapter<HomePageBean.DataBean.HotGoodsBean>(home_product_recyclerView, R.layout.item_hot_products2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomePageBean.DataBean.HotGoodsBean model) {

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));
            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter != null) {
                    HomePageBean.DataBean.HotGoodsBean currentbean = mJobDataAdapter.getItem(position);
                    if (currentbean != null) {
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id", getGoods_id + "");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }
                }

            }
        });

        //mJobDataAdapter.setData(datas);
        home_product_recyclerView.setAdapter(mJobDataAdapter);
    }




    /*
     * 0:跳到网页 1:跳转到文章 2:跳到课程
     *
     * */
    public class TopBannerDelegate implements BGABanner.Delegate<ImageView, String> {
        @Override
        public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
            if (getSwipers == null) return;
            HomeBannerBean.DataBean.SwipersBean advResourcePubRecord = getSwipers.get(position);
            if (advResourcePubRecord != null) {
                int getType = advResourcePubRecord.getType();
                String getParam = advResourcePubRecord.getParam();
                chooseIntoWebview(getType, getParam);
//                Toast.makeText(_mActivity,advResourcePubRecord.getImage(),Toast.LENGTH_LONG).show();
//                handleAdvResourcePubRecord(advResourcePubRecord);
            }
        }
    }


    public void chooseIntoWebview(int getType, String getParam) {
        switch (getType) {
            case 0://0:跳到网页
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "网页");
                }
                break;
            case 1:// 1:跳转到文章
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "文章");
                }
                break;
            case 2:// 2:跳到课程
                if (!StringUtils.isEmpty(getParam)) {
                    if (!StringUtils.isEmpty(getParam)) {
                        WebviewActivity.newIntance(_mActivity, getParam, "课堂详情接口");
                    }
                }
                break;
        }
    }


    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }


    class newHandTask implements View.OnClickListener {
        int taskID;

        public newHandTask(int inputid) {
            this.taskID = inputid;
        }

        @Override
        public void onClick(View v) {
            if (taskID == 1) {
                if (getAd != null) {
                    int getType = getAd.getType();
                    String getParam = getAd.getParam();

                    CacheHelper.setAlias(_mActivity, "getType", getType + "");
                    CacheHelper.setAlias(_mActivity, "getParam", getParam + "");
                    chooseIntoWebview(getType, getParam);
                }
            } else if (taskID == 2) {
                if (getFx != null) {
                    if (!StringUtils.isEmpty(getFx.getMarket()) & !StringUtils.isEmpty(getFx.getCode())) {
                        Bundle Bundle = new Bundle();
                        Bundle.putInt("type", 7);
                        Bundle.putString(Constant.mark, getFx.getMarket());
                        Bundle.putString(Constant.code, getFx.getCode());
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
                    }
                }
            } else if (taskID == 3) {
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 9);
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
            }
            if (taskID == 4) {
                lookCaishang();
            }

        }
    }


    public void lookCaishang() {
        String caishang_id = CacheHelper.getAlias(_mActivity, "caishang_id");
        if (!StringUtils.isEmpty(caishang_id)) {
            WebviewActivity.newIntance(_mActivity, caishang_id, "课堂详情接口");
        }
    }

    private void getadd_car(String goods_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("goods_id", goods_id + "");
        map.put("token", getToken);
        getPresenter().submit_addcar(map);
    }



    /*private class NewArrivalsAdapter extends BannerAdapter{

        public NewArrivalsAdapter(List<BannerShopBean> datas) {
            super(datas);
        }

        @Override
        public Object onCreateHolder(ViewGroup parent, int viewType) {
            RecyclerView recyclerView = new RecyclerView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return new NewArrivalsAdapter.BannerViewHolder(recyclerView);

        }

        @Override
        public void onBindView(Object holder, Object data, int position, int size) {
            //holder.imageView.setImageResource(data.imageRes);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {
            RecyclerView recyclerView;

            public BannerViewHolder(@NonNull RecyclerView view) {
                super(view);
                this.recyclerView = view;
            }
        }
    }*/



}
