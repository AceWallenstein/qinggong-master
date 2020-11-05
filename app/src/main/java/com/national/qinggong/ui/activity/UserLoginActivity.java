package com.national.qinggong.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.contract.PlatformLoginContract;
import com.national.qinggong.presenter.LoginPresenter;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 用户登录
 *
 * */
public class UserLoginActivity extends BaseActivity implements PlatformLoginContract.View {
    private static String mLoginValue = "mLoginValue";

    public static final String ISSHOWIING = "ISSHOWIING";
    @BindView(R.id.edPhone)
    EditText edPhone;
    @BindView(R.id.edpwd)
    EditText edpwd;
    @BindView(R.id.check_rember_pwd)
    CheckBox checkRemberPwd;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.tvForgetPwd)
    TextView tvForgetPwd;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    private boolean mIsShowing = true;

    public static void newIntent(Context context, int value) {
        Intent intent = new Intent(context, UserLoginActivity.class);
        intent.putExtra(mLoginValue, value);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.slide_in_from_bottom,
                R.anim.slide_out_to_bottom);
    }


    @Override
    protected void initdata() {

    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (StringUtils.isEmpty(edPhone.getText().toString())) {
                    ToastUtil("请输入用户名");
                    return;
                }
                if (StringUtils.isEmpty(edpwd.getText().toString())) {
                    ToastUtil("请输入密码");
                    return;
                }
                if(checkRemberPwd.isChecked()){
                    CacheHelper.put(UserLoginActivity.this,"userpass",edpwd.getText().toString());
                    CacheHelper.put(UserLoginActivity.this,"username",edPhone.getText().toString());
                    CacheHelper.put(UserLoginActivity.this,"check",true);
                }else {
                    CacheHelper.put(UserLoginActivity.this,"userpass","");
                    CacheHelper.put(UserLoginActivity.this,"username",edPhone.getText().toString());
                    CacheHelper.put(UserLoginActivity.this,"check",false);
                }





                String username = edPhone.getText().toString();
                String userpass = edpwd.getText().toString();

                userStockLogin(username,userpass,"huisutech.com");
//                intoUserInto();
                break;

        }
    }

    /*
    * http://120.24.219.141:12002/StockService/Login?username=test&password=huisutech.com&uuid=huisutech.com
    * 股票行情登录
    * */
    private void userStockLogin(String username,String userpass,String uuid) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", userpass);
        map.put("uuid", uuid);
        getPresenter().getUserInfo(map);
    }


    /*获取用户信息登录
    * */
    private void userLogin(String username,String userpass,String type) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", userpass);
        map.put("type", type);
        getPresenter().getUserLogin(map);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        dealUseNameAndPass();




    }



    /*
    * 处理用户记住用户名密码
    *
    * */


public  void  dealUseNameAndPass(){
    Boolean getcheck = (Boolean) CacheHelper.get(UserLoginActivity.this, "check", false);
    if (getcheck){
        ActivityUtils.startActivityAndFinish(UserLoginActivity.this,HomeLazzyActivity.class);
        checkRemberPwd.setChecked(true);
    }else {
        checkRemberPwd.setChecked(false);
    }
}






//    public void  intoUserInto(){
//      Bundle weekSalaryBundle = new Bundle();
//      weekSalaryBundle.putInt("type", 6);
//      PlatformForFragmentActivity.newInstance(_mActivity, weekSalaryBundle);
//    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(UserLoginActivity.this, UserLoginActivity.this);
    }
/*
* message:登录成功,uuid:9f54cd9e158710fa
* message:登录失败,账号或者密码不正确
* */
    @Override
    public void refreshLogin(Object userInfo) {
        Log.i("==xml=login=", userInfo.toString());
        if (userInfo!=null){
            if (userInfo instanceof  String){
                String StrLoginInfo = (String) userInfo;
                 if (StrLoginInfo.contains("uuid")){
                     String[] uuid = StrLoginInfo.split("uuid:");
                     Log.i("=1=xml=4=", uuid[1]);
                     CacheHelper.put(UserLoginActivity.this,"uuid",uuid[1]);
//                     ActivityUtils.startActivityAndFinish(UserLoginActivity.this,HomeLazzyActivity.class);
                 }else {
                     if (StrLoginInfo.contains("message")){
                         String[] message = StrLoginInfo.split("message:");
                         Toast.makeText(UserLoginActivity.this, message[1]+ "", Toast.LENGTH_LONG).show();
                     }
                 }
            }
        }
    }

    @Override
    public void refreshuserLogin(Object userInfo) {
        Log.i("==xml=userLogin=", userInfo.toString());
        if (userInfo!=null){
            if (userInfo instanceof  String){
                String StrLoginInfo = (String) userInfo;
                if (StrLoginInfo.contains("uuid")){
                    String[] uuid = StrLoginInfo.split("uuid:");
                    Log.i("=1=xml=4=", uuid[1]);
                    CacheHelper.setAlias(UserLoginActivity.this,"user_uuid",uuid[1]);
                    ActivityUtils.startActivityAndFinish(UserLoginActivity.this,HomeLazzyActivity.class);
                }else {
                    if (StrLoginInfo.contains("message")){
                        String[] message = StrLoginInfo.split("message:");
                        Toast.makeText(UserLoginActivity.this, message[1]+ "", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void stockLoginFinish() {
        Log.i("=1=xml=4login==", "=============");
        String username = edPhone.getText().toString();
        String userpass = edpwd.getText().toString();
        userLogin(username,userpass,"app");
    }

    @Override
    public void showToast(String content) {

    }
}
