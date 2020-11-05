package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.HomePageBean;
import java.util.Map;

public interface HomePageContract {
    interface View extends IView {
        void HomeTask(HomePageBean userInfo);
        void add_addCar(DeleteCarBean userInfo);
    }
    interface Presenter {
        void homeInfo(Map<String, String> map);
        void submit_addcar(Map<String, String> map);
    }
}
