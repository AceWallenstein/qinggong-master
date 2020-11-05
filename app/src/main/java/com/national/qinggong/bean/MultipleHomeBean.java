package com.national.qinggong.bean;

import java.util.List;

public class MultipleHomeBean {
    /**
     * 广告
     */
    public static int TYPE_ADV = 0;
    /**
     * 分类
     */
    public static int TYPE_CATE = 2;
    /**
     * 列表
     */
    public static int TYPE_LIST = 3;



    /**
     * 列表
     */
    public static int TYPE_MESSAGE = 1;





    private int type = -1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int code;
    public String stateMeaagee;
    public List<HomeBannerBean> adBeanList;
    public List<CateageBean> cateageBeanList;
    public List<ListBean> listBeans;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static int getTypeAdv() {
        return TYPE_ADV;
    }

    public static void setTypeAdv(int typeAdv) {
        TYPE_ADV = typeAdv;
    }

    public static int getTypeCate() {
        return TYPE_CATE;
    }

    public static void setTypeCate(int typeCate) {
        TYPE_CATE = typeCate;
    }

    public static int getTypeList() {
        return TYPE_LIST;
    }

    public static void setTypeList(int typeList) {
        TYPE_LIST = typeList;
    }

    public String getStateMeaagee() {
        return stateMeaagee;
    }

    public void setStateMeaagee(String stateMeaagee) {
        this.stateMeaagee = stateMeaagee;
    }

    public List<HomeBannerBean> getAdBeanList() {
        return adBeanList;
    }

    public void setAdBeanList(List<HomeBannerBean> adBeanList) {
        this.adBeanList = adBeanList;
    }

    public List<CateageBean> getCateageBeanList() {
        return cateageBeanList;
    }

    public void setCateageBeanList(List<CateageBean> cateageBeanList) {
        this.cateageBeanList = cateageBeanList;
    }

    public List<ListBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<ListBean> listBeans) {
        this.listBeans = listBeans;
    }
}
