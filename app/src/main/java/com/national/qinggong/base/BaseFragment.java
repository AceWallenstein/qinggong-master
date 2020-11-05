package com.national.qinggong.base;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<T extends IPresenter> extends SupportFragment {
    protected Activity mContext;
    protected T mPresenter;
    protected View rootView;
    private static final int PHONE_STATE_PREM = 126;
    private Unbinder butter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mPresenter = getPresenter();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        butter= ButterKnife.bind(BaseFragment.this,rootView);
        EventBus.getDefault().register(BaseFragment.this);
        initdata();
        return rootView;
    }

    protected abstract void initdata();

    protected abstract int getLayoutId();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.onViewDestroy();
        }
        EventBus.getDefault().unregister(this);
        butter.unbind();
    }
    /**
     * 获取Presenter
     */
    protected T getPresenter() {
        return mPresenter;
    }

}
