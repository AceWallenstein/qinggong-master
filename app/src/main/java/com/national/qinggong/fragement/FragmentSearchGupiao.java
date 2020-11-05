package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.SearchBeanCode;
import com.national.qinggong.bean.StockBlockBean;
import com.national.qinggong.contract.AnalyListContract;
import com.national.qinggong.presenter.AnalyPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.Constant;
import com.national.qinggong.util.StringUtils;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

//fragment_gupiao_search.xml
public class FragmentSearchGupiao extends BaseFragment implements AnalyListContract.View, TextWatcher {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.search_bg)
    LinearLayout searchBg;
    @BindView(R.id.search_name)
    TextView searchName;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;//
    private List<StockBlockBean.StockListbean> oldlistbean;
    private JoneBaseAdapter<String> mVagueAdapter;
    private List<String> mInputSelectData = new ArrayList<>();


    public static FragmentSearchGupiao newInstance() {
        Bundle args = new Bundle();
        FragmentSearchGupiao fragment = new FragmentSearchGupiao();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        initVagueRecycleView();
        etSearch.addTextChangedListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getBlockStyleInfo();
    }

    /*
     * 分类
     * */
    private void getBlockStyleInfo() {
        Map<String, String> map = new HashMap<>();
        getPresenter().getBlockInfo(map);
    }

    @Override
    protected AnalyPresenter getPresenter() {
        return new AnalyPresenter(_mActivity, FragmentSearchGupiao.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gupiao_search;
    }
    private void initVagueRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mVagueAdapter = new JoneBaseAdapter<String>(recyclerView, R.layout.item_search_vague_textview) {

            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, String model) {
                helper.setText(R.id.tv_search_vague, Html.fromHtml(model));
            }
        };
        mVagueAdapter.setData(mInputSelectData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mVagueAdapter);
        mVagueAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                hideSoftInput();
//                mInputSelectData.clear();
//                String currentdata = mInputSelectData.get(position);
                String currentdata =Html.fromHtml(mInputSelectData.get(position))+"";

                String[] name = currentdata.split(" ");
                String gupiaoname = name[0];
                String gupiao_MARK = name[1];
                String gupiao_code = name[2];
                /*进入k线*/
                Log.i("========666688=========", gupiao_MARK + "---------------------------");
                Log.i("========666688=========", gupiao_code + "---------------------------");
                Bundle Bundle = new Bundle();
                Bundle.putInt("type", 7);
                Bundle.putString(Constant.mark, gupiao_MARK);
                Bundle.putString(Constant.code, gupiao_code);
                PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
//                _mActivity.onBackPressed();
            }
        });
    }

    @OnClick({R.id.rl_back, R.id.search_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.search_name:
                String mark_code = etSearch.getText().toString();
                if (StringUtils.isEmpty(mark_code)) {
                    ToastUtilMsg.showToast(_mActivity, "搜索内容为空");
                    return;
                }
                yiBanSousuo(mark_code);
             /*   int flag = 0;
               for (int i = 0; i <oldlistbean.size() ; i++) {
                    String getName = oldlistbean.get(i).getBlock();
                    String getCode = oldlistbean.get(i).getCode();
                    if (getName.equals(mark_code)||getCode.equals(mark_code)){
                        flag=1;
                        Log.i("========66663=========", getName + "---------------------------");
                        Log.i("========66662=========", getCode + "---------------------------");
                        start(ClassFyFragemrnt.newInstance(getName));
                      break;
                    }else {
                        Log.i("========666688=========", getCode + "---------------------------");
                    }
                }
                if (flag!=1){
                    ToastUtilMsg.showToast(_mActivity, "没有搜索到相关股票信息");
                }*/
                break;
        }
    }



    public  void  yiBanSousuo(String inputname){
        if (oldlistbean==null)return;
        List<SearchBeanCode> companyName=new ArrayList<>();
        for (int i = 0; i <oldlistbean.size() ; i++) {
            SearchBeanCode searchBeanCode=new SearchBeanCode();
            searchBeanCode.setName(oldlistbean.get(i).getName()+"");
            searchBeanCode.setCode(oldlistbean.get(i).getCode());
            searchBeanCode.setMark(oldlistbean.get(i).getMark());
            companyName.add(searchBeanCode);
        }
        List<String> returnCurrent= isContanit(inputname,true,companyName);
        mInputSelectData=returnCurrent;
//        IDSelectData= IDlist;
        mVagueAdapter.setData(mInputSelectData);
      /*  for (int i = 0; i <returnCurrent.size() ; i++) {
            Log.i("========66667=========", returnCurrent.get(i).toString() + "---------------------------");
        }*/

    }




    //模糊查询结果
    private List<String> isContanit(String keywords, boolean changeColor,List<SearchBeanCode> allCompany) {
        List<String> mlist = new ArrayList<>();
        for (SearchBeanCode s : allCompany) {
            String getmame = s.getName();
            String getCode = s.getCode();
            String getMark = s.getMark();
            if (getmame.contains(keywords)) {
                String replaceAll = getmame.replaceAll(keywords, "<font color='#FF0000'>" + keywords + "</font>")+" "+getMark+" "+getCode;
                mlist.add(replaceAll);
            }else if (getCode.contains(keywords)){
                String replaceAll = getmame+" "+getMark+" "+getCode.replaceAll(keywords, "<font color='#FF0000'>" + keywords + "</font>");
                mlist.add(replaceAll);
            }
        }
        return mlist;
    }





    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    /*
     * 4892
     * */
    @Override
    public void refreshBlockInfo(List<StockBlockBean.StockListbean> Info) {
        oldlistbean = (List<StockBlockBean.StockListbean>) Info;
        Log.i("========6666=========", oldlistbean.size() + "---------------------------");


    }

    @Override
    public void refreshMultiMarketInfo(List<MultiMarketBean> Info) {

    }

    @Override
    public void refreshContractInfo(Object Info) {

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String temp = s.toString();
        if (StringUtils.isEmpty(temp)){
            if (mVagueAdapter!=null){
//                Log.i("size====",mVagueAdapter.getData().size()+"");
                if (mVagueAdapter.getData().size()>0) {
                    mVagueAdapter.getData().clear();
                    mVagueAdapter.notifyDataSetChanged();
                }
            }
            return;
        }
        if (temp.equals(" ")) {
            s.delete(0, 1);
        }
        if (temp.equals("\n")) {
            s.delete(0, 1);
        }
        Log.i("size====",s.toString().length()+"");

        yiBanSousuo(s.toString());
    }
}
