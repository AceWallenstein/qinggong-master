package com.national.qinggong.bean;

import java.io.Serializable;
import java.util.List;
/*
* 首页banner 图
* */
public class HomeBannerBean extends  AdBean{


    /**
     * code : 1
     * data : {"swipers":[{"id":2,"image":"http://guofu.vshop365.cn/upload/df/403c7a300973d3ab4dbbef64a8cad3.png","desc":"演示轮播图2的说明文字","type":1,"param":"1","sort":12,"create_at":"2020-03-19 13:56:33","is_deleted":0},{"id":1,"image":"http://guofu.vshop365.cn/upload/3c/abcdd113cfc85499bd551a3e09565a.jpg","desc":"演示轮播图1的说明文字","type":2,"param":"baidu.com","sort":0,"create_at":"2020-03-18 13:47:34","is_deleted":0},{"id":3,"image":"http://guofu.vshop365.cn/upload/d9/a0fa159497dba698544480bb75ceb0.jpg","desc":"演示轮播图3的说明文字","type":1,"param":null,"sort":0,"create_at":"2020-03-19 13:56:49","is_deleted":0},{"id":4,"image":"http://guofu.vshop365.cn/upload/3a/9cce3cdefc72b60f767bec69faa666.jpg","desc":"演示轮播图4的说明文字","type":1,"param":null,"sort":0,"create_at":"2020-03-19 13:57:11","is_deleted":0}],"courses":[{"id":4,"sort":4,"term":"初识股票","poster":"http://guofu.vshop365.cn/upload/58/9264a4730d53d3b70ef19c32514da1.png","title":"贺织漫乐城店客定图留档","tags":["精品课程","零基础"],"content":"<p style=\"text-align: center;\"><strong>贺织全定系列<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\">细节自定义<\/p>\r\n\r\n<p style=\"text-align: center;\">面料自定义<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">110s国产全羊毛系列<\/p>\r\n\r\n<p style=\"text-align: center;\">以工作为主的穿着场合<\/p>\r\n\r\n<p style=\"text-align: center;\">羊毛支数不用太高，以方便打理为主<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a7/e38bba79f5812465c43adb76a202eb.jpg\" style=\"max-width:100%;border:0\" /><\/p>\r\n\r\n<p style=\"text-align: center;\">客选细节，彩色修边线<\/p>\r\n\r\n<p style=\"text-align: center;\"><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/ee/737e7aec6c10f5ec1e20b9bcf849ea.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/88/bf02e2a3aac2b07e17251a4f266948.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/dc/2c36087658136fca526c93b7cfccca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/83/2d0d50c74b08848d1b0671621c53a9.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/79/124efe9cad11761bd72930215347a6.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/98/4a56a0d392e345d745c6ea6e0c6946.jpg\" style=\"max-width:100%;border:0\" /><\/p>","views":5,"create_at":"2020-04-10 15:56:14","is_deleted":0},{"id":3,"sort":3,"term":"初识股票","poster":"http://guofu.vshop365.cn/upload/05/04e0f2998a0621fca0f4e3c7e56ab5.png","title":"稳重应对严肃面试","tags":["测试","你好"],"content":"<p style=\"text-align: center;\">严肃的企业,律师,银行等面试<\/p>\r\n\r\n<p style=\"text-align: center;\">选择一套合身的深色西装,<\/p>\r\n\r\n<p style=\"text-align: center;\">稳重大方, 干净简洁.<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/f4/ef3831c3f4194ebbc83d0d299b1dc3.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/b6/a49443afdcc1af4816d1e9f9f386d7.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/7b/527f6262d22b0a6f1b93a38486c5dd.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/42/c5f57ab8d8823615e81c5c430273ba.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/f5/ae7bfbb6ceb0af5a4e6cf8a4904a21.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/2d/fe7c12110f759d5dc951bc6e0fd8f0.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>","views":11,"create_at":"2020-04-15 17:01:46","is_deleted":0},{"id":2,"sort":2,"term":"进阶课程","poster":"http://guofu.vshop365.cn/upload/a5/7acbe9902164b3cf3136ce86afd8e0.png","title":"贺织宝贝定制","tags":["零基础","精品课程"],"content":"<p style=\"text-align: center;\">为了宝贝的表演节目而定制的三件套西服<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">一定是当天全场的亮点<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/06/f1a9f8095e842249ee8019515b34d2.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/5d/d02144030ecd11314ecb4e619b1594.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/3b/466119dbfe2c8f1025b348f5406043.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d5/8a1b1b890866bec36fe94cf0036af0.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a9/a7c2752a5a8df6c6dfc7e8e4021d6c.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/85/934524f06cfdf7227f6f94f5f52eca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d8/ca7a3aff5511bd8cc4f05de1b07060.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>","views":14,"create_at":"2020-04-15 15:42:06","is_deleted":0}],"ad":{"type":1,"image":"http://hezhi.iyoutui.cn/upload/be/b71a9439e722209d8a63e96182d555.png","url":"","id":1},"activity":{"id":1,"title":"最好不超过八个字","subtitle":"最好不超过八个字","poster":"http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png","start":"2020-04-20","end":"2020-04-24","content":"<p>阿斯顿发水淀粉a<\/p>\r\n\r\n<p>阿斯顿发<\/p>\r\n\r\n<p>😊<\/p>","views":0,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"},"articles":[{"title":"贺织漫乐城店客定图留档"},{"title":"稳重应对严肃面试"},{"title":"贺织宝贝定制"}]}
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
         * swipers : [{"id":2,"image":"http://guofu.vshop365.cn/upload/df/403c7a300973d3ab4dbbef64a8cad3.png","desc":"演示轮播图2的说明文字","type":1,"param":"1","sort":12,"create_at":"2020-03-19 13:56:33","is_deleted":0},{"id":1,"image":"http://guofu.vshop365.cn/upload/3c/abcdd113cfc85499bd551a3e09565a.jpg","desc":"演示轮播图1的说明文字","type":2,"param":"baidu.com","sort":0,"create_at":"2020-03-18 13:47:34","is_deleted":0},{"id":3,"image":"http://guofu.vshop365.cn/upload/d9/a0fa159497dba698544480bb75ceb0.jpg","desc":"演示轮播图3的说明文字","type":1,"param":null,"sort":0,"create_at":"2020-03-19 13:56:49","is_deleted":0},{"id":4,"image":"http://guofu.vshop365.cn/upload/3a/9cce3cdefc72b60f767bec69faa666.jpg","desc":"演示轮播图4的说明文字","type":1,"param":null,"sort":0,"create_at":"2020-03-19 13:57:11","is_deleted":0}]
         * courses : [{"id":4,"sort":4,"term":"初识股票","poster":"http://guofu.vshop365.cn/upload/58/9264a4730d53d3b70ef19c32514da1.png","title":"贺织漫乐城店客定图留档","tags":["精品课程","零基础"],"content":"<p style=\"text-align: center;\"><strong>贺织全定系列<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\">细节自定义<\/p>\r\n\r\n<p style=\"text-align: center;\">面料自定义<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">110s国产全羊毛系列<\/p>\r\n\r\n<p style=\"text-align: center;\">以工作为主的穿着场合<\/p>\r\n\r\n<p style=\"text-align: center;\">羊毛支数不用太高，以方便打理为主<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a7/e38bba79f5812465c43adb76a202eb.jpg\" style=\"max-width:100%;border:0\" /><\/p>\r\n\r\n<p style=\"text-align: center;\">客选细节，彩色修边线<\/p>\r\n\r\n<p style=\"text-align: center;\"><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/ee/737e7aec6c10f5ec1e20b9bcf849ea.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/88/bf02e2a3aac2b07e17251a4f266948.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/dc/2c36087658136fca526c93b7cfccca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/83/2d0d50c74b08848d1b0671621c53a9.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/79/124efe9cad11761bd72930215347a6.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/98/4a56a0d392e345d745c6ea6e0c6946.jpg\" style=\"max-width:100%;border:0\" /><\/p>","views":5,"create_at":"2020-04-10 15:56:14","is_deleted":0},{"id":3,"sort":3,"term":"初识股票","poster":"http://guofu.vshop365.cn/upload/05/04e0f2998a0621fca0f4e3c7e56ab5.png","title":"稳重应对严肃面试","tags":["测试","你好"],"content":"<p style=\"text-align: center;\">严肃的企业,律师,银行等面试<\/p>\r\n\r\n<p style=\"text-align: center;\">选择一套合身的深色西装,<\/p>\r\n\r\n<p style=\"text-align: center;\">稳重大方, 干净简洁.<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/f4/ef3831c3f4194ebbc83d0d299b1dc3.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/b6/a49443afdcc1af4816d1e9f9f386d7.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/7b/527f6262d22b0a6f1b93a38486c5dd.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/42/c5f57ab8d8823615e81c5c430273ba.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/f5/ae7bfbb6ceb0af5a4e6cf8a4904a21.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/2d/fe7c12110f759d5dc951bc6e0fd8f0.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>","views":11,"create_at":"2020-04-15 17:01:46","is_deleted":0},{"id":2,"sort":2,"term":"进阶课程","poster":"http://guofu.vshop365.cn/upload/a5/7acbe9902164b3cf3136ce86afd8e0.png","title":"贺织宝贝定制","tags":["零基础","精品课程"],"content":"<p style=\"text-align: center;\">为了宝贝的表演节目而定制的三件套西服<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">一定是当天全场的亮点<\/p>\r\n\r\n<p style=\"text-align: center;\">&nbsp;<\/p>\r\n\r\n<p style=\"text-align: center;\">PS:入店购衣的客户全部都是可以免费获赠<\/p>\r\n\r\n<p style=\"text-align: center;\"><strong>贺织个人形象照<\/strong><strong>一组<\/strong><\/p>\r\n\r\n<p style=\"text-align: center;\"><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/06/f1a9f8095e842249ee8019515b34d2.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/5d/d02144030ecd11314ecb4e619b1594.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/3b/466119dbfe2c8f1025b348f5406043.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d5/8a1b1b890866bec36fe94cf0036af0.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/a9/a7c2752a5a8df6c6dfc7e8e4021d6c.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/85/934524f06cfdf7227f6f94f5f52eca.jpg\" style=\"max-width:100%;border:0\" /><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/d8/ca7a3aff5511bd8cc4f05de1b07060.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>\r\n\r\n<p><strong><img alt=\"\" src=\"http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg\" style=\"max-width:100%;border:0\" /><\/strong><\/p>","views":14,"create_at":"2020-04-15 15:42:06","is_deleted":0}]
         * ad : {"type":1,"image":"http://hezhi.iyoutui.cn/upload/be/b71a9439e722209d8a63e96182d555.png","url":"","id":1}
         * activity : {"id":1,"title":"最好不超过八个字","subtitle":"最好不超过八个字","poster":"http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png","start":"2020-04-20","end":"2020-04-24","content":"<p>阿斯顿发水淀粉a<\/p>\r\n\r\n<p>阿斯顿发<\/p>\r\n\r\n<p>😊<\/p>","views":0,"sort":0,"is_deleted":0,"create_at":"2020-04-20 11:41:45"}
         * articles : [{"title":"贺织漫乐城店客定图留档"},{"title":"稳重应对严肃面试"},{"title":"贺织宝贝定制"}]
         */

        private AdBean ad;
        private Market market;


        private ActivityArticleBean.DataBean.ListBean activity;
        private List<SwipersBean> swipers;
        private List<ClassTypeBean.DataBean.ListBean> courses;
        private List<ArticlesBean> articles;


        public Market getMarket() {
            return market;
        }

        public void setMarket(Market market) {
            this.market = market;
        }

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public ActivityArticleBean.DataBean.ListBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityArticleBean.DataBean.ListBean activity) {
            this.activity = activity;
        }

        public List<SwipersBean> getSwipers() {
            return swipers;
        }

        public void setSwipers(List<SwipersBean> swipers) {
            this.swipers = swipers;
        }

        public List<ClassTypeBean.DataBean.ListBean> getCourses() {
            return courses;
        }

        public void setCourses(List<ClassTypeBean.DataBean.ListBean> courses) {
            this.courses = courses;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }


        public  static  class Market{
            private String hq;
            private FxBean fx;

            public String getHq() {
                return hq;
            }

            public void setHq(String hq) {
                this.hq = hq;
            }

            public FxBean getFx() {
                return fx;
            }

            public void setFx(FxBean fx) {
                this.fx = fx;
            }


            public static class FxBean{

                private String market;
                private String code;

                public String getMarket() {
                    return market;
                }

                public void setMarket(String market) {
                    this.market = market;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }




        }






        public static class AdBean {
            /**
             * type : 1
             * image : http://hezhi.iyoutui.cn/upload/be/b71a9439e722209d8a63e96182d555.png
             * url :
             * id : 1
             */

            private int type;
            private String image;
            private String url;
            private int id;
            private String param;

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }



            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ActivityBean {
            /**
             * id : 1
             * title : 最好不超过八个字
             * subtitle : 最好不超过八个字
             * poster : http://guofu.vshop365.cn/upload/4f/10c672a2a6ddd91b8ab837aaf6f33c.png
             * start : 2020-04-20
             * end : 2020-04-24
             * content : <p>阿斯顿发水淀粉a</p>

             <p>阿斯顿发</p>

             <p>😊</p>
             * views : 0
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

        public static class SwipersBean {
            /**
             * id : 2
             * image : http://guofu.vshop365.cn/upload/df/403c7a300973d3ab4dbbef64a8cad3.png
             * desc : 演示轮播图2的说明文字
             * type : 1
             * param : 1
             * sort : 12
             * create_at : 2020-03-19 13:56:33
             * is_deleted : 0
             */

            private int id;
            private String image;
            private String desc;
            private int type;
            private String param;
            private int sort;
            private String create_at;
            private int is_deleted;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getParam() {
                return param;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

        public static class CoursesBean extends ListBean implements Serializable {
            /**
             * id : 4
             * sort : 4
             * term : 初识股票
             * poster : http://guofu.vshop365.cn/upload/58/9264a4730d53d3b70ef19c32514da1.png
             * title : 贺织漫乐城店客定图留档
             * tags : ["精品课程","零基础"]
             * content : <p style="text-align: center;"><strong>贺织全定系列</strong></p>

             <p style="text-align: center;">细节自定义</p>

             <p style="text-align: center;">面料自定义</p>

             <p style="text-align: center;">&nbsp;</p>

             <p style="text-align: center;">110s国产全羊毛系列</p>

             <p style="text-align: center;">以工作为主的穿着场合</p>

             <p style="text-align: center;">羊毛支数不用太高，以方便打理为主</p>

             <p style="text-align: center;">&nbsp;</p>

             <p style="text-align: center;">PS:入店购衣的客户全部都是可以免费获赠</p>

             <p style="text-align: center;"><strong>贺织个人形象照</strong><strong>一组</strong></p>

             <p style="text-align: center;"><img alt="" src="http://hezhi.iyoutui.cn/upload/a7/e38bba79f5812465c43adb76a202eb.jpg" style="max-width:100%;border:0" /></p>

             <p style="text-align: center;">客选细节，彩色修边线</p>

             <p style="text-align: center;"><img alt="" src="http://hezhi.iyoutui.cn/upload/ee/737e7aec6c10f5ec1e20b9bcf849ea.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/88/bf02e2a3aac2b07e17251a4f266948.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/dc/2c36087658136fca526c93b7cfccca.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/83/2d0d50c74b08848d1b0671621c53a9.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/79/124efe9cad11761bd72930215347a6.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/22/fafe1d3c527702f39aa69164cde1a9.jpg" style="max-width:100%;border:0" /><img alt="" src="http://hezhi.iyoutui.cn/upload/98/4a56a0d392e345d745c6ea6e0c6946.jpg" style="max-width:100%;border:0" /></p>
             * views : 5
             * create_at : 2020-04-10 15:56:14
             * is_deleted : 0
             */

            private int id;
            private int sort;
            private String term;
            private String poster;
            private String title;
            private String content;
            private int views;
            private String create_at;
            private int is_deleted;
            private List<String> tags;

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

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
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

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }
        }

        public static class ArticlesBean {
            /**
             * title : 贺织漫乐城店客定图留档
             */

            private String title;
            private String id;
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
