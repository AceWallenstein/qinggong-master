package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.PersonCenterBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.PersonCenterContract;
import com.national.qinggong.presenter.PersonCenterPresenter;
import com.national.qinggong.ui.activity.AppLoginActivity;
import com.national.qinggong.ui.activity.ChangePassword;
import com.national.qinggong.ui.activity.LivePlayListActivity;
import com.national.qinggong.ui.activity.MyLiveMainActivity;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.SplashMessageActivity;
import com.national.qinggong.util.ActivityUtils;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*个人中心*/
public class FragmentPersonCenter extends BaseFragment implements PersonCenterContract.View {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_head)
    ImageView user_head;
    @BindView(R.id.user_email)
    TextView userEmail;
    @BindView(R.id.alert_live_main)
    RelativeLayout alert_live_main;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.jifen_point_tv)
    TextView jifenPointTv;
    @BindView(R.id.user_ecit)
    TextView userEcit;
    @BindView(R.id.search_top_rel)
    LinearLayout searchTopRel;
    @BindView(R.id.twinkling_refreshlayout)
    NestedScrollView twinklingRefreshlayout;
    Unbinder unbinder;

    public static FragmentPersonCenter newInstance() {
        Bundle args = new Bundle();
        FragmentPersonCenter fragment = new FragmentPersonCenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PersonCenterPresenter getPresenter() {
        return new PersonCenterPresenter(_mActivity, FragmentPersonCenter.this);
    }

    @Override
    protected void initdata() {
        getPersonInfo();
    }

    private void getPersonInfo() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

//        token=6900314512ee92d52ff0e3e59c550a13
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().getPersonData(map);
    }

    @Override
    public void onResume() {
        super.onResume();

        getPersonInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_center;
    }

    @Override
    public void personCenterData(PersonCenterBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    PersonCenterBean.DataBean.UserInfoBean getUserInfo = userInfo.getData().getUserInfo();
                    if (getUserInfo != null) {
                        userEmail.setText(StringUtils.isEmpty(getUserInfo.getAccount()) ? "" : getUserInfo.getAccount());
                        userName.setText(StringUtils.isEmpty(getUserInfo.getNickName()) ? "" : getUserInfo.getNickName());
                        if (!StringUtils.isEmpty(getUserInfo.getAvatarUrl())) {
                            Glide.with(_mActivity).load(getUserInfo.getAvatarUrl()).into((ImageView) user_head);
                        }
                        if(getUserInfo.getIs_anchor()==1){
                            alert_live_main.setVisibility(View.VISIBLE);
                        }else{
                            alert_live_main.setVisibility(View.GONE);
                        }
                        jifenPointTv.setText(getUserInfo.getPoints() + "");
                        CacheHelper.setAlias(_mActivity, "integral_points", getUserInfo.getPoints() + "");

                    }
                }
            }
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_back, R.id.user_ecit, R.id.integral_center_rel, R.id.mymessage_rel, R.id.my_collect, R.id.alert_pass, R.id.alert_user_info, R.id.jifen_mingxi, R.id.alert_live_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jifen_mingxi:
                Bundle mingxiBundle = new Bundle();
                mingxiBundle.putInt("type", 23);
                PlatformForFragmentActivity.newInstance(_mActivity, mingxiBundle);
                break;
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.alert_user_info:
                Bundle alertBundle = new Bundle();
                alertBundle.putInt("type", 26);
                PlatformForFragmentActivity.newInstance(_mActivity, alertBundle);
                break;
            case R.id.alert_pass:
                ChangePassword.open(_mActivity, userEmail.getText().toString());
                break;
            case R.id.user_ecit:
                  ActivityUtils.startActivityAndFinish(_mActivity, AppLoginActivity.class);

                break;
            case R.id.alert_live_main:
                MyLiveMainActivity.open(_mActivity);
                break;
            case R.id.my_collect:
                Bundle collectBundle = new Bundle();
                collectBundle.putInt("type", 32);
                PlatformForFragmentActivity.newInstance(_mActivity, collectBundle);
                break;
            case R.id.mymessage_rel:
                Bundle messageBundle = new Bundle();
                messageBundle.putInt("type", 33);
                PlatformForFragmentActivity.newInstance(_mActivity, messageBundle);
                break;
            case R.id.integral_center_rel:
                Bundle classBundle = new Bundle();
                classBundle.putInt("type", 20);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
                break;
        }
    }
}
