package com.national.qinggong.bean;

import java.util.List;

public class ChatConsultBean {

    /**
     * code : 1
     * msg : success
     * data : {"list":{"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"chat_id":2,"user_id":14662,"to_user_id":14663,"content":"Good morning","type":"text","create_time":"2020-07-07 18:43:34","is_read":1,"user_avatar":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","to_user_avatar":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELiaTCR593LeScrg0B3fKWpv9icoD6pBmcGY9htf1pacVAeftH0UVKUBjZianqLAP4qgT6jBnbpIdSlQ/132"}]},"to_user":{"user_id":14662,"nickName":"?"}}
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
         * list : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"chat_id":2,"user_id":14662,"to_user_id":14663,"content":"Good morning","type":"text","create_time":"2020-07-07 18:43:34","is_read":1,"user_avatar":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","to_user_avatar":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELiaTCR593LeScrg0B3fKWpv9icoD6pBmcGY9htf1pacVAeftH0UVKUBjZianqLAP4qgT6jBnbpIdSlQ/132"}]}
         * to_user : {"user_id":14662,"nickName":"?"}
         */

        private ListBean list;
        private ToUserBean to_user;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public ToUserBean getTo_user() {
            return to_user;
        }

        public void setTo_user(ToUserBean to_user) {
            this.to_user = to_user;
        }

        public static class ListBean {
            /**
             * total : 1
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"chat_id":2,"user_id":14662,"to_user_id":14663,"content":"Good morning","type":"text","create_time":"2020-07-07 18:43:34","is_read":1,"user_avatar":"https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132","to_user_avatar":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELiaTCR593LeScrg0B3fKWpv9icoD6pBmcGY9htf1pacVAeftH0UVKUBjZianqLAP4qgT6jBnbpIdSlQ/132"}]
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
                 * chat_id : 2
                 * user_id : 14662
                 * to_user_id : 14663
                 * content : Good morning
                 * type : text
                 * create_time : 2020-07-07 18:43:34
                 * is_read : 1
                 * user_avatar : https://wx.qlogo.cn/mmhead/1g0iax6wubUmYibpETSa5W8YqxaV0CnNKEic9aib2dJOCJo/132
                 * to_user_avatar : https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELiaTCR593LeScrg0B3fKWpv9icoD6pBmcGY9htf1pacVAeftH0UVKUBjZianqLAP4qgT6jBnbpIdSlQ/132
                 */
                private int mType;
                public int getmType() {
                    return mType;
                }

                public void setmType(int mType) {
                    this.mType = mType;
                }
                private int chat_id;
                private int user_id;
                private int to_user_id;
                private String content;
                private String type;
                private String create_time;
                private int is_read;
                private String user_avatar;
                private String to_user_avatar;

                public int getChat_id() {
                    return chat_id;
                }

                public void setChat_id(int chat_id) {
                    this.chat_id = chat_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getTo_user_id() {
                    return to_user_id;
                }

                public void setTo_user_id(int to_user_id) {
                    this.to_user_id = to_user_id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public int getIs_read() {
                    return is_read;
                }

                public void setIs_read(int is_read) {
                    this.is_read = is_read;
                }

                public String getUser_avatar() {
                    return user_avatar;
                }

                public void setUser_avatar(String user_avatar) {
                    this.user_avatar = user_avatar;
                }

                public String getTo_user_avatar() {
                    return to_user_avatar;
                }

                public void setTo_user_avatar(String to_user_avatar) {
                    this.to_user_avatar = to_user_avatar;
                }
            }
        }

        public static class ToUserBean {
            /**
             * user_id : 14662
             * nickName : ?
             */

            private int user_id;
            private String nickName;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }
    }
}
