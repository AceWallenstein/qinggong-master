package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.HomeMessagebean;
import com.national.qinggong.bean.ReadUserInfoBean;

import java.util.Map;

public interface SystemMessageContract {
    interface View extends IView {
        void refreshMessagerInfo(HomeMessagebean userInfo);
        void refreshMessagerfinishInfo();


        void refreshReadMessagerInfo(ReadUserInfoBean userInfo);
    }

    interface Presenter {

        void getMessage(Map<String, String> map);

        void getUpdateReadMessage(Map<String, String> map);

    }
}
