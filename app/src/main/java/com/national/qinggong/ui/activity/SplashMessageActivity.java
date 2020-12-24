package com.national.qinggong.ui.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.felipecsl.gifimageview.library.GifImageView;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.MyUtil;

import java.io.IOException;

public class SplashMessageActivity extends BaseActivity {

    private GifImageView iv_start;

    @Override
    protected void initdata() {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iv_start = findViewById(R.id.iv_start);

        iv_start.setScaleType(ImageView.ScaleType.CENTER);
        try {
            iv_start.setBytes( MyUtil.toByteArray(getResources().openRawResource(R.drawable.bg_start)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        iv_start.startAnimation();

        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.alic);
        mediaPlayer.start();


        CountDownTimer timer = new CountDownTimer(2100, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                String getToken = CacheHelper.getAlias(SplashMessageActivity.this, "getToken");
                if (getToken != null && !TextUtils.isEmpty(getToken)) {
                    ActivityUtils.startActivityAndFinish(SplashMessageActivity.this, HomeLazzyActivity.class);
                } else {
                    ActivityUtils.startActivityAndFinish(SplashMessageActivity.this, AppLoginActivity.class);
                }
            }
        }.start();

    }


    @Override
    protected int initResourceLayout() {
        return R.layout.ad_layout;
    }
}
