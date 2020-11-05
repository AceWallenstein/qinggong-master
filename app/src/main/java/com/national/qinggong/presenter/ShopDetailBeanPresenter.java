package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.bean.ShopDetailBean;
import com.national.qinggong.contract.SearchShopListContract;
import com.national.qinggong.contract.ShopDetailBeanContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class ShopDetailBeanPresenter extends BasePresenter<ShopDetailBeanContract.View> implements ShopDetailBeanContract.Presenter {
    public ShopDetailBeanPresenter(Activity activity, ShopDetailBeanContract.View view) {
        super(activity, view);
    }

    @Override
    public void getShopDetail(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .homeDetail(map)
                .compose(RequestManager.<ShopDetailBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ShopDetailBean>() {
                    @Override
                    public void accept(ShopDetailBean userInfo) throws Exception {
                        if (mView != null) {
                            if (userInfo != null) {
                                mView.refreshShopDetail(userInfo);
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
