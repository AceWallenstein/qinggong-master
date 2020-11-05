package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.SaleManBean;

import java.util.Map;

public interface SalemanListContract {
    interface View extends IView {
        void refreshSaleMan(SaleManBean userInfo);
        void stockFinish();

    }

    interface Presenter {
        void getNewList(Map<String, String> map);
    }
}
