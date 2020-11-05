package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.HomeRecycleviewHolder;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.AdBean;
import com.national.qinggong.bean.CateageBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.ListBean;
import com.national.qinggong.bean.MultipleHomeBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

import static com.national.qinggong.bean.MultipleHomeBean.TYPE_MESSAGE;

/*首页*/
public class MainFragment extends BaseFragment {
//    @BindView(R.id.banner_home_top)
//    BGABanner banner;

    //    @BindView(R.id.first)
//    FrameLayout first;
    @BindView(R.id.home_recyclerView)
    RecyclerView home_recycleview;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private JoneBaseAdapter<HomeBean> mJobDataAdapter;
    private ProgressLayout mProgressLayout;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(_mActivity);
    }

    @Override
    protected void initdata() {
//        StatusBarUtil.transparencyBar(_mActivity);
        initRefresh();
//        initAdapter();

    }

    @Subscribe
    public void onEventMainThread(AdBean adBean) {

    }

    public void initAdapter() {
        View firstview = LayoutInflater.from(_mActivity).inflate(R.layout.banner_layour, null);
        View footview = LayoutInflater.from(_mActivity).inflate(R.layout.test_bottom, null);
//        ImageView testbanner = (ImageView) footview.findViewById(R.id.testbanner);
//        LinearLayout.LayoutParams linearLayout =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        testbanner.setLayoutParams(linearLayout);
        mJobDataAdapter = new JoneBaseAdapter<HomeBean>(home_recycleview, R.layout.item_main_list_item) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HomeBean model) {
                helper.setText(R.id.title, model.getMsg() + "");
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        home_recycleview.setLayoutManager(mJobLinearlayoutManager);
        mJobDataAdapter.addHeaderView(firstview);
        mJobDataAdapter.addFooterView(footview);
        home_recycleview.setAdapter(mJobDataAdapter.getHeaderAndFooterAdapter());

        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }
        mJobDataAdapter.setData(datas);
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
        twinkling_refreshlayout.setTargetView(home_recycleview);
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

    /*
     * 0 ,1,2
     * */
    List<MultipleHomeBean> multipleHomeBeanList = new ArrayList<MultipleHomeBean>();
    public void loadData() {



        /*新增消息公告*/
        MultipleHomeBean multipleHomemessage = new MultipleHomeBean();
        multipleHomemessage.setType(TYPE_MESSAGE);
        multipleHomeBeanList.add(multipleHomemessage);


        MultipleHomeBean CateagemultipleHomeBean = new MultipleHomeBean();
        CateagemultipleHomeBean.setType(CateagemultipleHomeBean.TYPE_CATE);
        List<CateageBean> catelistbean = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CateageBean catelistbean1 = new CateageBean();
            catelistbean1.setMsg("黄金" + i);
            catelistbean.add(catelistbean1);
        }
        CateagemultipleHomeBean.setCateageBeanList(catelistbean);
        multipleHomeBeanList.add(CateagemultipleHomeBean);

        MultipleHomeBean ListmultipleHomeBean = new MultipleHomeBean();
        ListmultipleHomeBean.setType(ListmultipleHomeBean.TYPE_LIST);
        List<ListBean> listbean = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            ListBean bean = new ListBean();
            bean.setMsg("测试" + i + "次");
            listbean.add(bean);
        }
        ListmultipleHomeBean.setListBeans(listbean);
        multipleHomeBeanList.add(ListmultipleHomeBean);
        LinearLayoutManager manage = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
//       manage.setAutoMeasureEnabled(true);
        home_recycleview.setLayoutManager(manage);
        HomeRecycleviewHolder adapter = new HomeRecycleviewHolder(_mActivity, multipleHomeBeanList);
        home_recycleview.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        getuserInfo();

    }

 /*   private void getuserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "");
        map.put("limit", "");
        getPresenter().getInfo(map);
    }*/

   /* @Override
    protected BannerPresenter getPresenter() {
        return new BannerPresenter(_mActivity, MainFragment.this);
    }*/

    private List<String> mToplist = new ArrayList<>(); //头部广告链接集合


}
