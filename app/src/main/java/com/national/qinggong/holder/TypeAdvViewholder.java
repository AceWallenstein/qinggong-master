package com.national.qinggong.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.bean.HomeBannerBean;
import com.national.qinggong.bean.MultipleHomeBean;
import com.national.qinggong.customview.SmallTopBannerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class TypeAdvViewholder extends  TypeAbstractViewHolder {
    private final LayoutInflater layoutInflater;
    private final BGABanner mBannerHomeTop;
    LinearLayout mLlNoBanner;
    Context context;
    public TypeAdvViewholder(View itemView,Context context) {
        super(itemView);
        this.context  =context;
        this.layoutInflater = LayoutInflater.from(context);
        mBannerHomeTop =(BGABanner) itemView.findViewById(R.id.banner_home_top);
        mLlNoBanner =(LinearLayout) itemView.findViewById(R.id.ll_no_banner);

    }
    private List<String> mToplist = new ArrayList<>(); //头部广告链接集合
    List<HomeBannerBean.DataBean.SwipersBean> getSwipers;
    @Override
    public void bindHolder(MultipleHomeBean model) {
//        Log.i("===mToplist=6",model.getAdBeanList()+"===============");
//        testbanner.setTag(0);
        List<HomeBannerBean> getAdBeanList = model.getAdBeanList();
        HomeBannerBean adlist = getAdBeanList.get(0);
        HomeBannerBean.DataBean getData = adlist.getData();
        setViewVisable(mBannerHomeTop);
        setViewGone(mLlNoBanner);

        initBannerView();
        getSwipers = getData.getSwipers();
         mToplist.clear();
        for (HomeBannerBean.DataBean.SwipersBean ad : getSwipers) {
//            mToplist.add(ad.getImage());
            mToplist.add("http://kangaroo.vtui365.com/uploads/20200417/7e349b9eb9ddc1c957e09702e3d1ab35.jpg");
        }
//        Log.i("===mToplist=",mToplist.size()+"===============");
        mBannerHomeTop.setData(mToplist, null);
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
    private void initBannerView() {
        mBannerHomeTop.setAdapter(new SmallTopBannerAdapter<ImageView, String>());
        mBannerHomeTop.setDelegate(new TopBannerDelegate());
    }

    public static void setViewGone(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
        }
    }
    public class TopBannerDelegate implements BGABanner.Delegate<ImageView, String> {
        @Override
        public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
            HomeBannerBean.DataBean.SwipersBean advResourcePubRecord = getSwipers.get(position);
            if (advResourcePubRecord != null) {
                Toast.makeText(context,advResourcePubRecord.getImage(),Toast.LENGTH_LONG).show();
//                handleAdvResourcePubRecord(advResourcePubRecord);
            }
        }
    }
}
