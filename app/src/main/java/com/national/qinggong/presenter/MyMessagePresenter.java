package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.contract.MyMessageContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyMessagePresenter extends BasePresenter<MyMessageContract.View> implements MyMessageContract.Presenter {
    public MyMessagePresenter(Activity activity, MyMessageContract.View view) {
        super(activity, view);
    }

    @Override
    public void getMessageList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .myMessage(map)
                .compose(RequestManager.<MyMessageBean>applyIoSchedulers())
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
                .subscribe(new Consumer<MyMessageBean>() {
                    @Override
                    public void accept(MyMessageBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshMessagePage(userInfo);

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
