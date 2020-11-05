package com.national.qinggong;

import android.app.Application;
import android.content.Context;

import com.national.qinggong.util.MyGlideImageLoader;
import com.tencent.rtmp.TXLiveBase;
import com.yxd.imagepickers.ImagePicker;
import com.yxd.imagepickers.view.CropImageView;

public class MyApplication extends Application {
    private static Context context;
    private static MyApplication instance;
    private String licenceUrl = "http://license.vod2.myqcloud.com/license/v1/546b65db66a328be7dc1d60e3f9e5147/TXLiveSDK.licence";
    private String licenseKey = "98f22831d71d862750313a2b10e64ae8";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = MyApplication.this;
        initPic();

        TXLiveBase.getInstance().setLicence(
                this,
                licenceUrl,
                licenseKey
        );

    }
    public static Context getContext() {
        return context;
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    private void initPic(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new MyGlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

}
