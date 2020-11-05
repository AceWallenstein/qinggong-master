package com.national.qinggong.fragement;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.FaceAdviseBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.FaceToContract;
import com.national.qinggong.presenter.FacePresenter;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentFackIdvise extends BaseFragment implements FaceToContract.View {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_search_lin)
    LinearLayout titleSearchLin;
    @BindView(R.id.target_tv)
    TextView targetTv;
    @BindView(R.id.target_rela)
    RelativeLayout targetRela;
    @BindView(R.id.lin_content)
    LinearLayout linContent;
    @BindView(R.id.lin_title)
    LinearLayout linTitle;
    @BindView(R.id.yijian_content)
    EditText yijianContent;
    @BindView(R.id.shoujihao)
    EditText shoujihao;
    @BindView(R.id.submit)
    TextView submit;

    public static FragmentFackIdvise newInstance() {
        Bundle args = new Bundle();
        FragmentFackIdvise fragment = new FragmentFackIdvise();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        tvTitle.setText("意见反馈");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmentadvice_facetoface;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }


    @OnClick({R.id.rl_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.submit:
                if (StringUtils.isEmpty(yijianContent.getText().toString())) {
                    ToastUtilMsg.showToast(_mActivity, "意见内容为空?");
                    return;
                }
                if (StringUtils.isEmpty(shoujihao.getText().toString())) {
                    ToastUtilMsg.showToast(_mActivity, "手机号或者邮箱为空?");
                    return;
                }

                String getyijianContent = yijianContent.getText().toString().trim();
                String getshouji = shoujihao.getText().toString().trim();
                String getcheck = (String) CacheHelper.get(_mActivity, "username", "");
                if (!StringUtils.isEmpty(getcheck)) {
                    getuserInfo(getcheck, getyijianContent, getshouji);
                }
                break;
        }
    }

    private void getuserInfo(String username, String content, String phone) {
      /*  Map<String, String> map = new HashMap<>();
        map.put("username", username + "");
        map.put("content", content);
        map.put("phone", phone);*/
        getPresenter().submitAdvisenfo(phone,content,username);
    }

    @Override
    protected FacePresenter getPresenter() {
        return new FacePresenter(_mActivity, FragmentFackIdvise.this);
    }

    @Override
    public void refreadviseTask(FaceAdviseBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                ToastUtilMsg.showToast(_mActivity, userInfo.getMsg());
            }
        }
    }

    @Override
    public void showToast(String content) {

    }
}
