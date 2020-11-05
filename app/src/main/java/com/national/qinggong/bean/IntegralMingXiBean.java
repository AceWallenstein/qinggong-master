package com.national.qinggong.bean;

import java.util.List;

public class IntegralMingXiBean {

    /**
     * code : 1
     * msg : success
     * data : {"list":{"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"log_id":12554,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:51"},{"log_id":12553,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:47"}]}}
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
         * list : {"total":2,"per_page":15,"current_page":1,"last_page":1,"data":[{"log_id":12554,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:51"},{"log_id":12553,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:47"}]}
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
             * total : 2
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"log_id":12554,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:51"},{"log_id":12553,"user_id":14666,"value":2,"describe":"积分奖励","remark":"","wxapp_id":10001,"create_time":"2020-08-05 18:24:47"}]
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
                 * log_id : 12554
                 * user_id : 14666
                 * value : 2
                 * describe : 积分奖励
                 * remark :
                 * wxapp_id : 10001
                 * create_time : 2020-08-05 18:24:51
                 */

                private int log_id;
                private int user_id;
                private int value;
                private String describe;
                private String remark;
                private int wxapp_id;
                private String create_time;

                public int getLog_id() {
                    return log_id;
                }

                public void setLog_id(int log_id) {
                    this.log_id = log_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }

                public String getDescribe() {
                    return describe;
                }

                public void setDescribe(String describe) {
                    this.describe = describe;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
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
            }
        }
    }
}
