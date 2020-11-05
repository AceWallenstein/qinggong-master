package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.AboutOursBean;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.contract.FragmentAboutOursContract;
import com.national.qinggong.contract.NewsListContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class FragmentAboutOursPresenter extends BasePresenter<FragmentAboutOursContract.View> implements FragmentAboutOursContract.Presenter {
    public FragmentAboutOursPresenter(Activity activity, FragmentAboutOursContract.View view) {
        super(activity, view);
    }

    @Override
    public void getAboutOurs(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .getAboutOurs(map)
                        .compose(RequestManager.<AboutOursBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<AboutOursBean>() {
                            @Override
                            public void accept(AboutOursBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshNews(userInfo);
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
