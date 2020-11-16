package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import com.national.qinggong.bean.MyFansBean;
import com.national.qinggong.customview.CornerTransform;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

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
public class MyFansActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.fans_recycleview)
    RecyclerView fans_recycleview;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    @BindView(R.id.tv_number)
    TextView tv_number;


    private JoneBaseAdapter<MyFansBean.Data.Lists.Datas> newArrival_DataAdapter;
    private String anchor_id="";

    public static void open(Context context,String anchor_id){
        Intent intent=new Intent(context,MyFansActivity.class);
        intent.putExtra("anchor_id",anchor_id);
        context.startActivity(intent);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_myfans;
    }

    @Override
    protected void initdata() {

        anchor_id=getIntent().getStringExtra("anchor_id");

        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newsaleData();
        getFans();
    }



    public void newsaleData() {
        newArrival_DataAdapter = new JoneBaseAdapter<MyFansBean.Data.Lists.Datas>(fans_recycleview, R.layout.item_my_fans) {

            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, MyFansBean.Data.Lists.Datas model) {
                helper.setText(R.id.tv_name, model.user.nickName + "");

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

                Glide.with(MyFansActivity.this).load(model.user.avatarUrl).into((ImageView) helper.getView(R.id.iv_user_head));
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyFansActivity.this, LinearLayoutManager.VERTICAL, false);
        fans_recycleview.setLayoutManager(layoutManager);
        fans_recycleview.setHasFixedSize(true);
        fans_recycleview.setAdapter(newArrival_DataAdapter);

        newArrival_DataAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
                switch (childView.getId()) {
                    case R.id.tv_number:
                        String user_id = newArrival_DataAdapter.getData().get(position).anchor_id+"";
                        LiveAnchorDetailActivity.open(MyFansActivity.this, user_id+ "");
                        break;
                }}
        });
        newArrival_DataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                //String anchor_id = newArrival_DataAdapter.getData().get(position).anchor_id+"";
                //LiveAnchorDetailActivity.open(MyFansActivity.this, anchor_id+ "");
            }
        });
    }




    private void getFans() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("anchor_id",anchor_id);

        RetrofitClient.getApiService(API.APP_QING_GONG)
                .getFans(map)
                .compose(RequestManager.<MyFansBean>applyIoSchedulers())
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
                .subscribe(new Consumer<MyFansBean>() {
                               @Override
                               public void accept(MyFansBean myFansBean) throws Exception {
                                   if (myFansBean.code == 1) {
                                       if (myFansBean.data.list.data!=null&myFansBean.data.list.data.size()>0){
                                           tv_number.setText(myFansBean.data.list.data.size()+"");
                                           newArrival_DataAdapter.setData(myFansBean.data.list.data);
                                           newArrival_DataAdapter.notifyDataSetChanged();
                                           fans_recycleview.setVisibility(View.VISIBLE);
                                           emptyLayout.setVisibility(View.GONE);
                                       }else {
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
