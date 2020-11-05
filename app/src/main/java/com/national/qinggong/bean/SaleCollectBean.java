package com.national.qinggong.bean;

import java.util.List;

public class SaleCollectBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"list":[{"user_id":14662,"avatarUrl":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","nickName":"?","account":"183@163.com","phone":""}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * user_id : 14662
             * avatarUrl : https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132
             * nickName : ?
             * account : 183@163.com
             * phone :
             */

            private int user_id;
            private String avatarUrl;
            private String nickName;
            private String account;
            private String phone;
            private String anchor_id;

            public String getAnchor_id() {
                return anchor_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
