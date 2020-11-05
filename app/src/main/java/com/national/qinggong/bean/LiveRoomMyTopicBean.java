package com.national.qinggong.bean;

import java.util.List;

public class LiveRoomMyTopicBean {


    /**
     * code : 1
     * msg : success
     * data : {"anchor":{"id":17,"merch_id":0,"user_id":14664,"describe":"测试主播","image_id":19898,"wxapp_id":10001,"visits":77,"create_time":1600322394,"update_time":1600322394,"time":1,"fans":1},"user":{"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":64,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":[],"username":null,"identity":1,"status":1,"address_default":null,"grade":null}}
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
         * anchor : {"id":17,"merch_id":0,"user_id":14664,"describe":"测试主播","image_id":19898,"wxapp_id":10001,"visits":77,"create_time":1600322394,"update_time":1600322394,"time":1,"fans":1}
         * user : {"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":64,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":[],"username":null,"identity":1,"status":1,"address_default":null,"grade":null}
         */

        private AnchorBean anchor;
        private UserBean user;

        public AnchorBean getAnchor() {
            return anchor;
        }

        public void setAnchor(AnchorBean anchor) {
            this.anchor = anchor;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class AnchorBean {
            /**
             * id : 17
             * merch_id : 0
             * user_id : 14664
             * describe : 测试主播
             * image_id : 19898
             * wxapp_id : 10001
             * visits : 77
             * create_time : 1600322394
             * update_time : 1600322394
             * time : 1
             * fans : 1
             */

            private int id;
            private int merch_id;
            private int user_id;
            private String describe;
            private int image_id;
            private int wxapp_id;
            private int visits;
            private int create_time;
            private int update_time;
            private int time;
            private int fans;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMerch_id() {
                return merch_id;
            }

            public void setMerch_id(int merch_id) {
                this.merch_id = merch_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public int getVisits() {
                return visits;
            }

            public void setVisits(int visits) {
                this.visits = visits;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }
        }

        public static class UserBean {
            /**
             * open_id :
             * account : 18326949252@163.com
             * password : e10adc3949ba59abbe56e057f20f883e
             * nickName : 测试
             * avatarUrl : http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png
             * gender : 未知
             * country : China
             * province :
             * city :
             * address_id : 0
             * balance : 0.00
             * points : 64
             * pay_money : 0.00
             * expend_money : 0.00
             * grade_id : 0
             * is_new_user : 1
             * phone : 13625627267
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
             * identity : 1
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
            private Object scene;
            private Object source_id;
            private Object phonemodel;
            private Object username;
            private int identity;
            private int status;
            private Object address_default;
            private Object grade;
            private List<?> address;

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
