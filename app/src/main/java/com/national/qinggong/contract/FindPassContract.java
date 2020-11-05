package com.national.qinggong.contract;



import com.national.qinggong.base.IView;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;

import java.util.Map;

public interface FindPassContract {
    interface View extends IView {
        void refreshsendSms(BaseStrBean userInfo);
        void refreshfinPassSubmit(BaseBean userInfo);
//        void refreSmsLogin(LoginBean userInfo);

    }

    interface Presenter {

//        void getUserLogin(Map<String, String> map);
        void getsendSms(Map<String, String> map);
        void finPassSubmit(Map<String, String> map);
//        void getLoginSms(Map<String, String> map);

    }
}
