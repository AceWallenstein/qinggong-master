package com.national.qinggong.request;

public class API {
    public static final boolean isTest = true; //  false 为正式环境   true 为测试环境
    public static final String APPSHARED =isTest ? "http://guofu.vshop365.cn/":"http://guofu.vshop365.cn/";//正式地址 1
    //成功返回code
    public static final String SUCCESS_CODE = "10000";
    //错误
    public static final String SUCCESS_ERRORCODE = "10002";
    //token失效
    public static final String TOKENFALIR  ="10001";



    /*
    * 股票相关
    * */



    public static final String APP_gp =isTest ? "http://120.24.219.141:12002/StockService/":"http://guofu.vshop365.cn/";//正式地址 1



    public static final String APP_PORT_8802 = "http://120.24.219.141:8802/BackService/";//
    public static final String APP_QING_GONG = "http://qgshop.meiliancheng.cn/";//




}
