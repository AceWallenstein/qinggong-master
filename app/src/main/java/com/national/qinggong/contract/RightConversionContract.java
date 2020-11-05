package com.national.qinggong.contract;

import com.national.qinggong.base.IView;

import java.util.Map;

public interface RightConversionContract {
    interface View extends IView {
        void RightDuiHuanTask(Object userInfo);
//        void refreshPostfinally();
    }
    interface Presenter {
        void RightDuiHuanInfo(Map<String, String> map);
    }
}
