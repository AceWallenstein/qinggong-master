package com.national.qinggong.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtilMsg {

    private static Toast myToast;
    private static String oldMsg;

    public static void showToast(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (context == null) {
            return;
        }
        if (myToast == null) {
            myToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            myToast.setText(msg);
            myToast.setDuration(Toast.LENGTH_SHORT);
        }
        if(TextUtils.equals(oldMsg,msg)){//两个消息相同只显示一次
            myToast.cancel();
            myToast=null;
            if(myToast!=null){
                myToast.setText(msg);
                myToast.setDuration(Toast.LENGTH_SHORT);
                myToast.show();
            }else{
                myToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                myToast.show();
            }
        }else {
            myToast.show();
        }
        oldMsg = msg;
    }

    //利用上下文统一管理Toast生命周期，补充该方法后可在调用后直接miss
    public static void missToast(Context context) {
        if (myToast!=null){
            myToast.cancel();//仅仅为隐藏，如果不调用下面myToast=null在同一界面使用出现首次点击Toast不能正常弹出的问题
            myToast=null;
        }
    }


}
