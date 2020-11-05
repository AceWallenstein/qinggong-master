package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.national.qinggong.R;
import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;
import com.tencent.rtmp.TXLiveConstants;

public class PlayVideoActivity extends AppCompatActivity {
    public static void open(Context context, String video) {
        Intent intent = new Intent(context, PlayVideoActivity.class);
        intent.putExtra("video", video);
        context.startActivity(intent);
    }
    private SuperPlayerView mSuperPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        mSuperPlayerView = findViewById(R.id.main_super_player_view);
        // 播放器配置
        SuperPlayerGlobalConfig prefs = SuperPlayerGlobalConfig.getInstance();
        // 开启悬浮窗播放
        prefs.enableFloatWindow = false;
        // 设置悬浮窗的初始位置和宽高
        SuperPlayerGlobalConfig.TXRect rect = new SuperPlayerGlobalConfig.TXRect();
        rect.x = 0;
        rect.y = 0;
        rect.width = 810;
        rect.height = 540;
        prefs.floatViewRect = rect;
        // 播放器默认缓存个数
        prefs.maxCacheItem = 5;
        // 设置播放器渲染模式
        prefs.enableHWAcceleration = true;
        prefs.renderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;

        // 通过URL方式的视频信息配置
        SuperPlayerModel model2 = new SuperPlayerModel();
        model2.title = getIntent().getStringExtra("title");
        model2.url = getIntent().getStringExtra("video");
        // 开始播放
        mSuperPlayerView.playWithModel(model2);
        mSuperPlayerView.setPlayerViewCallback(new SuperPlayerView.OnSuperPlayerViewCallback() {
            @Override
            public void onStartFullScreenPlay() {

            }

            @Override
            public void onStopFullScreenPlay() {

            }

            @Override
            public void onClickFloatCloseBtn() {

            }

            @Override
            public void onClickSmallReturnBtn() {
                finish();
            }

            @Override
            public void onStartFloatWindowPlay() {

            }


        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 重新开始播放
        if (mSuperPlayerView.getPlayState() == SuperPlayerConst.PLAYSTATE_PLAYING) {
            mSuperPlayerView.onResume();
            if (mSuperPlayerView.getPlayMode() == SuperPlayerConst.PLAYMODE_FLOAT) {
                mSuperPlayerView.requestPlayMode(SuperPlayerConst.PLAYMODE_WINDOW);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 停止播放
        if (mSuperPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mSuperPlayerView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放
        mSuperPlayerView.release();
        if (mSuperPlayerView.getPlayMode() != SuperPlayerConst.PLAYMODE_FLOAT) {
            mSuperPlayerView.resetPlayer();
        }
    }
}
