package com.national.qinggong.bean;

import java.util.List;

public class SaleManBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":{"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"user_id":14662,"open_id":"","account":"183@163.com","password":null,"nickName":"?","avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","gender":0,"country":"1","province":"","city":"","address_id":0,"balance":"0.00","points":0,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":1594108614,"update_time":0,"phone":"","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}]}}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * list : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"user_id":14662,"open_id":"","account":"183@163.com","password":null,"nickName":"?","avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","gender":0,"country":"1","province":"","city":"","address_id":0,"balance":"0.00","points":0,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":1594108614,"update_time":0,"phone":"","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}]}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 1
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"user_id":14662,"open_id":"","account":"183@163.com","password":null,"nickName":"?","avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","gender":0,"country":"1","province":"","city":"","address_id":0,"balance":"0.00","points":0,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":1594108614,"update_time":0,"phone":"","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * user_id : 14662
                 * open_id :
                 * account : 183@163.com
                 * password : null
                 * nickName : ?
                 * avatarUrl : https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132
                 * gender : 0
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
                 * is_delete : 0
                 * wxapp_id : 10001
                 * create_time : 1594108614
                 * update_time : 0
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
                 * address : null
                 * username : null
                 * identity : 1
                 * status : 1
                 */

                private int user_id;
                private String open_id;
                private String account;
                private Object password;
                private String nickName;
                private String avatarUrl;
                private int gender;
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
                private int create_time;
                private int update_time;
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

                public Object getPassword() {
                    return password;
                }

                public void setPassword(Object password) {
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

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
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
