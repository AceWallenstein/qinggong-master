package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursBean;
import com.national.qinggong.bean.AboutOursNewsBean;

import java.util.Map;

public interface FragmentAboutOursContract {
    interface View extends IView {
        void refreshNews(AboutOursBean userInfo);
    }

    interface Presenter {
        void getAboutOurs(Map<String, String> map);
    }
}
