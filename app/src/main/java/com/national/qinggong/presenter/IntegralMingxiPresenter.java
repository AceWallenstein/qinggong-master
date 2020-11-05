package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.IntegralDetailBean;
import com.national.qinggong.bean.IntegralMingXiBean;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.contract.IntegralMingxiContract;
import com.national.qinggong.contract.IntegralShopingDetailContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class IntegralMingxiPresenter extends BasePresenter<IntegralMingxiContract.View> implements IntegralMingxiContract.Presenter {
    public IntegralMingxiPresenter(Activity activity, IntegralMingxiContract.View view) {
        super(activity, view);
    }

    @Override
    public void getNewList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .IntegralMingXi(map)
                        .compose(RequestManager.<IntegralMingXiBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.refreshPostfinally();
                                mView.stockFinish();
                            }
                        })
                        .subscribe(new Consumer<IntegralMingXiBean>() {
                            @Override
                            public void accept(IntegralMingXiBean userInfo) throws Exception {
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
