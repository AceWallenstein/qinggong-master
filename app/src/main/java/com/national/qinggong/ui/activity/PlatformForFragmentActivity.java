package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.fragement.AboutAppFragment;
import com.national.qinggong.fragement.ActivityFragment;
import com.national.qinggong.fragement.ArticleFragment;
import com.national.qinggong.fragement.ClassRoomFragment;
import com.national.qinggong.fragement.FragmentAboutNew;
import com.national.qinggong.fragement.FragmentChangeHistory;
import com.national.qinggong.fragement.FragmentConsult;
import com.national.qinggong.fragement.FragmentFackIdvise;
import com.national.qinggong.fragement.FragmentHelpCenter;
import com.national.qinggong.fragement.FragmentIntegralConfirm;
import com.national.qinggong.fragement.FragmentIntegralDetail;
import com.national.qinggong.fragement.FragmentIntegralMingxi;
import com.national.qinggong.fragement.FragmentIntegralShoping;
import com.national.qinggong.fragement.FragmentMyCollect;
import com.national.qinggong.fragement.FragmentPersonCenter;
import com.national.qinggong.fragement.FragmentPersonInfoAlert;
import com.national.qinggong.fragement.FragmentPinPai;
import com.national.qinggong.fragement.FragmentRootMesage;
import com.national.qinggong.fragement.FragmentSalesmanList;
import com.national.qinggong.fragement.FragmentSearch;
import com.national.qinggong.fragement.FragmentSearchGupiao;
import com.national.qinggong.fragement.FragmentSearchPage;
import com.national.qinggong.fragement.FragmentSearchShopResult;
import com.national.qinggong.fragement.FragmentShopDetail;
import com.national.qinggong.fragement.MessageDetailFragment;
import com.national.qinggong.fragement.NewHandTaskFragment;
import com.national.qinggong.fragement.OfficialAccountsFragment;
import com.national.qinggong.fragement.OpenAccountFragement;
import com.national.qinggong.fragement.PersonInfoFragment;
import com.national.qinggong.fragement.SearchFragment;
import com.national.qinggong.fragement.SystemMessageFragment;
import com.national.qinggong.fragement.UserCenterFragment;
import com.national.qinggong.util.Constant;

import java.io.Serializable;
import java.util.List;

public class PlatformForFragmentActivity extends BaseActivity {


    private int indexType;

    /**
     * @author Xyjian
     * @version: 1.8.2
     * @Description: TODO(){bundle 里 必须穿参数 type 作为跳转fragment的标识 ，其他参数根据不同的fragment定义}
     * @date: 2017/11/15 9:27
     */
    public static void newInstance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PlatformForFragmentActivity.class);
        intent.putExtras(bundle);
        boolean befrom = bundle.getBoolean(Constant.IS_NEW_TASK, false);
        //来自通知
        if (befrom) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        indexType = extras.getInt("type", -1);
        switch (indexType) {
            case 0:
                //
//                loadRootFragment(R.id.fl_platform_content, ConsumeHomeFragment.newInstance(getIntent().getExtras()));
                break;
            case 1:
                //
//                ArrayList<String> list = extras.getStringArrayList("list");
//                loadRootFragment(R.id.fl_platform_content, HomeGoodJobFragment.newInstance(list));
                break;
            case 2:  //消息详情
//                ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, MessageDetailFragment.newInstance());
                break;
            case 3:  //课堂列表
//                ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, ClassRoomFragment.newInstance());
                break;
            case 4:  //账号开户
//                ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, OpenAccountFragement.newInstance());
                break;
            case 5:  //活动
//                ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, ActivityFragment.newInstance());
                break;
            case 6:  //个人中心
//              ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, UserCenterFragment.newInstance(true));
                break;
            case 7:  //指数k线

                break;
            case 8:  //新手任务
//              ArrayList<String> list = extras.getStringArrayList("list");
                loadRootFragment(R.id.fl_platform_content, NewHandTaskFragment.newInstance());
                break;
            case 9:
                loadRootFragment(R.id.fl_platform_content, AboutAppFragment.newInstance());
                break;
            case 10:
                loadRootFragment(R.id.fl_platform_content, OfficialAccountsFragment.newInstance(getIntent().getExtras().getString("qrcode")));
                break;
            case 11:
                Serializable userinfo = getIntent().getSerializableExtra("userinfo");
                loadRootFragment(R.id.fl_platform_content, PersonInfoFragment.newInstance(userinfo));
                break;
            case 12:
                loadRootFragment(R.id.fl_platform_content, SystemMessageFragment.newInstance());
                break;
            case 13:
                loadRootFragment(R.id.fl_platform_content, ArticleFragment.newInstance());
                break;
            case 14:
                String search = getIntent().getExtras().getString("search");
                loadRootFragment(R.id.fl_platform_content, SearchFragment.newInstance(search));
                break;
            case 15:
                loadRootFragment(R.id.fl_platform_content, FragmentSearch.newInstance());
                break;
            case 16:
                loadRootFragment(R.id.fl_platform_content, FragmentFackIdvise.newInstance());
                break;
            case 17:
                loadRootFragment(R.id.fl_platform_content, FragmentHelpCenter.newInstance());
                break;
            case 18:
                loadRootFragment(R.id.fl_platform_content, FragmentSearchGupiao.newInstance());
                break;
            case 19:

                String good_detail_id = getIntent().getExtras().getString("good_detail_id");
                loadRootFragment(R.id.fl_platform_content, FragmentShopDetail.newInstance(good_detail_id));
                break;
            case 20:
                loadRootFragment(R.id.fl_platform_content, FragmentIntegralShoping.newInstance());
                break;
            case 21:
                String good_id = getIntent().getExtras().getString("good_id");
                loadRootFragment(R.id.fl_platform_content, FragmentIntegralDetail.newInstance(good_id));
                break;
            case 22:
                loadRootFragment(R.id.fl_platform_content, FragmentIntegralConfirm.newInstance());
                break;
            case 23://积分明细
                loadRootFragment(R.id.fl_platform_content, FragmentIntegralMingxi.newInstance());
                break;
            case 24://兑换记录
                loadRootFragment(R.id.fl_platform_content, FragmentChangeHistory.newInstance());
                break;
            case 25://个人中心
                loadRootFragment(R.id.fl_platform_content, FragmentPersonCenter.newInstance());
                break;
            case 26://个人信息修改
                loadRootFragment(R.id.fl_platform_content, FragmentPersonInfoAlert.newInstance());
                break;
            case 27://新闻列表
                String title1 = getIntent().getExtras().getString("title");
                loadRootFragment(R.id.fl_platform_content, FragmentAboutNew.newInstance(title1));
                break;
            case 28://品牌介绍
                String into_type = getIntent().getExtras().getString("into_type");
                String title = getIntent().getExtras().getString("title");
                loadRootFragment(R.id.fl_platform_content, FragmentPinPai.newInstance(into_type,title));
                break;
            case 29://销售列表
                String search_name = getIntent().getExtras().getString("search_name");
                loadRootFragment(R.id.fl_platform_content, FragmentSalesmanList.newInstance(search_name));
                break;
            case 30://搜索页
//                String into_type = getIntent().getExtras().getString("into_type");
                loadRootFragment(R.id.fl_platform_content, FragmentSearchPage.newInstance());
                break;
            case 31://搜索商品列表结果
//                String into_type = getIntent().getExtras().getString("into_type");
                String search_name_result = getIntent().getExtras().getString("search_name");
                loadRootFragment(R.id.fl_platform_content, FragmentSearchShopResult.newInstance(search_name_result));
                break;
            case 32://
                loadRootFragment(R.id.fl_platform_content, FragmentMyCollect.newInstance());
                break;
            case 33://
                loadRootFragment(R.id.fl_platform_content, FragmentRootMesage.newInstance());
                break;
            case 34://
                String user_id = getIntent().getExtras().getString("user_id");
                loadRootFragment(R.id.fl_platform_content, FragmentConsult.newInstance(user_id));
                break;


        }
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 1.使用getSupportFragmentManager().getFragments()获取到当前Activity中添加的Fragment集合
         * 2.遍历Fragment集合，手动调用在当前Activity中的Fragment中的onActivityResult()方法。
         */
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_platform_for_fragment;
    }
}
