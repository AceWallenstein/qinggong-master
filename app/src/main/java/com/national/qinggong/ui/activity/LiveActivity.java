package com.national.qinggong.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anbetter.danmuku.DanMuView;
import com.anbetter.danmuku.model.DanMuModel;
import com.anbetter.danmuku.model.utils.DimensionUtil;
import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.LiveRoomDetailBean;
import com.national.qinggong.bean.LiveRoomGetTalkBean;
import com.national.qinggong.dialog.GoodPopupWindow;
import com.national.qinggong.dialog.dialog.TCInputTextMsgDialog;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.MyUtil;
import com.national.qinggong.util.ToastUtilMsg;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LiveActivity extends BaseActivity implements ITXLivePushListener {



    public static void open(Context context, String result,String AvatarUrl,String Fans,String Like_num,String NickName) {
        Intent intent = new Intent(context, LiveActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("AvatarUrl", AvatarUrl);
        intent.putExtra("Fans", Fans);
        intent.putExtra("Like_num", Like_num);
        intent.putExtra("NickName", NickName);
        context.startActivity(intent);
    }

    @BindView(R.id.live_message_recycler)
    RecyclerView live_message_recycler;
    @BindView(R.id.lin_rotate)
    View lin_rotate;
    @BindView(R.id.btn_start_message)
    View btn_start_message;
    @BindView(R.id.lin_btn)
    View lin_btn;
    @BindView(R.id.btn_live)
    View btn_live;
    @BindView(R.id.lin_message)
    View lin_message;
    @BindView(R.id.lin_head)
    View lin_head;
    @BindView(R.id.image_header_company)
    ImageView image_header_company;
    @BindView(R.id.image_fin)
    ImageView image_fin;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.companyFans)
    TextView companyFans;
    @BindView(R.id.tv_online)
    TextView tv_online;
    @BindView(R.id.danmaku_container_broadcast)
    DanMuView mDanMuContainerBroadcast;
    @BindView(R.id.iv_good)
    ImageView iv_good;
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;
    /**
     * SDK 提供的类
     */
    private TXLivePushConfig mLivePushConfig;                // SDK 推流 config
    private TXLivePusher mLivePusher;                    // SDK 推流类
    private TXCloudVideoView mPusherView;                    // SDK 推流本地预览类

    public String pushUrl = "";
    private int mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_540_960;   // 当前分辨率
    /**
     * 默认美颜参数
     */
    private int mBeautyLevel = 5;            // 美颜等级
    private int mBeautyStyle = TXLiveConstants.BEAUTY_STYLE_SMOOTH; // 美颜样式
    private int mWhiteningLevel = 3;            // 美白等级
    private int mRuddyLevel = 2;            // 红润等级

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_live;
    }

    private boolean isStart = false;
    private boolean isOpen = false;
    Disposable subscribe;
    private GoodPopupWindow goodPopupWindow = null;
    @Override
    protected void initdata() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        live_id = getIntent().getStringExtra("result");
        heade=getIntent().getStringExtra("AvatarUrl");
        follow=getIntent().getStringExtra("Fans");
        browas=getIntent().getStringExtra("Like_num");
        Glide.with(LiveActivity.this).load(heade).into(image_header_company);
        companyName.setText(getIntent().getStringExtra("NickName"));
        companyFans.setText(follow + " fans");
        tv_online.setText(browas + " Online");
        initPusher();
        mPusherView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
        mLivePusher.startCameraPreview(mPusherView);
        mLivePusher.setPushListener(this);

        setPushScene(TXLiveConstants.VIDEO_QUALITY_STANDARD_DEFINITION, true);
        //Tim
        lin_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePusher.switchCamera();
            }
        });
        btn_start_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TCInputTextMsgDialog textMsgDialog = new TCInputTextMsgDialog(LiveActivity.this, R.style.dialog, "To the fans...");
                textMsgDialog.setmOnTextSendListener(new TCInputTextMsgDialog.OnTextSendListener() {
                    @Override
                    public void onTextSend(String msg, boolean tanmuOpen) {
                        getLiveList(msg);
                        textMsgDialog.dismiss();
                    }
                });
                textMsgDialog.show();
            }
        });

        setStart();
        btn_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = true;
                setStart();
            }
        });
        image_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MyUtil.isFastDoubleClick()){
                    if (!isStart) {
                        finish();
                    } else {
                        Live_roomSetStatus("30");
                    }
                }

            }
        });


        goodPopupWindow = new GoodPopupWindow(this, onClickListener);

        goodPopupWindow.setListening(new GoodPopupWindow.PopwindowClickListening() {
            @Override
            public void addCar(String id) {
                addCart(id);
            }

            @Override
            public void goodDetail(String id) {
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 19);
                Bundle_about.putString("good_detail_id", id );
                PlatformForFragmentActivity.newInstance(LiveActivity.this, Bundle_about);
            }
        });


        iv_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopFormBottom(v);
            }
        });


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                /*case R.id.btn_take_photo:
                    System.out.println("btn_take_photo");
                    break;
                case R.id.btn_pick_photo:
                    System.out.println("btn_pick_photo");
                    break;*/
            }
        }
    };

    public void showPopFormBottom(View view) {
        //showAtLocation(View parent, int gravity, int x, int y)
        goodPopupWindow.showAtLocation(findViewById(R.id.rl_content), Gravity.CENTER, 0, 0);
    }


    private void addCart(String id) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("goods_id", id);
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .addCar(map)
                .compose(RequestManager.<DeleteCarBean>applyIoSchedulers())
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
                .subscribe(new Consumer<DeleteCarBean>() {
                               @Override
                               public void accept(DeleteCarBean deleteCarBean) throws Exception {
                                   if (deleteCarBean.getCode()==1){
                                       ToastUtilMsg.showToast(LiveActivity.this,"Done");
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    public void setStart() {
        if (isStart) {
            lin_message.setVisibility(View.VISIBLE);
            lin_head.setVisibility(View.VISIBLE);
            lin_btn.setVisibility(View.GONE);
            Live_roomSetStatus("20");
        } else {
            lin_btn.setVisibility(View.VISIBLE);
            lin_message.setVisibility(View.GONE);
            lin_head.setVisibility(View.GONE);
        }
    }

    public void startPush(String rtmpUrl) {
        mPusherView.setVisibility(View.VISIBLE);
        mLivePushConfig.setPauseFlag(TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO);// 设置暂停时，只停止画面采集，不停止声音采集。
        // 设置推流分辨率
        mLivePushConfig.setVideoResolution(mCurrentVideoResolution);
        // 设置美颜
        mLivePusher.setBeautyFilter(mBeautyStyle, mBeautyLevel, mWhiteningLevel, mRuddyLevel);
        //  mLivePusher.startCameraPreview(mPusherView);
        String rtmpURL = rtmpUrl; //此处填写您的 rtmp 推流地址
        int ret = mLivePusher.startPusher(rtmpURL.trim());
        if (ret == -5) {
            ToastUtilMsg.showToast(this, "Live streaming failed. Please restart live streaming");
            return;
        }
        ToastUtilMsg.showToast(this, "Start Live");
    }

    String live_id;

    private void getLiveList(String content) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("live_id", live_id);
        map.put("content", content);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_roomSendTalk(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object userInfo) throws Exception {
                                   getLiveListGetTalk();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void getLiveListGetTalk() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("live_id", live_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_roomGetTalk(map)
                .compose(RequestManager.<LiveRoomGetTalkBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveRoomGetTalkBean>() {
                               @Override
                               public void accept(LiveRoomGetTalkBean userInfo) throws Exception {
                                   loadDataAnn(userInfo.getData().getList());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void Live_roomSetStatus(final String status) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("id", live_id);
        map.put("status", status);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_roomSetStatus(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object userInfo) throws Exception {
                                   if (status.equals("20")) {
                                       getMessage();
                                       getLiveListGetTalk();
                                       Live_roomDetail();
                                   } else {
                                       LiveEndActivity.open(LiveActivity.this,heade,follow,browas,time);
                                       finish();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }
    String heade;
    String follow;
    String browas;
    long time;
    private void Live_roomDetail() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("live_id", live_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_roomDetail(map)
                .compose(RequestManager.<LiveRoomDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LiveRoomDetailBean>() {
                               @Override
                               public void accept(LiveRoomDetailBean userInfo) throws Exception {
                                   pushUrl = userInfo.getData().getDetail().getPush_url();
                                   heade=userInfo.getData().getDetail().getAnchor().getUser().getAvatarUrl();
                                   follow=userInfo.getData().getDetail().getAnchor().getFans()+"";
                                   browas=userInfo.getData().getDetail().getLike_num()+"";
                                   Glide.with(LiveActivity.this).load(userInfo.getData().getDetail().getAnchor().getUser().getAvatarUrl()).into(image_header_company);
                                   //companyName.setText(userInfo.getData().getUser().getNickName());
                                   companyFans.setText(userInfo.getData().getDetail().getAnchor().getFans() + " fans");
                                   tv_online.setText(userInfo.getData().getDetail().getLike_num() + " Online");
                                   if (!isOpen) {
                                       isOpen = true;
                                       startPush(pushUrl);
                                   }
                                   goodPopupWindow.setData(userInfo.getData().getDetail().getGoods());
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    public void loadDataAnn(final List<LiveRoomGetTalkBean.DataBean.ListBean> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        live_message_recycler.setLayoutManager(layoutManager);
        live_message_recycler.setHasFixedSize(true);
        final JoneBaseAdapter mJobDataAdapter = new JoneBaseAdapter<LiveRoomGetTalkBean.DataBean.ListBean>(live_message_recycler, R.layout.item_live_news) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, LiveRoomGetTalkBean.DataBean.ListBean model) {
                helper.setText(R.id.tv_msg_content, model.getUser().getNickName() + ":" + model.getContent());
            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, final int position) {

            }
        });

        mJobDataAdapter.setData(list);
        live_message_recycler.setAdapter(mJobDataAdapter);
        //live_message_recycler.scrollToPosition(mJobDataAdapter.getData().size() - 1);
    }

    public void danMu(String name) {
        DanMuModel danMuView = new DanMuModel();
        danMuView.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuView.setPriority(DanMuModel.NORMAL);
        danMuView.marginLeft = DimensionUtil.dpToPx(this, 30);
        // 显示的文本内容
        danMuView.textSize = DimensionUtil.spToPx(this, 14);
        danMuView.textColor = ContextCompat.getColor(this, R.color.white);
        danMuView.textMarginLeft = DimensionUtil.dpToPx(this, 5);
        danMuView.text = name;
        danMuView.text = name;
        // 弹幕文本背景
        danMuView.textBackground = ContextCompat.getDrawable(this, R.drawable.bg_live_danmu);
        danMuView.textBackgroundPaddingLeft = R.mipmap.icon_dan_mu;
        danMuView.textBackgroundMarginLeft = DimensionUtil.dpToPx(this, 15);
        danMuView.textBackgroundPaddingTop = DimensionUtil.dpToPx(this, 5);
        danMuView.textBackgroundPaddingBottom = DimensionUtil.dpToPx(this, 5);
        danMuView.textBackgroundPaddingRight = DimensionUtil.dpToPx(this, 15);
        mDanMuContainerBroadcast.add(danMuView);

    }

    public void getMessage() {
         subscribe = Flowable.interval(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        time = time + 5;
                        getLiveListGetTalk();
                        Live_roomDetail();
                    }
                });
    }

    /**
     * 初始化 SDK 推流器
     */
    private void initPusher() {
        mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
        mLivePushConfig.setVideoEncodeGop(5);
        mLivePusher.setConfig(mLivePushConfig);
    }

    /**
     * 设置推流场景
     * <p>
     * SDK 内部将根据具体场景，进行推流 分辨率、码率、FPS、是否启动硬件加速、是否启动回声消除 等进行配置
     * <p>
     * 适用于一般客户，方便快速进行配置
     * <p>
     * 专业客户，推荐通过 {@link TXLivePushConfig} 进行逐一配置
     */
    public void setPushScene(int type, boolean enableAdjustBitrate) {
        // 码率、分辨率自适应都关闭
        boolean autoBitrate = enableAdjustBitrate;
        boolean autoResolution = false;
        switch (type) {
            case TXLiveConstants.VIDEO_QUALITY_STANDARD_DEFINITION: /*360p*/
                if (mLivePusher != null) {
                    mLivePusher.setVideoQuality(TXLiveConstants.VIDEO_QUALITY_STANDARD_DEFINITION, autoBitrate, autoResolution);
                    mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_360_640;
                }
                break;
            case TXLiveConstants.VIDEO_QUALITY_HIGH_DEFINITION: /*540p*/
                if (mLivePusher != null) {
                    mLivePusher.setVideoQuality(TXLiveConstants.VIDEO_QUALITY_HIGH_DEFINITION, autoBitrate, autoResolution);
                    mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_540_960;
                }
                break;
            case TXLiveConstants.VIDEO_QUALITY_SUPER_DEFINITION: /*720p*/
                if (mLivePusher != null) {
                    mLivePusher.setVideoQuality(TXLiveConstants.VIDEO_QUALITY_SUPER_DEFINITION, autoBitrate, autoResolution);
                    mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_720_1280;
                }
                break;
            case TXLiveConstants.VIDEO_QUALITY_ULTRA_DEFINITION: /*1080p*/
                if (mLivePusher != null) {
                    mLivePusher.setVideoQuality(TXLiveConstants.VIDEO_QUALITY_ULTRA_DEFINITION, autoBitrate, autoResolution);
                    mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_1080_1920;
                }
                break;
            default:
                break;
        }
        // 设置场景化配置后，SDK 内部会根据场景自动选择相关的配置参数，所以我们这里把内部的config获取出来，赋值到外部。
        mLivePushConfig = mLivePusher.getConfig();

        // 是否开启硬件加速
        if (true) {
            mLivePushConfig.setHardwareAcceleration(TXLiveConstants.ENCODE_VIDEO_HARDWARE);
            mLivePusher.setConfig(mLivePushConfig);
        }
    }

    @Override
    public void onPushEvent(int event, Bundle param) {
        String msg = param.getString(TXLiveConstants.EVT_DESCRIPTION);
        // 如果开始推流，设置了隐私模式。 需要在回调里面设置，不能直接start之后直接pause
        if (event == TXLiveConstants.PUSH_EVT_PUSH_BEGIN) {
            /*if (mPushMoreFragment.isPrivateMode()) {
                mLivePusher.pausePusher();
            }*/
        }
        // Toast错误内容
        if (event < 0) {
            Toast.makeText(getApplicationContext(), param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
        }

        if (event == TXLiveConstants.PUSH_ERR_NET_DISCONNECT
                || event == TXLiveConstants.PUSH_ERR_INVALID_ADDRESS
                || event == TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL
                || event == TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL) {
            // 遇到以上错误，则停止推流
            ToastUtilMsg.showToast(this, "Live streaming failed. Please restart live streaming");
            //stopRTMPPush();
        } else if (event == TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL) {
            // 开启硬件加速失败
            mLivePushConfig.setHardwareAcceleration(TXLiveConstants.ENCODE_VIDEO_SOFTWARE);
            mLivePusher.setConfig(mLivePushConfig);
        } else if (event == TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION) {
        } else if (event == TXLiveConstants.PUSH_EVT_CHANGE_BITRATE) {
        } else if (event == TXLiveConstants.PUSH_WARNING_NET_BUSY) {
            /**
             * 显示网络繁忙的提示
             */
            // showNetBusyTips();
        } else if (event == TXLiveConstants.PUSH_EVT_START_VIDEO_ENCODER) {
//            int encType = param.getInt(TXLiveConstants.EVT_PARAM1);
//            boolean hwAcc = (encType == TXLiveConstants.ENCODE_VIDEO_HARDWARE);
//            Toast.makeText(CameraPusherActivity.this, "是否启动硬编：" + hwAcc, Toast.LENGTH_SHORT).show();
        } else if (event == TXLiveConstants.PUSH_EVT_OPEN_CAMERA_SUCC) {
            // 只有后置摄像头可以打开闪光灯，若默认需要开启闪光灯。 那么在打开摄像头成功后，才可以进行配置。 若果当前是前置，设定无效；若是后置，打开闪光灯。
            // mLivePusher.turnOnFlashLight(mPushMoreFragment.isFlashEnable());
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }


    /*private void setStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("status", "30");
        map.put("id", live_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .setStatus(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (!isStart) {
                finish();
            } else {
                Live_roomSetStatus("30");
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //setStatus();
        mLivePusher.stopPusher();
        if(subscribe!=null){
            subscribe.dispose();
        }
        // 隐藏本地预览的View
        // mPusherView.setVisibility(View.GONE);
        ToastUtilMsg.showToast(this, "Live Stop");
    }
}
