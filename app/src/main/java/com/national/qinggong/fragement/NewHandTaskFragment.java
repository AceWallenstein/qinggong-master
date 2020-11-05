package com.national.qinggong.fragement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.DoTaskBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.TaskBean;
import com.national.qinggong.contract.NewHandTaskContract;
import com.national.qinggong.presenter.NewHandTaskPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.ui.activity.WebviewActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.Constant;
import com.national.qinggong.util.Res;
import com.national.qinggong.util.StringUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/*
 * 新手任务
 * */
public class NewHandTaskFragment extends BaseFragment implements NewHandTaskContract.View {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.finish_num)
    TextView finish_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    JoneBaseAdapter<TaskBean.DataBean.TasksBean> joneBaseAdapter;


    public static NewHandTaskFragment newInstance() {
        Bundle args = new Bundle();
        NewHandTaskFragment fragment = new NewHandTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initdata() {
        tvTitle.setText("新手任务");
        initRecycleView();
//        getNewHandTask();
    }

    @Override
    public void onResume() {
        super.onResume();
        getNewHandTask();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        joneBaseAdapter = new JoneBaseAdapter<TaskBean.DataBean.TasksBean>(recyclerView, R.layout.item_newhand_task) {
            @Override
            public void fillItemData(BGAViewHolderHelper helper, int position, TaskBean.DataBean.TasksBean model) {

                helper.setText(R.id.title_task, model.getTitle());
                helper.setText(R.id.subtitle_task, model.getSubtitle());
                if (model.getStatus() == 1) {
                    helper.setText(R.id.go_finish, "已完成");
                    helper.setTextColor(R.id.go_finish, Color.parseColor("#888888"));
                } else {
                    helper.setText(R.id.go_finish, "立即完成");
                    helper.setTextColor(R.id.go_finish, Color.parseColor("#CEAF7F"));
                }
                if (model.getStatus()==0){
                    helper.setItemChildClickListener(R.id.task_content);
                    helper.setItemChildClickListener(R.id.go_finish);
                }







             /*   if (model.getTitle().contains("认证")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.home_authon));
                } else if (model.getTitle().contains("开户")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.home_open_gift));
                } else*/


                if (model.getTitle().contains("民丰")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.home_about));
                } else if (model.getTitle().contains("新手")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.new_activity));
                } else if (model.getTitle().contains("行情")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.hangqing_task));
                } else if (model.getTitle().contains("观看")) {
                    helper.setImageDrawable(R.id.new_now_title, Res.getDrawable(R.mipmap.kecheng_task));
                }

            }
        };
        recyclerView.setAdapter(joneBaseAdapter);

//        joneBaseAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
//            @Override
//            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//                handleItemClick(position);
//            }
//        });

        joneBaseAdapter.setOnItemChildClickListener(new BGAOnItemChildClickListener() {
            @Override
            public void onItemChildClick(ViewGroup parent, View childView, int position) {
               switch (childView.getId()){
                    case R.id.task_content:
                    case R.id.go_finish:
                        handleItemClick(position);
                        break;
                }
            }
        });
    }

    public void intoADNewHandDetail()
    {
        String getType = CacheHelper.getAlias(_mActivity, "getType");
        String getParam = CacheHelper.getAlias(_mActivity, "getParam");
        chooseIntoWebview(getType, getParam);
    }
    public void chooseIntoWebview(String getType, String getParam) {
        switch (getType) {
            case "0"://0:跳到网页
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "网页");
                }
                break;
            case "1":// 1:跳转到文章
                if (!StringUtils.isEmpty(getParam)) {
                    WebviewActivity.newIntance(_mActivity, getParam, "文章");
                }
                break;
            case "2":// 2:跳到课程
                if (!StringUtils.isEmpty(getParam)) {
                    if (!StringUtils.isEmpty(getParam)) {
                        WebviewActivity.newIntance(_mActivity, getParam, "课堂详情接口");
                    }
                }
                break;
        }
    }



  public  void   intoGuPiao(){
      String getMarket = CacheHelper.getAlias(_mActivity, "getMarket");
      String getCode = CacheHelper.getAlias(_mActivity, "getCode");
      if (!StringUtils.isEmpty(getMarket) & !StringUtils.isEmpty(getCode)) {
          Bundle Bundle = new Bundle();
          Bundle.putInt("type", 7);
          Bundle.putString(Constant.mark, getMarket);
          Bundle.putString(Constant.code, getCode);
          PlatformForFragmentActivity.newInstance(_mActivity, Bundle);
      }
    }

  public  void   lookCaishang(){
      String caishang_id = CacheHelper.getAlias(_mActivity, "caishang_id");
      if (!StringUtils.isEmpty(caishang_id)) {
          WebviewActivity.newIntance(_mActivity, caishang_id, "课堂详情接口");
      }
    }




    private void handleItemClick(int position) {
        TaskBean.DataBean.TasksBean model = joneBaseAdapter.getData().get(position);
        int taskID = joneBaseAdapter.getData().get(position).getId();
        if (taskID == 1) {
            intoADNewHandDetail();
        } else if (taskID == 2) {
            intoGuPiao();
        } else if (taskID == 3) {
            Bundle Bundle_about = new Bundle();
            Bundle_about.putInt("type", 9);
            PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
        }
        if (taskID == 4) {
            lookCaishang();
        }
        doTask(taskID+"");



        /*if (model.getTitle().contains("认证")) {
            Bundle weekSalaryBundle = new Bundle();
            weekSalaryBundle.putInt("type", 4);
            PlatformForFragmentActivity.newInstance(_mActivity, weekSalaryBundle);
        } else if (model.getTitle().contains("开户")) {
            Bundle weekSalaryBundle = new Bundle();
            weekSalaryBundle.putInt("type", 4);
            PlatformForFragmentActivity.newInstance(_mActivity, weekSalaryBundle);
        } else if (model.getTitle().contains("民丰")) {
            Bundle Bundle_about = new Bundle();
            Bundle_about.putInt("type", 9);
            PlatformForFragmentActivity.newInstance(_mActivity, Bundle_about);
        } else if (model.getTitle().contains("注册")) {

        } else if (model.getTitle().contains("新手")) {

        }*/



    }

    @Override
    protected NewHandTaskPresenter getPresenter() {
        return new NewHandTaskPresenter(_mActivity, NewHandTaskFragment.this);
    }

    private void getNewHandTask() {
        String getcheck = (String) CacheHelper.get(_mActivity, "username", "");
        Map<String, String> map = new HashMap<>();
        map.put("username", getcheck);
        getPresenter().newHandInfo(map);
    }


//task：任务ID    username：用户名
    private void doTask(String taskid) {
        String username = (String) CacheHelper.get(_mActivity, "username", "");
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("task", taskid);
        getPresenter().doTask(map);
    }
    @OnClick({R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
           /* case R.id.pass_alert:
                showAlertPassDialog();
                break;*/
        }
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_green_hand_task;
    }

    @Override
    public void refreshTask(TaskBean userInfo) {
        if (userInfo != null) {
            if (userInfo.getCode() == 1) {
                if (userInfo.getData() != null) {
                    TaskBean.DataBean getData = userInfo.getData();
                    if (getData.getTasks() != null & getData.getTasks().size() > 0) {
                        List<TaskBean.DataBean.TasksBean> getTasksList = getData.getTasks();
                        int count = 0;
                        for (int i = 0; i <getTasksList.size() ; i++) {
                            int getStatus = getTasksList.get(i).getStatus();
                            if (getStatus==1){
                                count++;
                            }
                        }
                        finish_num.setText("已完成"+count+"/"+getTasksList.size()+"个任务");


                        if (joneBaseAdapter == null) {
                            return;
                        }
                        joneBaseAdapter.getData().clear();
                        joneBaseAdapter.getData().addAll(getTasksList);
                    } else {
                        joneBaseAdapter.getData().clear();
                    }
                    joneBaseAdapter.notifyDataSetChanged();
                }
            }


        }
    }

    @Override
    public void doTask(DoTaskBean userInfo) {
     /*   if (userInfo!=null){

        }*/
    }

    @Override
    public void showToast(String content) {

    }
}
