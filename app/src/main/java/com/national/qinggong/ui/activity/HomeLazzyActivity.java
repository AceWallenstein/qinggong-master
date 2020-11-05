package com.national.qinggong.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.national.qinggong.R;

import static com.national.qinggong.R.id.rl_parent;

import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.fragement.HomeALexLazzyFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class HomeLazzyActivity extends BaseActivity {
    @Override
    protected void initdata() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadRootFragment(rl_parent, HomeALexLazzyFragment.newInstance());
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.homelazzyactivity;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        loadRootFragment(rl_parent, HomeALexLazzyFragment.newInstance());
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
}
