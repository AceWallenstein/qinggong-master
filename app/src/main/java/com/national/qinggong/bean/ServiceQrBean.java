package com.national.qinggong.bean;

public class ServiceQrBean {

    /**
     * code : 1
     * data : {"config":{"service":"4006001729","qrcode":""}}
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
         * config : {"service":"4006001729","qrcode":""}
         */

        private ConfigBean config;

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public static class ConfigBean {
            /**
             * service : 4006001729
             * qrcode :
             */

            private String service;
            private String qrcode;

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }
        }
    }
}
