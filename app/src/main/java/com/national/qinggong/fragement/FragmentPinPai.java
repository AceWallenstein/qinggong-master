package com.national.qinggong.fragement;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SysArticleBean;
import com.national.qinggong.contract.PinPaiContract;
import com.national.qinggong.presenter.NewsListPresenter;
import com.national.qinggong.presenter.PinPaiPresenter;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/*
* 品牌介绍
* */
public class FragmentPinPai extends BaseFragment implements PinPaiContract.View {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.tv_title_head_view)
    TextView tv_title_head_view;

    private static final String TAG = "FragmentPinPai";
    private String flay_Content = "";
    private String title = "";
    private String into_type;


    public static FragmentPinPai newInstance(String into_type,String title) {
        Bundle args = new Bundle();
        FragmentPinPai fragment = new FragmentPinPai();
        args.putString("into_type", into_type);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flay_Content = getArguments().getString("into_type");
        title = getArguments().getString("title");


    }
    @Override
    protected void initdata() {
        if (flay_Content == null) return;
        switch (flay_Content) {
            case "pingpai":////pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                getNewListInfo(flay_Content);
             break;
            case "huoban":////pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                getNewListInfo(flay_Content);
                break;
            case "rongyu":////pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                getNewListInfo(flay_Content);
                break;
        }
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });
        if (!TextUtils.isEmpty(title)){
            tv_title_head_view.setText(title);
        }
        setWebView("");
    }

    private void getNewListInfo(String  type) {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("type", type);
        getPresenter().getNewList(map);
    }
    @Override
    protected PinPaiPresenter getPresenter() {
        return new PinPaiPresenter(_mActivity, FragmentPinPai.this);
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

        if (!StringUtils.isEmpty(flay_Content)) {
            if (flay_Content.equals("网页")) {
                if (url.startsWith("http")) {
                    webView.loadUrl(url);
                } else {
                    webView.loadUrl("https://" + url);
                }
            } else {
                webView.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);
            }
        }


//

//            showDialog();

        //        webView.setWebChromeClient(wvcc);

        //oppo手机网页放大
        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        Log.d(TAG, "screenDensity = " + screenDensity);
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
    private class WebViewClients extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (pb != null) {
                pb.setProgress(newProgress);
                if (newProgress == 100) {
                    pb.setVisibility(View.GONE);
                }
            }

            super.onProgressChanged(view, newProgress);
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }
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
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pinpai_jieshao;
    }

    @Override
    public void refreshNews(SysArticleBean userInfo) {
    if (userInfo!=null){
        SysArticleBean.DataBean getData = userInfo.getData();
        if (getData!=null){
            Spanned spanned  = Html.fromHtml(getData.getContent());
            Log.i("==66666666666666=",spanned.toString());
            webView.loadDataWithBaseURL(null,setWebVIewImage(getData.getContent()), "text/html", "UTF-8", null);

        }
    }
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

    public static String setWebVIewImagetwo(String star) {
        String head = "<HTML><HEAD><LINK href=\"article.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>";
        return "<html>" + head + "<body>" + star + "</body></html>";

    }
    @Override
    public void showToast(String content) {

    }
}
