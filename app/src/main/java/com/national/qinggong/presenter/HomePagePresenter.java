package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.contract.HomePageContract;
import com.national.qinggong.contract.PlatformActivityArticleContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

//HomePageContract
public class HomePagePresenter  extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter {
    public HomePagePresenter(Activity activity, HomePageContract.View view) {
        super(activity, view);
    }

    @Override
    public void homeInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .homePage(map)
                .compose(RequestManager.<HomePageBean>applyIoSchedulers())
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
                .subscribe(new Consumer<HomePageBean>() {
                    @Override
                    public void accept(HomePageBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.HomeTask(userInfo);

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
    public void submit_addcar(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .addCar(map)
                        .compose(RequestManager.<DeleteCarBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockFinish();
                            }
                        })
                        .subscribe(new Consumer<DeleteCarBean>() {
                            @Override
                            public void accept(DeleteCarBean userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.add_addCar(userInfo);
                                        ToastUtilMsg.showToast(getActivity(),"Done");
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
