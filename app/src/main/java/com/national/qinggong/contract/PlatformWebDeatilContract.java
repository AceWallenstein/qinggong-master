package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.ActivityDetailBean;
import com.national.qinggong.bean.ArticleDetailBean;
import com.national.qinggong.bean.ClassRoomDetailBean;
import com.national.qinggong.bean.NewsDetailBean;

import java.util.Map;

public interface PlatformWebDeatilContract {
    interface View extends IView {
        void refreshArticleInfo(ArticleDetailBean userInfo);

        void refreshClassInfo(ClassRoomDetailBean userInfo);
        void getActivityInfo(ActivityDetailBean userInfo);

        void getNewDetailInfo(NewsDetailBean userInfo);




    }

    interface Presenter {

        void getArticleInfo(Map<String, String> map);
        void getClassRoomDetailInfo(Map<String, String> map);
        void getActivityDetailInfo(Map<String, String> map);
        void getNewsDetailInfo(Map<String, String> map);

    }
}
