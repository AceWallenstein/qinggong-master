package com.national.qinggong.bean;

import java.util.List;

public class IndexGetBannerBean {

    /**
     * code : 1
     * msg : success
     * data : {"banner":[{"banner_id":30,"wxapp_id":10001,"name":"积分","image_id":19898,"sort":100,"url":"","type":1,"create_time":"2020-10-13 15:34:10","update_time":"2020-10-13 15:34:10","is_delete":0,"image":{"file_id":19898,"storage":"local","group_id":0,"file_url":"","file_name":"2020072214262994c791113.png","file_size":81465,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"file_path":"http://qgshop.meiliancheng.cn/uploads/2020072214262994c791113.png"}}]}
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
        private List<BannerBean> banner;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class BannerBean {
            /**
             * banner_id : 30
             * wxapp_id : 10001
             * name : 积分
             * image_id : 19898
             * sort : 100
             * url :
             * type : 1
             * create_time : 2020-10-13 15:34:10
             * update_time : 2020-10-13 15:34:10
             * is_delete : 0
             * image : {"file_id":19898,"storage":"local","group_id":0,"file_url":"","file_name":"2020072214262994c791113.png","file_size":81465,"file_type":"image","extension":"png","is_user":0,"is_recycle":0,"is_delete":0,"file_path":"http://qgshop.meiliancheng.cn/uploads/2020072214262994c791113.png"}
             */

            private int banner_id;
            private int wxapp_id;
            private String name;
            private int image_id;
            private int sort;
            private String url;
            private int type;
            private String create_time;
            private String update_time;
            private int is_delete;
            private ImageBean image;

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public ImageBean getImage() {
                return image;
            }

            public void setImage(ImageBean image) {
                this.image = image;
            }

            public static class ImageBean {
                /**
                 * file_id : 19898
                 * storage : local
                 * group_id : 0
                 * file_url :
                 * file_name : 2020072214262994c791113.png
                 * file_size : 81465
                 * file_type : image
                 * extension : png
                 * is_user : 0
                 * is_recycle : 0
                 * is_delete : 0
                 * file_path : http://qgshop.meiliancheng.cn/uploads/2020072214262994c791113.png
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
