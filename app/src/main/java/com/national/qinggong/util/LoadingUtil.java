package com.national.qinggong.util;

import android.content.Context;

public class LoadingUtil {
    private static LoadingDialog ld;

    public static void show(Context context) {
        ld = new LoadingDialog(context);
        ld.setProgressTitle("Loading……")
                .setInterceptBack(false)//拦截返回键
                .show();
    }

    public static void hide() {
        if(ld!=null){
            ld.dismiss();
        }
    }
}
