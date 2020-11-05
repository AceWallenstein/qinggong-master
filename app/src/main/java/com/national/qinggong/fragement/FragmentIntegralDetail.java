package com.national.qinggong.fragement;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.IntegralDetailBean;
import com.national.qinggong.bean.IntegralShopBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.RightDuihuanIntegralBean;
import com.national.qinggong.bean.SendIntegralPointsBean;
import com.national.qinggong.contract.IntegralShopingDetailContract;
import com.national.qinggong.presenter.IntegralShopingDetailPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*积分详情*/
public class FragmentIntegralDetail extends BaseFragment implements IntegralShopingDetailContract.View {


    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.jifen_num_first)
    TextView jifenNumFirst;
    @BindView(R.id.shop_guize)
    TextView shopGuize;
    @BindView(R.id.shop_cut)
    ImageView shopCut;
    @BindView(R.id.buy_num)
    TextView buyNum;
    @BindView(R.id.shop_add)
    ImageView shopAdd;

    @BindView(R.id.jifen_num)
    TextView jifenNum;
    @BindView(R.id.right_buy_lin)
    LinearLayout rightBuyLin;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loop_viewpager_mz;
    @BindView(R.id.normal_indicator)
    RectIndicator rect_indicator;
    IntegralDetailBean.DataBean.DetailBean getDetail;
    private String good_id;

    public static FragmentIntegralDetail newInstance(String good_id) {
        Bundle args = new Bundle();
        FragmentIntegralDetail fragment = new FragmentIntegralDetail();
        args.putString("good_id", good_id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        good_id = getArguments().getString("good_id");
    }
    @Override
    protected void initdata() {

        sendIntegralPoint();
        initBanner();
        getIntegralShoping();
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


//        showBanner(loop_viewpager_mz);
    }

    private void showBanner(BannerViewPager bannerViewPager, List<IntegralDetailBean.DataBean.DetailBean.ImageBean> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout, getBanner, new PageHelperListener<IntegralDetailBean.DataBean.DetailBean.ImageBean>() {


            @Override
            public void bindView(View view, final IntegralDetailBean.DataBean.DetailBean.ImageBean data, int position) {
//                setText(view, R.id.loop_text, data.text);
                ImageView imageView = view.findViewById(R.id.loop_icon);
              /*  GlideApp.with(view).load(data.resId)
                        .into(imageView);*/
                GlideUtils.loadImageByUrl(data.getFile_path(), imageView);

            }

            @Override
            public void onItemClick(View view, IntegralDetailBean.DataBean.DetailBean.ImageBean data, int position) {
                super.onItemClick(view, data, position);
//                Toast.makeText(_mActivity, data.url + " " + position, Toast.LENGTH_SHORT).show();
             /*   Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);*/
            }
        });
    }

    @Override
    protected IntegralShopingDetailPresenter getPresenter() {
        return new IntegralShopingDetailPresenter(_mActivity, FragmentIntegralDetail.this);
    }

    private void getIntegralShoping() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("goods_id", good_id);
        map.put("token", getToken);
        getPresenter().IntegralDetailInfo(map);

    }

    public void sendIntegralPoint() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().sendIntegralDetailInfo(map);
    }

    /*
     *立即兑换
     * */
    public void submitRightNow(String goods_id, String goods_num, String goods_sku_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("goods_id", goods_id);
        map.put("goods_num", goods_num);
        map.put("goods_sku_id", "0");
        map.put("wxapp_id", "10001");
        map.put("token",getToken);
        getPresenter().submitRightInfo(map);

    }


    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral_detail;
    }

    private void setWebView(String url) {
        WebSettings webSettings = webView.getSettings();
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);//开启DOM storage API功能
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
        // 设置默认缩放方式尺寸是far
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 设置出现缩放工具
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setDefaultFontSize(20);

        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);

        webView.setLayerType(View.LAYER_TYPE_NONE, null);
        //设置https http兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        webSettings.setDatabaseEnabled(true);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        // 缓存白屏
//        String appCachePath = getApplicationContext().getCacheDir()
//                .getAbsolutePath() + "/webcache";
//        // 设置 Application Caches 缓存目录
//        webSettings.setAppCachePath(appCachePath);
//        webSettings.setDatabasePath(appCachePath);


        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setTextZoom(100); // 通过百分比来设置文字的大小，默认值是100。

        //设置默认编码
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.setWebChromeClient(new WebViewClients());
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setBlockNetworkImage(false);
//       webView.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);


//

//            showDialog();

        //        webView.setWebChromeClient(wvcc);

        //oppo手机网页放大
        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
            case DisplayMetrics.DENSITY_XHIGH:
            case DisplayMetrics.DENSITY_XXHIGH:
            default:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSettings.setDefaultZoom(zoomDensity);


    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
//            showDialog();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    private class WebViewClients extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            if (pb != null) {
//                pb.setProgress(newProgress);
//                if (newProgress == 100) {
//                    pb.setVisibility(View.GONE);
//                }
//            }

            super.onProgressChanged(view, newProgress);
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }
    }

    @Override
    public void IntegralDetailTask(IntegralDetailBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                IntegralDetailBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                     getDetail = getData.getDetail();
                    if (getDetail != null) {
                        String getGoods_name = getDetail.getGoods_name();
                        shopName.setText(StringUtils.isEmpty(getGoods_name) ? "" : getGoods_name);
                        shopGuize.setText(getDetail.getGoods_sku().getGoods_sales()+" exchange, "+getDetail.getGoods_sku().getStock_num()+" pieces left");
//                     shopImage
//                     htConten.setText();
                        String getContent = getDetail.getContent();
                        if (!StringUtils.isEmpty(getContent)) {
                            webView.loadDataWithBaseURL(null, setWebVIewImage(getContent), "text/html", "UTF-8", null);
                        }
                        int getNeed_points_num = getDetail.getNeed_points_num();
                        jifenNumFirst.setText(getNeed_points_num + " Credits");
                        jifenNum.setText(getNeed_points_num + " Credits");

                        List<IntegralDetailBean.DataBean.DetailBean.ImageBean> getImage = getDetail.getImage();
                        if (getImage != null && getImage.size() > 0) {
                            showBanner(loop_viewpager_mz, getImage);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void RightDuihuan(Object userInfo) {
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        JSONObject jsonObject = null;
        RightDuihuanIntegralBean homeBean = new Gson().fromJson(jsoncontent, new TypeToken<RightDuihuanIntegralBean>() {
        }.getType());
        if (homeBean!=null){
            RightDuihuanIntegralBean.DataBean getData = homeBean.getData();
            if (getData.isHas_error()){
                ToastUtilMsg.showToast(_mActivity,getData.getError_msg()+"");
            }else {

                int goods_id=  getDetail.getGoods_id();
                String goods_num = buyNum.getText().toString();
                CacheHelper.setAlias(_mActivity, "good_id", goods_id+ "");
                CacheHelper.setAlias(_mActivity, "goods_num", goods_num+ "");
                Bundle messageBundle = new Bundle();
                messageBundle.putInt("type", 22);
//                messageBundle.putString("goods_id",goods_id);
//                messageBundle.putString("goods_num",goods_num);
//                messageBundle.putString("goods_sku_id",goods_sku_id);
                PlatformForFragmentActivity.newInstance(_mActivity, messageBundle);
            }
        }
    }

    @Override
    public void sendIntegralPointsTask(SendIntegralPointsBean userInfo) {

    }

    @Override
    public void refreshPostfinally() {

    }

    // 适配image和table标签
    public static String setWebVIewImage(String star) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "<style>table{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + star + "</body></html>";

    }

    @Override
    public void showToast(String content) {

    }


    @OnClick({R.id.rl_back, R.id.shop_cut, R.id.shop_add, R.id.right_buy_lin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.shop_cut:
                int curvvaule = Integer.valueOf(buyNum.getText().toString());
                if (curvvaule == 1) {
                    Toast.makeText(getContext(), "已经达到最小数量了", Toast.LENGTH_LONG).show();
                } else {
                    --curvvaule;
                    buyNum.setText(curvvaule + "");
                }
                break;
            case R.id.shop_add:
                int curvvaule_add = Integer.valueOf(buyNum.getText().toString());
                ++curvvaule_add;
                buyNum.setText(curvvaule_add + "");
                break;
            case R.id.right_buy_lin:
                if (getDetail!=null){
                    int goods_id=  getDetail.getGoods_id();
                    String goods_num = buyNum.getText().toString();
                    submitRightNow(goods_id+"",goods_num,"");
                }


                break;
        }
    }
}
