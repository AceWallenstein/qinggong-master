package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.bean.ShopDetailBean;

import java.util.Map;

public interface ShopDetailBeanContract {
    interface View extends IView {
        void refreshShopDetail(ShopDetailBean userInfo);
        void stockFinish();
        void add_cancle_collect(CollectBean userInfo);
        void add_addCar(DeleteCarBean userInfo);

    }

    interface Presenter {
        void getShopDetail(Map<String, String> map);
        void submitCollectinfo(Map<String, String> map);
        void submit_addcar(Map<String, String> map);

    }
}
