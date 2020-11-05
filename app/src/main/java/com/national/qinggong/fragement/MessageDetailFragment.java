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
* 消息详情
* */
public class MessageDetailFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tv_title;
    public static MessageDetailFragment newInstance() {
        Bundle args = new Bundle();
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initdata() {
        tv_title.setText("消息详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.messagedetail_fragment;
    }
    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
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
