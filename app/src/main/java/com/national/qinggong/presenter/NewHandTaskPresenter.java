package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.DoTaskBean;
import com.national.qinggong.bean.TaskBean;
import com.national.qinggong.contract.NewHandTaskContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class NewHandTaskPresenter extends BasePresenter<NewHandTaskContract.View> implements  NewHandTaskContract.Presenter{
    public NewHandTaskPresenter(Activity activity, NewHandTaskContract.View view) {
        super(activity, view);
    }

    @Override
    public void newHandInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getNewHandTask(map)
                .compose(RequestManager.<TaskBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
//                        mView.bannerFinish();
                    }
                })
                .subscribe(new Consumer<TaskBean>() {
                    @Override
                    public void accept(TaskBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshTask(userInfo);

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
    public void doTask(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                        .getDoTask(map)
                        .compose(RequestManager.<DoTaskBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.bannerFinish();
                            }
                        })
                        .subscribe(new Consumer<DoTaskBean>() {
                            @Override
                            public void accept(DoTaskBean userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.doTask(userInfo);

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
