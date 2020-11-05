package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.contract.SalemanListContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class SalemanListPresenter extends BasePresenter<SalemanListContract.View> implements SalemanListContract.Presenter {
    public SalemanListPresenter(Activity activity, SalemanListContract.View view) {
        super(activity, view);
    }

    @Override
    public void getNewList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .salemanList(map)
                        .compose(RequestManager.<SaleManBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockLoginFinish();
                                mView.stockFinish();
                            }
                        })
                        .subscribe(new Consumer<SaleManBean>() {
                            @Override
                            public void accept(SaleManBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshSaleMan(userInfo);
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
