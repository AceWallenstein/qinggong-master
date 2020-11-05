package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.ActivityDetailBean;
import com.national.qinggong.bean.ArticleDetailBean;
import com.national.qinggong.bean.ClassRoomDetailBean;
import com.national.qinggong.bean.NewsDetailBean;
import com.national.qinggong.contract.PlatformWebDeatilContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*

* */
public class WebDetailPresenter extends BasePresenter<PlatformWebDeatilContract.View> implements  PlatformWebDeatilContract.Presenter {

    public WebDetailPresenter(Activity activity, PlatformWebDeatilContract.View view) {
        super(activity, view);
    }

    @Override
    public void getArticleInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getArticleDetailInfo(map)
                .compose(RequestManager.<ArticleDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ArticleDetailBean>() {
                    @Override
                    public void accept(ArticleDetailBean userInfo) throws Exception {
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
    public void getClassRoomDetailInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getClassDetailInfo(map)
                .compose(RequestManager.<ClassRoomDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ClassRoomDetailBean>() {
                    @Override
                    public void accept(ClassRoomDetailBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshClassInfo(userInfo);

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
    public void getActivityDetailInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getActivityDetailInfo(map)
                .compose(RequestManager.<ActivityDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ActivityDetailBean>() {
                    @Override
                    public void accept(ActivityDetailBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.getActivityInfo(userInfo);

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
    public void getNewsDetailInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .newsDetailList(map)
                .compose(RequestManager.<NewsDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<NewsDetailBean>() {
                    @Override
                    public void accept(NewsDetailBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.getNewDetailInfo(userInfo);

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
