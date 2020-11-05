package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.TCVideoFileInfo;
import com.national.qinggong.bean.UploadImageBean;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DateTimeUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddVideoActivity extends BaseActivity {
    public static void open(Context context) {
        Intent intent = new Intent(context, AddVideoActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.rel_image)
    RelativeLayout rel_image;
    @BindView(R.id.image_create)
    ImageView image_create;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.btn_ok)
    TextView btn_ok;
    @BindView(R.id.ed_name)
    EditText ed_name;

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_add_video;
    }

    @Override
    protected void initdata() {
        rel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TCVideoJoinChooseActivity.open(AddVideoActivity.this);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVideo();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 200) {
            TCVideoFileInfo video = data.getParcelableExtra("video");
            duration.setText(DateTimeUtil.formattedTime(video.getDuration() / 1000));
            Glide.with(this).load(video.getFileUri()).dontAnimate().into(image_create);
         File file = new File(video.getFilePath());
            RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), file);
            MultipartBody.Part file1 = MultipartBody.Part.createFormData("iFile", video.getFilePath(), requestFile);
            uploadVideo(file1);

            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(video.getFilePath());// videoPath 本地视频的路径
            Bitmap bitmap  = media.getFrameAtTime(4, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            File fileImage = getFile(bitmap);
            RequestBody requestFileImage = RequestBody.create(MediaType.parse("image/jpeg"), fileImage);
            MultipartBody.Part file1Image = MultipartBody.Part.createFormData("iFile", fileImage.getPath(), requestFileImage);
            uploadImage(file1Image);
            times = video.getDuration() / 1000;
        }
    }
    public File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    String file_id;
    String image_id;
    long times;

    private void uploadVideo(MultipartBody.Part iFile) {
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
                                   file_id = userInfo.getData().getFile_id();

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
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
                                   image_id = userInfo.getData().getFile_id();

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void addVideo() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("file_id", file_id);
        map.put("title", ed_name.getText().toString());
        map.put("image_id", image_id);
        map.put("times", times);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .liveVideoAdd(map)
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
