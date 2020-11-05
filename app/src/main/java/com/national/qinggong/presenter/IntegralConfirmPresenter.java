package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.contract.IntegralConfirmContract;
import com.national.qinggong.contract.IntegralShopingContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*积分订单提交 */
public class IntegralConfirmPresenter extends BasePresenter<IntegralConfirmContract.View> implements IntegralConfirmContract.Presenter {
    public IntegralConfirmPresenter(Activity activity, IntegralConfirmContract.View view) {
        super(activity, view);
    }

    @Override
    public void addAddressInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .addAddress(map)
                        .compose(RequestManager.<BaseBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<BaseBean>() {
                            @Override
                            public void accept(BaseBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.addAddressTask(userInfo);

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
    public void editAddressInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .editAddress(map)
                        .compose(RequestManager.<BaseBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<BaseBean>() {
                            @Override
                            public void accept(BaseBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.editAddressTask(userInfo);

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
    public void submitRightInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .right_integral(map)
                        .compose(RequestManager.<Object>applyIoSchedulers())
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
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.RightDuihuan(userInfo);

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
    public void submitOrderInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .submitOrder(map)
                        .compose(RequestManager.<Object>applyIoSchedulers())
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
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.submitOrderTask(userInfo);

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
