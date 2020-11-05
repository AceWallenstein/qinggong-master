package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.national.qinggong.contract.PlatformClassRoomContract;
import com.national.qinggong.presenter.ClassRoomPresenter;
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
 * 课堂列表
 * */
public class ClassRoomFragment extends BaseFragment implements PlatformClassRoomContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.title_search_lin)
    LinearLayout title_search_lin;
    @BindView(R.id.class_room_recyclerView)
    RecyclerView class_room_recyclerView;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<ClassTypeBean.DataBean.ListBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;

    private int mIndex = 1;
    private boolean mIsRefresh = true;
    private String type;
    @BindView(R.id.lin_title)
    LinearLayout lin_title;
    private String search_content;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        type = args.getString("type");
        search_content = args.getString("search_content");
        System.out.println("添加ClassRoomFragment"+type);
    }
    public static ClassRoomFragment newInstance() {
        Bundle args = new Bundle();
        ClassRoomFragment fragment = new ClassRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        if (!StringUtils.isEmpty(type)){
            if (type.equals("course")){
                lin_title.setVisibility(View.GONE);
            }

        }
        title_search_lin.setVisibility(View.VISIBLE);
        initRefresh();
        tv_title.setText("课堂列表");
        loadData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        getuserInfo("1", "20");
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
        twinkling_refreshlayout.setTargetView(class_room_recyclerView);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                initAdapter();
                twinkling_refreshlayout.finishRefreshing();

                mIsRefresh = true;
                mIndex = 1;
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

    public void loadData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2, LinearLayoutManager.VERTICAL, false);
        class_room_recyclerView.setLayoutManager(gridLayoutManager);
//       recycler_result.addItemDecoration(new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 10), true));
        mJobDataAdapter = new JoneBaseAdapter<ClassTypeBean.DataBean.ListBean>(class_room_recyclerView, R.layout.item_class_room) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, ClassTypeBean.DataBean.ListBean model) {
                helper.setText(R.id.title, model.getTitle() + "");
                helper.setText(R.id.view_num, model.getViews() + "人在学" + "");
                GlideUtils.loadImageByUrl(model.getPoster(), (ImageView) helper.getView(R.id.class_icon));
                LinearLayout lin_tag = helper.getView(R.id.lin_tag);
                lin_tag.removeAllViews();
                if (model.getTags() != null && model.getTags().size() > 0) {
                    for (int i = 0; i < model.getTags().size(); i++) {
                        View firstview = LayoutInflater.from(_mActivity).inflate(R.layout.tag_view, null);
                        TextView tag_tv = (TextView) firstview.findViewById(R.id.tag_tv);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(10,10,10,10);//4个参数按顺序分别是左上右下
                        tag_tv.setPadding(10, 5, 10, 5);
                        tag_tv.setLayoutParams(layoutParams);
                        tag_tv.setText(model.getTags().get(i).toString());
                        lin_tag.addView(firstview);
                    }


                }


//               model.getImage();
//                     if(position%2 == 1){
//                     String imageurl = "http://kangaroo.vtui365.com/uploads/20200421/7c11225696eb3ffa73e6c8f4d673293f.jpg";
//
//                 }else {
//                     String imageurl = "http://kangaroo.vtui365.com/uploads/20200417/7e349b9eb9ddc1c957e09702e3d1ab35.jpg";
////                     GlideUtils.loadImageByUrl(imageurl,(ImageView) helper.getView(R.id.class_icon));
//               Glide.with( _mActivity ).load(imageurl).skipMemoryCache(true).into((ImageView) helper.getView(R.id.class_icon) );//跳过内存缓存
//
//                 }


            }
        };
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Bundle bundle = new Bundle();
                ClassTypeBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
//                WebviewActivity.newIntance(_mActivity,currentbean,"课堂");
                String getParam = currentbean.getId()+"";
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "课堂详情接口");
                }

            }
        });

        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
//       mJobDataAdapter.setData(datas);
        class_room_recyclerView.setHasFixedSize(true);
        class_room_recyclerView.setAdapter(mJobDataAdapter);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.class_room_recycle;
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

    /*
     * ?page=1&limit=20
     * */
    private void getuserInfo(int page, String limit) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page+"");
        map.put("limit", limit);
        getPresenter().getClassRoomInfo(map);
    }

    @Override
    protected ClassRoomPresenter getPresenter() {
        return new ClassRoomPresenter(_mActivity, ClassRoomFragment.this);
    }

    @Override
    public void refreshArticleInfo(ClassTypeBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    if (mIsRefresh) {
                        mJobDataAdapter.getData().clear();
                        List<ClassTypeBean.DataBean.ListBean> getList = userInfo.getData().getList();
                        if (getList != null && getList.size() > 0) {
                            mJobDataAdapter.getData().addAll(getList);
                        }
                    } else {
                        List<ClassTypeBean.DataBean.ListBean> getList = userInfo.getData().getList();
                        mJobDataAdapter.getData().addAll(getList);
                    }
                    mJobDataAdapter.notifyDataSetChanged();
                    twinkling_refreshlayout.setEnableLoadmore(true);


                }else {
                    if (userInfo==null){
//                        setViewViable(true);
                    }
                    if (mIsRefresh) {
//                        setViewViable(false);
                        mJobDataAdapter.getData().clear();
                        mJobDataAdapter.notifyDataSetChanged();
                    }
                    twinkling_refreshlayout.setEnableLoadmore(true);
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
    public void refreShsearchInfo(ClassTypeBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    if (mIsRefresh) {
                        mJobDataAdapter.getData().clear();
                        List<ClassTypeBean.DataBean.ListBean> getList = userInfo.getData().getList();
                        if (getList != null && getList.size() > 0) {
                            mJobDataAdapter.getData().addAll(getList);
                        }
                    } else {
                        List<ClassTypeBean.DataBean.ListBean> getList = userInfo.getData().getList();
                        mJobDataAdapter.getData().addAll(getList);
                    }
                    mJobDataAdapter.notifyDataSetChanged();
                    twinkling_refreshlayout.setEnableLoadmore(true);


                }else {
                    if (userInfo==null){
//                        setViewViable(true);
                    }
                    if (mIsRefresh) {
//                        setViewViable(false);
                        mJobDataAdapter.getData().clear();
                        mJobDataAdapter.notifyDataSetChanged();
                    }
                    twinkling_refreshlayout.setEnableLoadmore(true);
                }
            }
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
