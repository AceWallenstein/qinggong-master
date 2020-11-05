package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.CarBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.contract.CarContract;
import com.national.qinggong.contract.MyMessageContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class CarPresenter  extends BasePresenter<CarContract.View> implements CarContract.Presenter {
    public CarPresenter(Activity activity, CarContract.View view) {
        super(activity, view);
    }

    @Override
    public void getMessageList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .CarList(map)
                .compose(RequestManager.<CarBean>applyIoSchedulers())
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
                .subscribe(new Consumer<CarBean>() {
                    @Override
                    public void accept(CarBean userInfo) throws Exception {
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

    @Override
    public void getDeleteCar(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                .deleteCar(map)
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
                                mView.deleteCar(userInfo);

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
