package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.util.DateTimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveEndActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.btn_ok)
    TextView btnOk;
    @BindView(R.id.btn_back)
    TextView btnBack;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_browse)
    TextView tvBrowse;

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_live_end;
    }

    public static void open(Context context, String heade, String follow, String browas, long time) {
        Intent intent = new Intent(context, LiveEndActivity.class);
        intent.putExtra("heade", heade);
        intent.putExtra("follow", follow);
        intent.putExtra("browas", browas);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }

    @Override
    protected void initdata() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(LiveEndActivity.this).load(getIntent().getStringExtra("heade")).into(imageHead);
        tvTime.setText(" Duration "+ DateTimeUtil.formattedTime(getIntent().getLongExtra("time",0)));
        tvFollow.setText(getIntent().getStringExtra("follow"));
        tvBrowse.setText(getIntent().getStringExtra("browas"));
    }


}
