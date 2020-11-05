package com.national.qinggong.contract;

import com.national.qinggong.base.IView;

import java.util.Map;

public interface PersonDataContract {
    interface View extends IView {
        void refreshUserPass(Object userInfo);

    }
    interface Presenter {
        void getUserInfo(Map<String, String> map);

    }
}
