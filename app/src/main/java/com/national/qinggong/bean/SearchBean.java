package com.national.qinggong.bean;

import java.util.List;

public class SearchBean {

    /**
     * code : 1
     * msg : success
     * data : {"searchlist":[{"id":6,"user_id":14666,"keywords":"food"},{"id":7,"user_id":14666,"keywords":"Cup"}],"list":[]}
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
        private List<SearchlistBean> searchlist;
        private List<GuessMyLikelistBean> list;

        public List<SearchlistBean> getSearchlist() {
            return searchlist;
        }

        public void setSearchlist(List<SearchlistBean> searchlist) {
            this.searchlist = searchlist;
        }

        public List<GuessMyLikelistBean> getList() {
            return list;
        }

        public void setList(List<GuessMyLikelistBean> list) {
            this.list = list;
        }

        public static class GuessMyLikelistBean {


            private int goods_id;
            private int user_id;
            private int category_id;
            private int wxapp_id;
            private String goods_name;
            private String number;
            private String images;
            private String more_info;
            private String content;
            private int create_time;
            private int update_time;
            private int is_hot;
            private int is_new;
            private int is_recommend;
            private int status;
            private int is_delete;
            private String file_path;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getWxapp_id() {
                return wxapp_id;
            }

            public void setWxapp_id(int wxapp_id) {
                this.wxapp_id = wxapp_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getMore_info() {
                return more_info;
            }

            public void setMore_info(String more_info) {
                this.more_info = more_info;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

        public static class SearchlistBean {
            /**
             * id : 6
             * user_id : 14666
             * keywords : food
             */
            private boolean isSeleted = false;

            public boolean isSeleted() {
                return isSeleted;
            }

            public void setSeleted(boolean seleted) {
                isSeleted = seleted;
            }
            private int id;
            private int user_id;
            private String keywords;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }
        }
    }
}
