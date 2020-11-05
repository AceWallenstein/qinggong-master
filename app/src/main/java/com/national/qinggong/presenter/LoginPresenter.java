package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.contract.PlatformLoginContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
* 登录
* */
public class LoginPresenter  extends BasePresenter<PlatformLoginContract.View> implements PlatformLoginContract.Presenter{
    public LoginPresenter(Activity activity, PlatformLoginContract.View view) {
        super(activity, view);
    }

    @Override
    public void getUserInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_gp)
                .getUserinfo(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.stockLoginFinish();
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshLogin(userInfo);

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
/*
* 用户登录
*
* */
    @Override
    public void getUserLogin(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_PORT_8802)
                .getUserLogin(map)
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
                                mView.refreshuserLogin(userInfo);

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
