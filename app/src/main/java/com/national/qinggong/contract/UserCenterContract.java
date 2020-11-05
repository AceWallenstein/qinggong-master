package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.ServiceQrBean;

import java.util.Map;

/*
* 我的
* */
public interface UserCenterContract {
    interface View extends IView {
        void refreshUserInfo(Object userInfo);
        void refreshArticleInfo(ClassTypeBean userInfo);
        void refreshServiceQRInfo(ServiceQrBean userInfo);


    }
    interface Presenter {
        void getUserInfo(Map<String, String> map);
        void getClassRoomInfo(Map<String, String> map);
        void getsPhoneQRInfo(Map<String, String> map);
    }
}
