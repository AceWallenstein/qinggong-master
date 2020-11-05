package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.contract.PlatformArticleContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
/*
* 文章
*
* */
public class PlatformArticlePresenter extends BasePresenter<PlatformArticleContract.View> implements PlatformArticleContract.Presenter {
    public PlatformArticlePresenter(Activity activity, PlatformArticleContract.View view) {
        super(activity, view);
    }

/*
* 消息文章请求
*
* ?page=1&limit=20
* */
    @Override
    public void getArticleInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getUserInfo(map)
                .compose(RequestManager.<ClassTypeBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ClassTypeBean>() {
                    @Override
                    public void accept(ClassTypeBean userInfo) throws Exception {
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

    @Override
    public void getSearchInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getSearch(map)
                .compose(RequestManager.<ClassTypeBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ClassTypeBean>() {
                    @Override
                    public void accept(ClassTypeBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreShsearchInfo(userInfo);

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
