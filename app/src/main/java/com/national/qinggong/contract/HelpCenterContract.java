package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.HelpCenterBean;

import java.util.Map;

public interface HelpCenterContract {
    interface View extends IView {
        void refreshTask(HelpCenterBean userInfo);

    }
    interface Presenter {
        void helpInfo(Map<String, String> map);

    }
}
