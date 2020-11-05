package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.bean.SearchBean;

import java.util.Map;

public interface SearchPageContract {
    interface View extends IView {
        void refreshSearchPage(SearchBean userInfo);
    }

    interface Presenter {
        void getSearchList(Map<String, String> map);
    }
}
