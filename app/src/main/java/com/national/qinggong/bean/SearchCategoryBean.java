package com.national.qinggong.bean;

import java.util.List;

public class SearchCategoryBean {

    /**
     * code : 1
     * msg : success
     * data : {"templet":{"category_style":20,"share_title":""},"list":[{"category_id":10430,"name":"Export Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:16","update_time":"2020-07-22 14:21:57","image":null,"child":[{"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:49","update_time":"2020-07-07 14:51:22","image":null}]},{"category_id":10431,"name":"Import Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:27","update_time":"2020-07-22 14:22:15","image":null,"child":[{"category_id":10431,"name":"Import Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:27","update_time":"2020-07-22 14:22:15","image":null}]},{"category_id":10433,"name":"Cross border","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-22 14:22:43","update_time":"2020-07-22 14:22:43","image":null,"child":[{"category_id":10434,"name":"AMAZON","parent_id":10433,"image_id":19897,"sort":100,"url":"www.baidu.com","wxapp_id":10001,"create_time":"2020-07-22 14:25:57","update_time":"2020-07-22 14:25:57","image":{"file_id":19897,"storage":"local","group_id":0,"file_url":"","file_name":"202007221425386394c1471.png","file_size":95934,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-22 14:25:38","file_path":"http://qingong.meiliancheng.cn/uploads/202007221425386394c1471.png"}},{"category_id":10435,"name":"EUROPEN","parent_id":10433,"image_id":19898,"sort":100,"url":"www.baidu.com","wxapp_id":10001,"create_time":"2020-07-22 14:26:37","update_time":"2020-07-22 14:26:37","image":{"file_id":19898,"storage":"local","group_id":0,"file_url":"","file_name":"2020072214262994c791113.png","file_size":81465,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-22 14:26:30","file_path":"http://qingong.meiliancheng.cn/uploads/2020072214262994c791113.png"}}]}]}
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
         * templet : {"category_style":20,"share_title":""}
         * list : [{"category_id":10430,"name":"Export Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:16","update_time":"2020-07-22 14:21:57","image":null,"child":[{"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:49","update_time":"2020-07-07 14:51:22","image":null}]},{"category_id":10431,"name":"Import Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:27","update_time":"2020-07-22 14:22:15","image":null,"child":[{"category_id":10431,"name":"Import Product","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:27","update_time":"2020-07-22 14:22:15","image":null}]},{"category_id":10433,"name":"Cross border","parent_id":0,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-22 14:22:43","update_time":"2020-07-22 14:22:43","image":null,"child":[{"category_id":10434,"name":"AMAZON","parent_id":10433,"image_id":19897,"sort":100,"url":"www.baidu.com","wxapp_id":10001,"create_time":"2020-07-22 14:25:57","update_time":"2020-07-22 14:25:57","image":{"file_id":19897,"storage":"local","group_id":0,"file_url":"","file_name":"202007221425386394c1471.png","file_size":95934,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-22 14:25:38","file_path":"http://qingong.meiliancheng.cn/uploads/202007221425386394c1471.png"}},{"category_id":10435,"name":"EUROPEN","parent_id":10433,"image_id":19898,"sort":100,"url":"www.baidu.com","wxapp_id":10001,"create_time":"2020-07-22 14:26:37","update_time":"2020-07-22 14:26:37","image":{"file_id":19898,"storage":"local","group_id":0,"file_url":"","file_name":"2020072214262994c791113.png","file_size":81465,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-07-22 14:26:30","file_path":"http://qingong.meiliancheng.cn/uploads/2020072214262994c791113.png"}}]}]
         */

        private TempletBean templet;
        private List<ListBean> list;

        public TempletBean getTemplet() {
            return templet;
        }

        public void setTemplet(TempletBean templet) {
            this.templet = templet;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class TempletBean {
            /**
             * category_style : 20
             * share_title :
             */

            private int category_style;
            private String share_title;

            public int getCategory_style() {
                return category_style;
            }

            public void setCategory_style(int category_style) {
                this.category_style = category_style;
            }

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }
        }

        public static class ListBean {
            /**
             * category_id : 10430
             * name : Export Product
             * parent_id : 0
             * image_id : 0
             * sort : 100
             * url :
             * wxapp_id : 10001
             * create_time : 2020-07-07 14:50:16
             * update_time : 2020-07-22 14:21:57
             * image : null
             * child : [{"category_id":10432,"name":"Food &amp; Beverage","parent_id":10430,"image_id":0,"sort":100,"url":"","wxapp_id":10001,"create_time":"2020-07-07 14:50:49","update_time":"2020-07-07 14:51:22","image":null}]
             */
            private boolean isSeleted = false;
            public boolean isSeleted() {
                return isSeleted;
            }

            public void setSeleted(boolean seleted) {
                isSeleted = seleted;
            }
            private int category_id;
            private String name;
            private int parent_id;
            private int image_id;
            private int sort;
            private String url;
            private int wxapp_id;
            private String create_time;
            private String update_time;
            private Object image;
            private List<ChildBean> child;

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

            public Object getImage() {
                return image;
            }

            public void setImage(Object image) {
                this.image = image;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * category_id : 10432
                 * name : Food &amp; Beverage
                 * parent_id : 10430
                 * image_id : 0
                 * sort : 100
                 * url :
                 * wxapp_id : 10001
                 * create_time : 2020-07-07 14:50:49
                 * update_time : 2020-07-07 14:51:22
                 * image : null
                 */
                private boolean isSeleted = false;
                public boolean isSeleted() {
                    return isSeleted;
                }

                public void setSeleted(boolean seleted) {
                    isSeleted = seleted;
                }
                private int category_id;
                private String name;
                private int parent_id;
                private int image_id;
                private int sort;
                private String url;
                private int wxapp_id;
                private String create_time;
                private String update_time;
                private Object image;

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

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }
            }
        }
    }
}
