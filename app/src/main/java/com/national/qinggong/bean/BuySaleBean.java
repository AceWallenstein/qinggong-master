package com.national.qinggong.bean;

public class BuySaleBean {

    private String BUY_SALE;
    private String BUY_SALE_price;
    private String BUY_SALE_liang;

    public BuySaleBean(String BUY_SALE, String BUY_SALE_price, String BUY_SALE_liang) {
        this.BUY_SALE = BUY_SALE;
        this.BUY_SALE_price = BUY_SALE_price;
        this.BUY_SALE_liang = BUY_SALE_liang;
    }

    public String getBUY_SALE() {
        return BUY_SALE;
    }

    public void setBUY_SALE(String BUY_SALE) {
        this.BUY_SALE = BUY_SALE;
    }

    public String getBUY_SALE_price() {
        return BUY_SALE_price;
    }

    public void setBUY_SALE_price(String BUY_SALE_price) {
        this.BUY_SALE_price = BUY_SALE_price;
    }

    public String getBUY_SALE_liang() {
        return BUY_SALE_liang;
    }

    public void setBUY_SALE_liang(String BUY_SALE_liang) {
        this.BUY_SALE_liang = BUY_SALE_liang;
    }
}
