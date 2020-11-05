package com.national.qinggong.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.national.qinggong.R;

public class LoadingDialog {

    private TextView tv_title;

    private Context mContext;
    private final Dialog mLoadingDialog;
    private long time = 1000;

    public LoadingDialog(Context context) {
        mContext = context;
        // 首先得到整个View
        @SuppressWarnings("all")
        View view = LayoutInflater.from(context).inflate(
                R.layout.fragment_progress_dialog, null);
        initView(view);
        // 创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context, R.style.loading_dialog) {
            @Override
            public void onBackPressed() {
                if (interceptBack) {
                    return;
                }
                mLoadingDialog.dismiss();
            }
        };
        // 设置返回键无效
        mLoadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        mLoadingDialog.setCancelable(!interceptBack);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mContext = null;
                if (dialog != null) dialog.dismiss();
            }
        });

    }
    private boolean interceptBack = true;
    private boolean canceledOnTouchOutside = true;

    public LoadingDialog setInterceptBack(boolean interceptBack) {
        this.interceptBack = interceptBack;
        mLoadingDialog.setCancelable(!interceptBack);
        return this;
    }
    public LoadingDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        mLoadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }
    public LoadingDialog setProgressTitle(String title) {
        tv_title.setText(title);
        return this;
    }

    public void show() {
        if(!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
            mLoadingDialog.setCanceledOnTouchOutside(true);
            Resources resources = mLoadingDialog.getContext().getResources();
            DisplayMetrics dm = resources.getDisplayMetrics();
            WindowManager.LayoutParams attributes = mLoadingDialog.getWindow().getAttributes();
            attributes.width=(int) (dm.widthPixels*0.5);
            mLoadingDialog.getWindow().setAttributes(attributes);
            try {
                int dividerID = mLoadingDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
                View divider = mLoadingDialog.findViewById(dividerID);
                if(divider!=null){
                    divider.setBackgroundResource(R.color.color_transparent);
                }
            } catch (Exception e) {
                //上面的代码，是用来去除Holo主题的蓝色线条
                e.printStackTrace();
            }
        }
    }

    private void initView(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
    }




    public void dismiss() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}

