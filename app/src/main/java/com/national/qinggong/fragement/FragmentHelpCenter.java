package com.national.qinggong.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.HelpCenterBean;
import com.national.qinggong.bean.HomeBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.contract.HelpCenterContract;
import com.national.qinggong.presenter.HelPCenterHelpPresebnter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class FragmentHelpCenter extends BaseFragment implements HelpCenterContract.View {
    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.help_recyclerView)
    RecyclerView help_recyclerView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
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
    Unbinder unbinder;
    private JoneBaseAdapter<HelpCenterBean.DataBean.ListBean> mJobDataAdapter;

    public static FragmentHelpCenter newInstance() {
        Bundle args = new Bundle();
        FragmentHelpCenter fragment = new FragmentHelpCenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        tv_title.setText("帮助中心");
        inithelp();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        gethelpnfo();
    }

    @Override
    protected HelPCenterHelpPresebnter getPresenter() {
        return new HelPCenterHelpPresebnter(_mActivity, FragmentHelpCenter.this);
    }

    public void inithelp() {
        mJobDataAdapter = new JoneBaseAdapter<HelpCenterBean.DataBean.ListBean>(help_recyclerView, R.layout.item_help_center) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, HelpCenterBean.DataBean.ListBean model) {
//                2020-05-13 18:48:15


                helper.setText(R.id.title, model.getQuestion() + "");
                helper.setText(R.id.content, model.getAnswer() + "");
                int currentposition = position + 1;
                helper.setText(R.id.tag, "Q" + currentposition + "");
//                if (model.getPoster()!=null){
//                    GlideUtils.loadImageByUrl(model.getPoster(),(ImageView) helper.getView(R.id.image));
//                }
            }
        };
        LinearLayoutManager mJobLinearlayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        help_recyclerView.setLayoutManager(mJobLinearlayoutManager);
        help_recyclerView.setAdapter(mJobDataAdapter);
        List<HomeBean> datas = new ArrayList<>();

        mJobDataAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

//                ActivityArticleBean.DataBean.ListBean currentbean = mJobDataAdapter.getItem(position);
//                WebviewActivity.newIntance(_mActivity,currentbean,"活动");

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_help_center;
    }

    private void gethelpnfo() {
        Map<String, String> map = new HashMap<>();
//        map.put("username", username);
//        map.put("page", page + "");
//        map.put("limit", limit);
        getPresenter().helpInfo(map);
    }

    @Override
    public void refreshTask(HelpCenterBean userInfo) {

        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                HelpCenterBean.DataBean getData = userInfo.getData();
                if (getData.getList() != null & getData.getList().size() > 0) {
                    mJobDataAdapter.setData(getData.getList());
                    mJobDataAdapter.notifyDataSetChanged();
                }
            }
        }


    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    public void showToast(String content) {

    }


    @OnClick({R.id.rl_back, R.id.lin_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.lin_content:
                break;
        }
    }
}
