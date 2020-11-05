package com.national.qinggong.presenter;

import android.app.Activity;

import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.bean.SearchBean;
import com.national.qinggong.contract.SalemanListContract;
import com.national.qinggong.contract.SearchPageContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*搜索页*/
public class SearchPagePresenter extends BasePresenter<SearchPageContract.View> implements SearchPageContract.Presenter {
    public SearchPagePresenter(Activity activity, SearchPageContract.View view) {
        super(activity, view);
    }

    @Override
    public void getSearchList(Map<String, String> map) {
        addDisposable(RetrofitClient.getApiService(API.APP_QING_GONG)
                        .searchPage(map)
                        .compose(RequestManager.<SearchBean>applyIoSchedulers())
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
                        .subscribe(new Consumer<SearchBean>() {
                            @Override
                            public void accept(SearchBean userInfo) throws Exception {
                                if (mView != null) {
                                    if (userInfo != null) {
                                        mView.refreshSearchPage(userInfo);
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
