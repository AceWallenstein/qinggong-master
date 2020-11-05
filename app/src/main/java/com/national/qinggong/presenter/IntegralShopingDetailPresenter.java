package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.IntegralDetailBean;
import com.national.qinggong.bean.SendIntegralPointsBean;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.contract.IntegralShopingDetailContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class IntegralShopingDetailPresenter extends BasePresenter<IntegralShopingDetailContract.View> implements IntegralShopingDetailContract.Presenter {
    public IntegralShopingDetailPresenter(Activity activity, IntegralShopingDetailContract.View view) {
        super(activity, view);
    }

    @Override
    public void IntegralDetailInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .integralShopingDetail(map)
                .compose(RequestManager.<IntegralDetailBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.refreshPostfinally();
                    }
                })
                .subscribe(new Consumer<IntegralDetailBean>() {
                    @Override
                    public void accept(IntegralDetailBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.IntegralDetailTask(userInfo);

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

    @Override
    public void sendIntegralDetailInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .sendIntegralPoints(map)
                        .compose(RequestManager.<SendIntegralPointsBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                /*记得关掉*/
//                        mView.refreshPostfinally();
                            }
                        })
                        .subscribe(new Consumer<SendIntegralPointsBean>() {
                            @Override
                            public void accept(SendIntegralPointsBean userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.sendIntegralPointsTask(userInfo);

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

    @Override
    public void submitRightInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .right_integral(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
//                        mView.refreshPostfinally();
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.RightDuihuan(userInfo);

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
