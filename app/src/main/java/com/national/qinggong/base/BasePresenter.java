package com.national.qinggong.base;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by kai on 2018/10/19.
 */

public class BasePresenter<T> implements IPresenter {

    protected Activity mActivity;//与Activity交互
    protected T mView;//Presenter持有的View

    //protected Context context;

    //private ArrayMap<String, Disposable> disposableArrayMap;//管理Destroy集合
    private ConcurrentHashMap<String, Disposable> disposableArrayMap;//管理Destroy集合
    private CompositeDisposable disposablesDestroy = null;// 管理Destroy取消订阅者者

    public BasePresenter(Activity activity, T view) {
        this.mActivity = activity;
        this.mView = view;
        disposablesDestroy = new CompositeDisposable();
        // this.context = activity;
    }

    /*
    public BasePresenter(Context context, T view) {
        this.context = context;
        this.mView = view;
    }

    protected Context getContext(){
        return this.context;
    }
    */

    protected Activity getActivity(){
        return  this.mActivity;
    }

    @Override
    public void onViewDestroy() {
        unSubscribeAllDisposable();
    }


    /**
     * 将调用链 加入，直到调用onDestroy
     *
     * @param tag        加入调用链时的tag
     * @param disposable 传入一个disposable实现类，即rx调用链
     */
    protected void addDisposable(@NonNull String tag, Disposable disposable) {
        if (disposablesDestroy == null) {
            disposablesDestroy = new CompositeDisposable();
        }
        if(disposableArrayMap==null){
            // disposableArrayMap = new ArrayMap<>();
            disposableArrayMap = new ConcurrentHashMap<>();
        }
        disposableArrayMap.put(tag, disposable);
        disposablesDestroy.add(disposable);

    }

    /**
     * 将调用链 加入，直到调用onDestroy
     *
     * @param disposable 传入一个disposable实现类，即rx调用链
     */
    protected void addDisposable(Disposable disposable) {
        if (disposablesDestroy == null) {
            disposablesDestroy = new CompositeDisposable();
        }
        disposablesDestroy.add(disposable);
    }

    /**
     * onDestroy触发停止调用链
     */
    private void unSubscribeAllDisposable() {
        if (disposablesDestroy != null) {
            disposablesDestroy.clear();
            disposablesDestroy = null;
        }
        if (disposableArrayMap != null && disposableArrayMap.size() > 0) {
            disposableArrayMap.clear();
        }
    }

    /**
     * 移除一个或多个调用方法
     */
    protected void removeDisposable(Disposable... disposables) {
        if (disposablesDestroy != null) {
            for (Disposable d : disposables) {
                disposablesDestroy.remove(d);
            }
        }
    }

    /**
     * 通过tag移除多个调用链
     *
     * @param tags 订阅时候的tag
     */
    public void removeDisposableByTag(String... tags) {
        if (disposableArrayMap!=null&&disposableArrayMap.size() > 0) {
            for (String tag : tags) {
                if (disposableArrayMap.containsKey(tag)) {
                    disposablesDestroy.remove(disposableArrayMap.get(tag));
                    disposableArrayMap.remove(tag);
                } else {
                    //throw new RuntimeException("没有该tag: "+tag);
                }
            }
        }
    }

}
