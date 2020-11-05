package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.SearchBean;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.contract.SearchPageContract;
import com.national.qinggong.contract.SearchShopListContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class SearchShopListPresenter extends BasePresenter<SearchShopListContract.View> implements SearchShopListContract.Presenter {
    public SearchShopListPresenter(Activity activity, SearchShopListContract.View view) {
        super(activity, view);
    }

    @Override
    public void getSearchList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .searchShopList(map)
                        .compose(RequestManager.<SearchShopListBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                        mView.stockFinish();
                            }
                        })
                        .subscribe(new Consumer<SearchShopListBean>() {
                            @Override
                            public void accept(SearchShopListBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshSearchPage(userInfo);
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
    public void getSearchCategory(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .searchCategoryShopList(map)
                .compose(RequestManager.<SearchCategoryBean>applyIoSchedulers())
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
                .subscribe(new Consumer<SearchCategoryBean>() {
                    @Override
                    public void accept(SearchCategoryBean userInfo) throws Exception {
                        if (mView != null) {
                            if (userInfo != null) {
                                mView.refreshCategory(userInfo);
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
