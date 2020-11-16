package com.national.qinggong.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseActivity;
import com.national.qinggong.bean.PersonalDataBean;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * 创建时间：2020/11/16
 * 编写人：czy_yangxudong
 * 功能描述：个人资料
 */
public class PersonalDataActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.user_head)
    ImageView user_head;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.email_name)
    TextView email_name;
    @BindView(R.id.phone_info)
    TextView phone_info;
    @BindView(R.id.contry_info)
    TextView contry_info;

    public static void open(Context context, String user_id){
        Intent intent=new Intent(context, PersonalDataActivity.class);
        intent.putExtra("user_id",user_id);
        context.startActivity(intent);
    }

    private String user_id="";

    @Override
    protected void initdata() {
        user_id=getIntent().getStringExtra("user_id");
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPersonalData();
    }

    @Override
    protected int initResourceLayout() {
        return R.layout.activity_personal_data;
    }



    private void getPersonalData() {
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("user_id",user_id);

        RetrofitClient.getApiService(API.APP_QING_GONG)
                .getPersonalData(map)
                .compose(RequestManager.<PersonalDataBean>applyIoSchedulers())
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
                .subscribe(new Consumer<PersonalDataBean>() {
                               @Override
                               public void accept(PersonalDataBean personalDataBean) throws Exception {
                                   if (personalDataBean.code == 1) {
                                       if (!StringUtils.isEmpty(personalDataBean.data.user.avatarUrl)) {
                                           Glide.with(PersonalDataActivity.this).load(personalDataBean.data.user.avatarUrl).into((ImageView) user_head);
                                       }

                                       user_name.setText(personalDataBean.data.user.nickName);
                                       email_name.setText(personalDataBean.data.user.account);
                                       phone_info.setText(personalDataBean.data.user.phone);
                                       contry_info.setText(personalDataBean.data.user.country);
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
