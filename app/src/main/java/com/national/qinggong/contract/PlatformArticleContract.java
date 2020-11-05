package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.ClassTypeBean;

import java.util.Map;

/*
* 文章
* */
public interface PlatformArticleContract {
    interface View extends IView {
        void refreshArticleInfo(ClassTypeBean userInfo);
        void refreShsearchInfo(ClassTypeBean userInfo);

        void   refreshPostfinally();
    }

    interface Presenter {

        void getArticleInfo(Map<String, String> map);
        void getSearchInfo(Map<String, String> map);

//        //我的信息 新版
//        void getPersonalCenterInfo(Map<String, String> map);


    }
}
