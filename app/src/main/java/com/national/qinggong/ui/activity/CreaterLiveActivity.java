package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.UploadImageBean;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.MyGlideImageLoader;
import com.national.qinggong.util.ToastUtilMsg;
import com.yxd.imagepickers.ImagePicker;
import com.yxd.imagepickers.bean.ImageItem;
import com.yxd.imagepickers.ui.ImageGridActivity;
import com.yxd.imagepickers.view.CropImageView;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreaterLiveActivity extends BaseActivity {
    public static void open(Context context) {
        Intent intent = new Intent(context, CreaterLiveActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_creater_live;
    }


    @BindView(R.id.rl_back)
    LinearLayout rl_back;
    @BindView(R.id.rel_image)
    RelativeLayout rel_image;
    @BindView(R.id.image_create)
    ImageView image_create;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.tv_select_shop)
    TextView tv_select_shop;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.btn_ok)
    TextView btn_ok;
    private MyGlideImageLoader myGlideImageLoader;

    String startTime="";
    String endTime="";
    String goods_id="";
    String logo_image_id="";


    @Override
    protected void initdata() {
        myGlideImageLoader = new MyGlideImageLoader();
        ViewGroup.LayoutParams layoutParams = rel_image.getLayoutParams();
        layoutParams.width= (DensityUtil.getScreenWidth(this)-DensityUtil.dip2px(this,20))/2;
        layoutParams.height= (DensityUtil.getScreenWidth(this)-DensityUtil.dip2px(this,20))/2;
        rel_image.setLayoutParams(layoutParams);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.getInstance().setSelectLimit(1);
                ImagePicker.getInstance().setCrop(true);
                ImagePicker.getInstance().setMultiMode(false);
                ImagePicker.getInstance().setStyle(CropImageView.Style.RECTANGLE);
                Intent intent = new Intent(CreaterLiveActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
                startActivityForResult(intent, 12346);

            }
        });
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        //int month = calendar.get(Calendar.MONTH) + 1;
        int month = calendar.get(Calendar.MONTH) ;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final Calendar startDate = Calendar.getInstance();
        final Calendar endDate = Calendar.getInstance();
        startDate.set(year, month, day);
        endDate.set(year + 4, month, day);
        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerBuilder(CreaterLiveActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = formatter.format(date);
                        tv_start_time.setText(dateString);
                        startTime=dateString;

                    }
                }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                        .setCancelText("Cancel")//取消按钮文字
                        .setSubmitText("Sure")//确认按钮文字
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                        .build();
                pvTime.show();

            }
        });
        tv_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView pvTime = new TimePickerBuilder(CreaterLiveActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateString = formatter.format(date);
                        tv_end_time.setText(dateString);
                        endTime=dateString;
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                        .setCancelText("Cancel")//取消按钮文字
                        .setSubmitText("Sure")//确认按钮文字
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                        .build();
                pvTime.show();

            }
        });

        tv_select_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveShopSelectActivity.open(CreaterLiveActivity.this);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(startTime)){
                    ToastUtilMsg.showToast(CreaterLiveActivity.this,"Please select a start time");
                }else if(TextUtils.isEmpty(endTime)){
                    ToastUtilMsg.showToast(CreaterLiveActivity.this,"Please select the end time");
                }else{
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date sd1= null;
                    Date sd2= null;
                    try {
                        sd1 = df.parse(startTime);
                        sd2=df.parse(endTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //System.out.println(sd1.before(sd2));
                    //System.out.println(sd1.after(sd2));

                    if (sd1.before(sd2)){
                        getLiveListAdd();
                    }else{
                        ToastUtilMsg.showToast(CreaterLiveActivity.this,"The end time must not be earlier than the beginning time");
                    }

                }



            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == 12346) {
                List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    try {
                        File file = new File(images.get(0).path);
                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        MultipartBody.Part file1 = MultipartBody.Part.createFormData("iFile", images.get(0).path, requestFile);
                        uploadImage(file1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == 200&&data!=null) {
            goods_id=   data.getStringExtra("goods");
            int count = data.getIntExtra("count", 0);
            tv_select_shop.setText(count+" select");
        }
    }

    private void uploadImage(MultipartBody.Part iFile) {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .uploadImage(map, iFile)
                .compose(RequestManager.<UploadImageBean>applyIoSchedulers())
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
                .subscribe(new Consumer<UploadImageBean>() {
                               @Override
                               public void accept(UploadImageBean userInfo) throws Exception {
                                   String avatarUrl = userInfo.getData().getFile_path();
                                    logo_image_id = userInfo.getData().getFile_id();
                                   Glide.with(CreaterLiveActivity.this).load(avatarUrl).into(image_create);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }



    private void getLiveListAdd() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("goods_id", goods_id);
        map.put("start_time", startTime);
        map.put("end_time", endTime);
        map.put("logo_image_id", logo_image_id);
        map.put("name", ed_name.getText().toString().trim());
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .Live_roomAdd(map)
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
                                   MyBroadcastActivity.open(CreaterLiveActivity.this);
                                   finish();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }
}
