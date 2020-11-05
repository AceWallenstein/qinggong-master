package com.national.qinggong.bean;

import java.io.Serializable;

public class HomeBean implements Serializable {
    private String msg;
    private String status;
    private boolean isSeleted = false;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public boolean isSeleted() {
        return isSeleted;
    }

    public void setSeleted(boolean seleted) {
        isSeleted = seleted;
    }
}
