package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends BaseActivity {

    public static void open(Context context,String path) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra("path",path);
        context.startActivity(intent);
    }

    @BindView(R.id.photo_view)
    PhotoView photo_view;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @Override
    protected int initResourceLayout() {
        return R.layout.activity_photo_view;
    }


    @Override
    protected void initdata() {
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Glide.with(this)
                .load(getIntent().getStringExtra("path"))
                .thumbnail(0.05f)
                .into(photo_view);
    }
}
