package com.national.qinggong.bean;

import java.util.List;

public class PersonCenterBean {

    /**
     * code : 1
     * msg : success
     * data : {"userInfo":{"open_id":"","account":"1186888289@qq.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"","avatarUrl":"","gender":"未知","country":"1","province":"","city":"","address_id":0,"balance":"0.00","points":0,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"phone":"","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":[],"username":null,"identity":0,"status":1,"address_default":null,"grade":null}}
     */

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
        /**
         * userInfo : {"open_id":"","account":"1186888289@qq.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"","avatarUrl":"","gender":"未知","country":"1","province":"","city":"","address_id":0,"balance":"0.00","points":0,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"phone":"","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":[],"username":null,"identity":0,"status":1,"address_default":null,"grade":null}
         */

        private UserInfoBean userInfo;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * open_id :
             * account : 1186888289@qq.com
             * password : e10adc3949ba59abbe56e057f20f883e
             * nickName :
             * avatarUrl :
             * gender : 未知
             * country : 1
             * province :
             * city :
             * address_id : 0
             * balance : 0.00
             * points : 0
             * pay_money : 0.00
             * expend_money : 0.00
             * grade_id : 0
             * is_new_user : 1
             * phone :
             * fid : 0
             * mid : 1
             * role : null
             * smile : 0
             * weixin : null
             * job : null
             * source_name : null
             * source_text : null
             * source_cardid : null
             * lasttime : null
             * group_tags : null
             * company : null
             * position : null
             * source_type : 1
             * scene : null
             * source_id : null
             * phonemodel : null
             * address : []
             * username : null
             * identity : 0
             * status : 1
             * address_default : null
             * grade : null
             */

            private String open_id;
            private String account;
            private String password;
            private String nickName;
            private String avatarUrl;
            private String gender;
            private String country;
            private String province;
            private String city;
            private int address_id;
            private String balance;
            private int points;
            private String pay_money;
            private String expend_money;
            private int grade_id;
            private int is_new_user;
            private String phone;
            private int fid;
            private int mid;
            private Object role;
            private int smile;
            private Object weixin;
            private Object job;
            private Object source_name;
            private Object source_text;
            private Object source_cardid;
            private Object lasttime;
            private Object group_tags;
            private Object company;
            private Object position;
            private int source_type;
            private int is_anchor;
            private Object scene;
            private Object source_id;
            private Object phonemodel;
            private Object username;
            private int identity;
            private int status;
            private Object address_default;
            private Object grade;
            private List<?> address;

            public int getIs_anchor() {
                return is_anchor;
            }

            public String getOpen_id() {
                return open_id;
            }

            public void setOpen_id(String open_id) {
                this.open_id = open_id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public String getPay_money() {
                return pay_money;
            }

            public void setPay_money(String pay_money) {
                this.pay_money = pay_money;
            }

            public String getExpend_money() {
                return expend_money;
            }

            public void setExpend_money(String expend_money) {
                this.expend_money = expend_money;
            }

            public int getGrade_id() {
                return grade_id;
            }

            public void setGrade_id(int grade_id) {
                this.grade_id = grade_id;
            }

            public int getIs_new_user() {
                return is_new_user;
            }

            public void setIs_new_user(int is_new_user) {
                this.is_new_user = is_new_user;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public Object getRole() {
                return role;
            }

            public void setRole(Object role) {
                this.role = role;
            }

            public int getSmile() {
                return smile;
            }

            public void setSmile(int smile) {
                this.smile = smile;
            }

            public Object getWeixin() {
                return weixin;
            }

            public void setWeixin(Object weixin) {
                this.weixin = weixin;
            }

            public Object getJob() {
                return job;
            }

            public void setJob(Object job) {
                this.job = job;
            }

            public Object getSource_name() {
                return source_name;
            }

            public void setSource_name(Object source_name) {
                this.source_name = source_name;
            }

            public Object getSource_text() {
                return source_text;
            }

            public void setSource_text(Object source_text) {
                this.source_text = source_text;
            }

            public Object getSource_cardid() {
                return source_cardid;
            }

            public void setSource_cardid(Object source_cardid) {
                this.source_cardid = source_cardid;
            }

            public Object getLasttime() {
                return lasttime;
            }

            public void setLasttime(Object lasttime) {
                this.lasttime = lasttime;
            }

            public Object getGroup_tags() {
                return group_tags;
            }

            public void setGroup_tags(Object group_tags) {
                this.group_tags = group_tags;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public int getSource_type() {
                return source_type;
            }

            public void setSource_type(int source_type) {
                this.source_type = source_type;
            }

            public Object getScene() {
                return scene;
            }

            public void setScene(Object scene) {
                this.scene = scene;
            }

            public Object getSource_id() {
                return source_id;
            }

            public void setSource_id(Object source_id) {
                this.source_id = source_id;
            }

            public Object getPhonemodel() {
                return phonemodel;
            }

            public void setPhonemodel(Object phonemodel) {
                this.phonemodel = phonemodel;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getAddress_default() {
                return address_default;
            }

            public void setAddress_default(Object address_default) {
                this.address_default = address_default;
            }

            public Object getGrade() {
                return grade;
            }

            public void setGrade(Object grade) {
                this.grade = grade;
            }

            public List<?> getAddress() {
                return address;
            }

            public void setAddress(List<?> address) {
                this.address = address;
            }
        }
    }
}
