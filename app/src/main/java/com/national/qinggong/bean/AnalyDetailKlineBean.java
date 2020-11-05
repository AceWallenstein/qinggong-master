package com.national.qinggong.bean;

import java.util.List;

/*
*13、得到指定股票的历史数据
* */
public class AnalyDetailKlineBean  {



    private String code;
    private int fsdays;
    private int kdays;
    private String mark;
    private int zhouqi;
    private List<HistoryPeriodDatasBean> HistoryPeriodDatas;
    private List<LastMinuteTickDatasBean> LastMinuteTickDatas;
    private List<LastTickDatasBean> LastTickDatas;
    private List<TodayMin1DatasBean> TodayMin1Datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFsdays() {
        return fsdays;
    }

    public void setFsdays(int fsdays) {
        this.fsdays = fsdays;
    }

    public int getKdays() {
        return kdays;
    }

    public void setKdays(int kdays) {
        this.kdays = kdays;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getZhouqi() {
        return zhouqi;
    }

    public void setZhouqi(int zhouqi) {
        this.zhouqi = zhouqi;
    }

    public List<HistoryPeriodDatasBean> getHistoryPeriodDatas() {
        return HistoryPeriodDatas;
    }

    public void setHistoryPeriodDatas(List<HistoryPeriodDatasBean> HistoryPeriodDatas) {
        this.HistoryPeriodDatas = HistoryPeriodDatas;
    }

    public List<LastMinuteTickDatasBean> getLastMinuteTickDatas() {
        return LastMinuteTickDatas;
    }

    public void setLastMinuteTickDatas(List<LastMinuteTickDatasBean> LastMinuteTickDatas) {
        this.LastMinuteTickDatas = LastMinuteTickDatas;
    }

    public List<LastTickDatasBean> getLastTickDatas() {
        return LastTickDatas;
    }

    public void setLastTickDatas(List<LastTickDatasBean> LastTickDatas) {
        this.LastTickDatas = LastTickDatas;
    }

    public List<TodayMin1DatasBean> getTodayMin1Datas() {
        return TodayMin1Datas;
    }

    public void setTodayMin1Datas(List<TodayMin1DatasBean> TodayMin1Datas) {
        this.TodayMin1Datas = TodayMin1Datas;
    }




    /*
    *
    *
    * * @param high   最高价
     * @param low    最低价
     * @param open   开盘价
     * @param close  收盘价
    *
    * */
    public static class HistoryPeriodDatasBean {
        /**
         * amount : 7.22119E8
         * close : 10.7699995
         * high : 11.21
         * intDate : 20190509
         * intTime : 0
         * low : 10.75
         * open : 11.1299992
         * vol : 641280
         */

        private double amount;
        private float close;
        private float high;
        private Object intDate;
        private Object intTime;
        private float low;
        private double open;
        private float vol;

        /*
        *
        * 处理数据中的ma5
        *
        * */
        public float MA5Price;
        public float MA6Price;
        public float MA7Price;
        public float MA8Price;
        public float MA9Price;


        /*macd
        *
        * */
        public float dea;

        public float dif;

        public float macd;


        /*
        *
        * boll
        * */
        public float up;

        public float mb;

        public float dn;


        /*
        * rsi 相对强弱
        * */
        public float rsi1;

        public float rsi2;

        public float rsi3;


        /*
        *
        * kdj
        * */
        public float k;

        public float d;

        public float j;


        /*
        *
        * 成交量
        * */
        public float MA5Volume;

        public float MA10Volume;



        /*wr指标
        *
        * */
        public float r;

        public float getR() {
            return r;
        }

        public void setR(float r) {
            this.r = r;
        }

        public float getMA5Volume() {
            return MA5Volume;
        }

        public void setMA5Volume(float MA5Volume) {
            this.MA5Volume = MA5Volume;
        }

        public float getMA10Volume() {
            return MA10Volume;
        }

        public void setMA10Volume(float MA10Volume) {
            this.MA10Volume = MA10Volume;
        }

        public float getK() {
            return k;
        }

        public void setK(float k) {
            this.k = k;
        }

        public float getD() {
            return d;
        }

        public void setD(float d) {
            this.d = d;
        }

        public float getJ() {
            return j;
        }

        public void setJ(float j) {
            this.j = j;
        }

        public float getRsi1() {
            return rsi1;
        }

        public void setRsi1(float rsi1) {
            this.rsi1 = rsi1;
        }

        public float getRsi2() {
            return rsi2;
        }

        public void setRsi2(float rsi2) {
            this.rsi2 = rsi2;
        }

        public float getRsi3() {
            return rsi3;
        }

        public void setRsi3(float rsi3) {
            this.rsi3 = rsi3;
        }

        public float getUp() {
            return up;
        }

        public void setUp(float up) {
            this.up = up;
        }

        public float getMb() {
            return mb;
        }

        public void setMb(float mb) {
            this.mb = mb;
        }

        public float getDn() {
            return dn;
        }

        public void setDn(float dn) {
            this.dn = dn;
        }

        public float getDea() {
            return dea;
        }

        public void setDea(float dea) {
            this.dea = dea;
        }

        public float getDif() {
            return dif;
        }

        public void setDif(float dif) {
            this.dif = dif;
        }

        public float getMacd() {
            return macd;
        }

        public void setMacd(float macd) {
            this.macd = macd;
        }

        public float MA10Price;

        public float MA20Price;

        public float getMA6Price() {
            return MA6Price;
        }

        public void setMA6Price(float MA6Price) {
            this.MA6Price = MA6Price;
        }

        public float getMA7Price() {
            return MA7Price;
        }

        public void setMA7Price(float MA7Price) {
            this.MA7Price = MA7Price;
        }

        public float getMA8Price() {
            return MA8Price;
        }

        public void setMA8Price(float MA8Price) {
            this.MA8Price = MA8Price;
        }

        public float getMA9Price() {
            return MA9Price;
        }

        public void setMA9Price(float MA9Price) {
            this.MA9Price = MA9Price;
        }

        public float getMA5Price() {
            return MA5Price;
        }

        public void setMA5Price(float MA5Price) {
            this.MA5Price = MA5Price;
        }

        public float getMA10Price() {
            return MA10Price;
        }

        public void setMA10Price(float MA10Price) {
            this.MA10Price = MA10Price;
        }

        public float getMA20Price() {
            return MA20Price;
        }

        public void setMA20Price(float MA20Price) {
            this.MA20Price = MA20Price;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public float getClosePrice() {
            return close;
        }

        public void setClose(float close) {
            this.close = close;
        }

        public float getHighPrice() {
            return high;
        }

        public void setHigh(float high) {
            this.high = high;
        }

        public Object getIntDate() {
            return intDate;
        }

        public void setIntDate(Object intDate) {
            this.intDate = intDate;
        }

        public Object getIntTime() {
            return intTime;
        }

        public void setIntTime(Object intTime) {
            this.intTime = intTime;
        }

        public float getLowPrice() {
            return low;
        }

        public void setLow(float low) {
            this.low = low;
        }

        public double getOpenPrice() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public float getVolume() {
            return vol;
        }

        public void setVol(float vol) {
            this.vol = vol;
        }
    }

    public static class LastMinuteTickDatasBean {
        /**
         * Time : /Date(1588834682000+0800)/
         * amount : 2.680061E8
         * average : 216442.8
         * chicang : 0
         * close : 10.39
         * code : 600000
         * high : 10.46
         * jingliuchu : 0
         * jingliuru : 0
         * low : 10.37
         * mark : SH
         * market : null
         * open : 10.45
         * pbuy1 : 10.39
         * pbuy2 : 0
         * pbuy3 : 0
         * pbuy4 : 0
         * pbuy5 : 0
         * pre_amount : 0
         * pre_chicang : 0
         * pre_vol : 0
         * prev_close : 10.46
         * psale1 : 10.39
         * psale2 : 0
         * psale3 : 0
         * psale4 : 0
         * psale5 : 0
         * vbuy1 : 2936
         * vbuy2 : 0
         * vbuy3 : 0
         * vbuy4 : 0
         * vbuy5 : 0
         * vol : 257776
         * vsale1 : 2936
         * vsale2 : 658
         * vsale3 : 0
         * vsale4 : 0
         * vsale5 : 0
         * yes_chicang : 0
         */

        private Object  Time;
        private double amount;
        private double average;
        private int chicang;
        private double close;
        private String code;
        private double high;
        private int jingliuchu;
        private int jingliuru;
        private double low;
        private String mark;
        private Object market;
        private double open;
        private double pbuy1;
        private double pbuy2;
        private double pbuy3;
        private double pbuy4;
        private double pbuy5;
        private int pre_amount;
        private int pre_chicang;
        private int pre_vol;
        private double prev_close;
        private double psale1;
        private double psale2;
        private double psale3;
        private double psale4;
        private double psale5;
        private double vbuy1;
        private double vbuy2;
        private double vbuy3;
        private double vbuy4;
        private double vbuy5;
        private Object vol;
        private double vsale1;
        private double vsale2;
        private double vsale3;
        private double vsale4;
        private double vsale5;
        private double yes_chicang;

        public Object getTime() {
            return Time;
        }

        public void setTime(Object Time) {
            this.Time = Time;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public int getChicang() {
            return chicang;
        }

        public void setChicang(int chicang) {
            this.chicang = chicang;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public int getJingliuchu() {
            return jingliuchu;
        }

        public void setJingliuchu(int jingliuchu) {
            this.jingliuchu = jingliuchu;
        }

        public int getJingliuru() {
            return jingliuru;
        }

        public void setJingliuru(int jingliuru) {
            this.jingliuru = jingliuru;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public Object getMarket() {
            return market;
        }

        public void setMarket(Object market) {
            this.market = market;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getPbuy1() {
            return pbuy1;
        }

        public void setPbuy1(double pbuy1) {
            this.pbuy1 = pbuy1;
        }

        public double getPbuy2() {
            return pbuy2;
        }

        public void setPbuy2(double pbuy2) {
            this.pbuy2 = pbuy2;
        }

        public double getPbuy3() {
            return pbuy3;
        }

        public void setPbuy3(double pbuy3) {
            this.pbuy3 = pbuy3;
        }

        public double getPbuy4() {
            return pbuy4;
        }

        public void setPbuy4(double pbuy4) {
            this.pbuy4 = pbuy4;
        }

        public double getPbuy5() {
            return pbuy5;
        }

        public void setPbuy5(int pbuy5) {
            this.pbuy5 = pbuy5;
        }

        public int getPre_amount() {
            return pre_amount;
        }

        public void setPre_amount(int pre_amount) {
            this.pre_amount = pre_amount;
        }

        public int getPre_chicang() {
            return pre_chicang;
        }

        public void setPre_chicang(int pre_chicang) {
            this.pre_chicang = pre_chicang;
        }

        public int getPre_vol() {
            return pre_vol;
        }

        public void setPre_vol(int pre_vol) {
            this.pre_vol = pre_vol;
        }

        public double getPrev_close() {
            return prev_close;
        }

        public void setPrev_close(double prev_close) {
            this.prev_close = prev_close;
        }

        public double getPsale1() {
            return psale1;
        }

        public void setPsale1(double psale1) {
            this.psale1 = psale1;
        }

        public double getPsale2() {
            return psale2;
        }

        public void setPsale2(double psale2) {
            this.psale2 = psale2;
        }

        public double getPsale3() {
            return psale3;
        }

        public void setPsale3(int psale3) {
            this.psale3 = psale3;
        }

        public double getPsale4() {
            return psale4;
        }

        public void setPsale4(int psale4) {
            this.psale4 = psale4;
        }

        public double getPsale5() {
            return psale5;
        }

        public void setPsale5(double psale5) {
            this.psale5 = psale5;
        }

        public double getVbuy1() {
            return vbuy1;
        }

        public void setVbuy1(double vbuy1) {
            this.vbuy1 = vbuy1;
        }

        public double getVbuy2() {
            return vbuy2;
        }

        public void setVbuy2(double vbuy2) {
            this.vbuy2 = vbuy2;
        }

        public double getVbuy3() {
            return vbuy3;
        }

        public void setVbuy3(double vbuy3) {
            this.vbuy3 = vbuy3;
        }

        public double getVbuy4() {
            return vbuy4;
        }

        public void setVbuy4(double vbuy4) {
            this.vbuy4 = vbuy4;
        }

        public double getVbuy5() {
            return vbuy5;
        }

        public void setVbuy5(int vbuy5) {
            this.vbuy5 = vbuy5;
        }

        public Object getVol() {
            return vol;
        }

        public void setVol(Object vol) {
            this.vol = vol;
        }

        public double getVsale1() {
            return vsale1;
        }

        public void setVsale1(double vsale1) {
            this.vsale1 = vsale1;
        }

        public double getVsale2() {
            return vsale2;
        }

        public void setVsale2(double vsale2) {
            this.vsale2 = vsale2;
        }

        public double getVsale3() {
            return vsale3;
        }

        public void setVsale3(double vsale3) {
            this.vsale3 = vsale3;
        }

        public double getVsale4() {
            return vsale4;
        }

        public void setVsale4(double vsale4) {
            this.vsale4 = vsale4;
        }

        public double getVsale5() {
            return vsale5;
        }

        public void setVsale5(double vsale5) {
            this.vsale5 = vsale5;
        }

        public double getYes_chicang() {
            return yes_chicang;
        }

        public void setYes_chicang(int yes_chicang) {
            this.yes_chicang = yes_chicang;
        }
    }

    public static class LastTickDatasBean {
        /**
         * Time : /Date(1588834477000+0800)/
         * amount : 2.641317E8
         * average : 216442.8
         * chicang : 0
         * close : 10.38
         * code : 600000
         * high : 10.46
         * jingliuchu : 0
         * jingliuru : 0
         * low : 10.37
         * mark : SH
         * market : null
         * open : 10.45
         * pbuy1 : 10.38
         * pbuy2 : 10.37
         * pbuy3 : 10.36
         * pbuy4 : 10.35
         * pbuy5 : 10.34
         * pre_amount : 0
         * pre_chicang : 0
         * pre_vol : 0
         * prev_close : 10.46
         * psale1 : 10.39
         * psale2 : 10.4
         * psale3 : 10.41
         * psale4 : 10.42
         * psale5 : 10.43
         * vbuy1 : 1571
         * vbuy2 : 6044
         * vbuy3 : 4055
         * vbuy4 : 5920
         * vbuy5 : 507
         * vol : 254045
         * vsale1 : 1746
         * vsale2 : 2130
         * vsale3 : 186
         * vsale4 : 1770
         * vsale5 : 990
         * yes_chicang : 0
         */

        private String Time;
        private double amount;
        private double average;
        private int chicang;
        private double close;
        private String code;
        private double high;
        private int jingliuchu;
        private int jingliuru;
        private double low;
        private String mark;
        private Object market;
        private double open;
        private double pbuy1;
        private double pbuy2;
        private double pbuy3;
        private double pbuy4;
        private double pbuy5;
        private int pre_amount;
        private int pre_chicang;
        private int pre_vol;
        private double prev_close;
        private double psale1;
        private double psale2;
        private double psale3;
        private double psale4;
        private double psale5;
        private int vbuy1;
        private int vbuy2;
        private int vbuy3;
        private int vbuy4;
        private int vbuy5;
        private Object vol;
        private int vsale1;
        private int vsale2;
        private int vsale3;
        private int vsale4;
        private int vsale5;
        private int yes_chicang;

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public int getChicang() {
            return chicang;
        }

        public void setChicang(int chicang) {
            this.chicang = chicang;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public int getJingliuchu() {
            return jingliuchu;
        }

        public void setJingliuchu(int jingliuchu) {
            this.jingliuchu = jingliuchu;
        }

        public int getJingliuru() {
            return jingliuru;
        }

        public void setJingliuru(int jingliuru) {
            this.jingliuru = jingliuru;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public Object getMarket() {
            return market;
        }

        public void setMarket(Object market) {
            this.market = market;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getPbuy1() {
            return pbuy1;
        }

        public void setPbuy1(double pbuy1) {
            this.pbuy1 = pbuy1;
        }

        public double getPbuy2() {
            return pbuy2;
        }

        public void setPbuy2(double pbuy2) {
            this.pbuy2 = pbuy2;
        }

        public double getPbuy3() {
            return pbuy3;
        }

        public void setPbuy3(double pbuy3) {
            this.pbuy3 = pbuy3;
        }

        public double getPbuy4() {
            return pbuy4;
        }

        public void setPbuy4(double pbuy4) {
            this.pbuy4 = pbuy4;
        }

        public double getPbuy5() {
            return pbuy5;
        }

        public void setPbuy5(double pbuy5) {
            this.pbuy5 = pbuy5;
        }

        public int getPre_amount() {
            return pre_amount;
        }

        public void setPre_amount(int pre_amount) {
            this.pre_amount = pre_amount;
        }

        public int getPre_chicang() {
            return pre_chicang;
        }

        public void setPre_chicang(int pre_chicang) {
            this.pre_chicang = pre_chicang;
        }

        public int getPre_vol() {
            return pre_vol;
        }

        public void setPre_vol(int pre_vol) {
            this.pre_vol = pre_vol;
        }

        public double getPrev_close() {
            return prev_close;
        }

        public void setPrev_close(double prev_close) {
            this.prev_close = prev_close;
        }

        public double getPsale1() {
            return psale1;
        }

        public void setPsale1(double psale1) {
            this.psale1 = psale1;
        }

        public double getPsale2() {
            return psale2;
        }

        public void setPsale2(double psale2) {
            this.psale2 = psale2;
        }

        public double getPsale3() {
            return psale3;
        }

        public void setPsale3(double psale3) {
            this.psale3 = psale3;
        }

        public double getPsale4() {
            return psale4;
        }

        public void setPsale4(double psale4) {
            this.psale4 = psale4;
        }

        public double getPsale5() {
            return psale5;
        }

        public void setPsale5(double psale5) {
            this.psale5 = psale5;
        }

        public int getVbuy1() {
            return vbuy1;
        }

        public void setVbuy1(int vbuy1) {
            this.vbuy1 = vbuy1;
        }

        public int getVbuy2() {
            return vbuy2;
        }

        public void setVbuy2(int vbuy2) {
            this.vbuy2 = vbuy2;
        }

        public int getVbuy3() {
            return vbuy3;
        }

        public void setVbuy3(int vbuy3) {
            this.vbuy3 = vbuy3;
        }

        public int getVbuy4() {
            return vbuy4;
        }

        public void setVbuy4(int vbuy4) {
            this.vbuy4 = vbuy4;
        }

        public int getVbuy5() {
            return vbuy5;
        }

        public void setVbuy5(int vbuy5) {
            this.vbuy5 = vbuy5;
        }

        public Object getVol() {
            return vol;
        }

        public void setVol(Object vol) {
            this.vol = vol;
        }

        public int getVsale1() {
            return vsale1;
        }

        public void setVsale1(int vsale1) {
            this.vsale1 = vsale1;
        }

        public int getVsale2() {
            return vsale2;
        }

        public void setVsale2(int vsale2) {
            this.vsale2 = vsale2;
        }

        public int getVsale3() {
            return vsale3;
        }

        public void setVsale3(int vsale3) {
            this.vsale3 = vsale3;
        }

        public int getVsale4() {
            return vsale4;
        }

        public void setVsale4(int vsale4) {
            this.vsale4 = vsale4;
        }

        public int getVsale5() {
            return vsale5;
        }

        public void setVsale5(int vsale5) {
            this.vsale5 = vsale5;
        }

        public int getYes_chicang() {
            return yes_chicang;
        }

        public void setYes_chicang(int yes_chicang) {
            this.yes_chicang = yes_chicang;
        }
    }

    public static class TodayMin1DatasBean {
        /**
         * amount : 0
         * close : 10.49
         * high : 10.49
         * intDate : 20200507
         * intTime : 915
         * low : 10.49
         * open : 10.49
         * vol : 0
         */

        private int amount;
        private double close;
        private double high;
        private Object intDate;
        private int intTime;
        private double low;
        private double open;
        private Object vol;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public Object getIntDate() {
            return intDate;
        }

        public void setIntDate(Object intDate) {
            this.intDate = intDate;
        }

        public int getIntTime() {
            return intTime;
        }

        public void setIntTime(int intTime) {
            this.intTime = intTime;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public Object getVol() {
            return vol;
        }

        public void setVol(Object vol) {
            this.vol = vol;
        }
    }
}
