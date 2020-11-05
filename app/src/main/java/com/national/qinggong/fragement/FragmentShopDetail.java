package com.national.qinggong.fragement;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.bean.SendIntegralPointsBean;
import com.national.qinggong.bean.ShopDetailBean;
import com.national.qinggong.contract.ShopDetailBeanContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.HorizontalItemDecoration;
import com.national.qinggong.presenter.ShopDetailBeanPresenter;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.ui.activity.LiveAnchorDetailActivity;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.ScreenUtil;
import com.national.qinggong.util.StringUtils;
import com.national.qinggong.util.ToastUtilMsg;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.bean.RectBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.RectIndicator;
import com.zhengsr.viewpagerlib.type.BannerTransType;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
 *
 * 商品详情*/
public class FragmentShopDetail extends BaseFragment implements ShopDetailBeanContract.View {

    @BindView(R.id.shop_image)
    BannerViewPager shopImage;
    @BindView(R.id._detail_name)
    TextView DetailName;
    @BindView(R.id.shop_detail_content)
    TextView shopDetailContent;
    @BindView(R.id.user_head_image)
    ImageView userHeadImage;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_email)
    TextView userEmail;
    @BindView(R.id.shop_detail_text)
    TextView shop_detail_text;
    @BindView(R.id.chat_lin)
    LinearLayout chatLin;
    @BindView(R.id.call_phone)
    ImageView callPhone;
    @BindView(R.id.add_collect)
    ImageView addCollect;
    @BindView(R.id.shop_image_two)
    ImageView shopImageTwo;
    @BindView(R.id.home_recomment_recyclerView)
    RecyclerView home_product_recyclerView;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;

    @BindView(R.id.detail_chat)
    LinearLayout detail_chat;


    @BindView(R.id.normal_indicator)
    RectIndicator rect_indicator;
    private JoneBaseAdapter<ShopDetailBean.DataBeanX.ListBean.DataBean> mJobDataAdapter;
    private String getKefu;
    private int getUser_id;
    private String good_id;
    private String anchor_id="";

    public static FragmentShopDetail newInstance(String good_id) {
        Bundle args = new Bundle();
        FragmentShopDetail fragment = new FragmentShopDetail();
        args.putString("good_detail_id", good_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        good_id = getArguments().getString("good_detail_id");
    }

    @Override
    protected ShopDetailBeanPresenter getPresenter() {
        return new ShopDetailBeanPresenter(_mActivity, FragmentShopDetail.this);
    }

    @Override
    protected void initdata() {

        loadData();
        getShopDetail(good_id);
        sendIntegralPoint();
        initBanner();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });

        userHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(anchor_id)){
                    LiveAnchorDetailActivity.open(_mActivity, anchor_id);
                }
            }
        });
        ll_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(anchor_id)){
                    LiveAnchorDetailActivity.open(_mActivity, anchor_id);
                }
            }
        });
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
        shopImage.addIndicator(rect_indicator);
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

        shopImage.addPageBean(bean)
                .addIndicator(rect_indicator)
                .setCurrentPosition(1);
        PageBean bean2 = new PageBean();
        bean2.isAutoLoop = false;
        bean2.smoothScrollTime = 400;
        bean2.loopTime = 5000;
        bean2.transFormer = BannerTransType.DEPATH;
        shopImage.addPageBean(bean2)
                .setCurrentPosition(0);
//        showBanner(loop_viewpager_mz);
    }


    private void showBanner(BannerViewPager bannerViewPager, List<ShopDetailBean.DataBeanX.DetailBean.ImageBean> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout, getBanner, new PageHelperListener<ShopDetailBean.DataBeanX.DetailBean.ImageBean>() {


            @Override
            public void bindView(View view, final ShopDetailBean.DataBeanX.DetailBean.ImageBean data, int position) {
//                setText(view, R.id.loop_text, data.text);
                ImageView imageView = view.findViewById(R.id.loop_icon);
              /*  GlideApp.with(view).load(data.resId)
                        .into(imageView);*/
                GlideUtils.loadImageByUrl(data.getFile_path(), imageView);

            }

            @Override
            public void onItemClick(View view, ShopDetailBean.DataBeanX.DetailBean.ImageBean data, int position) {
                super.onItemClick(view, data, position);
//                Toast.makeText(_mActivity, data.url + " " + position, Toast.LENGTH_SHORT).show();
                Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
            }
        });
    }
    public void sendIntegralPoint() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
         RetrofitClient.getApiService(API.APP_QING_GONG)
                        .sendIntegralPoints(map)
                        .compose(RequestManager.<SendIntegralPointsBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                /*记得关掉*/
//                        mView.refreshPostfinally();
                            }
                        })
                        .subscribe(new Consumer<SendIntegralPointsBean>() {
                            @Override
                            public void accept(SendIntegralPointsBean userInfo) throws Exception {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        }
        );
    }

    public void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false);
        home_product_recyclerView.setLayoutManager(layoutManager);
        home_product_recyclerView.setHasFixedSize(true);
        home_product_recyclerView.addItemDecoration(new HorizontalItemDecoration(10, mContext));//10表示10dp
//        home_product_recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, DensityUtil.dip2px(mContext, 10), true));
        mJobDataAdapter = new JoneBaseAdapter<ShopDetailBean.DataBeanX.ListBean.DataBean>(home_product_recyclerView, R.layout.item_hot_products_detail_2) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ShopDetailBean.DataBeanX.ListBean.DataBean model) {

                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNumber() + "");
//
                GlideUtils.loadImageByUrl(model.getFile_path(), (ImageView) helper.getView(R.id.product_image));

                helper.setItemChildClickListener(R.id.iv_shop_car);

            }
        };

        mJobDataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                ShopDetailBean.DataBeanX.ListBean.DataBean currentbean = mJobDataAdapter.getItem(position);
                switch (childView.getId()) {
                    case R.id.iv_shop_car:
                        getadd_car(currentbean.getGoods_id()+"");
                        break;

                }
            }
        });
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (mJobDataAdapter != null) {
                    ShopDetailBean.DataBeanX.ListBean.DataBean currentbean = mJobDataAdapter.getItem(position);
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

        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
//        mJobDataAdapter.setData(datas);
        home_product_recyclerView.setAdapter(mJobDataAdapter);
    }


    private void getShopDetail(String goods_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("goods_id", goods_id + "");
        map.put("token", getToken);
        getPresenter().getShopDetail(map);
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
    protected int getLayoutId() {
        return R.layout.fragment_shop_detail;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    /*
     * 1已收藏，0未收藏）示例：1
     * */
    @Override
    public void refreshShopDetail(ShopDetailBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                ShopDetailBean.DataBeanX getData = userInfo.getData();
                if (getData != null) {
                    ShopDetailBean.DataBeanX.SalemanBean getSaleman = getData.getSaleman();
                    if (getSaleman != null) {
                        getKefu = getSaleman.getPhone();
                        getUser_id = getSaleman.getUser_id();
                        String getNickName = getSaleman.getNickName();
                        anchor_id = getSaleman.getAnchor_id()+"";
                        int getIs_like = getSaleman.getIs_like();
                        if (getIs_like == 1) {
                            addCollect.setImageResource(R.mipmap.hava_collect);
                        } else {
                            addCollect.setImageResource(R.mipmap.shop_detail_collect);
                        }

                        if (!StringUtils.isEmpty(getNickName)) {
                            userName.setText(getNickName);
                        }
                        String getAccount = getSaleman.getAccount();
                        if (!StringUtils.isEmpty(getAccount)) {
                            userEmail.setText(getAccount);
                        }
                        String getAvatarUrl = getSaleman.getAvatarUrl();
                        if (!StringUtils.isEmpty(getAvatarUrl)) {
                            CornerTransform transformation = new CornerTransform(_mActivity, 10);
                            //只是绘制左上角和右上角圆角
                            transformation.setExceptCorner(true, true, true, true);
                            Glide.with(_mActivity).load(getAvatarUrl)
                                    .bitmapTransform(transformation).into(userHeadImage);
                        }
                    }


                    ShopDetailBean.DataBeanX.DetailBean getDetail = getData.getDetail();
                    if (getDetail != null) {
//                        getUser_id=getDetail.getUser_id();
                        List<ShopDetailBean.DataBeanX.DetailBean.ImageBean> imagelist = getDetail.getImage();
                        if (imagelist != null & imagelist.size() > 0) {

                            CornerTransform transformation = new CornerTransform(_mActivity, 10);
                            //只是绘制左上角和右上角圆角
                            transformation.setExceptCorner(true, true, true, true);
                            showBanner(shopImage,imagelist);
                            Glide.with(_mActivity).load(imagelist.get(0).getFile_path())
                                    .bitmapTransform(transformation).into(shopImageTwo);

                        }

                        DetailName.setText(getDetail.getGoods_name());

                        Spanned content = Html.fromHtml(getDetail.getContent());
                        shopDetailContent.setText(content);
                    }
                    List<ShopDetailBean.DataBeanX.DetailBean.MoreInfoBean> more_info = getData.getDetail().getMore_info();
                    String str="";
                    for (int i = 0; i <more_info.size() ; i++) {
                        str=more_info.get(i).getK()+"  "+more_info.get(i).getV();
                    }
                    shop_detail_text.setText(str);
                    ShopDetailBean.DataBeanX.ListBean getList = getData.getList();
                    if (getList != null) {
                        List<ShopDetailBean.DataBeanX.ListBean.DataBean> goodlist = getList.getData();
                        if (goodlist != null & goodlist.size() > 0) {
                            mJobDataAdapter.setData(goodlist);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stockFinish() {

    }

    @Override
    public void add_cancle_collect(CollectBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                List<String> getData = userInfo.getData();
                if (getData != null & getData.size() > 0) {
                    ToastUtilMsg.showToast(_mActivity, "Done");
                    getShopDetail(good_id);
                }
            }
        }
    }

    @Override
    public void add_addCar(DeleteCarBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
              //  String getData = userInfo.getData();
               // if (getData != null) {
                    ToastUtilMsg.showToast(_mActivity,  "Done");
             //   }
            }
        }
    }

    @Override
    public void showToast(String content) {

    }

    @OnClick({R.id.chat_lin, R.id.call_phone, R.id.add_collect, R.id.shop_image_two, R.id.bottom_tab, R.id.add_car_lin, R.id.detail_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_lin:
            case R.id.detail_chat:
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 34);
                Bundle_about.putString("user_id", getUser_id + "");
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                break;
            case R.id.call_phone:
                if (StringUtils.isEmpty(getKefu)) {
                    return;
                }
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.requestEach(Manifest.permission.CALL_PHONE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    if (StringUtils.hasSimCard()) {
                                        Intent phoneIntent = new Intent("android.intent.action.CALL",
                                                Uri.parse("tel:" + getKefu));
                                        //启动
                                        startActivity(phoneIntent);
                                    } else {
                                        Toast.makeText(_mActivity, "您未安装sim卡,无法拨打电话", Toast.LENGTH_SHORT).show();
                                    }
                                    return;
                                }
                                if (permission.shouldShowRequestPermissionRationale) {// // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                                    return;
                                } else {
                                    Toast.makeText(_mActivity, "权限被拒绝，当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限", Toast.LENGTH_SHORT).show();
//                                // 用户拒绝了该权限，而且选中『不再询问』那么下次启动时，就不会提示出来了，
//                            startAppSettings();
                                }

                            }
                        });

                break;
            case R.id.add_collect:


                cancleCollectInfo(getUser_id + "");


                break;
            case R.id.add_car_lin:
                getadd_car(good_id + "");
                break;

            case R.id.shop_image_two:
                break;
            case R.id.bottom_tab:
                break;
        }
    }

    private void cancleCollectInfo(String sales_user_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("sales_user_id", sales_user_id + "");
        map.put("token", getToken);
        getPresenter().submitCollectinfo(map);
    }
}
