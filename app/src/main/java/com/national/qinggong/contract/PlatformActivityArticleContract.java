package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.ActivityArticleBean;

import java.util.Map;
/*
* 活动文章
* */
public interface PlatformActivityArticleContract {
    interface View extends IView {
        void refreshArticleInfo(ActivityArticleBean userInfo);
        void   refreshPostfinally();
    }

    interface Presenter {

        void getArticleInfo(Map<String, String> map);




    }
}
