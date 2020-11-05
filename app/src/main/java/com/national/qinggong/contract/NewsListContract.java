package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.BaseStrBean;

import java.util.Map;

public interface NewsListContract {
    interface View extends IView {
        void refreshNews(AboutOursNewsBean userInfo);
    }

    interface Presenter {
        void getNewList(Map<String, String> map);
    }
}
