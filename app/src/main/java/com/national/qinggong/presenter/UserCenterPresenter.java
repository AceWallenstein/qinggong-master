package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.MyApplication;
import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.ServiceQrBean;
import com.national.qinggong.contract.UserCenterContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UserCenterPresenter extends BasePresenter<UserCenterContract.View> implements UserCenterContract.Presenter {
    public UserCenterPresenter(Activity activity, UserCenterContract.View view) {
        super(activity, view);
    }

    /*
    *  CacheHelper.setAlias(UserLoginActivity.this,"user_uuid",uuid[1]);
    * */
    public String gainStockUUid() {
        String user_uuid = CacheHelper.getAlias(MyApplication.getInstance(), "user_uuid");
        return user_uuid;
    }
    @Override
    public void getUserInfo(Map<String, String> map) {
        String user_uuid = gainStockUUid();
        if (StringUtils.isEmpty(user_uuid))return;
        addDisposable(RetrofitClient.getApiService(API.APP_PORT_8802,user_uuid)
                .getCurrentUserinfo(map)
                .compose(RequestManager.<Object>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshUserInfo(userInfo);

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
    public void getClassRoomInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .gethotClassRoomInfo()
                .compose(RequestManager.<ClassTypeBean>applyIoSchedulers())
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
                .subscribe(new Consumer<ClassTypeBean>() {
                    @Override
                    public void accept(ClassTypeBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreshArticleInfo(userInfo);

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
    public void getsPhoneQRInfo(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                        .getPhoneQrInfo()
                        .compose(RequestManager.<ServiceQrBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<ServiceQrBean>() {
                            @Override
                            public void accept(ServiceQrBean userInfo) throws Exception {
                                if(mView!=null) {
                                    if (userInfo != null) {
                                        mView.refreshServiceQRInfo(userInfo);

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
