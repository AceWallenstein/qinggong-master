package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.FaceAdviseBean;

public interface FaceToContract {
    interface View extends IView {
        void refreadviseTask(FaceAdviseBean userInfo);

    }
    interface Presenter {
        void submitAdvisenfo(String phone, String content
                , String username);

    }

}
