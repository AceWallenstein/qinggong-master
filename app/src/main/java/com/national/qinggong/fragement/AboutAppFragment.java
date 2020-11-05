package com.national.qinggong.fragement;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 关于
 * */
public class AboutAppFragment extends BaseFragment {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static AboutAppFragment newInstance() {
        Bundle args = new Bundle();
        AboutAppFragment fragment = new AboutAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        tvTitle.setText("关于国富民丰");
    }

    @OnClick({R.id.rl_back,R.id.suggest_face,R.id.help_rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.suggest_face:
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 16);
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                break;
            case R.id.help_rel:
                Bundle help = new Bundle();
                help.putInt("type", 17);
                PlatformForFragmentActivity.newInstance(_mActivity, help);
                break;


        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.about_app;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
