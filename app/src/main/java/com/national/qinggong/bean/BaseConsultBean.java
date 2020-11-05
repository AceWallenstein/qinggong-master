package com.national.qinggong.bean;

public class BaseConsultBean {
/*
* 消息类型
* */

    private String status;
    private String content;
    private int mType;
    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
