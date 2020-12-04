package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
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
import com.national.qinggong.bean.VisitsBean;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.customview.test.AutoPollAdapter;
import com.national.qinggong.customview.test.AutoPollRecyclerView;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TestActivity extends BaseActivity {

    @BindView(R.id.rv_recycleView)
    AutoPollRecyclerView mRecyclerView;

    private JoneBaseAdapter<VisitsBean.Data.Lists.Datas> newArrival_DataAdapter;
    private String anchor_id="";

    public static void open(Context context ){
        Intent intent=new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initdata() {


        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; ) {
            list.add(" Item: " + ++i);
        }
        AutoPollAdapter adapter = new AutoPollAdapter(this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRecyclerView.setAdapter(adapter);
        if (true) //保证itemCount的总个数宽度超过屏幕宽度->自己处理
            mRecyclerView.start();

    }





}
