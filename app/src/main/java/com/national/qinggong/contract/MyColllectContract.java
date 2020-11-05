package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.SaleCollectBean;

import java.util.Map;

public interface MyColllectContract {
    interface View extends IView {
        void refreshSaleMan(SaleCollectBean userInfo);
        void add_cancle_collect(CollectBean userInfo);
        void stockFinish();

    }

    interface Presenter {
        void getNewList(Map<String, String> map);
        void submitCollectinfo(Map<String, String> map);
    }
}
