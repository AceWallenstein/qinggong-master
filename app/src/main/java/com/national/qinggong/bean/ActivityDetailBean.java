package com.national.qinggong.bean;

public class ActivityDetailBean {

    /**
     * code : 1
     * data : {"activity":{"id":1,"title":"参与活动领取奖品","subtitle":"活动详情","poster":"http://guofu.vshop365.cn/upload/25/51047854a870955b840e4fd5f4751d.png","start":"2020-04-20","end":"2020-04-24","content":"<p style=\"text-align: center;\"><br />\r\n<strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span style=\"font-size:36px;\"><span style=\"font-family:Microsoft YaHei;\">金融+供应链<br />\r\n智慧供应链金融创新发展论坛<br />\r\n金融+科技<br />\r\n珠海-澳门&ldquo;区块链+特色金融&rdquo;<br />\r\n发展论坛<\/span><\/span><\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><span style=\"font-size:36px;\"><span style=\"font-family:Microsoft YaHei;\"><img alt=\"\" src=\"http://guofu.vshop365.cn/upload/3c/c8cb01c16b9e5aa19a3f6694c955ca.png\" style=\"max-width:100%;border:0\" /><br />\r\n&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<\/span><\/span><\/strong><\/p>","views":1,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"}}
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
         * activity : {"id":1,"title":"参与活动领取奖品","subtitle":"活动详情","poster":"http://guofu.vshop365.cn/upload/25/51047854a870955b840e4fd5f4751d.png","start":"2020-04-20","end":"2020-04-24","content":"<p style=\"text-align: center;\"><br />\r\n<strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span style=\"font-size:36px;\"><span style=\"font-family:Microsoft YaHei;\">金融+供应链<br />\r\n智慧供应链金融创新发展论坛<br />\r\n金融+科技<br />\r\n珠海-澳门&ldquo;区块链+特色金融&rdquo;<br />\r\n发展论坛<\/span><\/span><\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><span style=\"font-size:36px;\"><span style=\"font-family:Microsoft YaHei;\"><img alt=\"\" src=\"http://guofu.vshop365.cn/upload/3c/c8cb01c16b9e5aa19a3f6694c955ca.png\" style=\"max-width:100%;border:0\" /><br />\r\n&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<\/span><\/span><\/strong><\/p>","views":1,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"}
         */

        private ActivityBean activity;

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public static class ActivityBean {
            /**
             * id : 1
             * title : 参与活动领取奖品
             * subtitle : 活动详情
             * poster : http://guofu.vshop365.cn/upload/25/51047854a870955b840e4fd5f4751d.png
             * start : 2020-04-20
             * end : 2020-04-24
             * content : <p style="text-align: center;"><br />
             <strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span style="font-size:36px;"><span style="font-family:Microsoft YaHei;">金融+供应链<br />
             智慧供应链金融创新发展论坛<br />
             金融+科技<br />
             珠海-澳门&ldquo;区块链+特色金融&rdquo;<br />
             发展论坛</span></span></strong></p>

             <p style="text-align: center;"><strong><span style="font-size:36px;"><span style="font-family:Microsoft YaHei;"><img alt="" src="http://guofu.vshop365.cn/upload/3c/c8cb01c16b9e5aa19a3f6694c955ca.png" style="max-width:100%;border:0" /><br />
             &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span></span></strong></p>
             * views : 1
             * sort : 0
             * is_deleted : 0
             * create_at : 2020-04-20 11:41:45
             */

            private int id;
            private String title;
            private String subtitle;
            private String poster;
            private String start;
            private String end;
            private String content;
            private int views;
            private int sort;
            private int is_deleted;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}
