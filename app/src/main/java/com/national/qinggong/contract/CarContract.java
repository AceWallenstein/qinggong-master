package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.CarBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.MyMessageBean;

import java.util.Map;

public interface CarContract {
    interface View extends IView {
        void deleteCar(DeleteCarBean userInfo);
        void refreshMessagePage(CarBean userInfo);
        void stockFinish();
    }

    interface Presenter {
        void getMessageList(Map<String, String> map);
        void getDeleteCar(Map<String, String> map);
    }
}
