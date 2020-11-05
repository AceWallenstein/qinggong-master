package com.national.qinggong.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.national.qinggong.MyApplication;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author Alex
 * @version V1.2.1
 * @Title: ${}
 * @Description: ${todo}(字符串相关工具类)
 * @date ${date} 上午11:16
 */
public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0||"".equals(s.toString().trim());
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * If u want more please visit http://toutiao.com/i6231678548520731137/
     */









    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 获取正则匹配的部分
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return 正则匹配的部分
     */
    public static List<String> getMatches(String regex, CharSequence input) {
        if (input == null) return null;
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * 获取正则匹配分组
     *
     * @param input 要分组的字符串
     * @param regex 正则表达式
     * @return 正则匹配分组
     */
    public static String[] getSplits(String input, String regex) {
        if (input == null) return null;
        return input.split(regex);
    }

    /**
     * 替换正则匹配的第一部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换正则匹配的第一部分
     */
    public static String getReplaceFirst(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * 替换所有正则匹配的部分
     *
     * @param input       要替换的字符串
     * @param regex       正则表达式
     * @param replacement 代替者
     * @return 替换所有正则匹配的部分
     */
    public static String getReplaceAll(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }


    /**
     * 提取出城市或者县
     *
     * @param city
     * @param district
     * @return
     */
    public static String extractLocation(final String city, final String district) {
        return district.contains("县") ? district.substring(0, district.length() - 1) : city.substring(0, city.length() - 1);
    }

    public static String getFromAssets(Context context, String fileName) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            inputReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    /**
     * 检查当前真实姓名是否符合姓名规则
     *
     * @param realName
     * @return true:不符合  false :符合
     */
    public static boolean checkRealName(String realName) {
        return isEmpty(realName) || realName.length() < 2 || realName.length() > 12;
    }

    /**
     * 检查当前真实姓名是否符合姓名规则  （汉子2~6 英文3~11）
     *
     * @param realName
     * @return true:符合  false :不符合
     */
    public static boolean matchRealName(String realName) {
        String reg = "(([\\u4E00-\\u9FA5]{2,6})|([a-zA-Z]{3,11}))";
        if (TextUtils.isEmpty(realName)) {
            return false;
        }
        return realName.matches(reg);
    }

    /**
     * 字符是否为汉字
     *
     * @param c
     * @return
     */
    public static boolean isChineseChar(char c) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        return matcher.matches();
    }

    /**
     * 效验姓名
     *
     * @param s
     * @param more 多于等于几个汉字
     * @param less 少于等于几个汉字
     * 其中more必须小于less,两者都大于0
     * @return true 符合 false 不符合
     */
    public static boolean verifyRealName(String s,int more,int less) {
        int len = length(s);
        if (len <= 0) {
            return false;
        }
        if (s.length() < more || s.length() > less) {
            return false;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char aChar = chars[i];
            boolean chineseChar = StringUtils.isChineseChar(aChar);
            if (!chineseChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 效验姓名
     *
     * @param s
     * @return true 符合 false 不符合
     */
    public static boolean verifyRealName(String s) {
        int len = length(s);
        if (len <= 0) {
            return false;
        }
        if (s.length() < 2 || s.length() > 12) {
            return false;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            char aChar = chars[i];
            boolean chineseChar = StringUtils.isChineseChar(aChar);
            if (!chineseChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查当前企业名称是否符合规则  （汉字2~30）
     *
     * @param companyName
     * @return true:符合  false :不符合
     */
    public static boolean matchCompanyName(String companyName) {
        String reg = "([\\u4E00-\\u9FA5]{2,30})";
        if (TextUtils.isEmpty(companyName)) {
            return false;
        }
        return companyName.matches(reg);
    }

    /**
     * @author Xyjian
     * @version: 1.8.2
     * @Description: TODO(){字符串是否符合字符串验证规则}
     * @date: 2017/10/25 10:14
     */
    public static boolean matchStringLength(String str, int limitMin, int limitMax) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = getLength(str);

        if (length >= limitMin && length <= limitMax) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Xyjian
     * @version: 1.8.2
     * @Description: TODO(){字符串的长度}
     * @date: 2017/10/25 10:14
     */
    public static int getLength(String str) {
        if (str == null)
            return 0;
        char c[] = str.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i]))
                len++;
        }

        return len;
    }

    public static boolean isLetter(char c) {
        int k = 128;
        return c / k == 0;
    }







    /*
    public static String getCompanyHomeUrl(Context context,String companyId,String branchId){
        return API.TO_COMPANY_DETAIL+"companyId="+companyId+
                "&appSource=android"
                +"&appkey="+ParamUtils.getAppKey()+"&token="+ParamUtils.getToken()
                +"&clientId="+ParamUtils.getClientId()+"&branchId="+branchId+"&companyCode="+"";
    }
    */
    public static void spannColorAndSize(final TextView inputtextview, String firstinput, String secondinput, int color_first, int color_second, int fontSize) {
        String text = inputtextview.getText().toString().trim();
        if(StringUtils.isEmpty(text) ){
            return;
        }
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan colorSpan_first = new ForegroundColorSpan(color_first);
        ForegroundColorSpan colorSpan_second = new ForegroundColorSpan(color_second);
        if (!StringUtils.isEmpty(firstinput)){
            int end = text.indexOf(firstinput);// 索引值
            spannableString.setSpan(colorSpan_first, end, end + firstinput.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            if(fontSize > 0) {
                spannableString.setSpan(new AbsoluteSizeSpan(fontSize, true), end, end + firstinput.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        if (!StringUtils.isEmpty(secondinput)){
            int secondinput_end = text.lastIndexOf(secondinput);
            spannableString.setSpan(colorSpan_second, secondinput_end, secondinput_end + secondinput.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            if(fontSize > 0) {
                spannableString.setSpan(new AbsoluteSizeSpan(fontSize, true),secondinput_end, secondinput_end + secondinput.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        inputtextview.setText(spannableString);
    }
    /*
      * operator获取网络运营商 1.移动流量 2.联通流量网络 3.电信流量网络
      *
      * */
    public static boolean return0peratorType(String operatortype_str) {
        if (!isEmpty(operatortype_str)){
            if (!operatortype_str.equals("2")){
                return true;
            }
        }
        return false;
    }
    /**
     * @param color 关键字颜色
     * @param text 文本
     * @param keyword 关键字
     * @return
     */
    public static SpannableString getHighLightKeyWord(int color, String text, String keyword) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return s;
    }


/*
* netType 网络状态：0未知；1流量 2 wifi；3 数据流量+wifi
* */
    public static boolean returnnetType(String networktype_str) {
        if (!isEmpty(networktype_str)){
            if (networktype_str.equals("1")||networktype_str.equals("3")){
                return true;
            }
        }
        return false;
    }





/*
* [a-zA-Z]{3,12}


[\u4e00-\u9fa5]{2,6}
* */
//public  void  inputUserName(String  userName){
//    String txt = userName;

//    Pattern p = Pattern.compile("[0-9]*");
//    Matcher m = p.matcher(txt);
//    if(m.matches() ){
////        Toast.makeText(Main.this,"输入的是数字", Toast.LENGTH_SHORT).show();
//    }
//    p=Pattern.compile("[a-zA-Z]{3,12}");
//    m=p.matcher(txt);
//    if(m.matches()){
////        Toast.makeText(Main.this,"输入的是字母", Toast.LENGTH_SHORT).show();
//    }
//    p=Pattern.compile("[\\u4e00-\\u9fa5]{2,6}");
//    m=p.matcher(txt);
//    if(m.matches()){
////        Toast.makeText(Main.this,"输入的是汉字", Toast.LENGTH_SHORT).show();
//    }
//}

/*
* 中文
* */
public static boolean inputUserNameChinese(String userName) {
    Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]{2,6}");
    Matcher matcher = pattern.matcher(userName);
    return matcher.matches();
}
/*
* 英文
* */
public static boolean inputUserNameEG(String userName) {
    Pattern pattern = Pattern.compile("[a-zA-Z]{3,12}");
    Matcher matcher = pattern.matcher(userName);
    return matcher.matches();
}
/*
* 数字
* */
public static boolean inputUserNameNumber(String userName) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher matcher = pattern.matcher(userName);
    return matcher.matches();
}
public static String getChooseState(String State){
    String returnName = "";
    if (isEmpty(State)){
        returnName="";
    }
    switch (State){

        case "日k":
            returnName="1";
            break;
        case "周k":
            returnName="2";
            break;
        case "月k":
            returnName="3";
            break;
        case "5分钟":
            returnName="4";
            break;
        case "15分钟":
            returnName="5";
            break;
        case "30分钟":
            returnName="6";
            break;
        case "60分钟":
            returnName="7";
            break;
        case "120分钟":
            returnName="8";
            break;
        case "ticket"://分笔
            returnName="9";
            break;
        case "1分钟":
            returnName="10";
            break;
        case "分时":
            returnName="11";
            break;
        case "季度":
            returnName="15";
            break;
        case "1年":
            returnName="16";
            break;
        case "240分钟":
            returnName="17";
            break;
    }
    return returnName;
}







    /**
     * 判断是否包含SIM卡
     *
     * @return 状态
     */
    public static boolean hasSimCard() {
        Context context = MyApplication.getContext();
        TelephonyManager telMgr = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        boolean result = true;
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                result = false; // 没有SIM卡
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }
//        Log.d(TAG, result ? 有SIM卡 : 无SIM卡);
        return result;
    }

}
