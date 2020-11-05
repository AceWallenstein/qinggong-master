package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.contract.HomePageContract;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class IntegralShopingPresenter extends BasePresenter<IntegralShopingContract.View> implements IntegralShopingContract.Presenter {
    public IntegralShopingPresenter(Activity activity, IntegralShopingContract.View view) {
        super(activity, view);
    }

    @Override
    public void IntegralInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .integralShoping(map)
                        .compose(RequestManager.<Object>applyIoSchedulers())
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
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.IntegralTask(userInfo);

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
