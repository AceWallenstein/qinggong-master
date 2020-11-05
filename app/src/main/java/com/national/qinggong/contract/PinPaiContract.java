package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.SysArticleBean;

import java.util.Map;

public interface PinPaiContract {
    interface View extends IView {
        void refreshNews(SysArticleBean userInfo);
    }

    interface Presenter {
        void getNewList(Map<String, String> map);
    }
}
