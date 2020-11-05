package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.NewsListContract;
import com.national.qinggong.presenter.NewsListPresenter;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * 新闻列表

 * */
public class FragmentAboutNew extends BaseFragment implements NewsListContract.View {


    @BindView(R.id.new_recycleview)
    RecyclerView newRecycleview;
    Unbinder unbinder;
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.tv_title_head_view)
    TextView tv_title_head_view;
    Unbinder unbinder1;
    private JoneBaseAdapter<AboutOursNewsBean.DataBeanX.ListBean.DataBean> newArrival_DataAdapter;

    private String title="";

    public static FragmentAboutNew newInstance(String title) {
        Bundle args = new Bundle();
        FragmentAboutNew fragment = new FragmentAboutNew();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NewsListPresenter getPresenter() {
        return new NewsListPresenter(_mActivity, FragmentAboutNew.this);
    }

    @Override
    protected void initdata() {

        title = getArguments().getString("title");
        if (!TextUtils.isEmpty(title)){
            tv_title_head_view.setText(title);
        }
        newArrivData();
        getNewListInfo();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.finish();
            }
        });
    }

    private void getNewListInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("category_id", "10001");
        getPresenter().getNewList(map);
    }

    public void newArrivData() {
        newArrival_DataAdapter = new JoneBaseAdapter<AboutOursNewsBean.DataBeanX.ListBean.DataBean>(newRecycleview, R.layout.item_news) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, AboutOursNewsBean.DataBeanX.ListBean.DataBean model) {
                helper.setText(R.id.new_title, model.getArticle_title() + "");



                //2020-10-17
                String times[]=model.getView_time().split("-");
                helper.setText(R.id.new_time, times[2] + "-"+times[1]+"-"+times[0]);
                //helper.setText(R.id.new_time, model.getView_time() + "");

                helper.setText(R.id.new_content, model.getArticle_title() + "");

 /*
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));*/
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        newRecycleview.setLayoutManager(layoutManager);
        newRecycleview.setHasFixedSize(true);
        newRecycleview.setAdapter(newArrival_DataAdapter);
        List<HomeBean> datas = new ArrayList<>();
     /*   for (int i = 0; i < 3; i++) {
            HomeBean homeBean = new HomeBean();
            homeBean.setMsg("测试" + i);
            datas.add(homeBean);
        }*/
//        mJobDataAdapter.setData(datas);

        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                AboutOursNewsBean.DataBeanX.ListBean.DataBean currentbean = newArrival_DataAdapter.getItem(position);

                String getParam = currentbean.getArticle_id() + "";
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "新闻详情");
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_aboutnews;
    }


    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    public void refreshNews(AboutOursNewsBean userInfo) {
        if (userInfo.getCode() == 1) {
            if (userInfo.getData() != null) {
                AboutOursNewsBean.DataBeanX getData = userInfo.getData();
                AboutOursNewsBean.DataBeanX.ListBean getList = getData.getList();
                if (getList != null) {
                    if (getList.getData() != null & getList.getData().size() > 0) {
                        List<AboutOursNewsBean.DataBeanX.ListBean.DataBean> listdata = getList.getData();
                        newArrival_DataAdapter.setData(listdata);
                    }
                }
            }
        }
    }

    @Override
    public void showToast(String content) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
