package com.national.qinggong.bean;

import java.util.List;

public class ShopDetailBean {

    /**
     * code : 1
     * msg : success
     * data : {"detail":{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":[{"k":"Item size","v":"12.5*9.3cm/500ml/195g"},{"k":"material","v":"PP+ABS+201ss"}],"content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"image":[{"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png","image_id":"19893"}],"category":{"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","create_time":"2020-07-07 14:50:49"}},"saleman":{"nickName":"?","avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","account":"183@163.com","user_id":14662,"phone":"","is_like":1},"list":{"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}]}}
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
         * detail : {"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":[{"k":"Item size","v":"12.5*9.3cm/500ml/195g"},{"k":"material","v":"PP+ABS+201ss"}],"content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"image":[{"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png","image_id":"19893"}],"category":{"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","create_time":"2020-07-07 14:50:49"}}
         * saleman : {"nickName":"?","avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","account":"183@163.com","user_id":14662,"phone":"","is_like":1}
         * list : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}]}
         */

        private DetailBean detail;
        private SalemanBean saleman;
        private ListBean list;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public SalemanBean getSaleman() {
            return saleman;
        }

        public void setSaleman(SalemanBean saleman) {
            this.saleman = saleman;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class DetailBean {
            /**
             * goods_id : 1
             * user_id : 14662
             * category_id : 10432
             * wxapp_id : 10001
             * goods_name : Vegetable shredder
             * number : XY-708
             * images : a:1:{i:0;s:5:"19893";}
             * more_info : [{"k":"Item size","v":"12.5*9.3cm/500ml/195g"},{"k":"material","v":"PP+ABS+201ss"}]
             * content : &lt;p&gt;12312312312&lt;/p&gt;
             * create_time : 2020-07-07 15:00:20
             * update_time : 2020-07-22 11:42:36
             * is_hot : 1
             * is_new : 1
             * is_recommend : 1
             * status : 1
             * is_delete : 0
             * image : [{"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png","image_id":"19893"}]
             * category : {"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","create_time":"2020-07-07 14:50:49"}
             */

            private int goods_id;
            private int user_id;
            private int category_id;
            private int wxapp_id;
            private String goods_name;
            private String number;
            private String images;
            private String content;
            private String create_time;
            private String update_time;
            private int is_hot;
            private int is_new;
            private int is_recommend;
            private int status;
            private int is_delete;
            private CategoryBean category;
            private List<MoreInfoBean> more_info;
            private List<ImageBean> image;

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

            public CategoryBean getCategory() {
                return category;
            }

            public void setCategory(CategoryBean category) {
                this.category = category;
            }

            public List<MoreInfoBean> getMore_info() {
                return more_info;
            }

            public void setMore_info(List<MoreInfoBean> more_info) {
                this.more_info = more_info;
            }

            public List<ImageBean> getImage() {
                return image;
            }

            public void setImage(List<ImageBean> image) {
                this.image = image;
            }

            public static class CategoryBean {
                /**
                 * category_id : 10432
                 * name : Food &amp; Beverage
                 * parent_id : 10430
                 * image_id : 0
                 * sort : 100
                 * url :
                 * create_time : 2020-07-07 14:50:49
                 */

                private int category_id;
                private String name;
                private int parent_id;
                private int image_id;
                private int sort;
                private String url;
                private String create_time;

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
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

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }

            public static class MoreInfoBean {
                /**
                 * k : Item size
                 * v : 12.5*9.3cm/500ml/195g
                 */

                private String k;
                private String v;

                public String getK() {
                    return k;
                }

                public void setK(String k) {
                    this.k = k;
                }

                public String getV() {
                    return v;
                }

                public void setV(String v) {
                    this.v = v;
                }
            }

            public static class ImageBean {
                /**
                 * file_path : http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png
                 * image_id : 19893
                 */

                private String file_path;
                private String image_id;

                public String getFile_path() {
                    return file_path;
                }

                public void setFile_path(String file_path) {
                    this.file_path = file_path;
                }

                public String getImage_id() {
                    return image_id;
                }

                public void setImage_id(String image_id) {
                    this.image_id = image_id;
                }
            }
        }

        public static class SalemanBean {
            /**
             * nickName : ?
             * avatarUrl : https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132
             * account : 183@163.com
             * user_id : 14662
             * phone :
             * is_like : 1
             */

            private String nickName;
            private String avatarUrl;
            private String account;
            private int user_id;
            private String phone;
            private int is_like;
            private String anchor_id;

            public String getAnchor_id() {
                return anchor_id;
            }

            public void setAnchor_id(String anchor_id) {
                this.anchor_id = anchor_id;
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

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }
        }

        public static class ListBean {
            /**
             * total : 1
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"goods_id":1,"user_id":14662,"category_id":10432,"wxapp_id":10001,"goods_name":"Vegetable shredder","number":"XY-708","images":"a:1:{i:0;s:5:\"19893\";}","more_info":"a:2:{i:0;a:2:{s:1:\"k\";s:9:\"Item size\";s:1:\"v\";s:21:\"12.5*9.3cm/500ml/195g\";}i:1;a:2:{s:1:\"k\";s:8:\"material\";s:1:\"v\";s:12:\"PP+ABS+201ss\";}}","content":"&lt;p&gt;12312312312&lt;/p&gt;","create_time":"2020-07-07 15:00:20","update_time":"2020-07-22 11:42:36","is_hot":1,"is_new":1,"is_recommend":1,"status":1,"is_delete":0,"file_path":"http://qingong.meiliancheng.cn//uploads/20200707145218d03160914.png"}]
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
}
