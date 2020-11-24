package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.BrowseRecordsBean;
import com.national.qinggong.bean.MyFansBean;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.fragement.FragmentShopDetail;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 创建时间：2020/11/16
 * 编写人：czy_yangxudong
 * 功能描述：
 */
public class BrowseRecordsActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.fans_recycleview)
    RecyclerView fans_recycleview;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    @BindView(R.id.tv_number)
    TextView tv_number;


    private JoneBaseAdapter<BrowseRecordsBean.Data.Lists.Datas> newArrival_DataAdapter;

    public static void open(Context context){
        Intent intent=new Intent(context, BrowseRecordsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_browse_records;
    }

    @Override
    protected void initdata() {


        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newsaleData();
        getBrowseRecords();
    }


    public void newsaleData() {
        newArrival_DataAdapter = new JoneBaseAdapter<BrowseRecordsBean.Data.Lists.Datas>(fans_recycleview, R.layout.item_browse_record) {

            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, BrowseRecordsBean.Data.Lists.Datas model) {
                helper.setText(R.id.tv_name, model.goods.goods_name + "");
                helper.setText(R.id.tv_type, model.goods.number + "");

                String time1[]=model.create_time.split(" ");
                String time2[]=time1[0].split("-");

                switch (time2[1]){
                    case "01":
                        helper.setText(R.id.tv_date, "January "+time2[2]+", "+time2[0]);
                        break;
                    case "02":
                        helper.setText(R.id.tv_date, "February "+time2[2]+", "+time2[0]);
                        break;
                    case "03":
                        helper.setText(R.id.tv_date, "March "+time2[2]+", "+time2[0]);
                        break;
                    case "04":
                        helper.setText(R.id.tv_date, "April "+time2[2]+", "+time2[0]);
                        break;
                    case "05":
                        helper.setText(R.id.tv_date, "May "+time2[2]+", "+time2[0]);
                        break;
                    case "06":
                        helper.setText(R.id.tv_date, "June "+time2[2]+", "+time2[0]);
                        break;
                    case "07":
                        helper.setText(R.id.tv_date, "July "+time2[2]+", "+time2[0]);
                        break;
                    case "08":
                        helper.setText(R.id.tv_date, "August "+time2[2]+", "+time2[0]);
                        break;
                    case "09":
                        helper.setText(R.id.tv_date, "September "+time2[2]+", "+time2[0]);
                        break;
                    case "10":
                        helper.setText(R.id.tv_date, "October "+time2[2]+", "+time2[0]);
                        break;
                    case "11":
                        helper.setText(R.id.tv_date, "November "+time2[2]+", "+time2[0]);
                        break;
                    case "12":
                        helper.setText(R.id.tv_date, "December "+time2[2]+", "+time2[0]);
                        break;
                }
                //helper.setText(R.id.tv_date, model.create_time + "");

                helper.setItemChildClickListener(R.id.tv_number);

                Glide.with(BrowseRecordsActivity.this).load(model.goods.logo).into((ImageView) helper.getView(R.id.iv_user_head));
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(BrowseRecordsActivity.this, LinearLayoutManager.VERTICAL, false);
        fans_recycleview.setLayoutManager(layoutManager);
        fans_recycleview.setHasFixedSize(true);
        fans_recycleview.setAdapter(newArrival_DataAdapter);

        newArrival_DataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                switch (childView.getId()) {
                    case R.id.tv_number:
                        //String user_id = newArrival_DataAdapter.getData().get(position).anchor_id+"";
                        //LiveAnchorDetailActivity.open(BrowseRecordsActivity.this, user_id+ "");
                        break;
                }}
        });
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                String goods_id = newArrival_DataAdapter.getData().get(position).goods_id+"";
                Bundle Bundle_about = new Bundle();
                Bundle_about.putInt("type", 19);
                Bundle_about.putString("good_detail_id", goods_id + "");
                PlatformForFragmentActivity.newInstance(BrowseRecordsActivity.this, Bundle_about);
            }
        });
    }




    private void getBrowseRecords() {
        String getToken = CacheHelper.getAlias(this, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token",getToken);

        RetrofitClient.getApiService(API.APP_QING_GONG)
                .getBrowseRecords(map)
                .compose(RequestManager.<BrowseRecordsBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Consumer<BrowseRecordsBean>() {
                               @Override
                               public void accept(BrowseRecordsBean browseRecordsBean) throws Exception {
                                   if (browseRecordsBean.code == 1) {
                                       if (browseRecordsBean.data.list.data!=null&browseRecordsBean.data.list.data.size()>0){
                                           tv_number.setVisibility(View.VISIBLE);
                                           if (browseRecordsBean.data.list.data.size()==1){
                                               tv_number.setText("Total 1 item");
                                           }else{
                                               tv_number.setText("Total "+browseRecordsBean.data.list.data.size()+" items");
                                           }

                                           newArrival_DataAdapter.setData(browseRecordsBean.data.list.data);
                                           newArrival_DataAdapter.notifyDataSetChanged();
                                           fans_recycleview.setVisibility(View.VISIBLE);
                                           emptyLayout.setVisibility(View.GONE);
                                       }else {

                                           tv_number.setVisibility(View.GONE);

                                           emptyLayout.setVisibility(View.VISIBLE);
                                           emptyLayout.showEmpty();
                                           fans_recycleview.setVisibility(View.GONE);
                                       }
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

}
