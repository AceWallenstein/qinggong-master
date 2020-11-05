package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SaleCollectBean;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.contract.MyColllectContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.MyColllectPresenter;
import com.national.qinggong.ui.activity.LiveAnchorDetailActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*我的收藏
 *
 * SaleCollectBean
 * */
public class FragmentMyCollect extends BaseFragment implements MyColllectContract.View {
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.bbbbb_gggg)
    RelativeLayout bbbbbGggg;
    @BindView(R.id.title_collect)
    TextView titleCollect;
    @BindView(R.id.new_recycleview)
    RecyclerView newRecycleview;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    private JoneBaseAdapter<SaleCollectBean.DataBean.ListBean> newArrival_DataAdapter;

    public static FragmentMyCollect newInstance() {
        Bundle args = new Bundle();
        FragmentMyCollect fragment = new FragmentMyCollect();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected MyColllectPresenter getPresenter() {
        return new MyColllectPresenter(_mActivity, FragmentMyCollect.this);
    }

    @Override
    protected void initdata() {
        newsaleData();
        getCollectInfo();
    }

    private void getCollectInfo() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
//        map.put("search", search);
//        map.put("page", page + "");
        map.put("token", getToken);
        getPresenter().getNewList(map);
    }
    private void cancleCollectInfo(String sales_user_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("sales_user_id", sales_user_id + "");
        map.put("token", getToken);
        getPresenter().submitCollectinfo(map);
    }

    public void newsaleData() {
        newArrival_DataAdapter = new JoneBaseAdapter<SaleCollectBean.DataBean.ListBean>(newRecycleview, R.layout.item_my_collect) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SaleCollectBean.DataBean.ListBean model) {
                helper.setText(R.id.user_name, model.getNickName() + "");
                helper.setText(R.id.user_email, model.getAccount() + "");
//                helper.setText(R.id.new_content,  model.getArticle_title() +"");
                helper.setItemChildClickListener(R.id.collect_image);

                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getAvatarUrl())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.user_head));
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
        newArrival_DataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                switch (childView.getId()) {
                    case R.id.collect_image:
                        int getUser_id = newArrival_DataAdapter.getData().get(position).getUser_id();
                        cancleCollectInfo(getUser_id + "");
                        break;
                }}
            });
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                String getUser_id = newArrival_DataAdapter.getData().get(position).getAnchor_id();

                LiveAnchorDetailActivity.open(_mActivity, getUser_id+ "");
//                AboutOursNewsBean.DataBeanX.ListBean.DataBean currentbean = newArrival_DataAdapter.getItem(position);
//
//                String getParam = currentbean.getArticle_id()+"";
//                if (!StringUtils.isEmpty(getParam)) {
//                    WebviewActivity.newIntance(_mActivity, getParam, "新闻详情");
//                }
            }
        });
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_collect;
    }

    @Override
    public void refreshSaleMan(SaleCollectBean homeBean) {
        if (homeBean != null) {
            int getCode = homeBean.getCode();
            if (homeBean != null) {
                if (homeBean.getData() != null) {
                    List<SaleCollectBean.DataBean.ListBean> getList = homeBean.getData().getList();
                    if (getList!=null&getList.size()>0){
                        newArrival_DataAdapter.setData(getList);
                        newArrival_DataAdapter.notifyDataSetChanged();
                        newRecycleview.setVisibility(View.VISIBLE);
                        emptyLayout.setVisibility(View.GONE);
                    }else {
                        emptyLayout.setVisibility(View.VISIBLE);
                        emptyLayout.showEmpty();
                        newRecycleview.setVisibility(View.GONE);
                    }
            }
        }
        }
    }

    @Override
    public void add_cancle_collect(CollectBean userInfo) {
     if (userInfo!=null){
         if (userInfo.getCode()==1){
             List<String> getData = userInfo.getData();
             if (getData!=null&getData.size()>0){
                 ToastUtilMsg.showToast(_mActivity,getData.get(0).toString()+"");
                 getCollectInfo();
             }
         }
     }
    }

    @Override
    public void stockFinish() {

    }

    @Override
    public void showToast(String content) {

    }

    @OnClick({R.id.rl_back, R.id.emptyLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.emptyLayout:
                break;
        }
    }
}
