package com.national.qinggong.util;

/**
 * 创建时间：2020/11/25
 * 编写人：czy_yangxudong
 * 功能描述：
 */
public class MyUtil {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
