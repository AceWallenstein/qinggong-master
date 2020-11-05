package com.national.qinggong.bean;

import java.util.List;

public class LiveVideoListBean {

    /**
     * code : 1
     * msg :
     * data : {"list":{"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":6,"wxapp_id":10001,"user_id":14664,"image_id":19922,"file_id":19923,"times":"12.43","title":"合肥融创乐园","create_time":"2020-10-15 15:58:12","update_time":"2020-10-17 09:37:09","is_delete":0,"status":1,"file_path":"http://qgshop.meiliancheng.cn/uploads/2020101515581251afe8835.mp4","file_name":"2020101515581251afe8835.mp4","file_url":"","image":{"file_id":19922,"storage":"local","group_id":0,"file_url":"","file_name":"20201015155736e97811045.png","file_size":24198,"file_type":"image","extension":"png","is_user":1,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-15 15:57:36","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015155736e97811045.png"}}]}}
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
         * list : {"total":1,"per_page":15,"current_page":1,"last_page":1,"data":[{"id":6,"wxapp_id":10001,"user_id":14664,"image_id":19922,"file_id":19923,"times":"12.43","title":"合肥融创乐园","create_time":"2020-10-15 15:58:12","update_time":"2020-10-17 09:37:09","is_delete":0,"status":1,"file_path":"http://qgshop.meiliancheng.cn/uploads/2020101515581251afe8835.mp4","file_name":"2020101515581251afe8835.mp4","file_url":"","image":{"file_id":19922,"storage":"local","group_id":0,"file_url":"","file_name":"20201015155736e97811045.png","file_size":24198,"file_type":"image","extension":"png","is_user":1,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-15 15:57:36","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015155736e97811045.png"}}]}
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
             * total : 1
             * per_page : 15
             * current_page : 1
             * last_page : 1
             * data : [{"id":6,"wxapp_id":10001,"user_id":14664,"image_id":19922,"file_id":19923,"times":"12.43","title":"合肥融创乐园","create_time":"2020-10-15 15:58:12","update_time":"2020-10-17 09:37:09","is_delete":0,"status":1,"file_path":"http://qgshop.meiliancheng.cn/uploads/2020101515581251afe8835.mp4","file_name":"2020101515581251afe8835.mp4","file_url":"","image":{"file_id":19922,"storage":"local","group_id":0,"file_url":"","file_name":"20201015155736e97811045.png","file_size":24198,"file_type":"image","extension":"png","is_user":1,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-15 15:57:36","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015155736e97811045.png"}}]
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
                 * wxapp_id : 10001
                 * user_id : 14664
                 * image_id : 19922
                 * file_id : 19923
                 * times : 12.43
                 * title : 合肥融创乐园
                 * create_time : 2020-10-15 15:58:12
                 * update_time : 2020-10-17 09:37:09
                 * is_delete : 0
                 * status : 1
                 * file_path : http://qgshop.meiliancheng.cn/uploads/2020101515581251afe8835.mp4
                 * file_name : 2020101515581251afe8835.mp4
                 * file_url :
                 * image : {"file_id":19922,"storage":"local","group_id":0,"file_url":"","file_name":"20201015155736e97811045.png","file_size":24198,"file_type":"image","extension":"png","is_user":1,"is_recycle":0,"is_delete":0,"wxapp_id":10001,"create_time":"2020-10-15 15:57:36","file_path":"http://qgshop.meiliancheng.cn/uploads/20201015155736e97811045.png"}
                 */

                private int id;
                private int wxapp_id;
                private int user_id;
                private int image_id;
                private int file_id;
                private String times;
                private String title;
                private String create_time;
                private String update_time;
                private int is_delete;
                private int status;
                private String file_path;
                private String file_name;
                private String file_url;
                private ImageBean image;
                private int topic_id;

                public int getTopic_id() {
                    return topic_id;
                }

                public void setTopic_id(int topic_id) {
                    this.topic_id = topic_id;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getWxapp_id() {
                    return wxapp_id;
                }

                public void setWxapp_id(int wxapp_id) {
                    this.wxapp_id = wxapp_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public int getFile_id() {
                    return file_id;
                }

                public void setFile_id(int file_id) {
                    this.file_id = file_id;
                }

                public String getTimes() {
                    return times;
                }

                public void setTimes(String times) {
                    this.times = times;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getFile_path() {
                    return file_path;
                }

                public void setFile_path(String file_path) {
                    this.file_path = file_path;
                }

                public String getFile_name() {
                    return file_name;
                }

                public void setFile_name(String file_name) {
                    this.file_name = file_name;
                }

                public String getFile_url() {
                    return file_url;
                }

                public void setFile_url(String file_url) {
                    this.file_url = file_url;
                }

                public ImageBean getImage() {
                    return image;
                }

                public void setImage(ImageBean image) {
                    this.image = image;
                }

                public static class ImageBean {
                    /**
                     * file_id : 19922
                     * storage : local
                     * group_id : 0
                     * file_url :
                     * file_name : 20201015155736e97811045.png
                     * file_size : 24198
                     * file_type : image
                     * extension : png
                     * is_user : 1
                     * is_recycle : 0
                     * is_delete : 0
                     * wxapp_id : 10001
                     * create_time : 2020-10-15 15:57:36
                     * file_path : http://qgshop.meiliancheng.cn/uploads/20201015155736e97811045.png
                     */

                    private int file_id;
                    private String storage;
                    private int group_id;
                    private String file_url;
                    private String file_name;
                    private int file_size;
                    private String file_type;
                    private String extension;
                    private int is_user;
                    private int is_recycle;
                    private int is_delete;
                    private int wxapp_id;
                    private String create_time;
                    private String file_path;

                    public int getFile_id() {
                        return file_id;
                    }

                    public void setFile_id(int file_id) {
                        this.file_id = file_id;
                    }

                    public String getStorage() {
                        return storage;
                    }

                    public void setStorage(String storage) {
                        this.storage = storage;
                    }

                    public int getGroup_id() {
                        return group_id;
                    }

                    public void setGroup_id(int group_id) {
                        this.group_id = group_id;
                    }

                    public String getFile_url() {
                        return file_url;
                    }

                    public void setFile_url(String file_url) {
                        this.file_url = file_url;
                    }

                    public String getFile_name() {
                        return file_name;
                    }

                    public void setFile_name(String file_name) {
                        this.file_name = file_name;
                    }

                    public int getFile_size() {
                        return file_size;
                    }

                    public void setFile_size(int file_size) {
                        this.file_size = file_size;
                    }

                    public String getFile_type() {
                        return file_type;
                    }

                    public void setFile_type(String file_type) {
                        this.file_type = file_type;
                    }

                    public String getExtension() {
                        return extension;
                    }

                    public void setExtension(String extension) {
                        this.extension = extension;
                    }

                    public int getIs_user() {
                        return is_user;
                    }

                    public void setIs_user(int is_user) {
                        this.is_user = is_user;
                    }

                    public int getIs_recycle() {
                        return is_recycle;
                    }

                    public void setIs_recycle(int is_recycle) {
                        this.is_recycle = is_recycle;
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

                    public String getFile_path() {
                        return file_path;
                    }

                    public void setFile_path(String file_path) {
                        this.file_path = file_path;
                    }
                }
            }
        }
    }
}
