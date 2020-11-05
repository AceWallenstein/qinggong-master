package com.national.qinggong.fragement;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class FragmentSearch extends BaseFragment {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.et_search)
    EditText et_search;
    private String searchCondition;

    public static FragmentSearch newInstance() {
        Bundle args = new Bundle();
        FragmentSearch fragment = new FragmentSearch();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void initdata() {
        initSearchEdittext();
    }
    //编辑框变更监听
    private void initSearchEdittext() {
     /*   et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {

                } else {
                    searchCondition = s.toString().replaceAll(" ", "");
                    Log.i("========搜索=========", searchCondition+ "=======");
//                   tv_select_job.setText(searchCondition);
                    Bundle searchBundle = new Bundle();
                    searchBundle.putInt("type", 14);
                    searchBundle.putString("search", searchCondition);
                    PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);
                }
            }
        });*/
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    hideSoftInput();
                    //mJobDataAdapter.getData().clear();
                    //mJobDataAdapter.notifyDataSetChanged();
                    if(TextUtils.isEmpty(et_search.getText().toString().trim())){
                        Toast.makeText(_mActivity,"请输入搜索内容",Toast.LENGTH_SHORT).show();
                    }else{
                        String searchresult = et_search.getText().toString().trim();
                        searchCondition = searchresult.replaceAll(" ", "");
                        Bundle searchBundle = new Bundle();
                        searchBundle.putInt("type", 14);
                        searchBundle.putString("search", searchCondition);
                        PlatformForFragmentActivity.newInstance(_mActivity, searchBundle);

                    }
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_input;
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
