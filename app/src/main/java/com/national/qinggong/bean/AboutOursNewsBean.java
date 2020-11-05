package com.national.qinggong.bean;

import java.util.List;

public class AboutOursNewsBean {

    /**
     * code : 1
     * msg : success
     * data : {"list":{"total":4,"per_page":15,"current_page":1,"last_page":1,"data":[{"article_id":10018,"article_title":"aboutus","show_type":10,"category_id":10011,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10011,"name":"Brand","sort":100,"wxapp_id":10001,"create_time":"2020-07-08 15:30:52","update_time":"2020-07-08 15:30:52"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10017,"article_title":"Honor","show_type":10,"category_id":10013,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10013,"name":"Honor","sort":100,"wxapp_id":10001,"create_time":"2020-07-09 15:55:32","update_time":"2020-07-09 15:55:32"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10016,"article_title":"THE 127TH CANTON FAIR OPENS ONLINE","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":1,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":1,"view_time":"2020-07-08"},{"article_id":10015,"article_title":"BELARUS CONSUL-GENERAL VISITED ALIC","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":0,"view_time":"2020-07-08"}]}}
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
         * list : {"total":4,"per_page":15,"current_page":1,"last_page":1,"data":[{"article_id":10018,"article_title":"aboutus","show_type":10,"category_id":10011,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10011,"name":"Brand","sort":100,"wxapp_id":10001,"create_time":"2020-07-08 15:30:52","update_time":"2020-07-08 15:30:52"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10017,"article_title":"Honor","show_type":10,"category_id":10013,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10013,"name":"Honor","sort":100,"wxapp_id":10001,"create_time":"2020-07-09 15:55:32","update_time":"2020-07-09 15:55:32"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10016,"article_title":"THE 127TH CANTON FAIR OPENS ONLINE","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":1,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":1,"view_time":"2020-07-08"},{"article_id":10015,"article_title":"BELARUS CONSUL-GENERAL VISITED ALIC","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":0,"view_time":"2020-07-08"}]}
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
             * total : 4
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"article_id":10018,"article_title":"aboutus","show_type":10,"category_id":10011,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10011,"name":"Brand","sort":100,"wxapp_id":10001,"create_time":"2020-07-08 15:30:52","update_time":"2020-07-08 15:30:52"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10017,"article_title":"Honor","show_type":10,"category_id":10013,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10013,"name":"Honor","sort":100,"wxapp_id":10001,"create_time":"2020-07-09 15:55:32","update_time":"2020-07-09 15:55:32"},"show_views":0,"view_time":"2020-07-09"},{"article_id":10016,"article_title":"THE 127TH CANTON FAIR OPENS ONLINE","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":1,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":1,"view_time":"2020-07-08"},{"article_id":10015,"article_title":"BELARUS CONSUL-GENERAL VISITED ALIC","show_type":10,"category_id":10001,"image_id":0,"article_sort":100,"article_status":1,"virtual_views":0,"actual_views":0,"image":null,"category":{"category_id":10001,"name":"News","sort":100,"wxapp_id":10001,"create_time":"2019-07-29 14:45:04","update_time":"2020-07-08 15:30:30"},"show_views":0,"view_time":"2020-07-08"}]
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
                 * article_id : 10018
                 * article_title : aboutus
                 * show_type : 10
                 * category_id : 10011
                 * image_id : 0
                 * article_sort : 100
                 * article_status : 1
                 * virtual_views : 0
                 * actual_views : 0
                 * image : null
                 * category : {"category_id":10011,"name":"Brand","sort":100,"wxapp_id":10001,"create_time":"2020-07-08 15:30:52","update_time":"2020-07-08 15:30:52"}
                 * show_views : 0
                 * view_time : 2020-07-09
                 */

                private int article_id;
                private String article_title;
                private int show_type;
                private int category_id;
                private int image_id;
                private int article_sort;
                private int article_status;
                private int virtual_views;
                private int actual_views;
                private Object image;
                private CategoryBean category;
                private int show_views;
                private String view_time;

                public int getArticle_id() {
                    return article_id;
                }

                public void setArticle_id(int article_id) {
                    this.article_id = article_id;
                }

                public String getArticle_title() {
                    return article_title;
                }

                public void setArticle_title(String article_title) {
                    this.article_title = article_title;
                }

                public int getShow_type() {
                    return show_type;
                }

                public void setShow_type(int show_type) {
                    this.show_type = show_type;
                }

                public int getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(int category_id) {
                    this.category_id = category_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public int getArticle_sort() {
                    return article_sort;
                }

                public void setArticle_sort(int article_sort) {
                    this.article_sort = article_sort;
                }

                public int getArticle_status() {
                    return article_status;
                }

                public void setArticle_status(int article_status) {
                    this.article_status = article_status;
                }

                public int getVirtual_views() {
                    return virtual_views;
                }

                public void setVirtual_views(int virtual_views) {
                    this.virtual_views = virtual_views;
                }

                public int getActual_views() {
                    return actual_views;
                }

                public void setActual_views(int actual_views) {
                    this.actual_views = actual_views;
                }

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }

                public CategoryBean getCategory() {
                    return category;
                }

                public void setCategory(CategoryBean category) {
                    this.category = category;
                }

                public int getShow_views() {
                    return show_views;
                }

                public void setShow_views(int show_views) {
                    this.show_views = show_views;
                }

                public String getView_time() {
                    return view_time;
                }

                public void setView_time(String view_time) {
                    this.view_time = view_time;
                }

                public static class CategoryBean {
                    /**
                     * category_id : 10011
                     * name : Brand
                     * sort : 100
                     * wxapp_id : 10001
                     * create_time : 2020-07-08 15:30:52
                     * update_time : 2020-07-08 15:30:52
                     */

                    private int category_id;
                    private String name;
                    private int sort;
                    private int wxapp_id;
                    private String create_time;
                    private String update_time;

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

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
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
            }
        }
    }
}
