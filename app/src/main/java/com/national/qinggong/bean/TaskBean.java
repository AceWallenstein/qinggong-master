package com.national.qinggong.bean;

import java.util.List;

public class TaskBean {

    /**
     * code : 1
     * data : {"tasks":[{"title":"完成实名认证，账户安全有保障","subtitle":"增加认证奖励","status":0},{"title":"开户有好礼","subtitle":"增加开户奖励","status":0},{"title":"了解国富民丰","subtitle":"赶快认证","status":0},{"title":"手机号注册","subtitle":"赶快注册","status":1},{"title":"参与新手活动","subtitle":"赶快参加","status":0}]}
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
        private List<TasksBean> tasks;

        public List<TasksBean> getTasks() {
            return tasks;
        }

        public void setTasks(List<TasksBean> tasks) {
            this.tasks = tasks;
        }

        public static class TasksBean {
            /**
             * title : 完成实名认证，账户安全有保障
             * subtitle : 增加认证奖励
             * status : 0
             */

            private String title;
            private int id;
            private String subtitle;
            private int status;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
