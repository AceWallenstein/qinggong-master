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
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.AboutOursBean;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.FragmentAboutOursContract;
import com.national.qinggong.contract.NewsListContract;
import com.national.qinggong.presenter.FragmentAboutOursPresenter;
import com.national.qinggong.presenter.NewsListPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.WebviewActivity;
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
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * 关于我们
 * */
public class FragmentAboutOurs extends BaseFragment implements FragmentAboutOursContract.View{

    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loop_viewpager_mz;
    @BindView(R.id.normal_indicator)
    RectIndicator rect_indicator;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;


    private JoneBaseAdapter<AboutOursBean.Data.CategoryList> newArrival_DataAdapter;

    public static FragmentAboutOurs  newInstance() {
        Bundle args = new Bundle();
        FragmentAboutOurs fragment = new FragmentAboutOurs();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        newArrivData();
        initBanner();
        getAoutOurs();
    }

    private void getAoutOurs() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        getPresenter().getAboutOurs(map);
    }

    @Override
    protected FragmentAboutOursPresenter getPresenter() {
        return new FragmentAboutOursPresenter(_mActivity, FragmentAboutOurs.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about_ours;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }
    }


    public void initBanner() {
        loop_viewpager_mz.addIndicator(rect_indicator);
        RectBean rectBean = new RectBean();
        rectBean.horizonMargin = 30;
        rectBean.normalColor = _mActivity.getResources().getColor(R.color.color_no_choose);
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
        bean.loopMaxCount=2;
        bean.smoothScrollTime = 400;
        bean.loopTime = 5000;
        bean.transFormer = BannerTransType.DEPATH;

        loop_viewpager_mz.addPageBean(bean)
                .addIndicator(rect_indicator)
                .setCurrentPosition(1);
    }

    @Override
    public void refreshNews(AboutOursBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.code;
            if (getCode == 1) {
                AboutOursBean.Data getData = userInfo.data;
                if (getData != null) {
                    List<AboutOursBean.Data.Banner> getBanner = getData.banner;
                    if (getBanner != null && getBanner.size() > 0) {
                        showBanner(loop_viewpager_mz, getBanner);
                        if (getBanner.size()==1){
                            rect_indicator.setVisibility(View.INVISIBLE);
                        }else{
                            rect_indicator.setVisibility(View.VISIBLE);
                        }
                    }

                    List<AboutOursBean.Data.CategoryList> getRecommend_goods = getData.categoryList;
                    if (getRecommend_goods != null && getRecommend_goods.size() > 0) {
                        newArrival_DataAdapter.setData(getRecommend_goods);
                    }
                }
            }
        }
    }


    private void showBanner(BannerViewPager bannerViewPager, List<AboutOursBean.Data.Banner> getBanner) {

        bannerViewPager.setPageListener(R.layout.loop_layout, getBanner, new PageHelperListener<AboutOursBean.Data.Banner>() {


            @Override
            public void bindView(View view, final AboutOursBean.Data.Banner data, int position) {
                ImageView imageView = view.findViewById(R.id.loop_icon);
                GlideUtils.loadImageByUrl(data.image.file_path, imageView);

            }

            @Override
            public void onItemClick(View view, AboutOursBean.Data.Banner data, int position) {
                super.onItemClick(view, data, position);
//                Toast.makeText(_mActivity, data.url + " " + position, Toast.LENGTH_SHORT).show();
                /*Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);*/
            }
        });
    }





    public void newArrivData() {
        newArrival_DataAdapter = new JoneBaseAdapter<AboutOursBean.Data.CategoryList>(recycleview, R.layout.item_about_ours) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, AboutOursBean.Data.CategoryList model) {
                helper.setText(R.id.tv_title, model.name + "");

 /*
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);*/
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                /*Glide.with(_mActivity).load(model.image.file_path)
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image)); */
                Glide.with(_mActivity).load(model.image.file_path).into((ImageView) helper.getView(R.id.iv_bg));
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        recycleview.setLayoutManager(layoutManager);
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(newArrival_DataAdapter);

        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                AboutOursBean.Data.CategoryList currentbean = newArrival_DataAdapter.getItem(position);

                String getParam = currentbean.category_id;
                switch (getParam){
                    case "10001":
                        Bundle pinpaiBundle = new Bundle();
                        pinpaiBundle.putInt("type", 28);
                        pinpaiBundle.putString("into_type","pingpai");//pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                        pinpaiBundle.putString("title", currentbean.name);
                        PlatformForFragmentActivity.newInstance(_mActivity, pinpaiBundle);
                        break;
                    case "10011":
                        Bundle shipBundle = new Bundle();
                        shipBundle.putInt("type", 28);
                        shipBundle.putString("into_type","huoban");//pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                        shipBundle.putString("title", currentbean.name);
                        PlatformForFragmentActivity.newInstance(_mActivity, shipBundle);
                        break;
                    case "10012":
                        Bundle classBundle = new Bundle();
                        classBundle.putInt("type", 27);
                        classBundle.putString("title", currentbean.name);
                        PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
                        break;
                    case "10013":
                        Bundle honorBundle = new Bundle();
                        honorBundle.putInt("type", 28);
                        honorBundle.putString("into_type","rongyu");//pingpai(pingpai：品牌,huoban：合作伙伴,rongyu：荣
                        honorBundle.putString("title", currentbean.name);
                        PlatformForFragmentActivity.newInstance(_mActivity, honorBundle);
                        break;
                }
            }
        });
    }

    @Override
    public void showToast(String content) {

    }
}
