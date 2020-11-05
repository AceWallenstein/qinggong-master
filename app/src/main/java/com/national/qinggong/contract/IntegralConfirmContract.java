package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.IntegralDetailBean;

import java.util.Map;

public interface IntegralConfirmContract {
    interface View extends IView {
        void addAddressTask(BaseBean userInfo);

        void editAddressTask(BaseBean userInfo);
        void RightDuihuan(Object userInfo);
        void submitOrderTask(Object userInfo);

    }

    interface Presenter {
        void addAddressInfo(Map<String, String> map);

        void editAddressInfo(Map<String, String> map);
        void submitRightInfo(Map<String, String> map);
        void submitOrderInfo(Map<String, String> map);
    }
}
