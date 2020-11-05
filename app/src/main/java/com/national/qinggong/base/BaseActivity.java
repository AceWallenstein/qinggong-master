package com.national.qinggong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.national.qinggong.util.ToastUtilMsg;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity<T extends IPresenter> extends SupportActivity {
    private Unbinder butter;
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initResourceLayout());
        butter = ButterKnife.bind(this);

        mPresenter = getPresenter();
        initdata();
    }
    /**
     * 获取Presenter
     */
    protected T getPresenter() {
        return mPresenter;
    }
    /*首次数据*/
    protected abstract void initdata();

    @Override
    protected void onResume() {
        super.onResume();
    }

    /*抽象布局*/
    protected abstract int initResourceLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onViewDestroy();
        }
        butter.unbind();
    }
    public void ToastUtil(String toastMsg) {
        ToastUtilMsg.showToast(getApplicationContext(), toastMsg);
    }
}
