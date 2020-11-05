package com.national.qinggong.fragement;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/*
 * 微信公众号跳转以及保存二维码
 * */
public class OfficialAccountsFragment extends BaseFragment {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.target_tv)
    TextView targetTv;
    @BindView(R.id.target_rela)
    RelativeLayout targetRela;
    @BindView(R.id.lin_content)
    LinearLayout linContent;
    @BindView(R.id.open_wechint)
    TextView openWechint;
    private String qrcode;

    @BindView(R.id.qr_image)
    ImageView qr_image;


    public static OfficialAccountsFragment newInstance(String QRurl) {
        Bundle args = new Bundle();
        OfficialAccountsFragment fragment = new OfficialAccountsFragment();
        args.putString("qrcode", QRurl);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrcode = getArguments().getString("qrcode");
    }

    @Override
    protected void initdata() {
        GlideUtils.loadImageByUrl(qrcode, qr_image);
        tvTitle.setText("微信公众号");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.official_accounts_fragment;
    }


    @OnClick({R.id.rl_back, R.id.open_wechint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.open_wechint:
                try {
                    if (StringUtils.isEmpty(qrcode)) return;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        checkPermissionRequest(qrcode);
                    } else {
                        //保存图片到相册
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                url2bitmap(qrcode);
                            }
                        }).start();
                    }
                } catch (Exception e) {
                }

                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void checkPermissionRequest(final String url) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    url2bitmap(url);
                                }
                            }).start();
                            return;
                        }
                        if (permission.shouldShowRequestPermissionRationale) {// // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                            return;
                        } else {
                            Toast.makeText(_mActivity, "权限被拒绝，当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限", Toast.LENGTH_SHORT).show();
//                                // 用户拒绝了该权限，而且选中『不再询问』那么下次启动时，就不会提示出来了，
//                            startAppSettings();
                        }

                    }
                });
    }

    public void url2bitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            if (bm != null) {
                save2Album(bm, url);
            }
        } catch (Exception e) {
            _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(_mActivity, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void save2Album(Bitmap bitmap, String picUrl) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "国富民丰");
        if (!appDir.exists()) appDir.mkdir();
        String[] str = picUrl.split("/");
        String fileName = str[str.length - 1];
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            onSaveSuccess(file);
        } catch (IOException e) {
            _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(_mActivity, "保存失败", Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
    }

    private void onSaveSuccess(final File file) {
        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                Toast.makeText(_mActivity, "保存成功，图片所在文件夹:SD卡根路径/国富民丰", Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(_mActivity, "" + "请安装最新版微信..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
