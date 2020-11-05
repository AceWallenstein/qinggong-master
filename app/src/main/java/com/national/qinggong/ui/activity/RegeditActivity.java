package com.national.qinggong.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.bean.CorrentLogin;
import com.national.qinggong.bean.CountryBean;
import com.national.qinggong.bean.RegeditSuccessBean;
import com.national.qinggong.contract.RegeditContract;
import com.national.qinggong.presenter.RegeditPresenter;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.CommonUtils;
import com.national.qinggong.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegeditActivity extends BaseActivity implements RegeditContract.View {

    @BindView(R.id.user_regist)
    TextView user_change;
    @BindView(R.id.saleman_regist)
    TextView saleman_change;
    @BindView(R.id.guanliyuan_tv)
    TextView guanliyuan_tv;
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.pass_input)
    EditText passInput;
    @BindView(R.id.mima_lin)
    LinearLayout mimaLin;
    @BindView(R.id.against_pass)
    EditText againstPass;
    @BindView(R.id.get_yanzhengma)
    TextView getYanzhengma;
    @BindView(R.id.regist_tv)
    TextView registTv;
    @BindView(R.id.relay_regist_tv)
    RelativeLayout relayRegistTv;
    @BindView(R.id.have_login_tv)
    TextView haveLoginTv;
    @BindView(R.id.yanzhengmaInput)
    TextView yanzhengmaInput;


    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.input_country)
    EditText input_country;

    int loginFlag;

    private boolean runningDownTimer;     //判断是否在倒计时
    private OptionsPickerView<CountryBean.DataBean.ListBean> pvCustomOptions;
    private int getCountry_id;

    @Override
    protected void initdata() {
        SpannableString sStr = new SpannableString("By creating an account, you agree that you have read and accepted our Conditions of Use and Privacy Notice.");
        sStr.setSpan(new ForegroundColorSpan(Color.parseColor("#D20B17")), 70, 87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sStr.setSpan(new ForegroundColorSpan(Color.parseColor("#D20B17")), 92, 107, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(sStr);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.register_activity;
    }

    /**
     * 倒计时
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            runningDownTimer = true;
            getYanzhengma.setText((l / 1000) + "Seconds later");
            getYanzhengma.setTextColor(Color.parseColor("#cccccc"));
        }

        @Override
        public void onFinish() {
            runningDownTimer = false;
            getYanzhengma.setText("resend");
            getYanzhengma.setClickable(true);
            getYanzhengma.setTextColor(Color.parseColor("#ffffff"));
        }

    };

    @OnClick({R.id.rl_back, R.id.get_yanzhengma, R.id.regist_tv, R.id.user_regist, R.id.saleman_regist, R.id.guanliyuan_tv, R.id.have_login_tv,R.id.country_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.country_lin:
                countryList();
                break;
            case R.id.have_login_tv:
                finish();
                break;
            case R.id.user_regist:
                loginFlag = 0;
                user_change.setBackgroundResource(R.drawable.yanzhengma_bg);
                saleman_change.setBackground(null);
                guanliyuan_tv.setBackground(null);
                user_change.setTextColor(Color.parseColor("#FFFFFF"));
                saleman_change.setTextColor(Color.parseColor("#9BA8C2"));
                guanliyuan_tv.setTextColor(Color.parseColor("#9BA8C2"));
                break;
            case R.id.saleman_regist:
                loginFlag = 1;
                user_change.setBackground(null);
                guanliyuan_tv.setBackground(null);
                saleman_change.setBackgroundResource(R.drawable.yanzhengma_bg);
                user_change.setTextColor(Color.parseColor("#9BA8C2"));
                saleman_change.setTextColor(Color.parseColor("#FFFFFF"));
                guanliyuan_tv.setTextColor(Color.parseColor("#9BA8C2"));
                break;
            case R.id.guanliyuan_tv:
                loginFlag = 2;
                guanliyuan_tv.setBackgroundResource(R.drawable.yanzhengma_bg);
                saleman_change.setBackground(null);
                user_change.setBackground(null);
                user_change.setTextColor(Color.parseColor("#9BA8C2"));
                saleman_change.setTextColor(Color.parseColor("#9BA8C2"));
                guanliyuan_tv.setTextColor(Color.parseColor("#FFFFFF"));

                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.get_yanzhengma:
                if (!CommonUtils.isFastDoubleClick()) {
                    if (StringUtils.isEmpty(phoneInput.getText().toString())) {
                        ToastUtil("Please enter your cell  email");
                        return;
                    }
                    sendSms(phoneInput.getText().toString());
                }

                break;
            case R.id.regist_tv:
//
                String strphone = phoneInput.getText().toString().trim();
                String strpwd = passInput.getText().toString().trim();
                String against_pwd = againstPass.getText().toString().trim();
                String strVerificationCode = yanzhengmaInput.getText().toString().trim();

                if (TextUtils.isEmpty(strphone)) {
                    ToastUtil("Please enter your cell email");
                    return;
                }
                if (TextUtils.isEmpty(strVerificationCode)) {
                    ToastUtil("Please enter a verification code");
                    return;
                }
                if (TextUtils.isEmpty(strpwd)) {
                    ToastUtil("Please enter your password ");
                    return;
                }
                if (TextUtils.isEmpty(against_pwd)) {
                    ToastUtil("Please enter your password again");
                    return;
                }
                if (!against_pwd.equals(strpwd)){
                    ToastUtil("The two passwords are not equal");
                    return;
                }

//                if (strphone.length() != 11) {
//                    ToastUtil("请输入有效手机号");
//                    return;
//                }
                userRegedit(strphone, strVerificationCode, strpwd,getCountry_id+"");

                break;
        }
    }

    private void userRegedit(String phone, String smscode, String password,String country_id) {
        Map<String, String> map = new HashMap<>();
        map.put("identity", loginFlag + "");
        map.put("account", phone);
        map.put("smscode", smscode);
        map.put("password", password);
        map.put("wxapp_id", "10001");
        map.put("country_id", country_id);
        getPresenter().regeditSubmit(map);
    }

    private void sendSms(String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("email", phone);
        map.put("wxapp_id", "10001");
        getPresenter().getsendSms(map);
    }

    private void countryList() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        getPresenter().getcountry(map);
    }
    @Override
    protected RegeditPresenter getPresenter() {
        return new RegeditPresenter(RegeditActivity.this, RegeditActivity.this);
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

    /*
     * RegeditSuccessBean
     *
     * {"code":0.0,"msg":"验证码错误","data":[]}
     * */
    @Override
    public void refreshregeditSubmit(Object userInfo) {
//        userInfo.
        Log.i("======注册=======", userInfo.toString());
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsoncontent);
            if (jsonObject != null) {
                int reason = jsonObject.getInt("code");
                if (reason == 0) {//统一处理
//                    Log.i("json=1=",reason);
                    CorrentLogin newConsulist = new Gson().fromJson(userInfo.toString(), new TypeToken<CorrentLogin>() {
                    }.getType());
                    String day = newConsulist.getMsg();
                    ToastUtil(day);
                } else {
                    BaseStrBean newConsulist = new Gson().fromJson(userInfo.toString(), new TypeToken<BaseStrBean>() {
                    }.getType());
                    String day = newConsulist.getMsg();
                    ToastUtil(day);
                    List<String> userdata = newConsulist.getData();
                    if (userdata != null) {
                        String gegistinfo = userdata.get(0).toString();
                        ToastUtil(gegistinfo);
                        finish();
                    }
                /*    String getUser_id = userdata.getUser_id();
//                     示例：2(0会员，1工人，2客服)
                    String rolename = "会员";
                    String getPhone = phoneInput.getText().toString();
                    CacheHelper.setAlias(RegeditActivity.this, "user_id", getUser_id + "");
                    CacheHelper.setAlias(RegeditActivity.this, "phone", getPhone);
                    CacheHelper.setAlias(RegeditActivity.this, "role", rolename);*/
//                    ActivityUtils.startActivityAndFinish(RegeditActivity.this, HomeActivity.class);
//                    Intent intent = new Intent(RegeditActivity.this, HomeActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                }
            }


//            Log.i("json==",jsoncontent);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void countryList(CountryBean userInfo) {
        Log.i("======00000=======", userInfo.toString());
        if (userInfo!=null){
            CountryBean.DataBean getData = userInfo.getData();
            if (getData.getList()!=null&getData.getList().size()>0){
                cardItem.clear();
                cardItem = getData.getList();
                initCustomOptionPicker();

            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downTimer.cancel();
    }

    @Override
    public void showToast(String content) {

    }
     List<CountryBean.DataBean.ListBean> cardItem = new ArrayList<>();






    private void initCustomOptionPicker() {

      //条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getName();
                 getCountry_id = cardItem.get(options1).getCountry_id();
                input_country.setText(tx);
            }
        })
                .setLayoutRes(R.layout.country_view_list, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                        tvAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                getCardData();
//                                pvCustomOptions.setPicker(cardItem);
                            }
                        });

                    }
                })
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据

        pvCustomOptions.show();
    }
}
