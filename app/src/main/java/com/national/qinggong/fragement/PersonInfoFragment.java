package com.national.qinggong.fragement;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.UserInfoBean;
import com.national.qinggong.contract.PersonDataContract;
import com.national.qinggong.dialog.OnDialogClickListener;
import com.national.qinggong.dialog.dialog.custom.AlertPassDialog;
import com.national.qinggong.presenter.PersonDataPresenter;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonInfoFragment extends BaseFragment implements PersonDataContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.mingcheng)
    TextView mingcheng;


    private Serializable userinfo;
    private AlertPassDialog alertPassDialog;
    EditText old_pass, new_pass, again_new_pass;

    public static PersonInfoFragment newInstance() {
        Bundle args = new Bundle();
        PersonInfoFragment fragment = new PersonInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static PersonInfoFragment newInstance(Serializable userinfo) {
        Bundle args = new Bundle();
        PersonInfoFragment fragment = new PersonInfoFragment();
        args.putSerializable("userinfo", userinfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PersonDataPresenter getPresenter() {
        return new PersonDataPresenter(_mActivity, PersonInfoFragment.this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userinfo = getArguments().getSerializable("userinfo");

    }

    @Override
    protected void initdata() {
        tv_title.setText("个人资料");
        if (userinfo != null) {
            UserInfoBean currenyuserin = (UserInfoBean) userinfo;
            mingcheng.setText(currenyuserin.getName());
        }
    }

    @OnClick({R.id.rl_back, R.id.pass_alert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.pass_alert:
                showAlertPassDialog();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_info_alert;
    }

    @Override
    public void refreshUserPass(Object userInfo) {
        if (userInfo != null) {
            if (userInfo instanceof Boolean) {
                Boolean alertBoolean = (Boolean) userInfo;
                if (alertBoolean) {
                    Toast.makeText(_mActivity, "密码修改成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(_mActivity, "密码修改失败", Toast.LENGTH_LONG).show();
                }

            }
        }
    }


    /*
     * 修改密码弹框
     * */
    public void showAlertPassDialog() {
        alertPassDialog = new AlertPassDialog(_mActivity);
//        alertPassDialog.setText(R.id.tip_message_tv,"");
        old_pass = (EditText) alertPassDialog.findView(R.id.old_pass);
        new_pass = (EditText) alertPassDialog.findView(R.id.new_pass);
        again_new_pass = (EditText) alertPassDialog.findView(R.id.again_new_pass);
        alertPassDialog.show();
        alertPassDialog.setOnDialogClickListener(new OnDialogClickListener() {
            @Override
            public void onDialogClick(Dialog dialog, int id) {
                switch (id) {
                    case R.id.submit_alert:
                        if (StringUtils.isEmpty(old_pass.getText().toString())) {
                            Toast.makeText(_mActivity, "请输入原密码", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (StringUtils.isEmpty(new_pass.getText().toString())) {
                            Toast.makeText(_mActivity, "请输入新密码", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (StringUtils.isEmpty(again_new_pass.getText().toString())) {
                            Toast.makeText(_mActivity, "请再次输入新密码", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!new_pass.getText().toString().equals(again_new_pass.getText().toString())) {
                            Toast.makeText(_mActivity, "两次新密码不相同，请重新输入..", Toast.LENGTH_LONG).show();
                            return;
                        }
                        getUserInfo(again_new_pass.getText().toString());
                        alertPassDialog.dismiss();
//                        isSaveEdit=true;
//                        isEdit = true;
//                        isRecruEdit = true;
//                        _mActivity.onBackPressed();
                        break;
                    case R.id.old_pass:
//                        alertPassDialog.dismiss();
//                        _mActivity.onBackPressed();
                        break;
                    case R.id.new_pass:
//                        alertPassDialog.dismiss();
//                        _mActivity.onBackPressed();
                        break;
                    case R.id.again_new_pass:
//                        alertPassDialog.dismiss();
//                        _mActivity.onBackPressed();
                        break;


                }
            }
        });
    }


    /*
     * username=test&password=123456
     * */
    private void getUserInfo(String userpass) {
        String getcheck = (String) CacheHelper.get(_mActivity, "username", "");
        Map<String, String> map = new HashMap<>();
        map.put("username", getcheck);
        map.put("password", userpass);
        getPresenter().getUserInfo(map);
    }

    @Override
    public void showToast(String content) {

    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
