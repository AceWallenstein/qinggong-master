package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.DoTaskBean;
import com.national.qinggong.bean.TaskBean;

import java.util.Map;

public interface NewHandTaskContract {
    interface View extends IView {
        void refreshTask(TaskBean userInfo);
        void doTask(DoTaskBean userInfo);

    }
    interface Presenter {
        void newHandInfo(Map<String, String> map);
        void doTask(Map<String, String> map);

    }
}
