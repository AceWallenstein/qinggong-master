package com.national.qinggong.bean;

import java.util.List;

public class HelpCenterBean {

    /**
     * code : 1
     * data : {"currentPage":"1","totalPage":1,"list":[{"id":22,"question":"测试问答·11","answer":"阿斯顿发送；看剖家哦几个地方阿迪舒服噶完工","create_at":"2020-05-28 17:55:21","is_deleted":0}]}
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
         * currentPage : 1
         * totalPage : 1
         * list : [{"id":22,"question":"测试问答·11","answer":"阿斯顿发送；看剖家哦几个地方阿迪舒服噶完工","create_at":"2020-05-28 17:55:21","is_deleted":0}]
         */

        private String currentPage;
        private int totalPage;
        private List<ListBean> list;

        public String getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
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
             * id : 22
             * question : 测试问答·11
             * answer : 阿斯顿发送；看剖家哦几个地方阿迪舒服噶完工
             * create_at : 2020-05-28 17:55:21
             * is_deleted : 0
             */

            private int id;
            private String question;
            private String answer;
            private String create_at;
            private int is_deleted;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }
        }
    }
}
