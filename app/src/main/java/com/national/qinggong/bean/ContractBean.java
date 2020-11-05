package com.national.qinggong.bean;

import java.io.Serializable;

/*
* 指数分析合约
* */
public class ContractBean implements Serializable {

    /**
     * IsNight : 0
     * block : 美国NE
     * code : PLF0
     * mark : NE
     * name : 铂金01月
     * pinyin : PLF0
     * tradeTimeID : 12
     */

    private int IsNight;
    private String block;
    private String code;
    private String mark;
    private String name;
    private String pinyin;
    private String tradeTimeID;

    public int getIsNight() {
        return IsNight;
    }

    public void setIsNight(int IsNight) {
        this.IsNight = IsNight;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getTradeTimeID() {
        return tradeTimeID;
    }

    public void setTradeTimeID(String tradeTimeID) {
        this.tradeTimeID = tradeTimeID;
    }
}
