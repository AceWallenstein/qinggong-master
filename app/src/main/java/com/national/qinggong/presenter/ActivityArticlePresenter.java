package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.contract.PlatformActivityArticleContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
* 活动文章
* */
public class ActivityArticlePresenter extends BasePresenter<PlatformActivityArticleContract.View> implements PlatformActivityArticleContract.Presenter {
    public ActivityArticlePresenter(Activity activity, PlatformActivityArticleContract.View view) {
        super(activity, view);
    }

    @Override
    public void getArticleInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getActivityArticleInfo(map)
                .compose(RequestManager.<ActivityArticleBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ActivityArticleBean>() {
                    @Override
                    public void accept(ActivityArticleBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshArticleInfo(userInfo);

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
