package com.national.qinggong.bean;

import java.io.Serializable;
import java.util.List;

public class LiveRoomTopicListBean implements Serializable{

    /**
     * code : 1
     * msg : success
     * data : {"list":[{"id":10052,"merch_id":0,"anchor_id":17,"category_id":0,"logo_image_id":19935,"video_image_id":0,"goods_ids":["10"],"name":"jdjdjj","push_url":"rtmp://113828.livepush.myqcloud.com/live/10052","live_url":"http://qingong.meiliancheng.cn/live/10052.flv","content":"","start_time":{"text":"2020-10-20 15:35","value":1603179300},"end_time":{"text":"2020-10-20 15:35","value":1603179300},"status":{"text":"已开播","value":20},"min_viewnum":0,"type":10,"like_num":0,"show_tips":0,"join_tips":"","is_delete":0,"wxapp_id":10001,"create_time":"2020-10-20 15:35:52","update_time":"2020-10-20 15:36:09","file_path":"http://qgshop.meiliancheng.cn/uploads/2020102015353027c6e4461.png","file_name":"2020102015353027c6e4461.png","file_url":"","category":null,"anchor":{"id":17,"merch_id":0,"user_id":14664,"describe":"测试主播","image_id":19898,"wxapp_id":10001,"visits":81,"create_time":"2020-09-17 13:59:54","update_time":"2020-09-17 13:59:54","user":{"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}}},{"id":10054,"merch_id":0,"anchor_id":17,"category_id":0,"logo_image_id":19949,"video_image_id":0,"goods_ids":["10","11","11"],"name":"咯咯乐克乐克","push_url":"rtmp://113828.livepush.myqcloud.com/live/10054","live_url":"http://qingong.meiliancheng.cn/live/10054.flv","content":"","start_time":{"text":"2020-11-21 14:38","value":1605940731},"end_time":{"text":"2020-11-21 14:38","value":1605940731},"status":{"text":"未开播","value":10},"min_viewnum":0,"type":10,"like_num":0,"show_tips":0,"join_tips":"","is_delete":0,"wxapp_id":10001,"create_time":"2020-10-21 14:39:14","update_time":"2020-10-21 14:39:14","file_path":"http://qgshop.meiliancheng.cn/uploads/20201021143902f99ac5459.png","file_name":"20201021143902f99ac5459.png","file_url":"","category":null,"anchor":{"id":17,"merch_id":0,"user_id":14664,"describe":"测试主播","image_id":19898,"wxapp_id":10001,"visits":81,"create_time":"2020-09-17 13:59:54","update_time":"2020-09-17 13:59:54","user":{"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}}}]}
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

    public  class DataBean implements Serializable{
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public  class ListBean implements Serializable {
            /**
             * id : 10052
             * merch_id : 0
             * anchor_id : 17
             * category_id : 0
             * logo_image_id : 19935
             * video_image_id : 0
             * goods_ids : ["10"]
             * name : jdjdjj
             * push_url : rtmp://113828.livepush.myqcloud.com/live/10052
             * live_url : http://qingong.meiliancheng.cn/live/10052.flv
             * content :
             * start_time : {"text":"2020-10-20 15:35","value":1603179300}
             * end_time : {"text":"2020-10-20 15:35","value":1603179300}
             * status : {"text":"已开播","value":20}
             * min_viewnum : 0
             * type : 10
             * like_num : 0
             * show_tips : 0
             * join_tips :
             * is_delete : 0
             * wxapp_id : 10001
             * create_time : 2020-10-20 15:35:52
             * update_time : 2020-10-20 15:36:09
             * file_path : http://qgshop.meiliancheng.cn/uploads/2020102015353027c6e4461.png
             * file_name : 2020102015353027c6e4461.png
             * file_url :
             * category : null
             * anchor : {"id":17,"merch_id":0,"user_id":14664,"describe":"测试主播","image_id":19898,"wxapp_id":10001,"visits":81,"create_time":"2020-09-17 13:59:54","update_time":"2020-09-17 13:59:54","user":{"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}}
             */

            private int id;
            private int merch_id;
            private int anchor_id;
            private int category_id;
            private int logo_image_id;
            private int video_image_id;
            private int is_check;
            private String name;
            private String push_url;
            private String live_url;
            private String content;
            private StartTimeBean start_time;
            private EndTimeBean end_time;
            private StatusBean status;
            private int min_viewnum;
            private int type;
            private int like_num;
            private int show_tips;
            private String join_tips;
            private int is_delete;
            private int wxapp_id;
            private String create_time;
            private String update_time;
            private String file_path;
            private String file_name;
            private String file_url;
            private AnchorBean anchor;
            private List<String> goods_ids;

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
            }

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

            public int getAnchor_id() {
                return anchor_id;
            }

            public void setAnchor_id(int anchor_id) {
                this.anchor_id = anchor_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getLogo_image_id() {
                return logo_image_id;
            }

            public void setLogo_image_id(int logo_image_id) {
                this.logo_image_id = logo_image_id;
            }

            public int getVideo_image_id() {
                return video_image_id;
            }

            public void setVideo_image_id(int video_image_id) {
                this.video_image_id = video_image_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPush_url() {
                return push_url;
            }

            public void setPush_url(String push_url) {
                this.push_url = push_url;
            }

            public String getLive_url() {
                return live_url;
            }

            public void setLive_url(String live_url) {
                this.live_url = live_url;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public StartTimeBean getStart_time() {
                return start_time;
            }

            public void setStart_time(StartTimeBean start_time) {
                this.start_time = start_time;
            }

            public EndTimeBean getEnd_time() {
                return end_time;
            }

            public void setEnd_time(EndTimeBean end_time) {
                this.end_time = end_time;
            }

            public StatusBean getStatus() {
                return status;
            }

            public void setStatus(StatusBean status) {
                this.status = status;
            }

            public int getMin_viewnum() {
                return min_viewnum;
            }

            public void setMin_viewnum(int min_viewnum) {
                this.min_viewnum = min_viewnum;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getLike_num() {
                return like_num;
            }

            public void setLike_num(int like_num) {
                this.like_num = like_num;
            }

            public int getShow_tips() {
                return show_tips;
            }

            public void setShow_tips(int show_tips) {
                this.show_tips = show_tips;
            }

            public String getJoin_tips() {
                return join_tips;
            }

            public void setJoin_tips(String join_tips) {
                this.join_tips = join_tips;
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

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public String getFile_name() {
                return file_name;
            }

            public void setFile_name(String file_name) {
                this.file_name = file_name;
            }

            public String getFile_url() {
                return file_url;
            }

            public void setFile_url(String file_url) {
                this.file_url = file_url;
            }


            public AnchorBean getAnchor() {
                return anchor;
            }

            public void setAnchor(AnchorBean anchor) {
                this.anchor = anchor;
            }

            public List<String> getGoods_ids() {
                return goods_ids;
            }

            public void setGoods_ids(List<String> goods_ids) {
                this.goods_ids = goods_ids;
            }

            public  class StartTimeBean implements Serializable{
                /**
                 * text : 2020-10-20 15:35
                 * value : 1603179300
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public  class EndTimeBean implements Serializable{
                /**
                 * text : 2020-10-20 15:35
                 * value : 1603179300
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public  class StatusBean implements Serializable{
                /**
                 * text : 已开播
                 * value : 20
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public  class AnchorBean implements Serializable{
                /**
                 * id : 17
                 * merch_id : 0
                 * user_id : 14664
                 * describe : 测试主播
                 * image_id : 19898
                 * wxapp_id : 10001
                 * visits : 81
                 * create_time : 2020-09-17 13:59:54
                 * update_time : 2020-09-17 13:59:54
                 * user : {"user_id":14664,"open_id":"","account":"18326949252@163.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"测试","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/202010141610573fa791876.png","gender":"未知","country":"China","province":"","city":"","address_id":0,"balance":"0.00","points":82,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-08 16:44:51","update_time":"2020-10-16 09:24:48","phone":"13625627267","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}
                 */

                private int id;
                private int merch_id;
                private int user_id;
                private String describe;
                private int image_id;
                private int wxapp_id;
                private int visits;
                private String create_time;
                private String update_time;
                private UserBean user;

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

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public  class UserBean {
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
}
