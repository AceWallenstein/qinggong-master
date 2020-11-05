package com.national.qinggong.contract;

import com.national.qinggong.base.IView;

import java.util.Map;

public interface IntegralShopingContract {
    interface View extends IView {
        void IntegralTask(Object userInfo);
        void refreshPostfinally();
    }
    interface Presenter {
        void IntegralInfo(Map<String, String> map);
    }
}
