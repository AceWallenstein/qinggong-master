package com.national.qinggong.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.anbetter.danmuku.DanMuView;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.LivePeopleBean;
import com.national.qinggong.bean.LiveRoomDetailBean;
import com.national.qinggong.bean.LiveRoomGetTalkBean;
import com.national.qinggong.dialog.GoodPopupWindow;
import com.national.qinggong.dialog.OnDialogClickListener;
import com.national.qinggong.dialog.dialog.TCInputTextMsgDialog;
import com.national.qinggong.dialog.dialog.custom.LivePlayDialog;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.ToastUtilMsg;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import org.greenrobot.eventbus.EventBus;

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
import static com.tencent.rtmp.TXLiveConstants.PLAY_ERR_NET_DISCONNECT;
import static com.tencent.rtmp.TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN;

public class LivePalyActivity extends BaseActivity implements ITXLivePlayListener{
    @BindView(R.id.video_view)
    TXCloudVideoView video_view;

    private TXLivePlayer mLivePlayer;
    @BindView(R.id.live_message_recycler)
    RecyclerView live_message_recycler;
    @BindView(R.id.btn_start_message)
    View btn_start_message;

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
    @BindView(R.id.im_loading)
    SpinKitView im_loading;
    @BindView(R.id.tv_follow_play)
    TextView tv_follow_play;
    @BindView(R.id.iv_good)
    ImageView iv_good;
    @BindView(R.id.rl_content)
    RelativeLayout rl_content;
    private LivePlayDialog livePlayDialog;
    private String anchor_id="";

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_live_paly;
    }

    String pushUrl = "";
    String live_id;
    boolean Is_fans=false;

    private GoodPopupWindow goodPopupWindow = null;



    public static void open(Context context, String live_id,String pushUrl,String avatarUrl,String Fans,String Like_num ,String NickName,String Anchor_id) {
        Intent intent = new Intent(context, LivePalyActivity.class);
        intent.putExtra("result", live_id);
        intent.putExtra("pushUrl", pushUrl);
        intent.putExtra("avatarUrl", avatarUrl);
        intent.putExtra("Fans", Fans);
        intent.putExtra("Like_num", Like_num);
        intent.putExtra("NickName", NickName);
        intent.putExtra("Anchor_id", Anchor_id);
        context.startActivity(intent);
    }
    @Override
    protected void initdata() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        live_id = getIntent().getStringExtra("result");
        pushUrl = getIntent().getStringExtra("pushUrl");
        heade=getIntent().getStringExtra("avatarUrl");

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
                PlatformForFragmentActivity.newInstance(LivePalyActivity.this, Bundle_about);
            }
        });


        Glide.with(LivePalyActivity.this).load(heade).into(image_header_company);
        companyName.setText(getIntent().getStringExtra("NickName"));
        companyFans.setText(getIntent().getStringExtra("Fans")+ " fans");
        tv_online.setText(getIntent().getStringExtra("Like_num") + " Online");
        anchor_id = getIntent().getStringExtra("Anchor_id");
        image_header_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveAnchorDetailActivity.open(LivePalyActivity.this,anchor_id);
            }
        });
        iv_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopFormBottom(v);
            }
        });
//创建 player 对象
        mLivePlayer = new TXLivePlayer(this);
        mLivePlayer.setPlayListener(this);
//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(video_view);

        TXLivePlayConfig mPlayConfig = new TXLivePlayConfig();
//
//自动模式
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMinAutoAdjustCacheTime(1);
        mPlayConfig.setMaxAutoAdjustCacheTime(5);
//
        mPlayConfig.setConnectRetryCount(1);
        mPlayConfig.setConnectRetryInterval(10);
        mLivePlayer.setConfig(mPlayConfig);
        mLivePlayer.startPlay(pushUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
        mLivePlayer.setRenderMode(RENDER_MODE_FULL_FILL_SCREEN);

        btn_start_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TCInputTextMsgDialog textMsgDialog = new TCInputTextMsgDialog(LivePalyActivity.this, R.style.dialog, "To the fans...");
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

        image_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getLiveListGetTalk();
        getMessage();
        livePlayDialog = new LivePlayDialog(LivePalyActivity.this);


        //用户进出直播间
        userEnterOrOut("in");
        //获取直播间人数
        getLivePeople();
        Live_roomDetail();


        tv_follow_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Is_fans=!Is_fans;
                if(Is_fans){
                    livefocus();
                }else{
                    liveCancelFocus();
                }
            }
        });

    }

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
                                   getLivePeople();
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
    Disposable subscribe;
    public void getMessage() {
         subscribe = Flowable.interval(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getLiveListGetTalk();
                        getLivePeople();
                        Live_roomDetail();
                    }
                });
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
                               public void accept(final LiveRoomDetailBean userInfo) throws Exception {
                                   pushUrl = userInfo.getData().getDetail().getLive_url();
                                   heade = userInfo.getData().getDetail().getAnchor().getUser().getAvatarUrl();
                                   follow = userInfo.getData().getDetail().getAnchor().getFans() + "";
                                   browas = userInfo.getData().getDetail().getLike_num() + "";
                                   Glide.with(LivePalyActivity.this).load(userInfo.getData().getDetail().getAnchor().getUser().getAvatarUrl()).into(image_header_company);
                                   companyName.setText(userInfo.getData().getDetail().getAnchor().getUser().getNickName());

                                   companyFans.setText(userInfo.getData().getDetail().getAnchor().getFans() + " fans");


                                   //已关注主播
                                   if (userInfo.getData().getDetail().getIsfans().equals("1")){
                                       Is_fans=true;
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg_2);

                                   }else if (userInfo.getData().getDetail().getIsfans().equals("0")){
                                       //未关注主播
                                       Is_fans=false;
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg);
                                   }
                                   tv_follow_play.setPadding(DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5),DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5));


                                   image_header_company.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           LiveAnchorDetailActivity.open(LivePalyActivity.this,userInfo.getData().getDetail().getAnchor_id()+"");
                                       }
                                   });


                                   goodPopupWindow.setData(userInfo.getData().getDetail().getGoods());

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    @Override
    public void onPlayEvent(int i, Bundle bundle) {
        if (TXLiveConstants.PLAY_EVT_PLAY_END == i) {
            TextView view = (TextView) livePlayDialog.findView(R.id.tv_title);
            if(view!=null){
                view.setText("The live broadcast is over. Return to the desktop");
            }

            livePlayDialog.show();
            livePlayDialog.setOnDialogClickListener(new OnDialogClickListener() {
                @Override
                public void onDialogClick(Dialog dialog, int id) {
                    switch (id) {
                        case R.id.submit_alert:
                  finish();
                            break;
                    }
                }});
        } else if (TXLiveConstants.PLAY_EVT_PLAY_BEGIN == i) {
              im_loading.setVisibility(View.GONE);
        } else if (TXLiveConstants.PLAY_WARNING_VIDEO_PLAY_LAG == i) {
            // ToastUtil.show("视频播放卡顿");
            // im_loading.setVisibility(View.GONE);
        } else if (i == PLAY_ERR_NET_DISCONNECT) {
            TextView view = (TextView) livePlayDialog.findView(R.id.tv_title);
            if(view!=null){
                view.setText("The live broadcast is over. Return to the desktop");
            }            livePlayDialog.show();
            livePlayDialog.setOnDialogClickListener(new OnDialogClickListener() {
                @Override
                public void onDialogClick(Dialog dialog, int id) {
                    switch (id) {
                        case R.id.submit_alert:
                            finish();
                            break;
                    }
                }});
            // roomListenerCallback.onDebugLog("[AnswerRoom] 拉流失败：网络断开");
            // roomListenerCallback.onError(-1, "网络断开，拉流失败");
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }


    //获取直播间人数
    private void getLivePeople() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        //map.put("token", getToken);
        map.put("live_id", live_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .getLivePeople(map)
                 .compose(RequestManager.<LivePeopleBean>applyIoSchedulers())
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
                .subscribe(new Consumer<LivePeopleBean>() {
                               @Override
                               public void accept(LivePeopleBean livePeopleBean) throws Exception {
                                   //ToastUtilMsg.showToast(LivePalyActivity.this,"直播间人数="+livePeopleBean.getData().getNum());
                                   Log.i("test","直播间人数="+livePeopleBean.getData().getNum());
                                   tv_online.setText(livePeopleBean.getData().getNum()+ " Online");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    //in进，out出，
    private void userEnterOrOut(String type) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("type", type);
        map.put("live_id", live_id);
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .userEnterOrOut(map)
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
                               public void accept(Object object) throws Exception {
                                   //ToastUtilMsg.showToast(LivePalyActivity.this,"直播间人数="+livePeopleBean.getData().getNum());
                                   //Log.i("test","直播间人数="+livePeopleBean.getData().getNum());
                                   //tv_online.setText(livePeopleBean.getData().getNum()+ " Online");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }


    private void livefocus() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("anchor_id", anchor_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .livefocus(map)
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
                                   if(Is_fans){
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg_2);
                                   }else{
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg);
                                   }

                                   tv_follow_play.setPadding(DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5),DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void liveCancelFocus() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("anchor_id", anchor_id);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveCancelFocus(map)
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
                                   if(Is_fans){
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg_2);
                                   }else{
                                       tv_follow_play.setBackgroundResource(R.drawable.botton_login_bg);
                                   }
                                   tv_follow_play.setPadding(DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5),DensityUtil.dip2px(LivePalyActivity.this,15),DensityUtil.dip2px(LivePalyActivity.this,5));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }



    public void showPopFormBottom(View view) {
        //showAtLocation(View parent, int gravity, int x, int y)
        goodPopupWindow.showAtLocation(findViewById(R.id.rl_content), Gravity.CENTER, 0, 0);
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
                                       ToastUtilMsg.showToast(LivePalyActivity.this,"Done");
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }



    @Override
    protected void onDestroy() {
        userEnterOrOut("out");
        if(video_view!=null){
            mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
            video_view.onDestroy();
        }
        if(subscribe!=null){
            subscribe.dispose();
        }
        super.onDestroy();
    }




}
