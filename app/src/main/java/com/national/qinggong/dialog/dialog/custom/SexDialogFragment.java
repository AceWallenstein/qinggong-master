package com.national.qinggong.dialog.dialog.custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.national.qinggong.R;
import com.national.qinggong.bean.IndexCountryBean;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SexDialogFragment extends AppCompatDialogFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.bt_confirm)
    TextView bt_confirm;
    @BindView(R.id.wheelview_single)
    WheelView wheelview_single;
    private String sex = "";
    private List<IndexCountryBean.DataBean.ListBean> list = new ArrayList<>();

    public void newInstance(List<IndexCountryBean.DataBean.ListBean> list) {
        this.list = list;
    }

    public interface  ClickListener{
        void result(IndexCountryBean.DataBean.ListBean data);
    }

    public ClickListener onClickListener;

    public void setOnClickListener(ClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sexdialog, null);
        unbinder = ButterKnife.bind(this, view);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IndexCountryBean.DataBean.ListBean dataBean = list.get(index);
//                ToastUtilMsg.showToast(v.getContext(), dataBean.getName());
               onClickListener.result(dataBean);
                dismiss();
            }
        });
        initWheeView();

        return view;
    }

    int index = 0;

    private void initWheeView() {
        wheelview_single.setCyclic(false); //是否循环展示

        List<String> listStr=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            listStr.add(list.get(i).getName());
        }

        wheelview_single.setAdapter(new ArrayWheelAdapter(listStr));
        wheelview_single.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                index = position;
            }
        });
    }


/*    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View dialogView = getDialog().findViewById(R.id.design_bottom_sheet);
        dialogView.setBackgroundResource(android.R.color.transparent);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(dialogView);
        behavior.setState((BottomSheetBehavior.STATE_EXPANDED));

        if (behavior != null) {
            behavior.setPeekHeight(dpToPx(getResources(), 550f));
        }
    }*/


    int dpToPx(Resources res, float dp) {
        float v = new TypedValue().applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                res.getDisplayMetrics()
        );
        return (int) v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
