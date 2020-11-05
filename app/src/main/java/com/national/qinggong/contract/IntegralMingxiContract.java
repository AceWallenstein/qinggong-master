package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.IntegralMingXiBean;

import java.util.Map;

public interface IntegralMingxiContract {
    interface View extends IView {
        void refreshNews(IntegralMingXiBean userInfo);
        void stockFinish();
    }

    interface Presenter {
        void getNewList(Map<String, String> map);
    }
}
