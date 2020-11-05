package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
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
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.bean.ActivityDetailBean;
import com.national.qinggong.bean.ArticleDetailBean;
import com.national.qinggong.bean.ClassRoomDetailBean;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.NewsDetailBean;
import com.national.qinggong.contract.PlatformWebDeatilContract;
import com.national.qinggong.presenter.WebDetailPresenter;
import com.national.qinggong.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WebviewActivity extends BaseActivity implements PlatformWebDeatilContract.View {
    private static final String TAG = "WebviewActivity";
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.webview_content)
    TextView webview_content;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.study_people)
    TextView study_people;

    @BindView(R.id.title_lin_content)
    LinearLayout title_lin_content;


    private ClassTypeBean.DataBean.ListBean itembean;
    private ActivityArticleBean.DataBean.ListBean activityitembean;

    public static final String Falg = "WEBURL";
    private String flay_Content = "";
    private String content;
    private String parames_id;

    /*
     * 携带url参数
     * */
    public static void newIntance(Context context, String url_Parames, String flag) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("Parames", url_Parames);
        intent.putExtra(Falg, flag);
        context.startActivity(intent);
    }


    public static void newIntance(Context context, ClassTypeBean.DataBean.ListBean currentbean, String flag) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("itembean", currentbean);
        intent.putExtra(Falg, flag);
        context.startActivity(intent);
    }

    public static void newIntance(Context context, ActivityArticleBean.DataBean.ListBean currentbean, String flag) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra("itembean", currentbean);
        intent.putExtra(Falg, flag);
//        intent.putExtra(TITLE, title);
//        intent.putExtra(TITLEEVENT, titleEvent);
        context.startActivity(intent);
    }

    private void getArticleInfo(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("articleId", id);
        getPresenter().getArticleInfo(map);
    }

    /*
     * 课程详情
     *  /courseId=2
     * */
    private void getClassDetail(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("courseId", id);
        getPresenter().getClassRoomDetailInfo(map);
    }


    private void getActivityDetail(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("activityId", id);
        getPresenter().getActivityDetailInfo(map);
    }
    /*新闻*/
    private void getNewDetail(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("article_id", id);
        map.put("wxapp_id", "10001");
        getPresenter().getNewsDetailInfo(map);
    }


    @Override
    protected WebDetailPresenter getPresenter() {
        return new WebDetailPresenter(WebviewActivity.this, WebviewActivity.this);
    }

    @Override
    protected void initdata() {
        if (getIntent() != null) {
            flay_Content = getIntent().getStringExtra(Falg);
            if (flay_Content == null) return;
            switch (flay_Content) {
                case "活动":
                    tv_title.setText("活动详情");
                    activityitembean = (ActivityArticleBean.DataBean.ListBean) getIntent().getSerializableExtra("itembean");
                    content = activityitembean.getContent();
                    setData(activityitembean.getTitle(), activityitembean.getCreate_at(), activityitembean.getViews() + "");//人已学习
                    break;
                case "课堂":
                case "我的文章":
                    itembean = (ClassTypeBean.DataBean.ListBean) getIntent().getSerializableExtra("itembean");
                    if (itembean != null) {
                        content = itembean.getContent();
//             setWebView("https://www.cyzone.cn/article/583370.html");
                        setData(itembean.getTitle(), itembean.getCreate_at(), itembean.getViews() + "");//人已学习
                        if (flay_Content.equals("我的文章")){
                            tv_title.setText("文章详情");
                        }else {
                            tv_title.setText("课堂详情");
                        }
                    }
                    break;

                case "网页":
                    title_lin_content.setVisibility(View.GONE);
                    tv_title.setText("详情");
                    content = getIntent().getStringExtra("Parames");

                    break;
                case "文章":
                    tv_title.setText("文章");
                    parames_id = getIntent().getStringExtra("Parames");
                    Log.i("wenzhang", parames_id + "");
                    getArticleInfo(parames_id);
                    break;
                case "课堂详情接口":
                    tv_title.setText("课堂详情");
                    parames_id = getIntent().getStringExtra("Parames");
                    Log.i("wenzhang", parames_id + "");
                    getClassDetail(parames_id);
                    break;
                case "活动详情接口":
                    tv_title.setText("活动详情");
                    parames_id = getIntent().getStringExtra("Parames");
                    Log.i("wenzhang", parames_id + "");
                    getActivityDetail(parames_id);
                    break;
                case "新闻详情":
                    tv_title.setText("");
                    parames_id = getIntent().getStringExtra("Parames");
                    Log.i("NEWS===", parames_id + "");
                    getNewDetail(parames_id);
                    break;

            }

            setWebView(content);
        }
    }


    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                WebviewActivity.this.finish();
                break;

        }
    }
    /*
    * shzhi
    *     webview_content.setText(itembean.getTitle());
     time.setText(itembean.getCreate_at());
     study_people.setText(itembean.getViews()+"人已学习");
    * */

    public void setData(String content, String createtime, String views_num) {
        webview_content.setText(content);
        time.setText(createtime);
        study_people.setText(views_num);
    }


    private void setWebView(String url) {
        Log.e(TAG, "setWebView: ss");
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

    /*
     * {"code":0,"msg":"文章不存在"}*/
    @Override
    public void refreshArticleInfo(ArticleDetailBean userInfo) {
        if (userInfo != null) {
//文章获取后
            if (userInfo.getCode() == 0) {
                if (!StringUtils.isEmpty(userInfo.getMsg())) {
                    Toast.makeText(WebviewActivity.this, userInfo.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {//文章存在
                ArticleDetailBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getArticle() != null) {
                        ArticleDetailBean.DataBean.ArticleBean getArticle = getData.getArticle();
                        setData(getArticle.getTitle(), getArticle.getCreate_at(), getArticle.getViews() + "");//"人已学习"
                        webView.loadDataWithBaseURL(null, getArticle.getContent(), "text/html", "UTF-8", null);
                    } else {
                        finish();
                    }

                }


            }


        }
    }

    @Override
    public void refreshClassInfo(ClassRoomDetailBean userInfo) {
        if (userInfo != null) {
//文章获取后
            if (userInfo.getCode() == 0) {
                if (!StringUtils.isEmpty(userInfo.getMsg())) {
                    Toast.makeText(WebviewActivity.this, userInfo.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {//课堂存在
                ClassRoomDetailBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getCourse() != null) {
                        ClassRoomDetailBean.DataBean.CourseBean getArticle = getData.getCourse();
                        setData(getArticle.getTitle(), getArticle.getCreate_at(), getArticle.getViews() + "");//人已学习
                        webView.loadDataWithBaseURL(null, getArticle.getContent(), "text/html", "UTF-8", null);
                    } else {
                        finish();
                    }

                }


            }


        }
    }

    @Override
    public void getActivityInfo(ActivityDetailBean userInfo) {
        if (userInfo != null) {
//文章获取后
            if (userInfo.getCode() == 0) {
                if (!StringUtils.isEmpty(userInfo.getMsg())) {
                    Toast.makeText(WebviewActivity.this, userInfo.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {//课堂存在
                ActivityDetailBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getActivity() != null) {
                        ActivityDetailBean.DataBean.ActivityBean getArticle = getData.getActivity();
                        setData(getArticle.getTitle(), getArticle.getCreate_at(), getArticle.getViews() + "");//人已学习
                        webView.loadDataWithBaseURL(null, getArticle.getContent(), "text/html", "UTF-8", null);
                    } else {
                        finish();
                    }

                }


            }


        }
    }

    @Override
    public void getNewDetailInfo(NewsDetailBean userInfo) {
        if (userInfo != null) {
//文章获取后
            if (userInfo.getCode() == 0) {
                if (!StringUtils.isEmpty(userInfo.getMsg())) {
                    Toast.makeText(WebviewActivity.this, userInfo.getMsg(), Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {//课堂存在
                NewsDetailBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getDetail() != null) {
                        NewsDetailBean.DataBean.DetailBean getArticle = getData.getDetail();
                        setData(getArticle.getArticle_title(), getArticle.getView_time(), getArticle.getShow_views() + "");//人已学习
                        webView.loadDataWithBaseURL(null,setWebVIewImage(getArticle.getArticle_content()), "text/html", "UTF-8", null);
                    } else {
                        finish();
                    }

                }


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
    @Override
    public void showToast(String content) {

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

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_load_web;
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
}
