package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.bean.ChangePasswordBean;
import com.national.qinggong.bean.IndexCountryBean;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ChangePassword extends AppCompatActivity {


    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.phone_input)
    TextView phoneInput;
    @BindView(R.id.pass_input)
    EditText passInput;
    @BindView(R.id.mima_lin)
    LinearLayout mimaLin;
    @BindView(R.id.against_pass_input)
    EditText againstPassInput;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.relay_submit_tv)
    RelativeLayout relaySubmitTv;

    public static void open(Context context, String email) {
        Intent intent = new Intent(context, ChangePassword.class);
        intent.putExtra("email", email);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        phoneInput.setText(getIntent().getStringExtra("email"));

        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCount();
            }
        });
    }

    private void getCount() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("password", passInput.getText().toString());
        map.put("new_password", againstPassInput.getText().toString());
        map.put("token", getToken);
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .changePassword(map)
                .compose(RequestManager.<ChangePasswordBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ChangePasswordBean>() {
                               @Override
                               public void accept(ChangePasswordBean userInfo) throws Exception {
                                   if(userInfo.getCode()==1){
                                       ActivityUtils.startActivityAndFinish(ChangePassword.this, AppLoginActivity.class);
                                   }else{
                                       ToastUtilMsg.showToast(ChangePassword.this,userInfo.getMsg());
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
