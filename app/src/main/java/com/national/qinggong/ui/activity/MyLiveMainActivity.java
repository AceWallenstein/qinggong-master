package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.bean.LiveRoomMyTopicBean;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyLiveMainActivity extends AppCompatActivity {
    @BindView(R.id.user_head)
    ImageView userHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_email)
    TextView userEmail;
    @BindView(R.id.tv_Times)
    TextView tvTimes;
    @BindView(R.id.tv_Visits)
    TextView tvVisits;
    @BindView(R.id.tv_Fans)
    TextView tvFans;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.lin_create)
    LinearLayout lin_create;
    @BindView(R.id.lin_live_shop)
    LinearLayout lin_live_shop;
    @BindView(R.id.lin_live_video)
    LinearLayout lin_live_video;
    @BindView(R.id.lin_live_bro)
    LinearLayout lin_live_bro;

    public static void open(Context context) {
        Intent intent = new Intent(context, MyLiveMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_live_main);
        ButterKnife.bind(this);
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getLiveListBack();

        lin_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreaterLiveActivity.open(MyLiveMainActivity.this);
            }
        });
        lin_live_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveShopActivity.open(MyLiveMainActivity.this);
            }
        });
        lin_live_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyVideoActivity.open(MyLiveMainActivity.this);
            }
        });
        lin_live_bro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBroadcastActivity.open(MyLiveMainActivity.this);
            }
        });
    }

    private void getLiveListBack() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .live_roomMyTopic(map)
                .compose(RequestManager.<LiveRoomMyTopicBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Consumer<LiveRoomMyTopicBean>() {
                               @Override
                               public void accept(LiveRoomMyTopicBean userInfo) throws Exception {
                                   if (userInfo.getCode() == 1) {
                                       Glide.with(MyLiveMainActivity.this).load(userInfo.getData().getUser().getAvatarUrl()).into(userHead);
                                       userName.setText(userInfo.getData().getUser().getNickName());
                                       userEmail.setText(userInfo.getData().getUser().getAccount());
                                       tvTimes.setText(userInfo.getData().getAnchor().getTime() + "");
                                       tvVisits.setText(userInfo.getData().getAnchor().getVisits() + "");
                                       tvFans.setText(userInfo.getData().getAnchor().getFans() + "");
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }




}
