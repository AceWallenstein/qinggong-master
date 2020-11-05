package com.national.qinggong.contract;


import com.national.qinggong.base.IView;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.bean.CountryBean;

import java.util.Map;

public interface RegeditContract {
    interface View extends IView {
        void refreshsendSms(BaseStrBean userInfo);
        void refreshregeditSubmit(Object userInfo);
//        void refreSmsLogin(LoginBean userInfo);
        void countryList(CountryBean userInfo);
    }

    interface Presenter {

        //        void getUserLogin(Map<String, String> map);
        void getsendSms(Map<String, String> map);
        void regeditSubmit(Map<String, String> map);
        void getcountry(Map<String, String> map);

    }
}
