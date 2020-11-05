package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.IntegralDetailBean;
import com.national.qinggong.bean.SendIntegralPointsBean;

import java.util.Map;

public interface IntegralShopingDetailContract {
    interface View extends IView {
        void IntegralDetailTask(IntegralDetailBean userInfo);
        void RightDuihuan(Object userInfo);
        void sendIntegralPointsTask(SendIntegralPointsBean userInfo);
        void refreshPostfinally();
    }
    interface Presenter {
        void IntegralDetailInfo(Map<String, String> map);
        void sendIntegralDetailInfo(Map<String, String> map);
        void submitRightInfo(Map<String, String> map);
    }
}
