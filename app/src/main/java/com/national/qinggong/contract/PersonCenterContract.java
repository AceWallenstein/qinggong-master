package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.PersonCenterBean;

import java.util.Map;

public interface PersonCenterContract {

    interface View extends IView {
        void personCenterData(PersonCenterBean userInfo);
    }

    interface Presenter {
        void getPersonData(Map<String, String> map);
    }

}
