package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.contract.ConsultListContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
 * 询价列表
 * */
public class ConsultListPresenter extends BasePresenter<ConsultListContract.View> implements ConsultListContract.Presenter {
    public ConsultListPresenter(Activity activity, ConsultListContract.View view) {
        super(activity, view);
    }

    @Override
    public void getConsultList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .consultList(map)
                        .compose(RequestManager.<Object>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                mView.Disposable();
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockLoginFinish();
                            }
                        })
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshConsultPage(userInfo);
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
    public void getSendConsult(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .sendConsult(map)
                        .compose(RequestManager.<DeleteCarBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<DeleteCarBean>() {
                            @Override
                            public void accept(DeleteCarBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.sendConsult(userInfo);
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
