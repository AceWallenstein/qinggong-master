package com.national.qinggong.bean;

import java.util.List;

public class CarBean {

    /**
     * code : 1
     * msg : success
     * data : {"list":{"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":5,"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":1594105220,"update_time":1595389356,"is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200707145218d03160914.png"},{"id":6,"goods_id":2,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Fishing floats","number":"AN146510","images":"a:1:{i:0;s:5:\"19896\";}","more_info":"a:3:{i:0;a:2:{s:1:\"k\";s:5:\"Color\";s:1:\"v\";s:32:\"yellow, white, brown, and so on.\";}i:1;a:2:{s:1:\"k\";s:4:\"Size\";s:1:\"v\";s:8:\"Any size\";}i:2;a:2:{s:1:\"k\";s:6:\"Floats\";s:1:\"v\";s:3:\"HJS\";}}","content":"&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;we have many size&amp;nbsp;floats. if you&amp;nbsp;need anything, welcome to contact me.&lt;/p&gt;&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;&lt;br style=&quot;box-sizing: border-box; color: rgb(102, 102, 102); font-family: Geneva; font-size: 16px; white-space: normal;&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","create_time":1594274544,"update_time":1595389497,"is_hot":0,"is_new":1,"is_recommend":0,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200709140112186d54848.png"}]}}
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
         * list : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":5,"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":1594105220,"update_time":1595389356,"is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200707145218d03160914.png"},{"id":6,"goods_id":2,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Fishing floats","number":"AN146510","images":"a:1:{i:0;s:5:\"19896\";}","more_info":"a:3:{i:0;a:2:{s:1:\"k\";s:5:\"Color\";s:1:\"v\";s:32:\"yellow, white, brown, and so on.\";}i:1;a:2:{s:1:\"k\";s:4:\"Size\";s:1:\"v\";s:8:\"Any size\";}i:2;a:2:{s:1:\"k\";s:6:\"Floats\";s:1:\"v\";s:3:\"HJS\";}}","content":"&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;we have many size&amp;nbsp;floats. if you&amp;nbsp;need anything, welcome to contact me.&lt;/p&gt;&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;&lt;br style=&quot;box-sizing: border-box; color: rgb(102, 102, 102); font-family: Geneva; font-size: 16px; white-space: normal;&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","create_time":1594274544,"update_time":1595389497,"is_hot":0,"is_new":1,"is_recommend":0,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200709140112186d54848.png"}]}
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
             * total : 2
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"id":5,"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":1594105220,"update_time":1595389356,"is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200707145218d03160914.png"},{"id":6,"goods_id":2,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Fishing floats","number":"AN146510","images":"a:1:{i:0;s:5:\"19896\";}","more_info":"a:3:{i:0;a:2:{s:1:\"k\";s:5:\"Color\";s:1:\"v\";s:32:\"yellow, white, brown, and so on.\";}i:1;a:2:{s:1:\"k\";s:4:\"Size\";s:1:\"v\";s:8:\"Any size\";}i:2;a:2:{s:1:\"k\";s:6:\"Floats\";s:1:\"v\";s:3:\"HJS\";}}","content":"&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;we have many size&amp;nbsp;floats. if you&amp;nbsp;need anything, welcome to contact me.&lt;/p&gt;&lt;p style=&quot;box-sizing: border-box; margin-bottom: 10px;&quot;&gt;&lt;br style=&quot;box-sizing: border-box; color: rgb(102, 102, 102); font-family: Geneva; font-size: 16px; white-space: normal;&quot;/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;","create_time":1594274544,"update_time":1595389497,"is_hot":0,"is_new":1,"is_recommend":0,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn/uploads/20200709140112186d54848.png"}]
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
                 * id : 5
                 * goods_id : 1
                 * user_id : 14662
                 * category_id : 10432
                 * wxapp_id : 10001
                 * goods_name : Vegetable shredder
                 * number : XY-708
                 * images : a:1:{i:0;s:5:"19893";}
                 * more_info : a:2:{i:0;a:2:{s:1:"k";s:9:"Item size";s:1:"v";s:21:"12.5*9.3cm/500ml/195g";}i:1;a:2:{s:1:"k";s:8:"material";s:1:"v";s:12:"PP+ABS+201ss";}}
                 * content : &lt;p&gt;12312312312&lt;/p&gt;
                 * create_time : 1594105220
                 * update_time : 1595389356
                 * is_hot : 1
                 * is_new : 1
                 * is_recommend : 1
                 * status : 1
                 * is_delete : 0
                 * file_path : http://qingong.meiliancheng.cn/uploads/20200707145218d03160914.png
                 */

                private int id;
                private int goods_id;
                private int user_id;
                private int category_id;
                private int wxapp_id;
                private String goods_name;
                private String number;
                private String images;
                private String more_info;
                private String content;
                private int create_time;
                private int update_time;
                private int is_hot;
                private int is_new;
                private int is_recommend;
                private int status;
                private int is_delete;
                private String file_path;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

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
}
