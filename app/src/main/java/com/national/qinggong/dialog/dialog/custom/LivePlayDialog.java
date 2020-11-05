package com.national.qinggong.dialog.dialog.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;

import com.national.qinggong.R;
import com.national.qinggong.dialog.BaseDialog;

public class LivePlayDialog<D extends LivePlayDialog> extends BaseDialog<D> {


    public LivePlayDialog(@NonNull Context context) {
        super(context);
        this.setScaleWidth(0.8f);//宽度为手机屏幕的90%
        this.setGravity(Gravity.CENTER);//显示在手机屏幕中间
    }

    @Override
    public void onClick(View v, int id) {
        if (onDialogClickListener != null) {
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
        return R.layout.dialog_play_live;
    }

    @Override
    public void onCreateData() {
        setOnCilckListener(R.id.submit_alert);
    }

}
