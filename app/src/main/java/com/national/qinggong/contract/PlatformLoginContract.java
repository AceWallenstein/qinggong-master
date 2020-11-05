package com.national.qinggong.contract;

import com.national.qinggong.base.IView;

import java.util.Map;

/*
* 登录
* */
public interface PlatformLoginContract {
    interface View extends IView {
        void refreshLogin(Object userInfo);
        void refreshuserLogin(Object userInfo);
        void  stockLoginFinish();
    }

    interface Presenter {

        void getUserInfo(Map<String, String> map);

        /*用户登录*/
        void getUserLogin(Map<String, String> map);




    }

}
