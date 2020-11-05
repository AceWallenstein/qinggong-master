package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.PersonCenterBean;
import com.national.qinggong.contract.PersonCenterContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
public class PersonCenterPresenter extends BasePresenter<PersonCenterContract.View> implements PersonCenterContract.Presenter {
    public PersonCenterPresenter(Activity activity, PersonCenterContract.View view) {
        super(activity, view);
    }

    @Override
    public void getPersonData(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .personCenter(map)
                        .compose(RequestManager.<PersonCenterBean>applyIoSchedulers())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
//                        mView.stockLoginFinish();
                            }
                        })
                        .subscribe(new Consumer<PersonCenterBean>() {
                            @Override
                            public void accept(PersonCenterBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.personCenterData(userInfo);
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
