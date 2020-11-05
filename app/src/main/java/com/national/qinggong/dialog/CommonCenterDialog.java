package com.national.qinggong.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;


/**
 */

public class CommonCenterDialog<D extends CommonCenterDialog> extends BaseDialog<D>{

    public CommonCenterDialog(@NonNull Context context) {
        super(context);
        this.setScaleWidth(0.8f);//宽度为手机屏幕的80%
        this.setGravity(Gravity.CENTER);//显示在手机屏幕中间
    }

    @Override
    public void onClick(View v, int id) {
        if(onDialogClickListener != null) {
            onDialogClickListener.onDialogClick(this, id);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void show(int animType) {
        super.show(animType);
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public void onCreateData() {

    }
/*

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_common_center;
    }

    @Override
    public void onCreateData() {
        setOnCilckListener(R.id.centerdialog_ok, R.id.centerdialog_cancle);
    }
*/


}
