package com.national.qinggong.presenter;

import android.app.Activity;


import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.contract.FindPassContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class FindPassPresenter extends BasePresenter<FindPassContract.View> implements FindPassContract.Presenter {
    public FindPassPresenter(Activity activity, FindPassContract.View view) {
        super(activity, view);
    }

    @Override
    public void getsendSms(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .sendSms(map)
                        .compose(RequestManager.<BaseStrBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockLoginFinish();
                            }
                        })
                        .subscribe(new Consumer<BaseStrBean>() {
                            @Override
                            public void accept(BaseStrBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshsendSms(userInfo);

                                    }
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (mView != null) {
                                    mView.showToast(ExceptionHandler.handleException(throwable));
                                }
                            }
                        })
        );
    }
    @Override
    public void finPassSubmit(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .findPass(map)
                        .compose(RequestManager.<BaseBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockLoginFinish();
                            }
                        })
                        .subscribe(new Consumer<BaseBean>() {
                            @Override
                            public void accept(BaseBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshfinPassSubmit(userInfo);

                                    }
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                if (mView != null) {
                                    mView.showToast(ExceptionHandler.handleException(throwable));
                                }
                            }
                        })
        );
    }
}
