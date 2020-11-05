package com.national.qinggong.bean;

import java.util.List;

public class MyMessageBean {

    /**
     * code : 1
     * msg : success
     * data : {"list":{"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"chat_id":2,"user_id":14662,"to_user_id":10432,"content":"10001","type":"Fishing floats","create_time":1594178884,"is_read":3,"avatarUrl":"54444","nickName":"2020-07-09 14:02:24"}]}}
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
         * list : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"chat_id":2,"user_id":14662,"to_user_id":10432,"content":"10001","type":"Fishing floats","create_time":1594178884,"is_read":3,"avatarUrl":"54444","nickName":"2020-07-09 14:02:24"}]}
         */

        private ListBean list;
        private int new_message_num;

        public int getNew_message_num() {
            return new_message_num;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 2
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"chat_id":2,"user_id":14662,"to_user_id":10432,"content":"10001","type":"Fishing floats","create_time":1594178884,"is_read":3,"avatarUrl":"54444","nickName":"2020-07-09 14:02:24"}]
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
                 * to_user_id : 10432
                 * content : 10001
                 * type : Fishing floats
                 * create_time : 1594178884
                 * is_read : 3
                 * avatarUrl : 54444
                 * nickName : 2020-07-09 14:02:24
                 */

                private int chat_id;
                private int user_id;
                private int to_user_id;
                private int new_num;
                private String content;
                private String type;
                private long create_time;
                private int is_read;
                private String avatarUrl;
                private String nickName;

                public int getNew_num() {
                    return new_num;
                }

                public void setNew_num(int new_num) {
                    this.new_num = new_num;
                }

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

                public long getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public int getIs_read() {
                    return is_read;
                }

                public void setIs_read(int is_read) {
                    this.is_read = is_read;
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
            }
        }
    }
}
