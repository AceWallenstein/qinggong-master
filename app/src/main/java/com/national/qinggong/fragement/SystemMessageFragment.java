package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.HomeMessagebean;
import com.national.qinggong.bean.ReadUserInfoBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.SystemMessageContract;
import com.national.qinggong.presenter.SystemMessagePresenter;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.Constant;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class SystemMessageFragment extends BaseFragment implements SystemMessageContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.system_recyclerView)
    RecyclerView system_recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<HomeMessagebean.DataBean.ListBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;

    private int mIndex = 1;


    public static SystemMessageFragment newInstance() {
        Bundle args = new Bundle();
        SystemMessageFragment fragment = new SystemMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void initRefresh() {
        mProgressLayout = new ProgressLayout(_mActivity);
        twinkling_refreshlayout.setHeaderView(mProgressLayout);
        twinkling_refreshlayout.setFloatRefresh(true);
        twinkling_refreshlayout.setEnableOverScroll(false);
        twinkling_refreshlayout.setHeaderHeight(100);
        twinkling_refreshlayout.setMaxHeadHeight(120);
        twinkling_refreshlayout.setBottomHeight(70);
        twinkling_refreshlayout.setMaxBottomHeight(90);
        twinkling_refreshlayout.setTargetView(system_recyclerView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                initAdapter();
                twinkling_refreshlayout.finishRefreshing();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);

//                twinkling_refreshlayout.setEnableLoadmore(true);
            }
        });


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        String username = (String) CacheHelper.get(_mActivity, "username", "");
        if (!StringUtils.isEmpty(username)) {
            getuserMessageInfo(username, mIndex, "10");
        }
    }

    @Override
    protected void initdata() {
        tv_title.setText("系统消息");
        initRefresh();
        mJobDataAdapter = new JoneBaseAdapter<HomeMessagebean.DataBean.ListBean>(system_recyclerView, R.layout.item_system_message) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomeMessagebean.DataBean.ListBean model) {
//                2020-05-13 18:48:15
                if (!StringUtils.isEmpty(model.getCreate_at())){
                    if (model.getCreate_at().contains(" ")){
                        String[] time = model.getCreate_at().split(" ");
                        String secondtime = time[1];
                        String hourtime = secondtime.substring(0,secondtime.length()-3);
                        helper.setText(R.id.system_time, hourtime + "");
                    }else {
                        helper.setText(R.id.system_time, model.getCreate_at() + "");
                    }
                }


                helper.setText(R.id.system_title, model.getTitle() + "");
                helper.setText(R.id.system_content, model.getContent() + "");
//                if (model.getPoster()!=null){
//                    GlideUtils.loadImageByUrl(model.getPoster(),(ImageView) helper.getView(R.id.image));
//                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        system_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        system_recyclerView.setAdapter(mJobDataAdapter);
        List<HomeBean> datas = new ArrayList<>();

        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

//                ActivityArticleBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
//                WebviewActivity.newIntance(_mActivity,currentbean,"活动");

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_system_message;
    }


    @Override
    protected SystemMessagePresenter getPresenter() {
        return new SystemMessagePresenter(_mActivity, SystemMessageFragment.this);
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;

        }
    }

    private void getuserMessageInfo(String username, int page, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("page", page + "");
//        map.put("limit", limit);
        getPresenter().getMessage(map);
    }


    private void getReadMessageInfo(String username) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
//        map.put("id", id + "");
        getPresenter().getUpdateReadMessage(map);
    }


    @Override
    public void refreshMessagerInfo(HomeMessagebean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    HomeMessagebean.DataBean getData = userInfo.getData();
                    List<HomeMessagebean.DataBean.ListBean> getList = getData.getList();

                    mJobDataAdapter.setData(getList);
                    Log.i("===messaage==", getData.getList().size() + "");
                    if (getData.getList() != null & getData.getList().size() > 0) {
//                        message_num.setText(getData.getList().size() + "");
                    }
                }
            }

        }
    }

    @Override
    public void refreshMessagerfinishInfo() {
        String username = (String) CacheHelper.get(_mActivity, "username", "");
        if (!StringUtils.isEmpty(username)) {
            getReadMessageInfo(username);
        }
    }


    /*
    * {"code":1,"data":[],"msg":"操作成功"}
    * */
    @Override
    public void refreshReadMessagerInfo(ReadUserInfoBean userInfo) {
     if (userInfo!=null){
         int getCode = userInfo.getCode();
         if (getCode==1){
             if (!StringUtils.isEmpty(userInfo.getMsg())){
                 if (userInfo.getMsg().contains("成功")){
                     Log.i("===messa=====age==", userInfo.getMsg() + "");
                     EventBus.getDefault().post(new RefreshUrl(Constant.MSG_TO_apply));
                   ////
                 }
             }
         }
     }
    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
    @Override
    public void showToast(String content) {

    }
}
