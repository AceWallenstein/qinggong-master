package com.national.qinggong.bean;

import java.util.List;

public class LiveRoomGetTalkBean {

    /**
     * code : 1
     * msg : 发送成功
     * data : {"list":[{"talk_id":10,"user_id":14664,"live_id":10054,"content":"测试","create_time":"2020-10-22 09:27:19","update_time":"2020-10-22 09:27:19","wxapp_id":10001,"is_delete":0,"user":{"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * talk_id : 10
             * user_id : 14664
             * live_id : 10054
             * content : 测试
             * create_time : 2020-10-22 09:27:19
             * update_time : 2020-10-22 09:27:19
             * wxapp_id : 10001
             * is_delete : 0
             * user : {"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}
             */

            private int talk_id;
            private int user_id;
            private int live_id;
            private String content;
            private String create_time;
            private String update_time;
            private int wxapp_id;
            private int is_delete;
            private UserBean user;

            public int getTalk_id() {
                return talk_id;
            }

            public void setTalk_id(int talk_id) {
                this.talk_id = talk_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getLive_id() {
                return live_id;
            }

            public void setLive_id(int live_id) {
                this.live_id = live_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * user_id : 14664
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
                 * points : 82
                 * pay_money : 0.00
                 * expend_money : 0.00
                 * grade_id : 0
                 * is_new_user : 1
                 * is_delete : 0
                 * wxapp_id : 10001
                 * create_time : 2020-07-08 16:44:51
                 * update_time : 2020-10-16 09:24:48
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
                 * address : null
                 * username : null
                 * identity : 1
                 * status : 1
                 */

                private int user_id;
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
                private int is_delete;
                private int wxapp_id;
                private String create_time;
                private String update_time;
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
                private Object address;
                private Object username;
                private int identity;
                private int status;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
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

                public int getIs_delete() {
                    return is_delete;
                }

                public void setIs_delete(int is_delete) {
                    this.is_delete = is_delete;
                }

                public int getWxapp_id() {
                    return wxapp_id;
                }

                public void setWxapp_id(int wxapp_id) {
                    this.wxapp_id = wxapp_id;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
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

                public Object getAddress() {
                    return address;
                }

                public void setAddress(Object address) {
                    this.address = address;
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
            }
        }
    }
}
