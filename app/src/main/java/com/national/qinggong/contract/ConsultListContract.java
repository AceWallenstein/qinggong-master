package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.DeleteCarBean;

import java.util.Map;

public interface ConsultListContract {
    interface View extends IView {
        void refreshConsultPage(Object userInfo);

        void stockFinish();
        void Disposable();
        void sendConsult(DeleteCarBean userInfo);


    }

    interface Presenter {
        void getConsultList(Map<String, String> map);
        void getSendConsult(Map<String, String> map);
    }
}
