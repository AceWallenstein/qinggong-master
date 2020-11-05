package com.national.qinggong.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ContractBean;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.StockBlockBean;
import com.national.qinggong.contract.AnalyListContract;
import com.national.qinggong.presenter.AnalyPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.Constant;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
 * 分类
 *    citySeletedModel.getData().get(0).setSeleted(true);
                cityModelList.addAll(citySeletedModel.getData());
 * */
public class ClassFyFragemrnt extends BaseFragment implements AnalyListContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;


    @BindView(R.id.zhangfu)
    TextView zhangfu;

    @BindView(R.id.chengjiao)
    TextView chengjiao;

    @BindView(R.id.analy_first_tab_recyclerView)// 第一层tab分类
            RecyclerView analy_first_tab_recyclerView;


    @BindView(R.id.analysic_recyclerView)// 第一层tab分类
            RecyclerView analysic_recyclerView;


    public static final String ISSHOWIING = "ISSHOWIING";
    @BindView(R.id.title_search_lin)
    LinearLayout titleSearchLin;
    @BindView(R.id.target_tv)
    TextView targetTv;
    @BindView(R.id.target_rela)
    RelativeLayout targetRela;
    @BindView(R.id.lin_content)
    LinearLayout linContent;
    @BindView(R.id.lin_title)
    LinearLayout linTitle;
    @BindView(R.id.stock_where)
    TextView stockWhere;
    @BindView(R.id.zhangfu_icon)
    ImageView zhangfuIcon;
    @BindView(R.id.chengjiao_icon)
    ImageView chengjiaoIcon;
    Unbinder unbinder;
    private boolean mIsShowing = true;

    private JoneBaseAdapter<StockBlockBean.StockListbean> mJobDataAdapter;
    private JoneBaseAdapter<StockBlockBean.StockListbean> mlistDataAdapter;
    DecimalFormat df = new DecimalFormat("######0.00");


    List<StockBlockBean.StockListbean> oldlistbean;//原始数据
    List<StockBlockBean.StockListbean> listbean;//板块
    List<StockBlockBean.StockListbean> left_listbean=new ArrayList<>();//重新拼接板块
    List<StockBlockBean.StockListbean> new_listbean=new ArrayList<>();//

    List<StockBlockBean.StockListbean> contractList;//合约

    List<StockBlockBean.StockListbean> m_Code_List;//


    String defaultName = "";
    private String blockname;


    public static ClassFyFragemrnt newInstance(boolean isShow) {
        Bundle args = new Bundle();
        args.putBoolean(ISSHOWIING, isShow);
        ClassFyFragemrnt fragment = new ClassFyFragemrnt();
        fragment.setArguments(args);
        return fragment;
    }


    public static ClassFyFragemrnt newInstance(String blockName) {
        Bundle args = new Bundle();
       args.putString("blockname",blockName);
        ClassFyFragemrnt fragment = new ClassFyFragemrnt();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blockname = getArguments().getString("blockname");

    }


    @Override
    public void onResume() {
        super.onResume();


//        Toast.makeText(_mActivity, "" + "onResume", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //板块分类
        getBlockStyleInfo();
    }

    public void initAdapter() {
        mJobDataAdapter = new JoneBaseAdapter<StockBlockBean.StockListbean>(analy_first_tab_recyclerView, R.layout.item_analy_first_tab) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, StockBlockBean.StockListbean model) {
                helper.setText(R.id.textview_first_tab, model.getBlock() + "");
                if (model.isSeleted()) {
                    helper.getTextView(R.id.textview_first_tab).setBackgroundResource(R.drawable.vault_out_vauleseleted_shape);
                } else {
                    helper.getTextView(R.id.textview_first_tab).setBackgroundResource(R.drawable.vault_out_vaule_shape);
                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.HORIZONTAL, false);
        analy_first_tab_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        analy_first_tab_recyclerView.setAdapter(mJobDataAdapter);
        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                if (mJobDataAdapter.getData().get(position).isSeleted()) {

                } else {
                    for (int i = 0; i < mJobDataAdapter.getData().size(); i++) {
                        if (i == position) {
                            mJobDataAdapter.getData().get(i).setSeleted(true);
//                            getContractInfo();
                           /* String Mark = mJobDataAdapter.getData().get(i).getMark();
                            String code = mJobDataAdapter.getData().get(i).getCode();
                            getMultiMarketInfo("");*/
                            chooseName(mJobDataAdapter.getData().get(i).getBlock());
//                            Toast.makeText(_mActivity, "" + mJobDataAdapter.getData().get(position).getMsg(), Toast.LENGTH_LONG).show();
                        } else {
                            mJobDataAdapter.getData().get(i).setSeleted(false);
                        }
                    }

                    mJobDataAdapter.notifyDataSetChanged();
                }
            }
        });


      /*  ArrayList<String> tab_content = new ArrayList<>(Arrays.asList(_mActivity.getResources().getStringArray(R.array.tab_content)));
        List<HomeBean> datas = new ArrayList<>();
        for (int i = 0; i < tab_content.size(); i++) {
            HomeBean homeBean = new HomeBean();
            if (i == 0) {
                homeBean.setSeleted(true);
            }
            homeBean.setMsg(tab_content.get(i).toString());
            datas.add(homeBean);
        }*/
//        mJobDataAdapter.setData(datas);
    }


    /*列表初始化*/
    public void initlistAdapter() {
        mlistDataAdapter = new JoneBaseAdapter<StockBlockBean.StockListbean>(analysic_recyclerView, R.layout.item_analy_second) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, StockBlockBean.StockListbean model) {
                helper.setText(R.id.tv_left, StringUtils.isEmpty(model.getName()) ? "--" : model.getName() + "");
                helper.setText(R.id.ag_tv, model.getMark() == null ? "--" : model.getMark() + "");
                helper.setText(R.id.new_price, model.getNew_Privce() == null ? "--" : model.getNew_Privce() + "");



                if (chengjiao.getText().toString().equals("成交量")){
                    helper.setText(R.id.chengjiaoliang_tv, model.getChengjiao_num() == null ? "--" : model.getChengjiao_num() + "");
                }else {
                    helper.setText(R.id.chengjiaoliang_tv, model.getChicang_liang() == null ? "--" : model.getChicang_liang() + "");
                }
                if (zhangfu.getText().toString().equals("涨跌幅")){
                    helper.setText(R.id.zhangdiefu_tv, model.getZhang_die_fu() == null ? "--" : model.getZhang_die_fu() + "");
                }else {
                    helper.setText(R.id.zhangdiefu_tv, model.getZhangdie_zhi()== null ? "--" : model.getZhangdie_zhi() + "");
                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        analysic_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        analysic_recyclerView.setAdapter(mlistDataAdapter);
        analysic_recyclerView.setHasFixedSize(true);
        analysic_recyclerView.setNestedScrollingEnabled(false);
//        List<HomeBean> datas = new ArrayList<>();
//        for (int i = 0; i < 14; i++) {
//            HomeBean homeBean = new HomeBean();
//            homeBean.setMsg("白银延期" + i);
//            datas.add(homeBean);
//        }
//        mlistDataAdapter.setData(datas);
        mlistDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                if (contractList != null) {
                    /*进入k线*/
                    Bundle Bundle = new Bundle();
                    Bundle.putInt("type", 7);
                    Bundle.putString(Constant.mark, contractList.get(position).getMark());
                    Bundle.putString(Constant.code, contractList.get(position).getCode());
                    PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
                }

//              Toast.makeText(_mActivity, "" + mlistDataAdapter.getData().get(position).getName(), Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    protected void initdata() {
        tv_title.setText("指数分析");
        rl_back.setVisibility(View.GONE);
        initAdapter();
        initlistAdapter();
        if (!StringUtils.isEmpty(blockname))
        {
            rl_back.setVisibility(View.VISIBLE);
            Log.i("========66670=========", blockname + "---------------------------");
            analy_first_tab_recyclerView.setVisibility(View.GONE);
//            chooseName(blockname);
            getBlockStyleInfo();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.analysis_fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //...这个页面隐藏的操作
//            closeTimer();
        } else {
            //...这个页面显示的操作
//            getContractInfo();
//            startTime();
        }
    }


    private void getContractInfo() {
        Map<String, String> map = new HashMap<>();
        getPresenter().getContractInfo(map);
    }


    /*
     * 分类
     * */
    private void getBlockStyleInfo() {
        Map<String, String> map = new HashMap<>();
        getPresenter().getBlockInfo(map);
    }


    /*
     *
     * 12、得到多个股票的行情报价
     * StkLabels=SH600000,SZ000001
     *
     *市场代码和股票代码拼接 多个用  ，   连接
     * */
    private void getMultiMarketInfo(String labelStr) {
        Map<String, String> map = new HashMap<>();
        map.put("StkLabels", labelStr);
        getPresenter().getMultiMarketInfo(map);
    }

    /*
     合约
    *
    * */


    @Override
    protected AnalyPresenter getPresenter() {
        return new AnalyPresenter(_mActivity, ClassFyFragemrnt.this);
    }


    /*m模块选择
     * */
    public void chooseName(String choosename) {
//        Log.i("================choosen1", choosename + "=======");
        if (listbean == null) return;
//        Log.i("================choosen2", choosename + "=======");
        contractList = new ArrayList<>();
        m_Code_List = new ArrayList<>();
        for (int i = 0; i < oldlistbean.size(); i++) {
            StockBlockBean.StockListbean current = oldlistbean.get(i);
            String m_Code = current.getMark() + current.getCode();
            current.setM_code(m_Code);
            m_Code_List.add(current);

            if (current.getBlock().equals(choosename)) { //合约名
                contractList.add(current);
            }


        }
        mlistDataAdapter.setData(contractList);
        mlistDataAdapter.notifyDataSetChanged();
        StringBuffer string = new StringBuffer();
        Log.i("========m_Code=nu======", contractList.size() + "=======");

        for (int i = 0; i < contractList.size(); i++) {
            if (i < 1000) {
                string.append(contractList.get(i).getM_code()).append(",");
            }

        }
        String labelStr = string.toString();
        String StkLabels = labelStr.substring(0, labelStr.length() - 1);
        Log.i("========m_Code=2======", StkLabels + "=======");

        getMultiMarketInfo(StkLabels);
    }

int  index_1,index_2,index_3,index_4;
    /*
     *
     * 板块
     * */
    @Override
    public void refreshBlockInfo(List<StockBlockBean.StockListbean> Info) {
        oldlistbean = (List<StockBlockBean.StockListbean>) Info;
        contractList = new ArrayList<>();
        m_Code_List = new ArrayList<>();
        listbean = new ArrayList<>(); //处理模块数据
        if (!StringUtils.isEmpty(blockname))
        {


            for (int i = 0; i < oldlistbean.size(); i++) {
                StockBlockBean.StockListbean current = oldlistbean.get(i);
                listbean.add(current);//处理一次
            }
            for (int i = 0; i <listbean.size() ; i++) {
                String getBlock = listbean.get(i).getBlock();
                if (getBlock.equals("上海金属")){
                    index_1=i;
                } else if (getBlock.equals("股指期货")){
                    index_2=i;
                }else if (getBlock.equals("外汇")){
                    index_3=i;
                }else if (getBlock.equals("深证股票")){
                    index_4=i;
                }
            }
            left_listbean.add(listbean.get(index_1));
            left_listbean.add(listbean.get(index_2));
            left_listbean.add(listbean.get(index_3));
            left_listbean.add(listbean.get(index_4));
            new_listbean.addAll(left_listbean);
            listbean.addAll(left_listbean);
            listbean.removeAll(left_listbean);
            new_listbean.addAll(listbean);
            for (int i = 0; i < new_listbean.size(); i++) {

                StockBlockBean.StockListbean current = new_listbean.get(i);
                String m_Code = current.getMark() + current.getCode();
                current.setM_code(m_Code);
                m_Code_List.add(current);

                if (current.getBlock().equals(blockname)) { //模块
                    contractList.add(current);
                }


            }
            mlistDataAdapter.setData(contractList);
            mlistDataAdapter.notifyDataSetChanged();


            StringBuffer string = new StringBuffer();

            for (int i = 0; i < contractList.size(); i++) {
                if (i < 1000) {
                    string.append(contractList.get(i).getM_code()).append(",");
                }

            }

            String labelStr = string.toString();
            String StkLabels = labelStr.substring(0, labelStr.length() - 1);

            removeReplace(new_listbean);
            mJobDataAdapter.setData(new_listbean);
            mJobDataAdapter.notifyDataSetChanged();
            getMultiMarketInfo(StkLabels);
        }else {

        for (int i = 0; i < oldlistbean.size(); i++) {
            StockBlockBean.StockListbean current = oldlistbean.get(i);
            listbean.add(current);//处理一次
        }
        for (int i = 0; i <listbean.size() ; i++) {
            String getBlock = listbean.get(i).getBlock();
            if (getBlock.equals("上海金属")){
                index_1=i;
            } else if (getBlock.equals("股指期货")){
                index_2=i;
            }else if (getBlock.equals("外汇")){
                index_3=i;
            }else if (getBlock.equals("深证股票")){
                index_4=i;
            }
        }
        left_listbean.add(listbean.get(index_1));
        left_listbean.add(listbean.get(index_2));
        left_listbean.add(listbean.get(index_3));
        left_listbean.add(listbean.get(index_4));
        new_listbean.addAll(left_listbean);
        listbean.addAll(left_listbean);
        listbean.removeAll(left_listbean);
        new_listbean.addAll(listbean);


        for (int i = 0; i < new_listbean.size(); i++) {
            if (i == 0) {
                new_listbean.get(i).setSeleted(true);
                defaultName = new_listbean.get(i).getBlock();
            }
            StockBlockBean.StockListbean current = new_listbean.get(i);
            String m_Code = current.getMark() + current.getCode();
            current.setM_code(m_Code);
            m_Code_List.add(current);

            if (current.getBlock().equals(defaultName)) { //模块
                contractList.add(current);
            }


        }
        mlistDataAdapter.setData(contractList);
        mlistDataAdapter.notifyDataSetChanged();


        StringBuffer string = new StringBuffer();
        for (int i = 0; i < contractList.size(); i++) {
            string.append(contractList.get(i).getM_code()).append(",");
        }
        String labelStr = string.toString();
        String StkLabels = labelStr.substring(0, labelStr.length() - 1);

        removeReplace(new_listbean);
        mJobDataAdapter.setData(new_listbean);
        mJobDataAdapter.notifyDataSetChanged();
        getMultiMarketInfo(StkLabels);

        }
    }



    /*
    *
    * 没有模块tab
    * */











    /*
     *
     * 多个股票行情报价
     * */
    @Override
    public void refreshMultiMarketInfo(List<MultiMarketBean> Info) {
        if (Info != null && Info.size() > 0) {
            for (int i = 0; i < Info.size(); i++) {
                contractList.get(i).setNew_Privce(Info.get(i).getClose() + "");
//(现价-上一个交易日收盘价)/上一个交易日收盘价*100%
                try {
                    String zhangfu = df.format((Info.get(i).getClose() - Info.get(i).getPrev_close()) / Info.get(i).getPrev_close() * 100);
                    contractList.get(i).setZhang_die_fu(zhangfu + "%");
                    double zhangdiezhi= Info.get(i).getClose() - Info.get(i).getPrev_close();
                    String onepoint = df.format(zhangdiezhi);
                    if (zhangdiezhi > 0) {
                        contractList.get(i).setZhangdie_zhi("+" + onepoint + "%");
                    } else if (zhangdiezhi <= 0) {
                        contractList.get(i).setZhangdie_zhi(onepoint + "%");
                    }
                    contractList.get(i).setChicang_liang(Info.get(i).getChicang()+"");
                    contractList.get(i).setChengjiao_num(Info.get(i).getVol() + "");
                } catch (Exception throwable) {

                }


            }
        }

//        mlistDataAdapter.setData(contractList);
        mlistDataAdapter.notifyDataSetChanged();


        Log.i("========多个股票=========", Info.get(0).getHigh() + "=======");
    }

    public static String getStringFromAssert(Context context, String fileName) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            return new String(buffer, 0, buffer.length, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void refreshContractInfo(Object Info) {
        Log.i("=================", "refreshContractInfo");
        Info = Info.toString().replace(" ", "");
        List<ContractBean> newConsulist = new Gson().fromJson(Info.toString(), new TypeToken<List<ContractBean>>() {
        }.getType());//把JSON格式的字符串转为List
//        Toast.makeText(_mActivity, "" + "长度"+newConsulist.size(), Toast.LENGTH_LONG).show();
//        mlistDataAdapter.setData(newConsulist);
//        mlistDataAdapter.notifyDataSetChanged();
//        Log.i("=heyue=ssssss", newConsulist.size()+"");
    }

    @Override
    public void refreshmostPriceInfo(Object Info) {

    }

    @Override
    public void refreshZhangdiefuInfo(Object Info) {

    }

    @Override
    public void refreshVolumeInfo(Object Info) {

    }

    @Override
    public void showToast(String content) {

    }


    private Disposable mDisposable;

    public void startTime() {
        /*
         *
         * */
        mDisposable = Observable.interval(15, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        // 逻辑代码
                        getContractInfo();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }


    /**
     * 关闭定时器
     */
    public void closeTimer() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    public void removeReplace(List<StockBlockBean.StockListbean> listbean) {
        for (int i = 0; i < listbean.size(); i++) {
            int con = 1;
            for (int j = listbean.size() - 1; j > i; j--) {
                if (listbean.get(j).getBlock().equals(listbean.get(i).getBlock())) {
                    listbean.remove(j);
                    con++;
                }
            }
//        listbean.get(i).setGoods_number(con);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }



/*
    public  void  updatezhangDie_chicang(){
        String zhangfu = df.format((Info.get(i).getClose() - Info.get(i).getPrev_close()) / Info.get(i).getPrev_close() * 100);
        contractList.get(i).setZhang_die_fu(zhangfu + "%");
        double zhangdiezhi= Info.get(i).getClose() - Info.get(i).getPrev_close();
        String onepoint = df.format(zhangdiezhi);
        if (zhangdiezhi > 0) {
            contractList.get(i).setZhangdie_zhi("+" + onepoint + "%");
        } else if (zhangdiezhi <= 0) {
            contractList.get(i).setZhangdie_zhi(onepoint + "%");
        }
        contractList.get(i).setChicang_liang(Info.get(i).getChicang()+"");
        contractList.get(i).setChengjiao_num(Info.get(i).getVol() + "");
    }*/







    String  change_zhangzhi_zhangdiefu="涨跌幅";
    String  chegnjiaoliang_chicangliang="成交量";

    @OnClick({R.id.zhangfu, R.id.chengjiao,R.id.search_bg,R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.search_bg:
                Bundle pinglunbannner = new Bundle();
                pinglunbannner.putInt("type", 18);
                PlatformForFragmentActivity.newInstance(_mActivity, pinglunbannner);
                break;
            case R.id.zhangfu:
               if (change_zhangzhi_zhangdiefu.equals("涨跌幅")){
                   zhangfu.setText("涨跌值");
                   change_zhangzhi_zhangdiefu="涨跌值";
               }else {
                   zhangfu.setText("涨跌幅");
                   change_zhangzhi_zhangdiefu="涨跌幅";
               }
                mlistDataAdapter.notifyDataSetChanged();
                break;
            case R.id.chengjiao:
                if (chegnjiaoliang_chicangliang.equals("成交量")){
                    chengjiao.setText("持仓量");
                    chegnjiaoliang_chicangliang="持仓量";
                }else {
                    chengjiao.setText("成交量");
                    chegnjiaoliang_chicangliang="成交量";
                }
                mlistDataAdapter.notifyDataSetChanged();
                break;
        }
    }
}
