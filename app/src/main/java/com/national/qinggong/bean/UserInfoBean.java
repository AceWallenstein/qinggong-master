package com.national.qinggong.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    /**
     * id : 1180
     * vipid : 4
     * username : gfmf123123
     * name : 123
     * password : null
     * phone :
     * address :
     * mark : 股票,期货
     * salor :
     * creator : gf001
     * starttime : 2020-03-16T00:00:00
     * limittime : 2021-04-16T00:00:00
     * uuid : null
     * lastasktime : 0001-01-01T00:00:00
     */

    private int id;
    private int vipid;
    private String username;
    private String name;
    private Object password;
    private String phone;
    private String address;
    private String mark;
    private String salor;
    private String creator;
    private String starttime;
    private String limittime;
    private Object uuid;
    private String lastasktime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVipid() {
        return vipid;
    }

    public void setVipid(int vipid) {
        this.vipid = vipid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSalor() {
        return salor;
    }

    public void setSalor(String salor) {
        this.salor = salor;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getLimittime() {
        return limittime;
    }

    public void setLimittime(String limittime) {
        this.limittime = limittime;
    }

    public Object getUuid() {
        return uuid;
    }

    public void setUuid(Object uuid) {
        this.uuid = uuid;
    }

    public String getLastasktime() {
        return lastasktime;
    }

    public void setLastasktime(String lastasktime) {
        this.lastasktime = lastasktime;
    }
}
