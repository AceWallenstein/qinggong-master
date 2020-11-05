package com.national.qinggong.bean;

import java.util.List;

public class HomeMessagebean {

    /**
     * code : 1
     * data : {"CurrentPage":1,"totalPage":1,"list":[{"id":1,"title":"测试系统消息","content":"测试系统消息","sort":12,"is_deleted":0,"create_at":"2020-05-13 18:48:15","read":true},{"id":2,"title":"测试消息二","content":"你好，很高兴见到你","sort":0,"is_deleted":0,"create_at":"2020-05-13 20:52:12","read":true}]}
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
         * list : [{"id":1,"title":"测试系统消息","content":"测试系统消息","sort":12,"is_deleted":0,"create_at":"2020-05-13 18:48:15","read":true},{"id":2,"title":"测试消息二","content":"你好，很高兴见到你","sort":0,"is_deleted":0,"create_at":"2020-05-13 20:52:12","read":true}]
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

        public static class ListBean {
            /**
             * id : 1
             * title : 测试系统消息
             * content : 测试系统消息
             * sort : 12
             * is_deleted : 0
             * create_at : 2020-05-13 18:48:15
             * read : true
             */

            private int id;
            private String title;
            private String content;
            private int sort;
            private int is_deleted;
            private String create_at;
            private boolean read;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public boolean isRead() {
                return read;
            }

            public void setRead(boolean read) {
                this.read = read;
            }
        }
    }
}
