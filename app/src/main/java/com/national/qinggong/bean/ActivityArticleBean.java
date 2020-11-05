package com.national.qinggong.bean;

import java.io.Serializable;
import java.util.List;

public class ActivityArticleBean {

    /**
     * code : 1
     * data : {"CurrentPage":1,"totalPage":1,"list":[{"id":1,"title":"最好不超过八个字","subtitle":"最好不超过八个字","poster":"http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png","start":"2020-04-20","end":"2020-04-24","content":"<p>阿斯顿发水淀粉a<\/p>\r\n\r\n<p>阿斯顿发<\/p>\r\n\r\n<p>😊<\/p>","views":0,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"}]}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * CurrentPage : 1
         * totalPage : 1
         * list : [{"id":1,"title":"最好不超过八个字","subtitle":"最好不超过八个字","poster":"http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png","start":"2020-04-20","end":"2020-04-24","content":"<p>阿斯顿发水淀粉a<\/p>\r\n\r\n<p>阿斯顿发<\/p>\r\n\r\n<p>😊<\/p>","views":0,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"}]
         */

        private int CurrentPage;
        private int totalPage;
        private List<ListBean> list;

        public int getCurrentPage() {
            return CurrentPage;
        }

        public void setCurrentPage(int CurrentPage) {
            this.CurrentPage = CurrentPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * title : 最好不超过八个字
             * subtitle : 最好不超过八个字
             * poster : http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png
             * start : 2020-04-20
             * end : 2020-04-24
             * content : <p>阿斯顿发水淀粉a</p>

             <p>阿斯顿发</p>

             <p>😊</p>
             * views : 0
             * sort : 0
             * is_deleted : 0
             * create_at : 2020-04-20 11:41:45
             */

            private int id;
            private String title;
            private String subtitle;
            private String poster;
            private String start;
            private String end;
            private String content;
            private int views;
            private int sort;
            private int is_deleted;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}
