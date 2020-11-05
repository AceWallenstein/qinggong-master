package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.ClassTypeBean;

import java.util.Map;

public interface PlatformClassRoomContract {
    interface View extends IView {
        void refreshArticleInfo(ClassTypeBean userInfo);

        void   refreshPostfinally();


        void refreShsearchInfo(ClassTypeBean userInfo);
    }

    interface Presenter {

        void getClassRoomInfo(Map<String, String> map);


        void getSearchInfo(Map<String, String> map);
//        //我的信息 新版
//        void getPersonalCenterInfo(Map<String, String> map);


    }
}
