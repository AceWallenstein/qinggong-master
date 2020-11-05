package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.MyApplication;
import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.contract.PersonDataContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class PersonDataPresenter extends BasePresenter<PersonDataContract.View> implements PersonDataContract.Presenter {

    public PersonDataPresenter(Activity activity, PersonDataContract.View view) {
        super(activity, view);
    }
    public String gainStockUUid() {
        String user_uuid = CacheHelper.getAlias(MyApplication.getInstance(), "user_uuid");
        return user_uuid;
    }
    @Override
    public void getUserInfo(Map<String, String> map) {
        String user_uuid = gainStockUUid();
        if (StringUtils.isEmpty(user_uuid))return;
        addDisposable(RetrofitClient.getApiService(API.APP_PORT_8802,user_uuid)
                .getUserAlertPass(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
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
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshUserPass(userInfo);

                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(mView!=null) {
                            mView.showToast(ExceptionHandler.handleException(throwable));
                        }
                    }
                })
        );
    }
}
