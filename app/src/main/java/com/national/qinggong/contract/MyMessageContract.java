package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.MyMessageBean;
import java.util.Map;

public interface MyMessageContract {
    interface View extends IView {
        void refreshMessagePage(MyMessageBean userInfo);
        void stockFinish();
    }

    interface Presenter {
        void getMessageList(Map<String, String> map);
    }
}
