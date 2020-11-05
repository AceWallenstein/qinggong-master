package com.national.qinggong.bean;

import java.util.List;

public class IndexCountryBean {


    /**
     * code : 1
     * msg : success
     * data : {"list":[{"country_id":1,"wxapp_id":10001,"name":"China","create_time":1596525226,"update_time":1596525289,"is_delete":0},{"country_id":2,"wxapp_id":10001,"name":"USA","create_time":1596525296,"update_time":1596525334,"is_delete":0}]}
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
             * country_id : 1
             * wxapp_id : 10001
             * name : China
             * create_time : 1596525226
             * update_time : 1596525289
             * is_delete : 0
             */

            private String country_id;
            private String wxapp_id;
            private String name;
            private String create_time;
            private String update_time;
            private int is_delete;

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(String wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
            }
        }
    }
}
