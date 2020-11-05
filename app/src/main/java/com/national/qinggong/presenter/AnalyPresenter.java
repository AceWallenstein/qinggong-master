package com.national.qinggong.presenter;

import android.app.Activity;
import android.util.Log;

import com.national.qinggong.MyApplication;
import com.national.qinggong.base.BasePresenter;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.StockBlockBean;
import com.national.qinggong.contract.AnalyListContract;
import com.national.qinggong.request.API;
import com.national.qinggong.request.ExceptionHandler;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.StringUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/*
 * 指数分析
 * */
public class AnalyPresenter extends BasePresenter<AnalyListContract.View> implements AnalyListContract.Presenter {
    public AnalyPresenter(Activity activity, AnalyListContract.View view) {
        super(activity, view);
    }

    public String gainStockUUid() {
        String stick_uuid = (String) CacheHelper.get(MyApplication.getInstance(), "uuid", "");
        return stick_uuid;
    }


    /*
     * 指数行情分类
     * */
    @Override
    public void getBlockInfo(Map<String, String> map) {
        String StockUUid = gainStockUUid();
        if (StringUtils.isEmpty(StockUUid))return;
        Log.i("=================", "getContractInfo");
        addDisposable(RetrofitClient.getApiService(API.APP_gp, StockUUid)
                .getBlockInfo()
                .compose(RequestManager.<List<StockBlockBean.StockListbean>>applyIoSchedulers())
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
                .subscribe(new Consumer<List<StockBlockBean.StockListbean>>() {
                    @Override
                    public void accept(List<StockBlockBean.StockListbean> userInfo) throws Exception {
                        if (mView != null) {
                            if (userInfo != null) {
                                mView.refreshBlockInfo(userInfo);

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

    /*
     *  12、得到多个股票的行情报价
     *
     * */
    @Override
    public void getMultiMarketInfo(Map<String, String> map) {
        String StockUUid = gainStockUUid();
        if (StringUtils.isEmpty(StockUUid))return;
        addDisposable(RetrofitClient.getApiService(API.APP_gp, StockUUid)
                .getMultiMarketInfo(map)
                .compose(RequestManager.<List<MultiMarketBean>>applyIoSchedulers())
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
                .subscribe(new Consumer<List<MultiMarketBean>>() {
                    @Override
                    public void accept(List<MultiMarketBean> userInfo) throws Exception {
                        if (mView != null) {
                            if (userInfo != null) {
                                mView.refreshMultiMarketInfo(userInfo);

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
    public void getContractInfo(Map<String, String> map) {
        Log.i("=================", "getContractInfo");
        String StockUUid = gainStockUUid();
        if (StringUtils.isEmpty(StockUUid))return;
        addDisposable(RetrofitClient.getApiService(API.APP_gp, StockUUid)
                .getContractinfo()
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
                        if (mView != null) {
                            if (userInfo != null) {
                                mView.refreshContractInfo(userInfo);

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
    public void getmostPriceInfo(Map<String, String> map) {

    }

    @Override
    public void getzhangdieInfo(Map<String, String> map) {

    }

    @Override
    public void getVolumeInfo(Map<String, String> map) {

    }
}
