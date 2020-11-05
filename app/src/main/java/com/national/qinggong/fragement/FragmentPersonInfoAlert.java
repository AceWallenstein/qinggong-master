package com.national.qinggong.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.IndexCountryBean;
import com.national.qinggong.bean.PersonCenterBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.UploadImageBean;
import com.national.qinggong.contract.PersonCenterContract;
import com.national.qinggong.dialog.dialog.custom.SexDialogFragment;
import com.national.qinggong.presenter.PersonCenterPresenter;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.MyGlideImageLoader;
import com.national.qinggong.util.StringUtils;
import com.yxd.imagepickers.ImagePicker;
import com.yxd.imagepickers.bean.ImageItem;
import com.yxd.imagepickers.ui.ImageGridActivity;
import com.yxd.imagepickers.view.CropImageView;

import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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

public class FragmentPersonInfoAlert extends BaseFragment implements PersonCenterContract.View {
    @BindView(R.id.title_collect)
    TextView titleCollect;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.user_head)
    ImageView userHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.email_name)
    TextView emailName;
    @BindView(R.id.youxiaoxing_tv)
    TextView youxiaoxingTv;
    @BindView(R.id.phone_info)
    TextView phoneInfo;
    @BindView(R.id.contry_info)
    TextView contryInfo;
    @BindView(R.id.submit_alert)
    TextView submitAlert;
    @BindView(R.id.rel_country)
    LinearLayout rel_country;

    public static FragmentPersonInfoAlert newInstance() {
        Bundle args = new Bundle();
        FragmentPersonInfoAlert fragment = new FragmentPersonInfoAlert();
        fragment.setArguments(args);
        return fragment;
    }
    private int REQUEST_CODE_SELECT=12346;
    private MyGlideImageLoader myGlideImageLoader;
    @Override
    protected void initdata() {
        myGlideImageLoader = new MyGlideImageLoader();
        getPersonInfo();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });
        submitAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEdit();
            }
        });
        rel_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCount();
            }
        });

        userHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.getInstance().setSelectLimit(1);
                ImagePicker.getInstance().setCrop(true);
                ImagePicker.getInstance().setMultiMode(false);
                ImagePicker.getInstance().setStyle(CropImageView.Style.CIRCLE);
                Intent intent = new Intent(_mActivity, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);

            }
        });
    }


    private void getCount() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .indexCountry(map)
                .compose(RequestManager.<IndexCountryBean>applyIoSchedulers())
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
                .subscribe(new Consumer<IndexCountryBean>() {
                               @Override
                               public void accept(IndexCountryBean userInfo) throws Exception {
                                   if(userInfo.getCode()==1){
                                       SexDialog(userInfo.getData().getList());
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    private void uploadImage(MultipartBody.Part iFile) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
       // map.put("name", getToken+new Date().getTime());
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .uploadImage(map,iFile)
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
                                   avatarUrl=  userInfo.getData().getFile_path();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }
    private SexDialogFragment sexDialogFragment=null;
    public void SexDialog(List<IndexCountryBean.DataBean.ListBean> list) {
        if (sexDialogFragment==null){
            sexDialogFragment = new SexDialogFragment();
            sexDialogFragment.newInstance(list);
        }
        sexDialogFragment.setOnClickListener(new SexDialogFragment.ClickListener() {
            @Override
            public void result(IndexCountryBean.DataBean.ListBean data) {
                country=data.getName();
                contryInfo.setText(data.getName());
            }
        });

        sexDialogFragment.show(_mActivity.getSupportFragmentManager(), "shopSkuDialog");
    }
    @Override
    protected PersonCenterPresenter getPresenter() {
        return new PersonCenterPresenter(_mActivity, FragmentPersonInfoAlert.this);
    }

    String country="";
    String avatarUrl="";
    private void setEdit() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("nickName", userName.getText().toString().trim());
        map.put("avatarUrl",avatarUrl);
        map.put("phone", phoneInfo.getText().toString().trim());
        map.put("country", country);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .userEdit(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
//                        mView.refreshPostfinally();
                    }
                })
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object userInfo) throws Exception {
                                   _mActivity.finish();
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Toast.makeText(_mActivity, "修改失败", Toast.LENGTH_LONG).show();
                               }
                           }
                );
    }

    private void getPersonInfo() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
//        token=6900314512ee92d52ff0e3e59c550a13
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().getPersonData(map);
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_info;
    }

    public  byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }
    @Override
    public void personCenterData(PersonCenterBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    PersonCenterBean.DataBean.UserInfoBean getUserInfo = userInfo.getData().getUserInfo();
                    if (getUserInfo != null) {
                        emailName.setText(StringUtils.isEmpty(getUserInfo.getAccount()) ? "" : getUserInfo.getAccount());
                        userName.setText(StringUtils.isEmpty(getUserInfo.getNickName()) ? "" : getUserInfo.getNickName());
                        phoneInfo.setText(StringUtils.isEmpty(getUserInfo.getPhone()) ? "" : getUserInfo.getPhone());
                        contryInfo.setText(StringUtils.isEmpty(getUserInfo.getCountry()) ? "" : getUserInfo.getCountry());
                        avatarUrl=getUserInfo.getAvatarUrl();
                        if (!StringUtils.isEmpty(getUserInfo.getAvatarUrl())) {
                            Glide.with(_mActivity).load(getUserInfo.getAvatarUrl()).into((ImageView) userHead);
                        }


                    }
                }
            }
        }
    }

    @Override
    public void showToast(String content) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
             List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    myGlideImageLoader.displayImage(_mActivity,images.get(0).path,userHead, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    try {
                        File file  = new File(images.get(0).path);
                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        MultipartBody.Part file1 = MultipartBody.Part.createFormData("iFile", images.get(0).path, requestFile);
                        uploadImage(file1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
        }
    }

    public static String byte2hex(byte[] b)
    {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1){
                sb.append("0" + tmp);
            }else{
                sb.append(tmp);
            }

        }
        return sb.toString();
    }
}
