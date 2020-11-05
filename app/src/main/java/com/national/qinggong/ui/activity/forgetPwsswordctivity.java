package com.national.qinggong.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.contract.FindPassContract;
import com.national.qinggong.presenter.FindPassPresenter;
import com.national.qinggong.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class forgetPwsswordctivity extends BaseActivity implements FindPassContract.View {


    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.pass_input)
    EditText passInput;
    @BindView(R.id.mima_lin)
    LinearLayout mimaLin;
    @BindView(R.id.against_pass_input)
    EditText againstPassInput;
    @BindView(R.id.yanzhengma_input)
    EditText yanzhengmaInput;
    @BindView(R.id.getYanzhengma)
    TextView getYanzhengma;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.relay_submit_tv)
    RelativeLayout relaySubmitTv;
    @BindView(R.id.have_login_tv)
    TextView haveLoginTv;
    private boolean runningDownTimer;     //判断是否在倒计时


    @Override
    public int initResourceLayout() {
        return R.layout.forget_pass_activity;
    }


    @Override
    public void initdata() {

    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            getYanzhengma.setText((l / 1000) + "秒后重发");
            getYanzhengma.setTextColor(Color.parseColor("#cccccc"));
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            getYanzhengma.setText("重新发送");
            getYanzhengma.setClickable(true);
            getYanzhengma.setTextColor(Color.parseColor("#ff6653"));
        }

    };


    @OnClick({R.id.rl_back, R.id.getYanzhengma, R.id.mima_lin, R.id.login_tv, R.id.have_login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.have_login_tv:
                finish();
                break;
            case R.id.getYanzhengma:

                if (StringUtils.isEmpty(phoneInput.getText().toString())) {
                    ToastUtil("请输入手机号");
                    return;
                }
                sendSms(phoneInput.getText().toString());
                break;
            case R.id.mima_lin:
                break;
            case R.id.login_tv:
                String strphone = phoneInput.getText().toString().trim();
                String strpwd = passInput.getText().toString().trim();
                String againstPas = againstPassInput.getText().toString().trim();
                String strVerificationCode = yanzhengmaInput.getText().toString().trim();

                if (TextUtils.isEmpty(strphone)) {
                    ToastUtil("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(strVerificationCode)) {
                    ToastUtil("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(strpwd)) {
                    ToastUtil("请输入6-12密码");
                    return;
                }
                if (TextUtils.isEmpty(againstPas)) {
                    ToastUtil("请再次输入6-12密码");
                    return;
                }
                if (!againstPas.equals(strpwd)) {
                    ToastUtil("两次密码不相同，请重新输入");
                    return;
                }
                if (strphone.length() != 11) {
                    ToastUtil("请输入有效手机号");
                    return;
                }
                userfindpass(strphone, strVerificationCode, againstPas);
                break;
        }
    }


    private void userfindpass(String phone, String smscode, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("smscode", smscode);
        map.put("password", password);
        getPresenter().finPassSubmit(map);
    }

    private void sendSms(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        getPresenter().getsendSms(map);
    }

    @Override
    protected FindPassPresenter getPresenter() {
        return new FindPassPresenter(forgetPwsswordctivity.this, forgetPwsswordctivity.this);
    }

    @Override
    public void refreshsendSms(BaseStrBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                ToastUtil(userInfo.getData().toString());
                if (getYanzhengma.isClickable()) {
                    getYanzhengma.setClickable(false);
                }
                downTimer.start();  // 倒计时开始
                getYanzhengma.setText("Sending");

            }
        }
    }

    @Override
    public void refreshfinPassSubmit(BaseBean userInfo) {
        ToastUtil(userInfo.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downTimer.cancel();
    }

    @Override
    public void showToast(String content) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
