package com.national.qinggong.contract;

import com.national.qinggong.base.IView;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.StockBlockBean;

import java.util.List;
import java.util.Map;

/*
 * 指数列表
 * */
public interface AnalyListContract {
    interface View extends IView {


        //板块
        void refreshBlockInfo(List<StockBlockBean.StockListbean> Info);




        //多个股票行情
        void refreshMultiMarketInfo(List<MultiMarketBean> Info);


        //合约
        void refreshContractInfo(Object Info);

        //最新价
        void refreshmostPriceInfo(Object Info);

        //涨跌幅
        void refreshZhangdiefuInfo(Object Info);

        //成交量
        void refreshVolumeInfo(Object Info);

    }

    interface Presenter {

        //Block 板块
        void getBlockInfo(Map<String, String> map);

        /*
        * 多个股票
        * */
        void getMultiMarketInfo(Map<String, String> map);




        //合约
        void getContractInfo(Map<String, String> map);

        //最新价
        void getmostPriceInfo(Map<String, String> map);

        //涨跌幅

        void getzhangdieInfo(Map<String, String> map);


        //成交量

        void getVolumeInfo(Map<String, String> map);


    }
}
