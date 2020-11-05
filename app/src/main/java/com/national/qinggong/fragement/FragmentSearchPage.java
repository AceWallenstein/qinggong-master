package com.national.qinggong.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchBean;
import com.national.qinggong.contract.SearchPageContract;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.CustomPopWindow;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.customview.GridSpacingItemDecoration;
import com.national.qinggong.customview.HorizontalItemDecoration;
import com.national.qinggong.presenter.SearchPagePresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.DensityUtil;
import com.national.qinggong.util.GlideUtils;

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
 * 搜索页
 * */
public class FragmentSearchPage extends BaseFragment implements SearchPageContract.View {

    @BindView(R.id.search_bg)
    ImageView searchBg;
    @BindView(R.id.cateage_tv)
    TextView cateageTv;
    @BindView(R.id.input_search_content)
    EditText inputSearchContent;
    @BindView(R.id.search_image)
    ImageView searchImage;
    @BindView(R.id.history_recyclerview)
    RecyclerView historyRecyclerview;
    @BindView(R.id.recommend_recyclerview)
    RecyclerView recommend_recyclerview;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;

    int choose_product_saleman;

    private JoneBaseAdapter<SearchBean.DataBean.GuessMyLikelistBean> recommend_DataAdapter;

    private JoneBaseAdapter<SearchBean.DataBean.SearchlistBean> joneBaseAdapter;

    public static FragmentSearchPage newInstance() {
        Bundle args = new Bundle();
        FragmentSearchPage fragment = new FragmentSearchPage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        loadHistoryData();
        loadRecommend();

    }

    @Override
    public void onResume() {
        super.onResume();
        getSaleListInfo();
    }
    //    http://qingong.meiliancheng.cn/index.php?s=/api/index/searchIndex&wxapp_id=10001&token=6900314512ee92d52ff0e3e59c550a13

    private void getSaleListInfo() {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().getSearchList(map);
    }
    public void loadRecommend() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recommend_recyclerview.setLayoutManager(gridLayoutManager);

        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(2, DensityUtil.dip2px(_mActivity, 5), true);
        recommend_recyclerview.addItemDecoration(gridSpacingItemDecoration);
        recommend_recyclerview.setHasFixedSize(true);
        recommend_DataAdapter = new JoneBaseAdapter<SearchBean.DataBean.GuessMyLikelistBean>(recommend_recyclerview, R.layout.item_hot_products) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchBean.DataBean.GuessMyLikelistBean model) {
                LinearLayout lin_root = helper.getView(R.id.lin_root);
//               DensityUtil.setViewMargin(_mActivity,lin_root, true, 10, 5, 10, 0);
                helper.setText(R.id.name_product, model.getGoods_name() + "");
                helper.setText(R.id.number, model.getNumber() + "");
                CornerTransform transformation = new CornerTransform(_mActivity, 10);
                //只是绘制左上角和右上角圆角
                transformation.setExceptCorner(false, false, true, true);
//        Glide.with(context).load(list.get(position).getIcon()).into(holder.ivtype);
                Glide.with(_mActivity).load(model.getFile_path())
                        .bitmapTransform(transformation).into((ImageView) helper.getView(R.id.product_image));

            }
        };
        recommend_recyclerview.setAdapter(recommend_DataAdapter);

        recommend_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if (recommend_DataAdapter!=null){
                    SearchBean.DataBean.GuessMyLikelistBean currentbean = recommend_DataAdapter.getItem(position);
                    if (currentbean!=null){
                        int getGoods_id = currentbean.getGoods_id();
                        Bundle Bundle_about = new Bundle();
                        Bundle_about.putInt("type", 19);
                        Bundle_about.putString("good_detail_id",getGoods_id+"");
                        PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
                    }
                }

            }
        });
    }





    /*历史记录*/
    public void loadHistoryData() {
//        cityModelList.clear();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity,LinearLayoutManager.HORIZONTAL,false);
        historyRecyclerview.setLayoutManager(linearLayoutManager);
        joneBaseAdapter = new JoneBaseAdapter<SearchBean.DataBean.SearchlistBean>(historyRecyclerview,R.layout.item_history_jilu) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, SearchBean.DataBean.SearchlistBean model) {
                helper.setText(R.id.tv_name,model.getKeywords());
                if(model.isSeleted()){
                    helper.setBackgroundRes(R.id.tv_name,R.drawable.shop_detail_car_bg);
                    helper.setTextColor(R.id.tv_name,Color.parseColor("#ffffff"));
                }else {
                    helper.setBackgroundRes(R.id.tv_name,R.drawable.drawable_history_normal);
                    helper.setTextColor(R.id.tv_name,Color.parseColor("#333333"));
                }
            }
        };
        historyRecyclerview.setHasFixedSize(true);
        historyRecyclerview.setAdapter(joneBaseAdapter);
        joneBaseAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                if(joneBaseAdapter.getData().get(position).isSeleted()){

                }else {
                    for(int i =0;i<joneBaseAdapter.getData().size();i++){
                        if(i==position){
                            joneBaseAdapter.getData().get(i).setSeleted(true);
                        }else {
                            joneBaseAdapter.getData().get(i).setSeleted(false);
                        }
                    }
                    Bundle searchBundle = new Bundle();
                    searchBundle.putInt("type", 29);
                    Log.i("search=====123======",joneBaseAdapter.getItem(position).getKeywords());
                    searchBundle.putString("search_name",joneBaseAdapter.getItem(position).getKeywords());
                    PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                    joneBaseAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    protected SearchPagePresenter getPresenter() {
        return new SearchPagePresenter(_mActivity, FragmentSearchPage.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_page;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    public void refreshSearchPage(SearchBean userInfo) {
        if (userInfo!=null){
            if (userInfo.getCode()==1){
                SearchBean.DataBean getData = userInfo.getData();
                if (getData.getSearchlist()!=null&getData.getSearchlist().size()>0){
                    List<SearchBean.DataBean.SearchlistBean> getSearchlist = getData.getSearchlist();
                    joneBaseAdapter.setData(getSearchlist);
                    joneBaseAdapter.notifyDataSetChanged();
                }

                if (getData.getList()!=null&getData.getList().size()>0){
                    List<SearchBean.DataBean.GuessMyLikelistBean> datList = getData.getList();
                    recommend_DataAdapter.setData(datList);
                    recommend_DataAdapter.notifyDataSetChanged();
                    recommend_recyclerview.setVisibility(View.VISIBLE);
                    emptyLayout.setVisibility(View.GONE);
                }else {
                    emptyLayout.setVisibility(View.VISIBLE);
                    emptyLayout.showEmpty();
                    recommend_recyclerview.setVisibility(View.GONE);
                }
            }
        }

    }

    @Override
    public void showToast(String content) {

    }

    @OnClick({R.id.search_bg, R.id.cateage_tv, R.id.search_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_bg:
                _mActivity.onBackPressed();
                break;
            case R.id.cateage_tv:
                showPopBottom();
                break;
            case R.id.search_image:
                if (choose_product_saleman==1){
                    Bundle searchBundle = new Bundle();
                    searchBundle.putInt("type", 29);
                    searchBundle.putString("search_name",inputSearchContent.getText().toString());
                    PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                }else if (choose_product_saleman==0){

                    Bundle searchBundle = new Bundle();
                    searchBundle.putInt("type", 31);
                    searchBundle.putString("search_name",inputSearchContent.getText().toString());
                    PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                }


                break;
        }
    }
    private void showPopBottom(){
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.pop_cateage_choose,null);
        View product_tv = view.findViewById(R.id.product_tv);

        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(_mActivity)
                .setView(view)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popWindow.showAsDropDown(cateageTv,0,10);
        product_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cateageTv.setText("Product");
                choose_product_saleman=0;
                popWindow.dissmiss();
            }
        });
        View saleman_tv = view.findViewById(R.id.saleman_tv);
        saleman_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cateageTv.setText("Salesman");
                choose_product_saleman=1;
                popWindow.dissmiss();

            }
        });

    }
}
