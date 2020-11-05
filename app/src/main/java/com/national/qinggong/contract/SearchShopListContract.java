package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.SearchBean;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.bean.SearchShopListBean;

import java.util.Map;

public interface SearchShopListContract {
    interface View extends IView {
        void refreshSearchPage(SearchShopListBean userInfo);
        void stockFinish();

        void refreshCategory(SearchCategoryBean userInfo);
        void add_addCar(DeleteCarBean userInfo);

    }

    interface Presenter {
        void getSearchList(Map<String, String> map);

        void getSearchCategory(Map<String, String> map);
        void submit_addcar(Map<String, String> map);
    }
}
