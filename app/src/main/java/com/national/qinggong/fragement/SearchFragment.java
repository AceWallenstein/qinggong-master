package com.national.qinggong.fragement;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.customview.KnowledgePagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.knowledge_magicindicator)
    MagicIndicator knowledgeMagicindicator;
    @BindView(R.id.knowledge_viewpager)
    ViewPager knowledgeViewpager;
    private String search;

    public static SearchFragment newInstance(String search) {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        args.putString("search", search);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        search = getArguments().getString("search");
    }
    private static final String[] CHANNELS = new String[]{"文章", "课程"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    private KnowledgePagerAdapter mExamplePagerAdapter = null;
    private List<Fragment> fragmentList = new ArrayList<>();



    @Override
    protected void initdata() {
        tvTitle.setText("搜索");
        initMagicIndicator2();
        mExamplePagerAdapter = new KnowledgePagerAdapter(getFragmentManager(), fragmentList);
        knowledgeViewpager.setAdapter(mExamplePagerAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
    private void initMagicIndicator2() {


        for (int i = 0 ; i < CHANNELS.length ; i++) {

            Bundle bundle = new Bundle();
            if (i==0){
                ArticleFragment fragment=new ArticleFragment();
                bundle.putString("id", CHANNELS[i]);
                bundle.putString("type", "article");
                bundle.putString("search_content", search);
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
                System.out.println("添加SenRecommendFragment");
            }else
            if (i==1){
                ClassRoomFragment healthFragment=new ClassRoomFragment();
                bundle.putString("id", CHANNELS[i]);
                bundle.putString("type", "course");
                bundle.putString("search_content", search);
                healthFragment.setArguments(bundle);
                fragmentList.add(healthFragment);
                System.out.println("添加SenBabyHealthFragment");
            }else{}


        }

        knowledgeMagicindicator.setBackgroundColor(Color.parseColor("#F4F4F4"));
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return fragmentList == null ? 0 : fragmentList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#4D4D4D"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#de214b"));
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        knowledgeViewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setYOffset(UIUtil.dip2px(context, 3));
                indicator.setColors(Color.parseColor("#FF4081"));
                return indicator;
            }
        });
        knowledgeMagicindicator.setNavigator(commonNavigator);
//        mAdjustMode
        ViewPagerHelper.bind(knowledgeMagicindicator, knowledgeViewpager);
    }

    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;

        }
    }
    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }
}
