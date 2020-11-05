package com.national.qinggong.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**

 * @version V2.1.0
 * @Title: ${}
 * @Description: ${todo}(工具类)
 * @date ${date} 下午4:28
 */
public class CommonUtils {
    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }




    static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }


    public static String getTopActivity(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null)
            if (Build.VERSION.SDK_INT >= 29) {
                return runningTaskInfos.get(0).topActivity.getClassName();
            }
        else
            return "";
        return null;
    }

    /***
     * 对快速点击事件进行控制，防止多次点击
     *
     * @return
     */

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }




    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    // 替换、过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!");//替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String fomart) {
        String thisDateTime = null;
        String fomarts = null;
        try {
            fomarts = fomart == null ? "yyyy-MM-dd HH:mm" : fomart;
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(fomarts);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }

    /**
     * 格式到天
     *
     * @param time
     * @return
     */
    public static int getDay(long time) {
        int mDay = 0;
        try {
            String data = new SimpleDateFormat("yyyy-MM-dd").format(time);

            mDay = Integer.parseInt(data.split("-")[2]);
        } catch (Exception e) {
        }


        return mDay;
    }


    /**
     * 隐藏软键盘
     */
    public static void hideSoftKeybord(Context activity) {

        if (null == activity) {
            return;
        }
        try {
            final View v = ((Activity) activity).getWindow().peekDecorView();
            if (v != null && v.getWindowToken() != null) {
                InputMethodManager imm = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 打开键盘.
     *
     * @param context the context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //天 时 分 秒 对应的毫秒数
    public static Long dayMsec = 1000l * 60 * 60 * 24;
    public static Long hourMsec = 1000l * 60 * 60;
    public static Long minMsec = 1000l * 60;

    /**
     * 返回倒计时 时长
     *
     * @param timeStr 时间戳
     * @return
     */
    public static String getRemainigTime(Long timeStr) {
        String remainingTime = "";
        if (timeStr == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        long intervalTime = timeStr;
        if (intervalTime > 0) {
            Long day = intervalTime / 1000 / 60 / 60 / 24;
            Long hour = (intervalTime / (1000 * 60 * 60) - day * 24);
            Long min = (intervalTime / (1000 * 60) - day * 24 * 60 - hour * 60);
            Long second = (intervalTime / (1000) - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            sb.append(day + " 天 " + hour + " 时 " + min + " 分 " + second + " 秒 ");
        } else {
            sb.append("0 天 " + "0 时 " + "0 分 " + "0 秒 ");
        }
        return sb.toString();
    }

    public static String time2(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        try {
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 返回倒计时 时长 显示时分秒
     *
     * @param timeStr 时间戳
     * @return
     */
    public static String getInterValTime(Long timeStr) {
        String remainingTime = "";
        if (timeStr == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        String hourStr, minStr, secondStr;
        long intervalTime = timeStr;
        if (intervalTime > 0) {
            Long hour = intervalTime / (1000 * 60 * 60);
            Long min = (intervalTime / (1000 * 60) - hour * 60);
            Long second = (intervalTime / (1000) - hour * 60 * 60 - min * 60);

            hourStr = hour <= 9 ? "0" + hour : String.valueOf(hour);
            minStr = min <= 9 ? "0" + min : String.valueOf(min);
            secondStr = second <= 9 ? "0" + second : String.valueOf(second);

            sb.append(hourStr + "," + minStr + "," + secondStr);
        } else {
            sb.append("0,0,0");

        }
        return sb.toString();
    }


    /**
     * 24小时制转化成12小时制
     *
     * @param
     */
    public static String timeFormatStr() {
        Calendar calendar = Calendar.getInstance();
        String tempStr = "";
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 11) {
            tempStr = "下午";
        } else {
            tempStr = "上午";
        }
        return tempStr;
    }


    /**
     * 时间转化为星期
     *
     * @param
     */
    public static String getWeek(long time) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(time));

        int year = cd.get(Calendar.YEAR); //获取年份
        int month = cd.get(Calendar.MONTH); //获取月份
        int day = cd.get(Calendar.DAY_OF_MONTH); //获取日期
        int week = cd.get(Calendar.DAY_OF_WEEK); //获取星期

        String weekString;
        switch (week) {
            case Calendar.SUNDAY:
                weekString = "周日";
                break;
            case Calendar.MONDAY:
                weekString = "周一";
                break;
            case Calendar.TUESDAY:
                weekString = "周二";
                break;
            case Calendar.WEDNESDAY:
                weekString = "周三";
                break;
            case Calendar.THURSDAY:
                weekString = "周四";
                break;
            case Calendar.FRIDAY:
                weekString = "周五";
                break;
            default:
                weekString = "周六";
                break;

        }

        return weekString;
    }

    /**
     * 时间转化为星期
     *
     * @param
     */
    public static String getWeekScend(long time) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(time));
        int year = cd.get(Calendar.YEAR); //获取年份
        int month = cd.get(Calendar.MONTH); //获取月份
        int day = cd.get(Calendar.DAY_OF_MONTH); //获取日期
//        int week = cd.get(Calendar.DAY_OF_WEEK); //获取星期
        int week = cd.get(Calendar. DAY_OF_WEEK) ;
//        if (week == 0 ) {
//            week = 7 ;
//        }
        String weekString;
        switch (week) {
            case Calendar.SUNDAY:
                weekString = "星期日";
                break;
            case Calendar.MONDAY:
                weekString = "星期一";
                break;
            case Calendar.TUESDAY:
                weekString = "星期二";
                break;
            case Calendar.WEDNESDAY:
                weekString = "星期三";
                break;
            case Calendar.THURSDAY:
                weekString = "星期四";
                break;
            case Calendar.FRIDAY:
                weekString = "星期五";
                break;
            default:
                weekString = "星期六";
                break;

        }

        return weekString;
    }




    /**
     * 时间转化为星期
     *
     * @param
     */
    public static String getWeekScendThree(long time) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(time));

        int year = cd.get(Calendar.YEAR); //获取年份
        int month = cd.get(Calendar.MONTH); //获取月份
        int day = cd.get(Calendar.DAY_OF_MONTH); //获取日期
        int week = cd.get(Calendar.DAY_OF_WEEK); //获取星期


        String weeks = "";
        try {
            switch ((day % 7 + 1)) {
                case 1:
                    weeks = "一";
                    break;
                case 2:
                    weeks = "二";
                    break;
                case 3:
                    weeks = "三";
                    break;
                case 4:
                    weeks = "四";
                    break;
                case 5:
                    weeks = "五";
                    break;
                default:
                    weeks = "";
                    break;
            }

        } catch (Exception e) {
        }



        return "（第"+weeks+"周)";
    }

    /**
     * 中间4位使用*替换
     * @param phone
     * @return
     */
    public static String midleReplaceStar(String phone){
        String result=null;
        if (!StringUtils.isEmpty(phone)){
            if (phone.length()<7){
                result=phone;
            }else{
                String start = phone.substring(0,3);
                String end = phone.substring(phone.length()-4,phone.length());
                StringBuilder sb=new StringBuilder();
                sb.append(start).append("****").append(end);
                result=sb.toString();
            }
        }
        return result;
    }


    public static String subZeroAndDot(String s) {

        if (TextUtils.isEmpty(s) || s.equals("null")) {
            return "0";
        }

        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

}
