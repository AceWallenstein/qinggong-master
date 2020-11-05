package com.national.qinggong.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.national.qinggong.HomeActivity;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.bean.LoginBean;
import com.national.qinggong.contract.UserLoginContract;
import com.national.qinggong.presenter.UserLoginPresenter;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AppLoginActivity extends BaseActivity implements UserLoginContract.View {


    int loginFlag;
    @BindView(R.id.user_change)
    TextView user_change;
    @BindView(R.id.saleman_change)
    TextView saleman_change;
    @BindView(R.id.guanliyuan_tv)
    TextView guanliyuan_tv;
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.pass_input)
    EditText passInput;
    @BindView(R.id.mima_lin)
    LinearLayout mimaLin;
    @BindView(R.id.login_tv)
    TextView loginTv;

    @Override
    protected void initdata() {
//        passLoginTv.setText(Html.fromHtml("<u>"+"0123456"+"</u>"));
        CacheHelper.remove(AppLoginActivity.this, "getToken");

        String email = CacheHelper.getAlias(AppLoginActivity.this, "email");
        String password = CacheHelper.getAlias(AppLoginActivity.this, "password");
        if(email!=null&& !TextUtils.isEmpty(email)){
            phoneInput.setText(email);
            passInput.setText(password);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected int initResourceLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String user_id = CacheHelper.getAlias(AppLoginActivity.this, "user_id");
        if (!StringUtils.isEmpty(user_id)) {
            ActivityUtils.startActivityAndFinish(AppLoginActivity.this, HomeActivity.class);
        }
    }

    private void userLogin(String phone, String userpass) {
        Map<String, String> map = new HashMap<>();
        map.put("account", phone);
        map.put("password", userpass);
        map.put("wxapp_id", "10001");
        getPresenter().getUserLogin(map);
    }

    private void usersmsLogin(String phone, String smscode) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("smscode", smscode);
        getPresenter().getLoginSms(map);
    }

    private void sendSms(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        getPresenter().getsendSms(map);
    }

    @Override
    protected UserLoginPresenter getPresenter() {
        return new UserLoginPresenter(AppLoginActivity.this, AppLoginActivity.this);
    }


    @OnClick({R.id.user_change, R.id.saleman_change, R.id.phone_input, R.id.pass_input,  R.id.login_tv, R.id.forget_pass, R.id.zhuce_zhanghao,R.id.guanliyuan_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_change:
                loginFlag = 0;
                user_change.setBackgroundResource(R.drawable.logo_choose_bg);
                saleman_change.setBackground(null);
                guanliyuan_tv.setBackground(null);
                user_change.setTextColor(Color.parseColor("#D20B17"));
                saleman_change.setTextColor(Color.parseColor("#AFBEDB"));
                guanliyuan_tv.setTextColor(Color.parseColor("#AFBEDB"));
                break;
            case R.id.saleman_change://验证码登录
                loginFlag = 1;
                user_change.setBackground(null);
                guanliyuan_tv.setBackground(null);
                saleman_change.setBackgroundResource(R.drawable.logo_choose_bg);
                user_change.setTextColor(Color.parseColor("#AFBEDB"));
                saleman_change.setTextColor(Color.parseColor("#D20B17"));
                guanliyuan_tv.setTextColor(Color.parseColor("#AFBEDB"));
                break;

            case R.id.guanliyuan_tv:
                loginFlag=2;
                guanliyuan_tv.setBackgroundResource(R.drawable.logo_choose_bg);
                saleman_change.setBackground(null);
                user_change.setBackground(null);
                user_change.setTextColor(Color.parseColor("#AFBEDB"));
                saleman_change.setTextColor(Color.parseColor("#AFBEDB"));
                guanliyuan_tv.setTextColor(Color.parseColor("#D20B17"));

                break;
            case R.id.phone_input:
                break;
            case R.id.pass_input:
                break;

          /*  case R.id.get_yanzhengma:
                if (StringUtils.isEmpty(phoneInput.getText().toString())) {
                    ToastUtil("请输入手机号");
                    return;
                }
                sendSms(phoneInput.getText().toString());
                break;*/
            case R.id.login_tv:
//                ActivityUtils.startActivityAndFinish(AppLoginActivity.this, HomeLazzyActivity.class);
                if (StringUtils.isEmpty(phoneInput.getText().toString())) {
                    ToastUtil("Please enter your cell phone number or email");
                    return;
                }
                if (StringUtils.isEmpty(passInput.getText().toString())) {
                    ToastUtil("Please enter your password");
                    return;
                }

                if (StringUtils.isEmpty(passInput.getText().toString())) {
                    ToastUtil("Please enter your password");
                    return;
                }
                 userLogin(phoneInput.getText().toString(), passInput.getText().toString());



                break;
            case R.id.forget_pass:
                ActivityUtils.startActivity(AppLoginActivity.this, forgetPwsswordctivity.class);
                break;
            case R.id.zhuce_zhanghao:
                ActivityUtils.startActivity(AppLoginActivity.this, RegeditActivity.class);
                break;
        }
    }

    /**
     * 倒计时
     */
  /*  private boolean runningDownTimer;     //判断是否在倒计时
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

    };*/

    /*
     * {"code":0,"msg":"账号密码错误","data":[]}
     * LoginBean
     * */
    @Override
    public void refreshLogin(Object userInfoobj) {
        Log.i("=============", userInfoobj.toString());

        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfoobj);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsoncontent);
            if (jsonObject != null) {
                int reason = jsonObject.getInt("code");
                String msg = jsonObject.getString("msg");
                Log.i("=====1========", reason + "");
                Log.i("======2=======", msg);

                if (reason == 1) {
                    LoginBean userInfo = new Gson().fromJson(jsoncontent, new TypeToken<LoginBean>() {
                    }.getType());
                    if (userInfo != null) {
                        int getCode = userInfo.getCode();
                        if (getCode == 1) {
                            LoginBean.DataBean getData = userInfo.getData();
                            if (getData != null) {
                                String getToken = getData.getToken();
                                if (getToken != null) {
                                    ToastUtil("Sign in successfully");
                                    CacheHelper.setAlias(AppLoginActivity.this, "getToken", getToken+ "");
                                    CacheHelper.setAlias(AppLoginActivity.this, "email", phoneInput.getText().toString().trim()+ "");
                                    CacheHelper.setAlias(AppLoginActivity.this, "password", passInput.getText().toString().trim()+"");
                                    ActivityUtils.startActivityAndFinish(AppLoginActivity.this, HomeLazzyActivity.class);
                                }
                            }
                        } else {
                            ToastUtil(userInfo.getMsg());
                        }
                    }


                } else {
                    ToastUtil(msg);
                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void refreshsendSms(BaseStrBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
              /*  ToastUtil(userInfo.getData());
                if (getYanzhengma.isClickable()) {
                    getYanzhengma.setClickable(false);
                }
                downTimer.start();  // 倒计时开始
                getYanzhengma.setText("正在发送");*/

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        downTimer.cancel();
    }

    @Override
    public void refreSmsLogin(Object userInfoobj) {
    /*    Log.i("=============", userInfoobj.toString());
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfoobj);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsoncontent);
            if (jsonObject != null) {
                int reason = jsonObject.getInt("code");
                String msg = jsonObject.getString("msg");
                Log.i("=====1========", reason + "");
                Log.i("======2=======", msg);

                if (reason == 1) {
                    LoginBean userInfo = new Gson().fromJson(jsoncontent, new TypeToken<LoginBean>() {
                    }.getType());
                    if (userInfo != null) {
                        int getCode = userInfo.getCode();
                        if (getCode == 1) {
                            LoginBean.DataBean getData = userInfo.getData();
                            if (getData != null) {
                                LoginBean.DataBean.UserBean getUser = getData.getUser();
                                if (getUser != null) {
                                    int getUser_id = getUser.getUser_id();
                                    String getPhone = getUser.getPhone();

//                     示例：2(0会员，1工人，2客服)
                                    int getRole = getUser.getRole();
                                    String rolename = "";
                                    if (getRole == 0) {//0会员
                                        rolename = "会员";
                                    } else if (getRole == 1) {//1工人
                                        rolename = "工人";
                                    } else { //2客服
                                        rolename = "客服";
                                    }
                                    CacheHelper.setAlias(AppLoginActivity.this, "user_id", getUser_id + "");
                                    CacheHelper.setAlias(AppLoginActivity.this, "phone", getPhone);
                                    CacheHelper.setAlias(AppLoginActivity.this, "role", rolename);
                                    ActivityUtils.startActivityAndFinish(AppLoginActivity.this, HomeActivity.class);
                                }
                            }
                        } else {
                            ToastUtil(userInfo.getMsg());
                        }
                    }


                } else {
                    ToastUtil(msg);
                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void showToast(String content) {

    }
}
