package com.national.qinggong.fragement;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.ServiceQrBean;
import com.national.qinggong.bean.UserInfoBean;
import com.national.qinggong.contract.UserCenterContract;
import com.national.qinggong.customview.SelectableRoundedImageView;
import com.national.qinggong.presenter.UserCenterPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.functions.Consumer;

/*用户中心
 *
 * */
public class UserCenterFragment extends BaseFragment implements UserCenterContract.View {
    public static final String ISSHOWIING = "ISSHOWIING";
    @BindView(R.id.my_title)
    TextView myTitle;
    @BindView(R.id.img_user_avatar)
    SelectableRoundedImageView imgUserAvatar;
    @BindView(R.id.fl_head)
    FrameLayout flHead;
    @BindView(R.id.userhead)
    LinearLayout userhead;
    @BindView(R.id.lin_my)
    LinearLayout linMy;
    @BindView(R.id.ll_lookforwork_wait_interview)
    LinearLayout llLookforworkWaitInterview;
    @BindView(R.id.ll_interview_assistant)
    LinearLayout llInterviewAssistant;
    @BindView(R.id.ll_signup_record)
    LinearLayout llSignupRecord;
    @BindView(R.id.attontion_gongzhonghao_rel)
    RelativeLayout attontionGongzhonghaoRel;
    @BindView(R.id.recommend_friend_rel)
    RelativeLayout recommendFriendRel;
    @BindView(R.id.invite_friend_rel)
    RelativeLayout inviteFriendRel;
    @BindView(R.id.about_rel)
    RelativeLayout aboutRel;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.userphone)
    TextView userphone;
    UserInfoBean userInfo;
    @BindView(R.id.class_room_recyclerView)
    RecyclerView class_room_recyclerView;
    private JoneBaseAdapter<ClassTypeBean.DataBean.ListBean> mJobDataAdapter;
    private String phone;
    private String getQrcode;

    @Override
    protected void initdata() {
        loadData();
        getHotClassInfo();
    }

    public static UserCenterFragment newInstance(boolean isShow) {
        Bundle args = new Bundle();
        args.putBoolean(ISSHOWIING, isShow);
        UserCenterFragment fragment = new UserCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.usercenter_fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUserInfo();
        //电话 qr
        getPhoneQrInfo();
    }

    private void getPhoneQrInfo() {
        Map<String, String> map = new HashMap<>();
        getPresenter().getsPhoneQRInfo(map);
    }


    private void getUserInfo() {
        String getcheck = (String) CacheHelper.get(_mActivity, "username", "");
        Map<String, String> map = new HashMap<>();
        map.put("username", getcheck);
        getPresenter().getUserInfo(map);
    }

    @Override
    protected UserCenterPresenter getPresenter() {
        return new UserCenterPresenter(_mActivity, UserCenterFragment.this);
    }


    @Override
    public void refreshUserInfo(Object getuserInfo) {
        Log.i("=1=login=4=", getuserInfo.toString());
        userInfo = new Gson().fromJson(getuserInfo.toString(), UserInfoBean.class);//把JSON格式的字符串转为List
        if (userInfo != null) {
            String getUsername = userInfo.getUsername();
            if (!StringUtils.isEmpty(getUsername)) {
                username.setText(getUsername);
            }
            String getPhone = userInfo.getPhone();
            if (!StringUtils.isEmpty(getPhone)) {
                String spit_phone = getPhone;
                spit_phone = spit_phone.substring(0, 5) + "****" + spit_phone.substring(8, 11);
                userphone.setText(spit_phone);
            }
           /* else {
                String spit_phone = "13162700502";
                spit_phone = spit_phone.substring(0, 5) + "****" + spit_phone.substring(8, 11);
                userphone.setText(spit_phone);
            }*/
        }


    }

    @Override
    public void refreshArticleInfo(ClassTypeBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    mJobDataAdapter.getData().clear();
                    List<ClassTypeBean.DataBean.ListBean> getList = userInfo.getData().getList();
                    if (getList != null && getList.size() > 0) {
                        mJobDataAdapter.getData().addAll(getList);
                    }
                }
            }
        }
    }

    @Override
    public void refreshServiceQRInfo(ServiceQrBean userInfo) {
    if (userInfo!=null){
        if (userInfo.getCode()!=1)return;
        ServiceQrBean.DataBean getData = userInfo.getData();
        if (getData!=null){
            ServiceQrBean.DataBean.ConfigBean getConfig = getData.getConfig();
            if (getConfig!=null){
                phone=getConfig.getService();
                getQrcode=getConfig.getQrcode();
            }

        }
    }
    }

    @Override
    public void showToast(String content) {

    }

    public void loadData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 3, LinearLayoutManager.VERTICAL, false);
        class_room_recyclerView.setLayoutManager(gridLayoutManager);
//       recycler_result.addItemDecoration(new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 10), true));
        mJobDataAdapter = new JoneBaseAdapter<ClassTypeBean.DataBean.ListBean>(class_room_recyclerView, R.layout.item_hot_classroom) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ClassTypeBean.DataBean.ListBean model) {
                helper.setText(R.id.title_hot, model.getTitle() + "");
                TextView title_hot = helper.getTextView(R.id.title_hot);
                title_hot.setSingleLine(false);
                title_hot.setMaxLines(2);
                title_hot.setMaxEms(8);

                title_hot.setEllipsize(android.text.TextUtils.TruncateAt.valueOf("END"));


//                helper.setText(R.id.view_num, model.getViews() + "人在学" + "");
                GlideUtils.loadImageByUrl(model.getPoster(), (ImageView) helper.getView(R.id.image_hot));
//                LinearLayout lin_tag = helper.getView(R.id.lin_tag);
//                lin_tag.removeAllViews();
//                if (model.getTags() != null && model.getTags().size() > 0) {
//                    for (int i = 0; i < model.getTags().size(); i++) {
//                        View firstview = LayoutInflater.from(_mActivity).inflate(R.layout.tag_view, null);
//                        TextView tag_tv = (TextView) firstview.findViewById(R.id.tag_tv);
//                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        layoutParams.setMargins(10,10,10,10);//4个参数按顺序分别是左上右下
//                        tag_tv.setPadding(10, 5, 10, 5);
//                        tag_tv.setLayoutParams(layoutParams);
//                        tag_tv.setText(model.getTags().get(i).toString());
//                        lin_tag.addView(firstview);
//                    }
//
//
//                }


            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Bundle bundle = new Bundle();
                ClassTypeBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
//                WebviewActivity.newIntance(_mActivity, currentbean, "课堂");
                String getParam = currentbean.getId()+"";
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "课堂详情接口");
                }
            }
        });

        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
//       mJobDataAdapter.setData(datas);
        class_room_recyclerView.setHasFixedSize(true);
        class_room_recyclerView.setAdapter(mJobDataAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app, getString(R.string.app_name)));
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    @OnClick({R.id.attontion_gongzhonghao_rel, R.id.recommend_friend_rel, R.id.invite_friend_rel, R.id.about_rel,
            R.id.ll_signup_record, R.id.hot_lin_more,R.id.userhead,R.id.ll_interview_assistant,R.id.ll_lookforwork_wait_interview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.attontion_gongzhonghao_rel:
                if (StringUtils.isEmpty(getQrcode))return;
                Bundle bundle = new Bundle();
                bundle.putInt("type", 10);
                bundle.putString("qrcode",getQrcode);
                PlatformForFragmentActivity.newInstance(_mActivity, bundle);
                break;
            case R.id.recommend_friend_rel:
                share();
                break;
            case R.id.invite_friend_rel:
                share();
                break;
            case R.id.about_rel:
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 9);
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                break;
            case R.id.ll_signup_record://任务
                Bundle newpeopleBundle = new Bundle();
                newpeopleBundle.putInt("type", 8);
                PlatformForFragmentActivity.newInstance(_mActivity, newpeopleBundle);
                break;
            case R.id.ll_interview_assistant://客服
                if (StringUtils.isEmpty(phone)){
                    return;
                }
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.requestEach(Manifest.permission.CALL_PHONE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    if (StringUtils.hasSimCard()) {
                                        Intent phoneIntent = new Intent("android.intent.action.CALL",
                                                Uri.parse("tel:" + phone));
                                        //启动
                                        startActivity(phoneIntent);
                                    } else {
                                        Toast.makeText(_mActivity, "您未安装sim卡,无法拨打电话", Toast.LENGTH_SHORT).show();
                                    }




                                    return;
                                }
                                if (permission.shouldShowRequestPermissionRationale) {// // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                                    return;
                                }else {
                                    Toast.makeText(_mActivity, "权限被拒绝，当前应用缺少必要权限。请点击\"设置\"-\"权限\"-打开所需权限", Toast.LENGTH_SHORT).show();
//                                // 用户拒绝了该权限，而且选中『不再询问』那么下次启动时，就不会提示出来了，
//                            startAppSettings();
                                }

                            }
                        });




                break;
            case R.id.ll_lookforwork_wait_interview://文章
                Bundle activityBundle = new Bundle();
                activityBundle.putInt("type", 13);
                PlatformForFragmentActivity.newInstance(_mActivity, activityBundle);
//                ActivityUtils.startActivity(_mActivity, MessageActivity.class);
                break;

            case R.id.hot_lin_more:
                Bundle classBundle = new Bundle();
                classBundle.putInt("type", 3);
                PlatformForFragmentActivity.newInstance(_mActivity, classBundle);
                break;
            case R.id.userhead:
                if (userInfo!=null){
                    Bundle userheadBundle = new Bundle();
                    userheadBundle.putInt("type", 11);
                    userheadBundle.putSerializable("userinfo",userInfo);
                    PlatformForFragmentActivity.newInstance(_mActivity, userheadBundle);
                }

                break;
        }
    }

    private void getHotClassInfo() {
        Map<String, String> map = new HashMap<>();

        getPresenter().getClassRoomInfo(map);
    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
