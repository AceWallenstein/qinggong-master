package com.national.qinggong.fragement;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/*
开户第一步
*
* */
public class OpenAccountFragement extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tv_title;
    public static OpenAccountFragement newInstance() {
        Bundle args = new Bundle();
        OpenAccountFragement fragment = new OpenAccountFragement();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initdata() {
        tv_title.setText("信息采集");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.openaccount_fragment_first;
    }
    @OnClick({R.id.rl_back,R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.next:
                start(VerilyAccountFragment.newInstance());
                break;

        }
    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
