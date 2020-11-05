package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.bean.SaleCollectBean;
import com.national.qinggong.contract.MyColllectContract;
import com.national.qinggong.contract.MyMessageContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyColllectPresenter  extends BasePresenter<MyColllectContract.View> implements MyColllectContract.Presenter {
    public MyColllectPresenter(Activity activity, MyColllectContract.View view) {
        super(activity, view);
    }

    @Override
    public void getNewList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .myCollect(map)
                .compose(RequestManager.<SaleCollectBean>applyIoSchedulers())
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
                .subscribe(new Consumer<SaleCollectBean>() {
                    @Override
                    public void accept(SaleCollectBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshSaleMan(userInfo);

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
    public void submitCollectinfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .add_cancle_Collect(map)
                .compose(RequestManager.<CollectBean>applyIoSchedulers())
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
                .subscribe(new Consumer<CollectBean>() {
                    @Override
                    public void accept(CollectBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.add_cancle_collect(userInfo);

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
