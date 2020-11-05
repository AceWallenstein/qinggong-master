package com.national.qinggong.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CustomRecycleview extends RecyclerView {
    public CustomRecycleview(Context context) {
        super(context);
    }

    public CustomRecycleview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}