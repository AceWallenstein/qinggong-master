package com.national.qinggong.bean;

import java.util.List;

public class HomePageBean {

    /**
     * code : 1
     * msg : success
     * data : {"banner":[{"banner_id":28,"wxapp_id":10001,"name":"1","image_id":19895,"sort":100,"url":"","type":0,"create_time":"2020-07-07 16:35:54","update_time":"2020-07-07 16:35:54","is_delete":0,"image":{"file_id":19895,"storage":"local","group_id":0,"file_url":"","file_name":"202007071635480b9a26720.jpg","file_size":59365,"file_type":"image","extension":"jpg","is_user":0,"is_recycle":0,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/202007071635480b9a26720.jpg"}}],"hot_goods":[{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}],"new_goods":[{"goods_id":2,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Fishing floats","number":"AN146510","images":"a:1:{i:0;s:5:\"19896\";}","more_info":"a:3:{i:0;a:2:{s:1:\"k\";s:5:\"Color\";s:1:\"v\";s:32:\"yellow, white, brown, and so on.\";}i:1;a:2:{s:1:\"k\";s:4:\"Size\";s:1:\"v\";s:8:\"Any size\";}i:2;a:2:{s:1:\"k\";s:6:\"Floats\";s:1:\"v\";s:3:\"HJS\";}}","content":"&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;we have many size&amp;nbsp;floats. if you&amp;nbsp;need anything, welcome to contact me.&lt;/p&gt;&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;&lt;br style=&quot;box-sizing: border-box; color: rgb(102, 102, 102); font-family: Geneva; font-size: 16px; white-space: normal;&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","create_time":"2020-07-09 14:02:24","update_time":"2020-07-22 11:44:57","is_hot":0,"is_new":1,"is_recommend":0,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200709140112186d54848.png"},{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}],"recommend_goods":[{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}]}
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
        private List<BannerBean> banner;
        private List<HotGoodsBean> hot_goods;
        private List<NewGoodsBean> new_goods;
        private List<LiveBean> live;
        private List<PlayBackBean> playback;
        private List<AppOBean> appo;
        private List<RecommendGoodsBean> recommend_goods;

        public List<PlayBackBean> getPlayback() {
            return playback;
        }

        public List<AppOBean> getAppo() {
            return appo;
        }

        public void setLive(List<LiveBean> live) {
            this.live = live;
        }

        public void setPlayback(List<PlayBackBean> playback) {
            this.playback = playback;
        }

        public void setAppo(List<AppOBean> appo) {
            this.appo = appo;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<HotGoodsBean> getHot_goods() {
            return hot_goods;
        }

        public void setHot_goods(List<HotGoodsBean> hot_goods) {
            this.hot_goods = hot_goods;
        }

        public List<NewGoodsBean> getNew_goods() {
            return new_goods;
        }

        public void setNew_goods(List<NewGoodsBean> new_goods) {
            this.new_goods = new_goods;
        }

        public List<RecommendGoodsBean> getRecommend_goods() {
            return recommend_goods;
        }

        public void setRecommend_goods(List<RecommendGoodsBean> recommend_goods) {
            this.recommend_goods = recommend_goods;
        }

        public List<LiveBean> getLive() {
            return live;
        }

        public static class LiveBean {
            private int id;
            private int merch_id;
            private int anchor_id;
            private int category_id;
            private int logo_image_id;
            private int video_image_id;
            private String name;
            private String push_url;
            private String live_url;
            private String content;
            private LiveRoomListBean.DataBean.ListBean.StartTimeBean start_time;
            private LiveRoomListBean.DataBean.ListBean.EndTimeBean end_time;
            private LiveRoomListBean.DataBean.ListBean.StatusBean status;
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
            private boolean view_num;
            private String rtmp;
            private String HLS;
            private LiveRoomListBean.DataBean.ListBean.CategoryBean category;
            private LiveRoomListBean.DataBean.ListBean.AnchorBean anchor;
            private List<String> goods_ids;

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

            public LiveRoomListBean.DataBean.ListBean.StartTimeBean getStart_time() {
                return start_time;
            }

            public void setStart_time(LiveRoomListBean.DataBean.ListBean.StartTimeBean start_time) {
                this.start_time = start_time;
            }

            public LiveRoomListBean.DataBean.ListBean.EndTimeBean getEnd_time() {
                return end_time;
            }

            public void setEnd_time(LiveRoomListBean.DataBean.ListBean.EndTimeBean end_time) {
                this.end_time = end_time;
            }

            public LiveRoomListBean.DataBean.ListBean.StatusBean getStatus() {
                return status;
            }

            public void setStatus(LiveRoomListBean.DataBean.ListBean.StatusBean status) {
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

            public boolean isView_num() {
                return view_num;
            }

            public void setView_num(boolean view_num) {
                this.view_num = view_num;
            }

            public String getRtmp() {
                return rtmp;
            }

            public void setRtmp(String rtmp) {
                this.rtmp = rtmp;
            }

            public String getHLS() {
                return HLS;
            }

            public void setHLS(String HLS) {
                this.HLS = HLS;
            }

            public LiveRoomListBean.DataBean.ListBean.CategoryBean getCategory() {
                return category;
            }

            public void setCategory(LiveRoomListBean.DataBean.ListBean.CategoryBean category) {
                this.category = category;
            }

            public LiveRoomListBean.DataBean.ListBean.AnchorBean getAnchor() {
                return anchor;
            }

            public void setAnchor(LiveRoomListBean.DataBean.ListBean.AnchorBean anchor) {
                this.anchor = anchor;
            }

            public List<String> getGoods_ids() {
                return goods_ids;
            }

            public void setGoods_ids(List<String> goods_ids) {
                this.goods_ids = goods_ids;
            }

            public static class StartTimeBean {
                /**
                 * text : 2020-10-17 17:22
                 * value : 1602926520
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

            public static class EndTimeBean {
                /**
                 * text : 2020-10-17 18:22
                 * value : 1602930120
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

            public static class StatusBean {
                /**
                 * text : 未开播
                 * value : 10
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

            public static class CategoryBean {
                /**
                 * id : 5
                 * name : 测试分类2
                 * wxapp_id : 10001
                 * create_time : 2020-09-11 17:26:04
                 * update_time : 2020-09-17 14:00:54
                 */

                private int id;
                private String name;
                private int wxapp_id;
                private String create_time;
                private String update_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
            }

            public static class AnchorBean {
                /**
                 * id : 20
                 * merch_id : 0
                 * user_id : 14667
                 * describe : 测试13
                 * image_id : 19905
                 * wxapp_id : 10001
                 * visits : 2
                 * create_time : 2020-10-17 17:22:14
                 * update_time : 2020-10-17 17:22:14
                 * user : {"user_id":14667,"open_id":"","account":"1169709606@qq.com","password":"e10adc3949ba59abbe56e057f20f883e","nickName":"Ceshi","avatarUrl":"http://qgshop.meiliancheng.cn/uploads/20201013111945c84624663.png","gender":"未知","country":"China","province":"","city":"","address_id":12019,"balance":"0.00","points":6223,"pay_money":"0.00","expend_money":"0.00","grade_id":0,"is_new_user":1,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-13 10:39:42","update_time":"2020-10-17 17:18:59","phone":"15155555555","fid":0,"mid":1,"role":null,"smile":0,"weixin":null,"job":null,"source_name":null,"source_text":null,"source_cardid":null,"lasttime":null,"group_tags":null,"company":null,"position":null,"source_type":1,"scene":null,"source_id":null,"phonemodel":null,"address":null,"username":null,"identity":1,"status":1}
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
                private LiveRoomListBean.DataBean.ListBean.AnchorBean.UserBean user;

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

                public LiveRoomListBean.DataBean.ListBean.AnchorBean.UserBean getUser() {
                    return user;
                }

                public void setUser(LiveRoomListBean.DataBean.ListBean.AnchorBean.UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    /**
                     * user_id : 14667
                     * open_id :
                     * account : 1169709606@qq.com
                     * password : e10adc3949ba59abbe56e057f20f883e
                     * nickName : Ceshi
                     * avatarUrl : http://qgshop.meiliancheng.cn/uploads/20201013111945c84624663.png
                     * gender : 未知
                     * country : China
                     * province :
                     * city :
                     * address_id : 12019
                     * balance : 0.00
                     * points : 6223
                     * pay_money : 0.00
                     * expend_money : 0.00
                     * grade_id : 0
                     * is_new_user : 1
                     * is_delete : 0
                     * wxapp_id : 10001
                     * create_time : 2020-10-13 10:39:42
                     * update_time : 2020-10-17 17:18:59
                     * phone : 15155555555
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

        public static class BannerBean {
            /**
             * banner_id : 28
             * wxapp_id : 10001
             * name : 1
             * image_id : 19895
             * sort : 100
             * url :
             * type : 0
             * create_time : 2020-07-07 16:35:54
             * update_time : 2020-07-07 16:35:54
             * is_delete : 0
             * image : {"file_id":19895,"storage":"local","group_id":0,"file_url":"","file_name":"202007071635480b9a26720.jpg","file_size":59365,"file_type":"image","extension":"jpg","is_user":0,"is_recycle":0,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/202007071635480b9a26720.jpg"}
             */

            private int banner_id;
            private int wxapp_id;
            private String name;
            private int image_id;
            private int sort;
            private String url;
            private int type;
            private String create_time;
            private String update_time;
            private int is_delete;
            private ImageBean image;

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public ImageBean getImage() {
                return image;
            }

            public void setImage(ImageBean image) {
                this.image = image;
            }

            public static class ImageBean {
                /**
                 * file_id : 19895
                 * storage : local
                 * group_id : 0
                 * file_url :
                 * file_name : 202007071635480b9a26720.jpg
                 * file_size : 59365
                 * file_type : image
                 * extension : jpg
                 * is_user : 0
                 * is_recycle : 0
                 * is_delete : 0
                 * file_path : http://qingong.meiliancheng.cn/uploads/202007071635480b9a26720.jpg
                 */

                private int file_id;
                private String storage;
                private int group_id;
                private String file_url;
                private String file_name;
                private int file_size;
                private String file_type;
                private String extension;
                private int is_user;
                private int is_recycle;
                private int is_delete;
                private String file_path;

                public int getFile_id() {
                    return file_id;
                }

                public void setFile_id(int file_id) {
                    this.file_id = file_id;
                }

                public String getStorage() {
                    return storage;
                }

                public void setStorage(String storage) {
                    this.storage = storage;
                }

                public int getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

                public String getFile_url() {
                    return file_url;
                }

                public void setFile_url(String file_url) {
                    this.file_url = file_url;
                }

                public String getFile_name() {
                    return file_name;
                }

                public void setFile_name(String file_name) {
                    this.file_name = file_name;
                }

                public int getFile_size() {
                    return file_size;
                }

                public void setFile_size(int file_size) {
                    this.file_size = file_size;
                }

                public String getFile_type() {
                    return file_type;
                }

                public void setFile_type(String file_type) {
                    this.file_type = file_type;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }

                public int getIs_user() {
                    return is_user;
                }

                public void setIs_user(int is_user) {
                    this.is_user = is_user;
                }

                public int getIs_recycle() {
                    return is_recycle;
                }

                public void setIs_recycle(int is_recycle) {
                    this.is_recycle = is_recycle;
                }

                public int getIs_delete() {
                    return is_delete;
                }

                public void setIs_delete(int is_delete) {
                    this.is_delete = is_delete;
                }

                public String getFile_path() {
                    return file_path;
                }

                public void setFile_path(String file_path) {
                    this.file_path = file_path;
                }
            }
        }

        public static class PlayBackBean {

            /**
             * id : 6
             * name :
             * file_id : 5285890808740910837
             * file_format : mp4
             * start_time : {"text":"2020-10-16 18:55:52","value":1602845752}
             * end_time : {"text":"2020-10-16 18:57:16","value":1602845836}
             * duration : 86
             * show_video : null
             * file_size : 9764382
             * stream_param :
             * video_url : http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/5701af515285890808740910837/f0.mp4
             * stream_id : 10044
             * image_id : 0
             * video_status : 0
             * is_delete : 0
             * wxapp_id : 10001
             * create_time : 2020-10-16 18:57:18
             * update_time : 2020-10-16 18:57:18
             * file_path : null
             * file_name : null
             * file_url : null
             */

            private int id;
            private String name;
            private String file_id;
            private String file_format;
            private String start_time;
            private String end_time;
            private int duration;
            private Object show_video;
            private int file_size;
            private String stream_param;
            private String video_url;
            private int stream_id;
            private int image_id;
            private int video_status;
            private int is_delete;
            private int wxapp_id;
            private String create_time;
            private String update_time;
            private Object file_path;
            private Object file_name;
            private Object file_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public String getFile_format() {
                return file_format;
            }

            public void setFile_format(String file_format) {
                this.file_format = file_format;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public Object getShow_video() {
                return show_video;
            }

            public void setShow_video(Object show_video) {
                this.show_video = show_video;
            }

            public int getFile_size() {
                return file_size;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }

            public String getStream_param() {
                return stream_param;
            }

            public void setStream_param(String stream_param) {
                this.stream_param = stream_param;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public int getStream_id() {
                return stream_id;
            }

            public void setStream_id(int stream_id) {
                this.stream_id = stream_id;
            }

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public int getVideo_status() {
                return video_status;
            }

            public void setVideo_status(int video_status) {
                this.video_status = video_status;
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

            public Object getFile_path() {
                return file_path;
            }

            public void setFile_path(Object file_path) {
                this.file_path = file_path;
            }

            public Object getFile_name() {
                return file_name;
            }

            public void setFile_name(Object file_name) {
                this.file_name = file_name;
            }

            public Object getFile_url() {
                return file_url;
            }

            public void setFile_url(Object file_url) {
                this.file_url = file_url;
            }

            public static class StartTimeBean {
                /**
                 * text : 2020-10-16 18:55:52
                 * value : 1602845752
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

            public static class EndTimeBean {
                /**
                 * text : 2020-10-16 18:57:16
                 * value : 1602845836
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
        }
        public static class AppOBean{

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
             * status : {"text":"未开播","value":10}
             * is_check : 0
             * min_viewnum : 0
             * type : 10
             * like_num : 0
             * show_tips : 0
             * join_tips :
             * is_delete : 0
             * wxapp_id : 10001
             * create_time : 2020-10-20 15:35:52
             * update_time : 2020-10-21 15:55:46
             * file_path : http://qgshop.meiliancheng.cn/uploads/2020102015353027c6e4461.png
             * file_name : 2020102015353027c6e4461.png
             * file_url :
             */

            private int id;
            private int merch_id;
            private int anchor_id;
            private int category_id;
            private int logo_image_id;
            private int video_image_id;
            private String name;
            private String push_url;
            private String live_url;
            private String content;
            private StartTimeBean start_time;
            private EndTimeBean end_time;
            private StatusBean status;
            private int is_check;
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
            private List<String> goods_ids;

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

            public int getIs_check() {
                return is_check;
            }

            public void setIs_check(int is_check) {
                this.is_check = is_check;
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

            public List<String> getGoods_ids() {
                return goods_ids;
            }

            public void setGoods_ids(List<String> goods_ids) {
                this.goods_ids = goods_ids;
            }

            public static class StartTimeBean {
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

            public static class EndTimeBean {
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

            public static class StatusBean {
                /**
                 * text : 未开播
                 * value : 10
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
        }

        public static class HotGoodsBean {
            /**
             * goods_id : 1
             * user_id : 14662
             * category_id : 10432
             * wxapp_id : 10001
             * goods_name : Vegetable shredder
             * number : XY-708
             * images : a:1:{i:0;s:5:"19893";}
             * more_info : a:2:{i:0;a:2:{s:1:"k";s:9:"Item size";s:1:"v";s:21:"12.5*9.3cm/500ml/195g";}i:1;a:2:{s:1:"k";s:8:"material";s:1:"v";s:12:"PP+ABS+201ss";}}
             * content : &lt;p&gt;12312312312&lt;/p&gt;
             * create_time : 2020-07-07 15:00:20
             * update_time : 2020-07-22 11:42:36
             * is_hot : 1
             * is_new : 1
             * is_recommend : 1
             * status : 1
             * is_delete : 0
             * file_path : http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png
             */

            private int goods_id;
            private int user_id;
            private int category_id;
            private int wxapp_id;
            private String goods_name;
            private String number;
            private String images;
            private String more_info;
            private String content;
            private String create_time;
            private String update_time;
            private int is_hot;
            private int is_new;
            private int is_recommend;
            private int status;
            private int is_delete;
            private String file_path;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getMore_info() {
                return more_info;
            }

            public void setMore_info(String more_info) {
                this.more_info = more_info;
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

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
        }

        public static class NewGoodsBean {
            /**
             * goods_id : 2
             * user_id : 14662
             * category_id : 10432
             * wxapp_id : 10001
             * goods_name : Fishing floats
             * number : AN146510
             * images : a:1:{i:0;s:5:"19896";}
             * more_info : a:3:{i:0;a:2:{s:1:"k";s:5:"Color";s:1:"v";s:32:"yellow, white, brown, and so on.";}i:1;a:2:{s:1:"k";s:4:"Size";s:1:"v";s:8:"Any size";}i:2;a:2:{s:1:"k";s:6:"Floats";s:1:"v";s:3:"HJS";}}
             * content : &lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;we have many size&amp;nbsp;floats. if you&amp;nbsp;need anything, welcome to contact me.&lt;/p&gt;&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;&lt;br style=&quot;box-sizing: border-box; color: rgb(102, 102, 102); font-family: Geneva; font-size: 16px; white-space: normal;&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;
             * create_time : 2020-07-09 14:02:24
             * update_time : 2020-07-22 11:44:57
             * is_hot : 0
             * is_new : 1
             * is_recommend : 0
             * status : 1
             * is_delete : 0
             * file_path : http://qingong.meiliancheng.cn//uploads/20200709140112186d54848.png
             */

            private int goods_id;
            private int user_id;
            private int category_id;
            private int wxapp_id;
            private String goods_name;
            private String number;
            private String images;
            private String more_info;
            private String content;
            private String create_time;
            private String update_time;
            private int is_hot;
            private int is_new;
            private int is_recommend;
            private int status;
            private int is_delete;
            private String file_path;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getMore_info() {
                return more_info;
            }

            public void setMore_info(String more_info) {
                this.more_info = more_info;
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

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
        }

        public static class RecommendGoodsBean {
            /**
             * goods_id : 1
             * user_id : 14662
             * category_id : 10432
             * wxapp_id : 10001
             * goods_name : Vegetable shredder
             * number : XY-708
             * images : a:1:{i:0;s:5:"19893";}
             * more_info : a:2:{i:0;a:2:{s:1:"k";s:9:"Item size";s:1:"v";s:21:"12.5*9.3cm/500ml/195g";}i:1;a:2:{s:1:"k";s:8:"material";s:1:"v";s:12:"PP+ABS+201ss";}}
             * content : &lt;p&gt;12312312312&lt;/p&gt;
             * create_time : 2020-07-07 15:00:20
             * update_time : 2020-07-22 11:42:36
             * is_hot : 1
             * is_new : 1
             * is_recommend : 1
             * status : 1
             * is_delete : 0
             * file_path : http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png
             */

            private int goods_id;
            private int user_id;
            private int category_id;
            private int wxapp_id;
            private String goods_name;
            private String number;
            private String images;
            private String more_info;
            private String content;
            private String create_time;
            private String update_time;
            private int is_hot;
            private int is_new;
            private int is_recommend;
            private int status;
            private int is_delete;
            private String file_path;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getMore_info() {
                return more_info;
            }

            public void setMore_info(String more_info) {
                this.more_info = more_info;
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

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
        }
    }
}
