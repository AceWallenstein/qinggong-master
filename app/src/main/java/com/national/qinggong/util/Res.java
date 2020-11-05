package com.national.qinggong.util;

import android.graphics.drawable.Drawable;

import com.national.qinggong.MyApplication;

/**

 */
public class Res {

    @SuppressWarnings("static-access")
    public static int getColor(int paramInt)
    {
        return MyApplication.getInstance().getContext().getResources().getColor(paramInt);
    }
    //
    @SuppressWarnings("static-access")
    public static Drawable getDrawable(int paramInt) {
        return MyApplication.getInstance().getContext().getResources().getDrawable(paramInt);
    }
    //
    @SuppressWarnings("static-access")
    public static String getString(int paramInt){
        return MyApplication.getInstance().getContext().getResources().getString(paramInt);
    }


    @SuppressWarnings("static-access")
    public static String[] getStringArray(int paramInt){
        return MyApplication.getInstance().getContext().getResources().getStringArray(paramInt);


    }
}
