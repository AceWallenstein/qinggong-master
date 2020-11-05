package com.national.qinggong.util;

import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.national.qinggong.MyApplication;

import javax.sql.DataSource;

/**
 *

 */

public class GlideUtils {

    /**
     *通过uri展示图片 设置占位图
     * @param imgUrl 图片url
     * @param imageView 展示view
     * @param resourceId 默认占位图
     */
    public static void loadImageByUrl(String imgUrl, ImageView imageView,int resourceId) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .placeholder(resourceId)
                .error(resourceId)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }



    public static void loadImageByUrlbanner(String imgUrl, ImageView imageView,int resourceId) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
//                .placeholder(resourceId)
//                .error(resourceId)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }


    /**
     *通过uri展示图片 无默认占位图
     * @param imgUrl 图片url
     * @param imageView 展示view
     */
    public static void loadImageByUrlNoHold(String imgUrl, ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .centerCrop()
                .into(imageView);
    }
    /**
     *通过uri展示图片 有默认占位图
     * @param imgUrl 图片url
     * @param imageView 展示view
     */
    public static void loadImageByUrl(String imgUrl, ImageView imageView) {
//        Glide.with(MyApplication.getContext())
//                .load(imgUrl)
////                .placeholder(R.mipmap.icon_default_avatar)
////                .error(R.mipmap.icon_default_avatar)
//                .centerCrop()
//                .into(imageView);
        Glide.with(MyApplication.getContext()).load(imgUrl).skipMemoryCache(true).into(imageView);

    }
    public static void loadImageByUrl2(String imgUrl, final ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        int intrinsicWidth = resource.getIntrinsicWidth();
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        float scale = (float) DensityUtil.dip2px(MyApplication.getContext(),100)/(intrinsicWidth);
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh ;
                        params.width = DensityUtil.dip2px(MyApplication.getContext(),100) ;
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .into(imageView);
    }
    /**
     *通过图片resid展示图片
     * @param id 图片url
     * @param imageView 展示view
     */
    public static void loadImageById(@IdRes int id, ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(id)
//                .placeholder(R.mipmap.icon_default_avatar)
//                .error(R.mipmap.icon_default_avatar)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 通过url 展示图片，并设置图片
     * @param imgUrl
     * @param imageView
     */
    public static void loadRoundImageByUrl(String imgUrl, ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
//                .placeholder(R.mipmap.enterprise_placehold)
//                .error(R.mipmap.enterprise_placehold)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 通过url 展示图片，并设置图片
     * @param imgUrl
     * @param imageView
     */
    public static void loadRoundImageFitXYByUrl(String imgUrl, ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
//                .placeholder(R.mipmap.enterprise_placehold)
//                .error(R.mipmap.enterprise_placehold)
                .fitCenter()
                .into(imageView);
    }

    /**
     * 通过url 展示图片，并设置图片
     * @param imgUrl
     * @param imageView
     */
    public static void loadImageFitxy(String imgUrl, ImageView imageView) {
        Glide.with(MyApplication.getContext())
                .load(imgUrl)
//                .placeholder(R.mipmap.enterprise_placehold)
//                .error(R.mipmap.enterprise_placehold)
                .into(imageView);
    }

}
