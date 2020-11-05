package com.national.qinggong.bean;

import java.util.List;

public class LiveListBackBean {

    /**
     * code : 1
     * msg : success
     * data : {"total":4,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":6,"name":"测试","file_id":"5285890808740910837","file_format":"mp4","start_time":{"text":"2020-10-16 18:55:52","value":1602845752},"end_time":{"text":"2020-10-16 18:57:16","value":1602845836},"duration":86,"show_video":null,"file_size":9764382,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/5701af515285890808740910837/f0.mp4","stream_id":10044,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-16 18:57:18","update_time":"2020-10-16 18:57:18","file_path":"http://qgshop.meiliancheng.cn/uploads/202010161104262aeb99666.png","file_name":null,"file_url":null},{"id":7,"name":"哈哈","file_id":"5285890808769930208","file_format":"mp4","start_time":{"text":"2020-10-17 14:37:52","value":1602916672},"end_time":{"text":"2020-10-17 14:38:27","value":1602916707},"duration":36,"show_video":null,"file_size":3447918,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/e47592f65285890808769930208/f0.mp4","stream_id":10048,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-17 14:38:28","update_time":"2020-10-17 14:38:28","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015154841c491b3438.png","file_name":null,"file_url":null},{"id":8,"name":"是是是","file_id":"5285890808774858554","file_format":"mp4","start_time":{"text":"2020-10-17 17:24:34","value":1602926674},"end_time":{"text":"2020-10-17 17:24:34","value":1602926674},"duration":3,"show_video":null,"file_size":307435,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/4da7b0df5285890808774858554/f0.mp4","stream_id":10051,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-17 17:24:35","update_time":"2020-10-17 17:24:35","file_path":"http://qgshop.meiliancheng.cn/uploads/20201014093632c0a6d7828.png","file_name":null,"file_url":null},{"id":9,"name":"jdjdjj","file_id":"5285890808878996519","file_format":"mp4","start_time":{"text":"2020-10-20 15:36:18","value":1603179378},"end_time":{"text":"2020-10-20 15:37:43","value":1603179463},"duration":20,"show_video":null,"file_size":1891311,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/f8a986485285890808878996519/f0.mp4","stream_id":10052,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-20 15:37:45","update_time":"2020-10-20 15:37:45","file_path":"http://qgshop.meiliancheng.cn/uploads/2020102015353027c6e4461.png","file_name":null,"file_url":null}]}
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
         * total : 4
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":6,"name":"测试","file_id":"5285890808740910837","file_format":"mp4","start_time":{"text":"2020-10-16 18:55:52","value":1602845752},"end_time":{"text":"2020-10-16 18:57:16","value":1602845836},"duration":86,"show_video":null,"file_size":9764382,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/5701af515285890808740910837/f0.mp4","stream_id":10044,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-16 18:57:18","update_time":"2020-10-16 18:57:18","file_path":"http://qgshop.meiliancheng.cn/uploads/202010161104262aeb99666.png","file_name":null,"file_url":null},{"id":7,"name":"哈哈","file_id":"5285890808769930208","file_format":"mp4","start_time":{"text":"2020-10-17 14:37:52","value":1602916672},"end_time":{"text":"2020-10-17 14:38:27","value":1602916707},"duration":36,"show_video":null,"file_size":3447918,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/e47592f65285890808769930208/f0.mp4","stream_id":10048,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-17 14:38:28","update_time":"2020-10-17 14:38:28","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015154841c491b3438.png","file_name":null,"file_url":null},{"id":8,"name":"是是是","file_id":"5285890808774858554","file_format":"mp4","start_time":{"text":"2020-10-17 17:24:34","value":1602926674},"end_time":{"text":"2020-10-17 17:24:34","value":1602926674},"duration":3,"show_video":null,"file_size":307435,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/4da7b0df5285890808774858554/f0.mp4","stream_id":10051,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-17 17:24:35","update_time":"2020-10-17 17:24:35","file_path":"http://qgshop.meiliancheng.cn/uploads/20201014093632c0a6d7828.png","file_name":null,"file_url":null},{"id":9,"name":"jdjdjj","file_id":"5285890808878996519","file_format":"mp4","start_time":{"text":"2020-10-20 15:36:18","value":1603179378},"end_time":{"text":"2020-10-20 15:37:43","value":1603179463},"duration":20,"show_video":null,"file_size":1891311,"stream_param":"","video_url":"http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/f8a986485285890808878996519/f0.mp4","stream_id":10052,"image_id":0,"video_status":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-20 15:37:45","update_time":"2020-10-20 15:37:45","file_path":"http://qgshop.meiliancheng.cn/uploads/2020102015353027c6e4461.png","file_name":null,"file_url":null}]
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
             * id : 6
             * name : 测试
             * file_id : 5285890808740910837
             * file_format : mp4
             * start_time : {"text":"2020-10-16 18:55:52","value":1602845752}
             * end_time : {"text":"2020-10-16 18:57:16","value":1602845836}
             * duration : 86
             * show_video : null
             * file_size : 9764382
             * stream_param :
             * video_url : http://1303139081.vod2.myqcloud.com/6d1da79fvodcq1303139081/5701af515285890808740910837/f0.mp4
             * stream_id : 10044
             * image_id : 0
             * video_status : 0
             * is_delete : 0
             * wxapp_id : 10001
             * create_time : 2020-10-16 18:57:18
             * update_time : 2020-10-16 18:57:18
             * file_path : http://qgshop.meiliancheng.cn/uploads/202010161104262aeb99666.png
             * file_name : null
             * file_url : null
             */

            private int id;
            private String name;
            private String file_id;
            private String file_format;
           private long start_time;
            private long end_time;
            private int duration;
            private Object show_video;
            private int file_size;
            private String stream_param;
            private String video_url;
            private int stream_id;
            private int image_id;
            private int video_status;
            private int is_delete;
            private int wxapp_id;
            private String create_time;
            private String update_time;
            private String file_path;
            private Object file_name;
            private Object file_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFile_id() {
                return file_id;
            }

            public void setFile_id(String file_id) {
                this.file_id = file_id;
            }

            public String getFile_format() {
                return file_format;
            }

            public void setFile_format(String file_format) {
                this.file_format = file_format;
            }

            public long getStart_time() {
                return start_time;
            }

            public void setStart_time(long start_time) {
                this.start_time = start_time;
            }

            public long getEnd_time() {
                return end_time;
            }

            public void setEnd_time(long end_time) {
                this.end_time = end_time;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public Object getShow_video() {
                return show_video;
            }

            public void setShow_video(Object show_video) {
                this.show_video = show_video;
            }

            public int getFile_size() {
                return file_size;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }

            public String getStream_param() {
                return stream_param;
            }

            public void setStream_param(String stream_param) {
                this.stream_param = stream_param;
            }

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public int getStream_id() {
                return stream_id;
            }

            public void setStream_id(int stream_id) {
                this.stream_id = stream_id;
            }

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public int getVideo_status() {
                return video_status;
            }

            public void setVideo_status(int video_status) {
                this.video_status = video_status;
            }

            public int getIs_delete() {
                return is_delete;
            }

            public void setIs_delete(int is_delete) {
                this.is_delete = is_delete;
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public Object getFile_name() {
                return file_name;
            }

            public void setFile_name(Object file_name) {
                this.file_name = file_name;
            }

            public Object getFile_url() {
                return file_url;
            }

            public void setFile_url(Object file_url) {
                this.file_url = file_url;
            }

            public static class StartTimeBean {
                /**
                 * text : 2020-10-16 18:55:52
                 * value : 1602845752
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class EndTimeBean {
                /**
                 * text : 2020-10-16 18:57:16
                 * value : 1602845836
                 */

                private String text;
                private int value;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }
        }
    }
}
