package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.FaceAdviseBean;
import com.national.qinggong.contract.FaceToContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class FacePresenter extends BasePresenter<FaceToContract.View> implements  FaceToContract.Presenter{
    public FacePresenter(Activity activity, FaceToContract.View view) {
        super(activity, view);
    }

    @Override
    public void submitAdvisenfo( String phone, String content
            , String username) {
        addDisposable(RetrofitClient.getApiService(API.APPSHARED)
                .getFaceAdviseInfo(phone,content,username)
                .compose(RequestManager.<FaceAdviseBean>applyIoSchedulers())
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
                .subscribe(new Consumer<FaceAdviseBean>() {
                    @Override
                    public void accept(FaceAdviseBean userInfo) throws Exception {
                        if(mView!=null) {
                            if (userInfo != null) {
                                mView.refreadviseTask(userInfo);

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
