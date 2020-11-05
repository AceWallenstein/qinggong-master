package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.PlatformArticleContract;
import com.national.qinggong.presenter.PlatformArticlePresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * 文章
 * */
public class ArticleFragment extends BaseFragment implements PlatformArticleContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.title_search_lin)
    LinearLayout title_search_lin;
    @BindView(R.id.activity_recyclerView)
    RecyclerView message_recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;

    private int mIndex = 1;
    private boolean mIsRefresh = true;
    @BindView(R.id.lin_title)
    LinearLayout lin_title;
    private JoneBaseAdapter<ClassTypeBean.DataBean.ListBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;
    private String type;
    private String search_content;

    public static ArticleFragment newInstance() {
        Bundle args = new Bundle();
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        type = args.getString("type");
        search_content = args.getString("search_content");

        System.out.println("添加ArticleFragment"+type);
    }

    @Override
    protected void initdata() {
        title_search_lin.setVisibility(View.VISIBLE);
        if (!StringUtils.isEmpty(type)){
            if (type.equals("article")){
                lin_title.setVisibility(View.GONE);
            }

        }
        tv_title.setText("文章");
        initRefresh();
        mJobDataAdapter = new JoneBaseAdapter<ClassTypeBean.DataBean.ListBean>(message_recyclerView, R.layout.home_message_item) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ClassTypeBean.DataBean.ListBean model) {
                helper.setText(R.id.message_content, model.getTitle() + "");
                helper.setText(R.id.wenzhang_time, model.getCreate_at() + "");
                helper.setText(R.id.look_num, model.getViews() + "");
                GlideUtils.loadImageByUrl(model.getPoster(), (ImageView) helper.getView(R.id.message_icon));
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        message_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        message_recyclerView.setAdapter(mJobDataAdapter);
        List<HomeBean> datas = new ArrayList<>();
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                ClassTypeBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
                String getId = currentbean.getId()+"";
//                WebviewActivity.newIntance(_mActivity,currentbean,"我的文章");
                WebviewActivity.newIntance(_mActivity, getId, "文章");

            }
        });
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
        twinkling_refreshlayout.setTargetView(message_recyclerView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                initAdapter();

                mIsRefresh = true;
                mIndex = 1;
                twinkling_refreshlayout.finishRefreshing();
                if (!StringUtils.isEmpty(search_content)&!StringUtils.isEmpty(type)){
                    System.out.println("搜索"+search_content);
                    getSearchInfo(search_content,type);
                }else {
                    getuserInfo(mIndex, "20");
                }
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                if (!StringUtils.isEmpty(search_content)&!StringUtils.isEmpty(type)){
                    System.out.println("搜索"+search_content);
                    getSearchInfo(search_content,type);
                }else {
                    getuserInfo(mIndex, "20");
                }
//                twinkling_refreshlayout.setEnableLoadmore(true);
            }
        });
        twinkling_refreshlayout.startRefresh();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycle;
    }


    private void getuserInfo(int page, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page+"");
        map.put("limit", limit);
        getPresenter().getArticleInfo(map);
    }

/*
* ?keywords=宝贝&type=
* */
    private void getSearchInfo(String keywords, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("type", type);
        getPresenter().getSearchInfo(map);
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);





    }

    @Override
    protected PlatformArticlePresenter getPresenter() {
        return new PlatformArticlePresenter(_mActivity, ArticleFragment.this);
    }


    @OnClick({R.id.rl_back,R.id.title_search_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.title_search_lin:
                Bundle searchBundle = new Bundle();
                searchBundle.putInt("type", 15);
                PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                break;


        }
    }

    @Override
    public void refreshArticleInfo(ClassTypeBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                ClassTypeBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getList() != null & getData.getList().size() > 0) {
                        List<ClassTypeBean.DataBean.ListBean> getList = getData.getList();
                        mJobDataAdapter.setData(getList);
                        mJobDataAdapter.notifyDataSetChanged();
                    }
                }


            }
        }
    }

    @Override
    public void refreShsearchInfo(ClassTypeBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                ClassTypeBean.DataBean getData = userInfo.getData();
                if (getData != null) {
                    if (getData.getList() != null & getData.getList().size() > 0) {
                        List<ClassTypeBean.DataBean.ListBean> getList = getData.getList();
                        mJobDataAdapter.setData(getList);
                        mJobDataAdapter.notifyDataSetChanged();
                    }
                }


            }
        }
    }

    @Override
    public void refreshPostfinally() {
        if (twinkling_refreshlayout == null) {
            return;
        }
        if (mIsRefresh) {
            twinkling_refreshlayout.finishRefreshing();
        } else {
            twinkling_refreshlayout.finishLoadmore();
        }
    }

    @Override
    public void showToast(String content) {

    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
