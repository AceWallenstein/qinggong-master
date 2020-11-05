package com.national.qinggong.bean;

import java.io.Serializable;
import java.util.List;

/*
* 4、得到市场所有股票、期货合约的市场、代码、名称集合
*  的到板块
* */
public class StockBlockBean implements Serializable {

    private List<StockListbean> StockListbean;

    public List<StockBlockBean.StockListbean> getStockListbean() {
        return StockListbean;
    }

    public void setStockListbean(List<StockBlockBean.StockListbean> stockListbean) {
        StockListbean = stockListbean;
    }

   public class StockListbean implements Serializable{
        /**
         * IsNight : 0
         * block : 美国NE
         * code : PLF0
         * mark : NE
         * name : 铂金01月
         * pinyin : PLF0
         * tradeTimeID : 12
         */
        private boolean isSeleted = false;
        private int IsNight;
        private String block;
        private String code;
        private String mark;
        private String name;
        private String pinyin;
        private String tradeTimeID;


        /*新增*/
        private String M_code;
        private String New_Privce;
        private String zhang_die_fu;
        private String chengjiao_num;

        private String zhangdie_zhi;
        private String chicang_liang;

       public String getZhangdie_zhi() {
           return zhangdie_zhi;
       }

       public void setZhangdie_zhi(String zhangdie_zhi) {
           this.zhangdie_zhi = zhangdie_zhi;
       }

       public String getChicang_liang() {
           return chicang_liang;
       }

       public void setChicang_liang(String chicang_liang) {
           this.chicang_liang = chicang_liang;
       }

       /*新增*/
       public String getNew_Privce() {
           return New_Privce;
       }

       public void setNew_Privce(String new_Privce) {
           New_Privce = new_Privce;
       }

       public String getZhang_die_fu() {
           return zhang_die_fu;
       }

       public void setZhang_die_fu(String zhang_die_fu) {
           this.zhang_die_fu = zhang_die_fu;
       }

       public String getChengjiao_num() {
           return chengjiao_num;
       }

       public void setChengjiao_num(String chengjiao_num) {
           this.chengjiao_num = chengjiao_num;
       }

       public String getM_code() {
           return M_code;
       }

       public void setM_code(String m_code) {
           M_code = m_code;
       }

       public boolean isSeleted() {
           return isSeleted;
       }

       public void setSeleted(boolean seleted) {
           isSeleted = seleted;
       }
        public int getIsNight() {
            return IsNight;
        }

        public void setIsNight(int IsNight) {
            this.IsNight = IsNight;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
            this.block = block;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getTradeTimeID() {
            return tradeTimeID;
        }

        public void setTradeTimeID(String tradeTimeID) {
            this.tradeTimeID = tradeTimeID;
        }
    }

}
