package com.national.qinggong.bean;

import java.util.List;

public class ClassRoomBean {

    /**
     * code : 1
     * data : {"CurrentPage":1,"totalPage":1,"list":[{"id":1,"pid":0,"name":"初识股票","image":"http://guofu.vshop365.cn/upload/6a/ccae81f25c6dc6916c82660b17be8e.jpg","sort":12,"is_deleted":0,"create_at":"2020-04-15 11:44:47"},{"id":5,"pid":0,"name":"进阶课程","image":"http://guofu.vshop365.cn/upload/6a/ccae81f25c6dc6916c82660b17be8e.jpg","sort":0,"is_deleted":0,"create_at":"2020-04-18 19:34:10"}]}
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
         * list : [{"id":1,"pid":0,"name":"初识股票","image":"http://guofu.vshop365.cn/upload/6a/ccae81f25c6dc6916c82660b17be8e.jpg","sort":12,"is_deleted":0,"create_at":"2020-04-15 11:44:47"},{"id":5,"pid":0,"name":"进阶课程","image":"http://guofu.vshop365.cn/upload/6a/ccae81f25c6dc6916c82660b17be8e.jpg","sort":0,"is_deleted":0,"create_at":"2020-04-18 19:34:10"}]
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
             * pid : 0
             * name : 初识股票
             * image : http://guofu.vshop365.cn/upload/6a/ccae81f25c6dc6916c82660b17be8e.jpg
             * sort : 12
             * is_deleted : 0
             * create_at : 2020-04-15 11:44:47
             */

            private int id;
            private int pid;
            private String name;
            private String image;
            private int sort;
            private int is_deleted;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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
