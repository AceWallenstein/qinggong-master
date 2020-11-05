package com.national.qinggong.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.TCVideoEditerListAdapter;
import com.national.qinggong.bean.TCVideoFileInfo;
import com.national.qinggong.util.PickerManagerKit;
import com.national.qinggong.util.VideoChecker;
import com.tencent.liteav.basic.log.TXCLog;

import java.util.ArrayList;

public class TCVideoJoinChooseActivity extends Activity implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String TAG = "TCVideoJoinChooseActivity";
    // 视频拼接
    public static final int TYPE_MULTI_CHOOSE = 1;
    // 视频上传
    public static final int TYPE_PUBLISH_CHOOSE = 2;
    // 图片转场
    public static final int TYPE_MULTI_CHOOSE_PICTURE = 3;

    private Button mBtnOk;
    private RecyclerView mRecyclerView;
    private TextView mTvTitle;

    private int mType;

    public static void open(Activity context) {
        Intent intent = new Intent(context, TCVideoJoinChooseActivity.class);
        context.startActivityForResult(intent,200);
    }

    private TCVideoEditerListAdapter mAdapter;

    private Handler mHandler;
    private HandlerThread mHandlerThread;

    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<TCVideoFileInfo> fileInfoArrayList = (ArrayList<TCVideoFileInfo>) msg.obj;
            mAdapter.addAll(fileInfoArrayList);
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_tcvideo_join_choose);
        mHandlerThread = new HandlerThread("LoadList");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());


        init();

        loadVideoList();

    }


    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        mHandlerThread.quit();
        mHandlerThread = null;
        LinearLayout backLL = (LinearLayout) findViewById(R.id.back_ll);
        backLL.setOnClickListener(null);
        mBtnOk.setOnClickListener(null);
        super.onDestroy();
    }

    private void loadVideoList() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ArrayList<TCVideoFileInfo> fileInfoArrayList = PickerManagerKit.getInstance(TCVideoJoinChooseActivity.this).getAllVideo();

                    Message msg = new Message();
                    msg.obj = fileInfoArrayList;
                    mMainHandler.sendMessage(msg);
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadVideoList();
        }
    }

    private void init() {
        LinearLayout backLL = (LinearLayout) findViewById(R.id.back_ll);
        backLL.setOnClickListener(this);

        mBtnOk = (Button) findViewById(R.id.btn_ok);
        mBtnOk.setOnClickListener(this);


        mTvTitle = (TextView) findViewById(R.id.title_tv);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new TCVideoEditerListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setMultiplePick(false);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ok) {
            doSelect();

        } else if (id == R.id.back_ll) {
            finish();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Glide.with(this).pauseRequests();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    private void doSelect() {
        TCVideoFileInfo fileInfo = mAdapter.getSingleSelected();
        if (fileInfo == null) {
            TXCLog.d(TAG, "select file null");
            return;
        }
        if (VideoChecker.isVideoDamaged(this, fileInfo)) {
            VideoChecker.showErrorDialog(this, "该视频文件已经损坏");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("video",fileInfo);
        setResult(200,intent);
         /*   intent.putExtra(UGCKitConstants.VIDEO_URI, fileInfo.getFileUri().toString());
            intent.putExtra(UGCKitConstants.VIDEO_PATH, fileInfo.getFilePath());
            startActivity(intent);*/
       /*     Intent intent = new Intent(getApplicationContext(), TCVideoPublishActivity.class);
            intent.putExtra(TCConstants.VIDEO_EDITER_PATH, fileInfo.getFilePath());
            startActivity(intent);*/

        finish();

    }
}

