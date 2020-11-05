package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ChatMessageEvent;
import com.national.qinggong.bean.RefreshUrl;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

public class HomeALexLazzyFragment extends BaseFragment {
    @BindViews({R.id.stock_home, R.id.stock_wealth,R.id.message_home,R.id.car_home, R.id.stock_mine})
    List<LinearLayout> linetabs;
    private SupportFragment[] mFragments = new SupportFragment[5];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;

    @BindView(R.id.xiaoxi_num)
    TextView xiaoxi_num;
    public static HomeALexLazzyFragment newInstance() {
        HomeALexLazzyFragment fragment = new HomeALexLazzyFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = RootSortCateageFrragment.newInstance(false);
            mFragments[THIRD] = FragmentRootMesage.newInstance();
            mFragments[FOUR] = RootCartFrragment.newInstance(false);
            mFragments[FIVE] = FragmentAboutOurs.newInstance();
            loadMultipleRootFragment(R.id.fragment_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR],
                    mFragments[FIVE]

            );
        } else {
            mFragments[FIRST] = findChildFragment(HomeFragment.class);
            mFragments[SECOND] = findChildFragment(RootSortCateageFrragment.class);
            mFragments[THIRD] = findChildFragment(FragmentRootMesage.class);
            mFragments[FOUR] = findChildFragment(RootCartFrragment.class);
            mFragments[FIVE] = findChildFragment(FragmentAboutOurs.class);
        }
        selected(R.id.stock_home);
        setTabValue(0);

    }

    @OnClick({R.id.stock_home, R.id.stock_wealth,R.id.message_home,R.id.car_home, R.id.stock_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stock_home:
                selected(view.getId());
                setTabValue(0);
                break;
            case R.id.stock_wealth:
                selected(view.getId());
                setTabValue(1);
                break;
            case R.id.message_home:
                selected(view.getId());
                setTabValue(2);
            break;
            case R.id.car_home:
                selected(view.getId());
                setTabValue(3);
                break;
            case R.id.stock_mine:
                selected(view.getId());
                setTabValue(4);
                break;
        }

    }

    public static int preposition = 1;

    private void setTabValue(int postion) {
        switch (postion) {
            case 0:
                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
            case 1:
                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
            case 2:

                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
            case 3:
                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
            case 4:
                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
        }
        preposition = postion;


        Log.d("uiserTag1", preposition + "");

    }

    private void selected(int tabid) {
        for (LinearLayout linetabid : linetabs) {
            if (linetabid.getId() == tabid) {
                linetabid.setSelected(true);

            } else {
                linetabid.setSelected(false);
            }
        }

    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    //双击退出
    private long backTime = 2000;
    private long curTime;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - curTime > backTime) {
            //            ToastUtil.show(HomeActivity.this, R.string.exit_app);
            Toast.makeText(_mActivity, "Press again to exit", Toast.LENGTH_SHORT).show();
            curTime = System.currentTimeMillis();
        } else {
            System.exit(0);
        }
        return true;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }
    }

    @Subscribe
    public void refreshData(ChatMessageEvent refreshUrl) {
        if (null == refreshUrl) {
            return;
        }
        int new_message_num = refreshUrl.getNew_message_num();
        if(new_message_num>0){
            xiaoxi_num.setText(new_message_num+"");
            xiaoxi_num.setVisibility(View.VISIBLE);
        }else{
            xiaoxi_num.setVisibility(View.GONE);
        }
    }
}

