package com.national.qinggong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.AdBean;
import com.national.qinggong.fragement.ClassFyFragemrnt;
import com.national.qinggong.fragement.HomeFragment;

import com.national.qinggong.fragement.UserCenterFragment;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/*管理fragement*/
public class HomeActivity extends BaseActivity {

    @BindViews({R.id.stock_home, R.id.stock_wealth, R.id.stock_mine})
    List<LinearLayout> linetabs;
    private SupportFragment[] mFragments = new SupportFragment[3];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    //双击退出
    private long backTime = 2000;
    private long curTime;


    @Override
    protected void initdata() {
//        StatusBarUtil.transparencyBar(_mActivity);
        //初始默认首页
        //初始默认首页
        selected(R.id.stock_home);
        setTabValue(0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = ClassFyFragemrnt.newInstance(false);
            mFragments[THIRD] = UserCenterFragment.newInstance(false);
//            mFragments[FOUR] = UserCenterFragment.newInstance(false);
            loadMultipleRootFragment(R.id.fragment_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]

            );
        } else {
//            mFragments[FIRST] = findChildFragment(MyFragement.class);
//            mFragments[SECOND] = findChildFragment(ClassFyFragemrnt.class);
//            mFragments[THIRD] = findChildFragment(ClassFyFragemrnt.class);
//            mFragments[FOUR] = findChildFragment(MyFragement.class);
        }
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_home;
    }

    @OnClick({R.id.stock_home, R.id.stock_wealth, R.id.stock_mine})
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
            case R.id.stock_mine:
                selected(view.getId());
                setTabValue(2);
                break;
        }

    }

    @Subscribe
    public void onEventMainThread(AdBean adBean) {

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
//    private void initFragment(int tabid) {
//        FragmentTransaction transaction = HomeActivity.this.getSupportFragmentManager().beginTransaction();
//        hideFragments(transaction);
//        switch (tabid) {
//            case R.id.stock_home:
//                if (storefragment == null) {
//                    storefragment = StoreFragment.getInstance();
//                    transaction.add(R.id.fragment_container, storefragment);
//                }
//                transaction.show(storefragment);
//                break;
//            case R.id.stock_wealth:
//                if (typefragment == null) {
//                    typefragment = TypeFragment.getInstance();
//                    //                    wealthfragment = WealthFragment.getInstance();
//                    transaction.add(R.id.fragment_container, typefragment);
//                }
//                transaction.show(typefragment);
//
//                break;
//            case R.id.stock_mine:
//                if (minefragment == null) {
//                    minefragment = MineFragment.getInstance();
//                    transaction.add(R.id.fragment_container, minefragment);
//                }
//                transaction.show(minefragment);
//                break;
//        }
//        transaction.commitAllowingStateLoss();
//    }

//    //切换fragment
//    private void hideFragments(FragmentTransaction transaction) {
//        if (storefragment != null) {
//            transaction.hide(storefragment);
//        }
//        if (typefragment != null) {
//            transaction.hide(typefragment);
//        }
//        if (sucaifragment != null) {
//            transaction.hide(sucaifragment);
//        }
//        if (minefragment != null) {
//            transaction.hide(minefragment);
//        }
//    }
    //重写双击退出
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() - curTime > backTime) {
//            //            ToastUtil.show(HomeActivity.this, R.string.exit_app);
//            Toast.makeText(HomeActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
//            curTime = System.currentTimeMillis();
//        } else {
//            finish();
//        }
//    }

    @Override
    public void onBackPressedSupport() {
//        super.onBackPressedSupport();
        if (System.currentTimeMillis() - curTime > backTime) {
            //            ToastUtil.show(HomeActivity.this, R.string.exit_app);
            Toast.makeText(HomeActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            curTime = System.currentTimeMillis();
        } else {
            finish();
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
                Log.d("uiserTag", preposition + "");

                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
            case 3:
                showHideFragment(mFragments[postion], mFragments[preposition]);

                break;
        }
        preposition = postion;

        Log.d("uiserTag1", preposition + "");

    }
}
