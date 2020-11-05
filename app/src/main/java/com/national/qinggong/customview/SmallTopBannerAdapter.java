package com.national.qinggong.customview;

import android.view.View;
import android.widget.ImageView;


import com.national.qinggong.R;
import com.national.qinggong.util.GlideUtils;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by kai on 2018/9/27.
 */

public class SmallTopBannerAdapter<V extends View, M> implements BGABanner.Adapter<V,M> {

    @Override
    public void fillBannerItem(BGABanner banner, V itemView, M model, int position) {
        GlideUtils.loadImageByUrlbanner((String) model, (ImageView) itemView, R.mipmap.default_banner);
    }
}
