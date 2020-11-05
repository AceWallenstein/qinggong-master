package com.national.qinggong.contract;



import com.national.qinggong.base.IView;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;

import java.util.Map;

public interface UserLoginContract {
    interface View extends IView {
        void refreshLogin(Object userInfo);
        void refreshsendSms(BaseStrBean userInfo);
        void refreSmsLogin(Object userInfo);

    }

    interface Presenter {
        /*用户登录*/
        void getUserLogin(Map<String, String> map);
        void getsendSms(Map<String, String> map);
        void getLoginSms(Map<String, String> map);

    }
}
