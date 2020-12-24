package com.national.qinggong.ui.activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PrivacyPolicyActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_web)
    ImageView ivWeb;
    private String url, name;


    @Override
    protected void initdata() {
        initView();
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.privacy_policcy_web_view;
    }

    private void initView() {
        url = "http://qgshop.meiliancheng.cn/yszc.html";


        ivWeb.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);
        // 开启JavaScript支持
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");

        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        mWebView.getSettings().setSupportZoom(false);

        // 设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        mWebView.getSettings().setBuiltInZoomControls(true);

        // 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        mWebView.getSettings().setDomStorageEnabled(true);

        // 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        mWebView.requestFocus();

        // 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // 设置webview加载的页面的模式,缩放至屏幕的大小
        mWebView.getSettings().setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebView.getSettings().setBlockNetworkImage(false);
        // 加载链接
        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // 在开始加载网页时会回调
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 拦截 url 跳转,在里边添加点击链接跳转或者操作
                if(url.startsWith("http://")||url.startsWith("https://")){
                    view.loadUrl(url);
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 在结束加载网页时会回调

                // 获取页面内容
                view.loadUrl("javascript:window.java_obj.showSource("
                        + "document.getElementsByTagName('html')[0].innerHTML);");

                // 获取解析<meta name="share-description" content="获取到的值">
                view.loadUrl("javascript:window.java_obj.showDescription("
                        + "document.querySelector('meta[name=\"share-description\"]').getAttribute('content')"
                        + ");");
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest request) {
                // 在每一次请求资源时，都会通过这个函数来回调
                return super.shouldInterceptRequest(view, request);
            }

        });
    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {
        finish();
    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            System.out.println("====>html=" + html);
        }

        @JavascriptInterface
        public void showDescription(String str) {
            System.out.println("====>html=" + str);
        }
    }
}