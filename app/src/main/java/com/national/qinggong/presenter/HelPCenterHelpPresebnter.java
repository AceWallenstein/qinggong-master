package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.HelpCenterBean;
import com.national.qinggong.contract.HelpCenterContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class HelPCenterHelpPresebnter extends BasePresenter<HelpCenterContract.View> implements HelpCenterContract.Presenter {
    public HelPCenterHelpPresebnter(Activity activity, HelpCenterContract.View view) {
        super(activity, view);
    }

    @Override
    public void helpInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                        .getHelpnfo(map)
                        .compose(RequestManager.<HelpCenterBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<HelpCenterBean>() {
                            @Override
                            public void accept(HelpCenterBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshTask(userInfo);

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
