package com.national.qinggong.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yxd.imagepickers.loader.ImageLoader;

import java.io.File;

public class MyGlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(com.yxd.imagepickers.R.drawable.ic_default_image)           //设置错误图片
                .placeholder(com.yxd.imagepickers.R.drawable.ic_default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }




    //设置网络图片
    //.asBitmap()
    @Override
    public void displayInternetImage(Activity activity, String path, final ImageView imageView, int width, int height) {
       /* Glide.with(activity)
                .load(path)
                .asBitmap()
                .centerCrop()
                .thumbnail(0.05f)
                .error(R.drawable.ic_default_image)  //设置错误图片
                .placeholder(R.drawable.ic_default_image)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(false);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });*/



        Glide.with(activity)
                .load(path)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}