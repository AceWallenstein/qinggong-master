package com.national.qinggong.bean;

public class ArticleDetailBean {

    /**
     * code : 1
     * data : {"article":{"id":2,"sort":0,"category":"基础文章3","poster":"http://hezhi.iyoutui.cn/upload/cc/8459e7a96ec180d48fd32ac986b79a.png","title":"贺织宝贝定制","content":"<p style=\"text-align: center;\">为了宝贝的表演节目而定制的三件套西<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">一定是当天全场的亮点<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/06/f1a9f8095e842249ee8019515b34d2.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/5d/d02144030ecd11314ecb4e619b1594.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/3b/466119dbfe2c8f1025b348f5406043.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d5/8a1b1b890866bec36fe94cf0036af0.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a9/a7c2752a5a8df6c6dfc7e8e4021d6c.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/85/934524f06cfdf7227f6f94f5f52eca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d8/ca7a3aff5511bd8cc4f05de1b07060.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n","views":15,"create_at":"2020-04-15 15:42:06","is_deleted":0}}
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
         * article : {"id":2,"sort":0,"category":"基础文章3","poster":"http://hezhi.iyoutui.cn/upload/cc/8459e7a96ec180d48fd32ac986b79a.png","title":"贺织宝贝定制","content":"<p style=\"text-align: center;\">为了宝贝的表演节目而定制的三件套西<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">一定是当天全场的亮点<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/06/f1a9f8095e842249ee8019515b34d2.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/5d/d02144030ecd11314ecb4e619b1594.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/3b/466119dbfe2c8f1025b348f5406043.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d5/8a1b1b890866bec36fe94cf0036af0.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a9/a7c2752a5a8df6c6dfc7e8e4021d6c.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/85/934524f06cfdf7227f6f94f5f52eca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d8/ca7a3aff5511bd8cc4f05de1b07060.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n","views":15,"create_at":"2020-04-15 15:42:06","is_deleted":0}
         */

        private ArticleBean article;

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * id : 2
             * sort : 0
             * category : 基础文章3
             * poster : http://hezhi.iyoutui.cn/upload/cc/8459e7a96ec180d48fd32ac986b79a.png
             * title : 贺织宝贝定制
             * content : <p style="text-align: center;">为了宝贝的表演节目而定制的三件套西</p>

             <p style="text-align: center;">&nbsp;</p>

             <p style="text-align: center;">一定是当天全场的亮点</p>

             <p style="text-align: center;">&nbsp;</p>

             <p style="text-align: center;">PS:入店购衣的客户全部都是可以免费获赠</p>

             <p style="text-align: center;"><strong>贺织个人形象照</strong><strong>一组</strong></p>

             <p style="text-align: center;"><strong><img alt="" src="http://hezhi.iyoutui.cn/upload/06/f1a9f8095e842249ee8019515b34d2.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/5d/d02144030ecd11314ecb4e619b1594.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/3b/466119dbfe2c8f1025b348f5406043.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/d5/8a1b1b890866bec36fe94cf0036af0.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/a9/a7c2752a5a8df6c6dfc7e8e4021d6c.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/85/934524f06cfdf7227f6f94f5f52eca.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/d8/ca7a3aff5511bd8cc4f05de1b07060.jpg" style="max-width:100%;border:0" /></strong></p>

             <p><strong><img alt="" src="http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg" style="max-width:100%;border:0" /></strong></p>

             * views : 15
             * create_at : 2020-04-15 15:42:06
             * is_deleted : 0
             */

            private int id;
            private int sort;
            private String category;
            private String poster;
            private String title;
            private String content;
            private int views;
            private String create_at;
            private int is_deleted;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }
        }
    }
}
