package com.national.qinggong.bean;

import java.util.List;

/**
 * 创建时间：2020/11/3
 * 编写人：czy_yangxudong
 * 功能描述：
 */
public class LivePeopleBean {



    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String num;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
