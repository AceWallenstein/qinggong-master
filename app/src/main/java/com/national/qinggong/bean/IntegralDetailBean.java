package com.national.qinggong.bean;

import java.util.List;

public class IntegralDetailBean {

    /**
     * code : 1
     * msg : success
     * data : {"detail":{"goods_id":11709,"goods_name":"Men Boots Shoes","selling_point":"XWB5","category_id":0,"spec_type":10,"deduct_stock_type":10,"content":"<p>ArtNo: XWB5<br style=\"box-sizing: border-box;\"/>Category: Shoes<br style=\"box-sizing: border-box;\"/><\/p><p style=\"box-sizing: border-box; margin-bottom: 10px;\"><span style=\"box-sizing: border-box; display: block; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 30px; font-family: arial, helvetica, sans-serif; color: rgb(102, 102, 102); white-space: normal;\">&nbsp;Upper:PU<br style=\"box-sizing: border-box;\"/>&nbsp;Lining:Synthetic<br style=\"box-sizing: border-box;\"/>&nbsp;Insole:Synthetic<br style=\"box-sizing: border-box;\"/>&nbsp;Outsole:TPR<br style=\"box-sizing: border-box;\"/>&nbsp;Packing:BOX<br style=\"box-sizing: border-box;\"/><\/span><\/p><p><br/><\/p>","goods_sort":100,"buy_limit":0,"least_expend_money":"0.00","delivery_id":10001,"is_new_user_enjoy":0,"is_points_gift":1,"is_points_discount":1,"is_enable_balance_price":0,"is_enable_grade":1,"is_alone_grade":0,"alone_grade_equity":null,"is_ind_dealer":0,"dealer_money_type":10,"first_money":"0.00","second_money":"0.00","third_money":"0.00","is_rush":0,"is_points_exchange":1,"need_points_num":5000,"need_points_money":"0.00","goods_status":{"text":"上架","value":10},"comment_data_count":0,"goods_stock":110,"goods_image":"http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png","goods_sku":{"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null},"goods_multi_spec":null,"is_user_grade":false,"bought_num":0,"category":null,"image":[{"id":22548,"goods_id":11709,"image_id":19894,"file_path":"http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png","file_name":"202007071528093a25a0661.png","file_url":""}],"sku":[{"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null}],"comment_data":[],"goods_sales":1},"cart_total_num":0,"specData":null,"setting":{"points_name":"积分"}}
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
        /**
         * detail : {"goods_id":11709,"goods_name":"Men Boots Shoes","selling_point":"XWB5","category_id":0,"spec_type":10,"deduct_stock_type":10,"content":"<p>ArtNo: XWB5<br style=\"box-sizing: border-box;\"/>Category: Shoes<br style=\"box-sizing: border-box;\"/><\/p><p style=\"box-sizing: border-box; margin-bottom: 10px;\"><span style=\"box-sizing: border-box; display: block; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 30px; font-family: arial, helvetica, sans-serif; color: rgb(102, 102, 102); white-space: normal;\">&nbsp;Upper:PU<br style=\"box-sizing: border-box;\"/>&nbsp;Lining:Synthetic<br style=\"box-sizing: border-box;\"/>&nbsp;Insole:Synthetic<br style=\"box-sizing: border-box;\"/>&nbsp;Outsole:TPR<br style=\"box-sizing: border-box;\"/>&nbsp;Packing:BOX<br style=\"box-sizing: border-box;\"/><\/span><\/p><p><br/><\/p>","goods_sort":100,"buy_limit":0,"least_expend_money":"0.00","delivery_id":10001,"is_new_user_enjoy":0,"is_points_gift":1,"is_points_discount":1,"is_enable_balance_price":0,"is_enable_grade":1,"is_alone_grade":0,"alone_grade_equity":null,"is_ind_dealer":0,"dealer_money_type":10,"first_money":"0.00","second_money":"0.00","third_money":"0.00","is_rush":0,"is_points_exchange":1,"need_points_num":5000,"need_points_money":"0.00","goods_status":{"text":"上架","value":10},"comment_data_count":0,"goods_stock":110,"goods_image":"http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png","goods_sku":{"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null},"goods_multi_spec":null,"is_user_grade":false,"bought_num":0,"category":null,"image":[{"id":22548,"goods_id":11709,"image_id":19894,"file_path":"http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png","file_name":"202007071528093a25a0661.png","file_url":""}],"sku":[{"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null}],"comment_data":[],"goods_sales":1}
         * cart_total_num : 0
         * specData : null
         * setting : {"points_name":"积分"}
         */

        private DetailBean detail;
        private int cart_total_num;
        private Object specData;
        private SettingBean setting;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public int getCart_total_num() {
            return cart_total_num;
        }

        public void setCart_total_num(int cart_total_num) {
            this.cart_total_num = cart_total_num;
        }

        public Object getSpecData() {
            return specData;
        }

        public void setSpecData(Object specData) {
            this.specData = specData;
        }

        public SettingBean getSetting() {
            return setting;
        }

        public void setSetting(SettingBean setting) {
            this.setting = setting;
        }

        public static class DetailBean {
            /**
             * goods_id : 11709
             * goods_name : Men Boots Shoes
             * selling_point : XWB5
             * category_id : 0
             * spec_type : 10
             * deduct_stock_type : 10
             * content : <p>ArtNo: XWB5<br style="box-sizing: border-box;"/>Category: Shoes<br style="box-sizing: border-box;"/></p><p style="box-sizing: border-box; margin-bottom: 10px;"><span style="box-sizing: border-box; display: block; font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; font-size: 16px; line-height: 30px; font-family: arial, helvetica, sans-serif; color: rgb(102, 102, 102); white-space: normal;">&nbsp;Upper:PU<br style="box-sizing: border-box;"/>&nbsp;Lining:Synthetic<br style="box-sizing: border-box;"/>&nbsp;Insole:Synthetic<br style="box-sizing: border-box;"/>&nbsp;Outsole:TPR<br style="box-sizing: border-box;"/>&nbsp;Packing:BOX<br style="box-sizing: border-box;"/></span></p><p><br/></p>
             * goods_sort : 100
             * buy_limit : 0
             * least_expend_money : 0.00
             * delivery_id : 10001
             * is_new_user_enjoy : 0
             * is_points_gift : 1
             * is_points_discount : 1
             * is_enable_balance_price : 0
             * is_enable_grade : 1
             * is_alone_grade : 0
             * alone_grade_equity : null
             * is_ind_dealer : 0
             * dealer_money_type : 10
             * first_money : 0.00
             * second_money : 0.00
             * third_money : 0.00
             * is_rush : 0
             * is_points_exchange : 1
             * need_points_num : 5000
             * need_points_money : 0.00
             * goods_status : {"text":"上架","value":10}
             * comment_data_count : 0
             * goods_stock : 110
             * goods_image : http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png
             * goods_sku : {"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null}
             * goods_multi_spec : null
             * is_user_grade : false
             * bought_num : 0
             * category : null
             * image : [{"id":22548,"goods_id":11709,"image_id":19894,"file_path":"http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png","file_name":"202007071528093a25a0661.png","file_url":""}]
             * sku : [{"goods_sku_id":22475,"goods_id":11709,"spec_sku_id":"0","image_id":0,"goods_no":"XWB5","balance_price":"0.00","goods_price":"1111.00","line_price":"1111.00","stock_num":110,"goods_sales":1,"goods_weight":0,"image":null}]
             * comment_data : []
             * goods_sales : 1
             */

            private int goods_id;
            private String goods_name;
            private String selling_point;
            private int category_id;
            private int spec_type;
            private int deduct_stock_type;
            private String content;
            private int goods_sort;
            private int buy_limit;
            private String least_expend_money;
            private int delivery_id;
            private int is_new_user_enjoy;
            private int is_points_gift;
            private int is_points_discount;
            private int is_enable_balance_price;
            private int is_enable_grade;
            private int is_alone_grade;
            private Object alone_grade_equity;
            private int is_ind_dealer;
            private int dealer_money_type;
            private String first_money;
            private String second_money;
            private String third_money;
            private int is_rush;
            private int is_points_exchange;
            private int need_points_num;
            private String need_points_money;
            private GoodsStatusBean goods_status;
            private int comment_data_count;
            private int goods_stock;
            private String goods_image;
            private GoodsSkuBean goods_sku;
            private Object goods_multi_spec;
            private boolean is_user_grade;
            private int bought_num;
            private Object category;
            private int goods_sales;
            private List<ImageBean> image;
            private List<SkuBean> sku;
            private List<?> comment_data;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getSelling_point() {
                return selling_point;
            }

            public void setSelling_point(String selling_point) {
                this.selling_point = selling_point;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public int getSpec_type() {
                return spec_type;
            }

            public void setSpec_type(int spec_type) {
                this.spec_type = spec_type;
            }

            public int getDeduct_stock_type() {
                return deduct_stock_type;
            }

            public void setDeduct_stock_type(int deduct_stock_type) {
                this.deduct_stock_type = deduct_stock_type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getGoods_sort() {
                return goods_sort;
            }

            public void setGoods_sort(int goods_sort) {
                this.goods_sort = goods_sort;
            }

            public int getBuy_limit() {
                return buy_limit;
            }

            public void setBuy_limit(int buy_limit) {
                this.buy_limit = buy_limit;
            }

            public String getLeast_expend_money() {
                return least_expend_money;
            }

            public void setLeast_expend_money(String least_expend_money) {
                this.least_expend_money = least_expend_money;
            }

            public int getDelivery_id() {
                return delivery_id;
            }

            public void setDelivery_id(int delivery_id) {
                this.delivery_id = delivery_id;
            }

            public int getIs_new_user_enjoy() {
                return is_new_user_enjoy;
            }

            public void setIs_new_user_enjoy(int is_new_user_enjoy) {
                this.is_new_user_enjoy = is_new_user_enjoy;
            }

            public int getIs_points_gift() {
                return is_points_gift;
            }

            public void setIs_points_gift(int is_points_gift) {
                this.is_points_gift = is_points_gift;
            }

            public int getIs_points_discount() {
                return is_points_discount;
            }

            public void setIs_points_discount(int is_points_discount) {
                this.is_points_discount = is_points_discount;
            }

            public int getIs_enable_balance_price() {
                return is_enable_balance_price;
            }

            public void setIs_enable_balance_price(int is_enable_balance_price) {
                this.is_enable_balance_price = is_enable_balance_price;
            }

            public int getIs_enable_grade() {
                return is_enable_grade;
            }

            public void setIs_enable_grade(int is_enable_grade) {
                this.is_enable_grade = is_enable_grade;
            }

            public int getIs_alone_grade() {
                return is_alone_grade;
            }

            public void setIs_alone_grade(int is_alone_grade) {
                this.is_alone_grade = is_alone_grade;
            }

            public Object getAlone_grade_equity() {
                return alone_grade_equity;
            }

            public void setAlone_grade_equity(Object alone_grade_equity) {
                this.alone_grade_equity = alone_grade_equity;
            }

            public int getIs_ind_dealer() {
                return is_ind_dealer;
            }

            public void setIs_ind_dealer(int is_ind_dealer) {
                this.is_ind_dealer = is_ind_dealer;
            }

            public int getDealer_money_type() {
                return dealer_money_type;
            }

            public void setDealer_money_type(int dealer_money_type) {
                this.dealer_money_type = dealer_money_type;
            }

            public String getFirst_money() {
                return first_money;
            }

            public void setFirst_money(String first_money) {
                this.first_money = first_money;
            }

            public String getSecond_money() {
                return second_money;
            }

            public void setSecond_money(String second_money) {
                this.second_money = second_money;
            }

            public String getThird_money() {
                return third_money;
            }

            public void setThird_money(String third_money) {
                this.third_money = third_money;
            }

            public int getIs_rush() {
                return is_rush;
            }

            public void setIs_rush(int is_rush) {
                this.is_rush = is_rush;
            }

            public int getIs_points_exchange() {
                return is_points_exchange;
            }

            public void setIs_points_exchange(int is_points_exchange) {
                this.is_points_exchange = is_points_exchange;
            }

            public int getNeed_points_num() {
                return need_points_num;
            }

            public void setNeed_points_num(int need_points_num) {
                this.need_points_num = need_points_num;
            }

            public String getNeed_points_money() {
                return need_points_money;
            }

            public void setNeed_points_money(String need_points_money) {
                this.need_points_money = need_points_money;
            }

            public GoodsStatusBean getGoods_status() {
                return goods_status;
            }

            public void setGoods_status(GoodsStatusBean goods_status) {
                this.goods_status = goods_status;
            }

            public int getComment_data_count() {
                return comment_data_count;
            }

            public void setComment_data_count(int comment_data_count) {
                this.comment_data_count = comment_data_count;
            }

            public int getGoods_stock() {
                return goods_stock;
            }

            public void setGoods_stock(int goods_stock) {
                this.goods_stock = goods_stock;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public GoodsSkuBean getGoods_sku() {
                return goods_sku;
            }

            public void setGoods_sku(GoodsSkuBean goods_sku) {
                this.goods_sku = goods_sku;
            }

            public Object getGoods_multi_spec() {
                return goods_multi_spec;
            }

            public void setGoods_multi_spec(Object goods_multi_spec) {
                this.goods_multi_spec = goods_multi_spec;
            }

            public boolean isIs_user_grade() {
                return is_user_grade;
            }

            public void setIs_user_grade(boolean is_user_grade) {
                this.is_user_grade = is_user_grade;
            }

            public int getBought_num() {
                return bought_num;
            }

            public void setBought_num(int bought_num) {
                this.bought_num = bought_num;
            }

            public Object getCategory() {
                return category;
            }

            public void setCategory(Object category) {
                this.category = category;
            }

            public int getGoods_sales() {
                return goods_sales;
            }

            public void setGoods_sales(int goods_sales) {
                this.goods_sales = goods_sales;
            }

            public List<ImageBean> getImage() {
                return image;
            }

            public void setImage(List<ImageBean> image) {
                this.image = image;
            }

            public List<SkuBean> getSku() {
                return sku;
            }

            public void setSku(List<SkuBean> sku) {
                this.sku = sku;
            }

            public List<?> getComment_data() {
                return comment_data;
            }

            public void setComment_data(List<?> comment_data) {
                this.comment_data = comment_data;
            }

            public static class GoodsStatusBean {
                /**
                 * text : 上架
                 * value : 10
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

            public static class GoodsSkuBean {
                /**
                 * goods_sku_id : 22475
                 * goods_id : 11709
                 * spec_sku_id : 0
                 * image_id : 0
                 * goods_no : XWB5
                 * balance_price : 0.00
                 * goods_price : 1111.00
                 * line_price : 1111.00
                 * stock_num : 110
                 * goods_sales : 1
                 * goods_weight : 0
                 * image : null
                 */

                private int goods_sku_id;
                private int goods_id;
                private String spec_sku_id;
                private int image_id;
                private String goods_no;
                private String balance_price;
                private String goods_price;
                private String line_price;
                private int stock_num;
                private int goods_sales;
                private int goods_weight;
                private Object image;

                public int getGoods_sku_id() {
                    return goods_sku_id;
                }

                public void setGoods_sku_id(int goods_sku_id) {
                    this.goods_sku_id = goods_sku_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getSpec_sku_id() {
                    return spec_sku_id;
                }

                public void setSpec_sku_id(String spec_sku_id) {
                    this.spec_sku_id = spec_sku_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public String getGoods_no() {
                    return goods_no;
                }

                public void setGoods_no(String goods_no) {
                    this.goods_no = goods_no;
                }

                public String getBalance_price() {
                    return balance_price;
                }

                public void setBalance_price(String balance_price) {
                    this.balance_price = balance_price;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getLine_price() {
                    return line_price;
                }

                public void setLine_price(String line_price) {
                    this.line_price = line_price;
                }

                public int getStock_num() {
                    return stock_num;
                }

                public void setStock_num(int stock_num) {
                    this.stock_num = stock_num;
                }

                public int getGoods_sales() {
                    return goods_sales;
                }

                public void setGoods_sales(int goods_sales) {
                    this.goods_sales = goods_sales;
                }

                public int getGoods_weight() {
                    return goods_weight;
                }

                public void setGoods_weight(int goods_weight) {
                    this.goods_weight = goods_weight;
                }

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }
            }

            public static class ImageBean {
                /**
                 * id : 22548
                 * goods_id : 11709
                 * image_id : 19894
                 * file_path : http://qingong.meiliancheng.cn/uploads/202007071528093a25a0661.png
                 * file_name : 202007071528093a25a0661.png
                 * file_url :
                 */

                private int id;
                private int goods_id;
                private int image_id;
                private String file_path;
                private String file_name;
                private String file_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
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
            }

            public static class SkuBean {
                /**
                 * goods_sku_id : 22475
                 * goods_id : 11709
                 * spec_sku_id : 0
                 * image_id : 0
                 * goods_no : XWB5
                 * balance_price : 0.00
                 * goods_price : 1111.00
                 * line_price : 1111.00
                 * stock_num : 110
                 * goods_sales : 1
                 * goods_weight : 0
                 * image : null
                 */

                private int goods_sku_id;
                private int goods_id;
                private String spec_sku_id;
                private int image_id;
                private String goods_no;
                private String balance_price;
                private String goods_price;
                private String line_price;
                private int stock_num;
                private int goods_sales;
                private int goods_weight;
                private Object image;

                public int getGoods_sku_id() {
                    return goods_sku_id;
                }

                public void setGoods_sku_id(int goods_sku_id) {
                    this.goods_sku_id = goods_sku_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public String getSpec_sku_id() {
                    return spec_sku_id;
                }

                public void setSpec_sku_id(String spec_sku_id) {
                    this.spec_sku_id = spec_sku_id;
                }

                public int getImage_id() {
                    return image_id;
                }

                public void setImage_id(int image_id) {
                    this.image_id = image_id;
                }

                public String getGoods_no() {
                    return goods_no;
                }

                public void setGoods_no(String goods_no) {
                    this.goods_no = goods_no;
                }

                public String getBalance_price() {
                    return balance_price;
                }

                public void setBalance_price(String balance_price) {
                    this.balance_price = balance_price;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getLine_price() {
                    return line_price;
                }

                public void setLine_price(String line_price) {
                    this.line_price = line_price;
                }

                public int getStock_num() {
                    return stock_num;
                }

                public void setStock_num(int stock_num) {
                    this.stock_num = stock_num;
                }

                public int getGoods_sales() {
                    return goods_sales;
                }

                public void setGoods_sales(int goods_sales) {
                    this.goods_sales = goods_sales;
                }

                public int getGoods_weight() {
                    return goods_weight;
                }

                public void setGoods_weight(int goods_weight) {
                    this.goods_weight = goods_weight;
                }

                public Object getImage() {
                    return image;
                }

                public void setImage(Object image) {
                    this.image = image;
                }
            }
        }

        public static class SettingBean {
            /**
             * points_name : 积分
             */

            private String points_name;

            public String getPoints_name() {
                return points_name;
            }

            public void setPoints_name(String points_name) {
                this.points_name = points_name;
            }
        }
    }
}
