package com.national.qinggong.bean;

import java.util.List;
import com.national.qinggong.bean.AnalyDetailKlineBean.HistoryPeriodDatasBean;
/*
* 数据辅助类
*
* */
public class DataHelper {

    /**
     * 计算MA BOLL RSI KDJ MACD
     *
     * @param datas
     *  数据历史
     */
   public static void calculate(List<HistoryPeriodDatasBean> datas) {
        calculateMA(datas);
        calculateMACD(datas);
        calculateBOLL(datas);
        calculateRSI(datas);
        calculateKDJ(datas);
        calculateVolumeMA(datas);
    }
/*
* 成交量  ma5
* ma10
*
* */
    private static void calculateVolumeMA(List<HistoryPeriodDatasBean> entries) {
        float volumeMa5 = 0;
        float volumeMa10 = 0;

        for (int i = 0; i < entries.size(); i++) {
            HistoryPeriodDatasBean entry = entries.get(i);

            volumeMa5 += entry.getVolume();
            volumeMa10 += entry.getVolume();

            if (i >= 5) {

                volumeMa5 -= entries.get(i - 5).getVolume();
                entry.MA5Volume = (volumeMa5 / 5f);
            } else {

                entry.MA5Volume = (volumeMa5 / (i + 1f));
            }

            if (i >= 10) {
                volumeMa10 -= entries.get(i - 10).getVolume();
                entry.MA10Volume = (volumeMa10 / 5f);
            } else {
                entry.MA10Volume = (volumeMa10 / (i + 1f));
            }
        }
    }

    /*
* KDJ
* 
* */
    private static void calculateKDJ(List<HistoryPeriodDatasBean> datas) {
        float k = 0;
        float d = 0;

        for (int i = 0; i < datas.size(); i++) {
            HistoryPeriodDatasBean point = datas.get(i);
            final float closePrice = point.getClosePrice();
            int startIndex = i - 8;
            if (startIndex < 0) {
                startIndex = 0;
            }
            float max9 = Float.MIN_VALUE;
            float min9 = Float.MAX_VALUE;
            for (int index = startIndex; index <= i; index++) {
                max9 = Math.max(max9, datas.get(index).getHighPrice());
                min9 = Math.min(min9, datas.get(index).getLowPrice());

            }
            float rsv = 100f * (closePrice - min9) / (max9 - min9);
            if (i == 0) {
                k = rsv;
                d = rsv;
            } else {
                k = (rsv + 2f * k) / 3f;
                d = (k + 2f * d) / 3f;
            }
            point.k = k;
            point.d = d;
            point.j = 3f * k - 2 * d;
        }
    }

    /*
* 相对强弱指标RSI
* */
    private static void calculateRSI(List<HistoryPeriodDatasBean> datas) {
        float rsi1 = 0;
        float rsi2 = 0;
        float rsi3 = 0;
        float rsi1ABSEma = 0;
        float rsi2ABSEma = 0;
        float rsi3ABSEma = 0;
        float rsi1MaxEma = 0;
        float rsi2MaxEma = 0;
        float rsi3MaxEma = 0;
        for (int i = 0; i < datas.size(); i++) {
            HistoryPeriodDatasBean point = datas.get(i);
            final float closePrice = point.getClosePrice();
            if (i == 0) {
                rsi1 = 0;
                rsi2 = 0;
                rsi3 = 0;
                rsi1ABSEma = 0;
                rsi2ABSEma = 0;
                rsi3ABSEma = 0;
                rsi1MaxEma = 0;
                rsi2MaxEma = 0;
                rsi3MaxEma = 0;
            } else {
                float Rmax = Math.max(0, closePrice - datas.get(i - 1).getClosePrice());
                float RAbs = Math.abs(closePrice - datas.get(i - 1).getClosePrice());
                rsi1MaxEma = (Rmax + (6f - 1) * rsi1MaxEma) / 6f;
                rsi1ABSEma = (RAbs + (6f - 1) * rsi1ABSEma) / 6f;

                rsi2MaxEma = (Rmax + (12f - 1) * rsi2MaxEma) / 12f;
                rsi2ABSEma = (RAbs + (12f - 1) * rsi2ABSEma) / 12f;

                rsi3MaxEma = (Rmax + (24f - 1) * rsi3MaxEma) / 24f;
                rsi3ABSEma = (RAbs + (24f - 1) * rsi3ABSEma) / 24f;

                rsi1 = (rsi1MaxEma / rsi1ABSEma) * 100;
                rsi2 = (rsi2MaxEma / rsi2ABSEma) * 100;
                rsi3 = (rsi3MaxEma / rsi3ABSEma) * 100;
            }
            point.rsi1 = rsi1;
            point.rsi2 = rsi2;
            point.rsi3 = rsi3;
        }


    }

    /*
*  计算 BOLL 需要在计算ma之后进行
* */
    private static void calculateBOLL(List<HistoryPeriodDatasBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            HistoryPeriodDatasBean point = datas.get(i);
            final float closePrice = point.getClosePrice();
            if (i == 0) {
                point.mb = closePrice;
                point.up = Float.NaN;
                point.dn = Float.NaN;
            } else {
                int n = 20;
                if (i < 20) {
                    n = i + 1;
                }
                float md = 0;
                for (int j = i - n + 1; j <= i; j++) {
                    float c = datas.get(j).getClosePrice();
                    float m = point.getMA20Price();
                    float value = c - m;
                    md += value * value;
                }
                md = md / (n - 1);
                md = (float) Math.sqrt(md);
                point.mb = point.getMA20Price();
                point.up = point.mb + 2f * md;
                point.dn = point.mb - 2f * md;
            }
        }
    }

    /*
    *
    *
    *
    *
    *  第五天均值=（8.15+8.07+8.84+8.10+8.40）/5=8.31
    　　第六天均值=（8.07+8.84+8.10+8.40+9.10）/5=8.50
    　　第七天均值=（8.84+8.10+8.40+9.10+9.20）/5=8.73
    　　第八天均值=（8.10+8.40+9.10+9.20+9.10）/5=8.78
    　　第九天均值=（8.40+9.10+9.20+9.10+8.95）/5=8.95
    　　第十天均值=（9.10+9.20+9.10+8.95+8.70）/5=9.01
    *
    * */
    private static void calculateMA(List<HistoryPeriodDatasBean> datas) {
        float ma5 = 0;
        float ma6 = 0;
        float ma7 = 0;
        float ma8 = 0;
        float ma9 = 0;
        float ma10 = 0;
        float ma20 = 0;

        for (int i = 0; i < datas.size(); i++) {
            HistoryPeriodDatasBean point = datas.get(i);
            final double closePrice = point.getClosePrice();

            ma5 += closePrice;

            /*新增start*/
            ma6 += closePrice;
            ma7 += closePrice;
            ma8 += closePrice;
            ma9 += closePrice;
            /*end*/

            ma10 += closePrice;
            ma20 += closePrice;
            if (i >= 5) {
                ma5 -= datas.get(i - 5).getClosePrice();
                point.MA5Price = ma5 / 5f;
            } else {
                point.MA5Price = ma5 / (i + 1f);
            }
            if (i >= 6) {
                ma6 -= datas.get(i - 6).getClosePrice();
                point.MA6Price = ma6 / 6f;
            } else {
                point.MA6Price = ma6 / (i + 1f);
            }
            if (i >= 7) {
                ma7 -= datas.get(i - 7).getClosePrice();
                point.MA7Price = ma7 / 7f;
            } else {
                point.MA7Price = ma7 / (i + 1f);
            }
            if (i >= 8) {
                ma8 -= datas.get(i - 8).getClosePrice();
                point.MA8Price = ma8 / 8f;
            } else {
                point.MA8Price = ma8 / (i + 1f);
            }
            if (i >= 9) {
                ma9 -= datas.get(i - 9).getClosePrice();
                point.MA9Price = ma9 / 9f;
            } else {
                point.MA9Price = ma9 / (i + 1f);
            }

            if (i >= 10) {
                ma10 -= datas.get(i - 10).getClosePrice();
                point.MA10Price = ma10 / 10f;
            } else {
                point.MA10Price = ma10 / (i + 1f);
            }
            if (i >= 20) {
                ma20 -= datas.get(i - 20).getClosePrice();
                point.MA20Price = ma20 / 20f;
            } else {
                point.MA20Price = ma20 / (i + 1f);
            }
        }
    }





    /**
     * 计算macd
     *
     * @param datas
     */
    static void calculateMACD(List<HistoryPeriodDatasBean> datas) {
        float ema12 = 0;
        float ema26 = 0;
        float dif = 0;
        float dea = 0;
        float macd = 0;

        for (int i = 0; i < datas.size(); i++) {
            HistoryPeriodDatasBean point = datas.get(i);
            final float closePrice = point.getClosePrice();
            if (i == 0) {
                ema12 = closePrice;
                ema26 = closePrice;
            } else {
//                EMA（12） = 前一日EMA（12） X 11/13 + 今日收盘价 X 2/13
//                EMA（26） = 前一日EMA（26） X 25/27 + 今日收盘价 X 2/27
                ema12 = ema12 * 11f / 13f + closePrice * 2f / 13f;
                ema26 = ema26 * 25f / 27f + closePrice * 2f / 27f;
            }
//            DIF = EMA（12） - EMA（26） 。
//            今日DEA = （前一日DEA X 8/10 + 今日DIF X 2/10）
//            用（DIF-DEA）*2即为MACD柱状图。
            dif = ema12 - ema26;
            dea = dea * 8f / 10f + dif * 2f / 10f;
            macd = (dif - dea) * 2f;
            point.dif = dif;
            point.dea = dea;
            point.macd = macd;
        }

    }

    /**
     * 计算wr
     *
     * @param dataList
     */
    static void calculateWR(List<HistoryPeriodDatasBean> dataList) {
        Float r;
        for (int i = 0; i < dataList.size(); i++) {
            HistoryPeriodDatasBean point = dataList.get(i);
            int startIndex = i - 14;
            if (startIndex < 0) {
                startIndex = 0;
            }
            float max14 = Float.MIN_VALUE;
            float min14 = Float.MAX_VALUE;
            for (int index = startIndex; index <= i; index++) {
                max14 = Math.max(max14, dataList.get(index).getHighPrice());
                min14 = Math.min(min14, dataList.get(index).getLowPrice());
            }
            if (i < 13) {
                point.r = -10;
            } else {
                r = -100 * (max14 - dataList.get(i).getClosePrice()) / (max14 - min14);
                if (r.isNaN()) {
                    point.r = 0;
                } else {
                    point.r = r;
                }
            }
        }

    }

}
