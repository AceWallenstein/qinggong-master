package com.national.qinggong.dialog;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.bean.LiveRoomDetailBean;
import com.national.qinggong.ui.activity.LivePalyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建时间：2020/11/16
 * 编写人：czy_yangxudong
 * 功能描述：
 */
public class GoodPopupWindow extends PopupWindow {

    private Context mContext;

    private View view;

    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private RecyclerView rcv_good;
    private MyGoodAdapter adapter;

    private List<LiveRoomDetailBean.DataBean.DetailBean.GoodsBean>  list=new ArrayList<>();
    public void setData(List<LiveRoomDetailBean.DataBean.DetailBean.GoodsBean>  list){
        if (this.list.size()==0){
            this.list.addAll(list);
        }
    }


    public GoodPopupWindow(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.layout_popupwindow, null);

        /*DisplayMetrics outMetrics = new DisplayMetrics();

        ((LivePalyActivity)mContext).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        //int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;

        *//* 设置弹出窗口特征 *//*
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(heightPixels/3*2);*/

        rcv_good = (RecyclerView) view.findViewById(R.id.rcv_good);

        rcv_good.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MyGoodAdapter(mContext);
        rcv_good.setAdapter(adapter);

        //btn_take_photo = (Button) view.findViewById(R.id.btn_take_photo);
        //btn_pick_photo = (Button) view.findViewById(R.id.btn_pick_photo);
        //btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        // 取消按钮
       /* btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });*/
        // 设置按钮监听
        //btn_pick_photo.setOnClickListener(itemsOnClick);
        //btn_take_photo.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });



        this.setContentView(this.view);
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        //ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        //this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }




    public class MyGoodAdapter extends RecyclerView.Adapter {

        private Context context;

        public MyGoodAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_pop_good, null);
            inflate.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;

            //viewHolder.tv_end_hour.setText(list.get(position).extime_hi);


            /*viewHolder.ll_item.setOnClickListener(e -> {

            });*/




        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        //private List<BuyerBean.Data> list = new ArrayList<>();

        /*public void setData(List<BuyerBean.Data> list,int index) {
            this.list = list;
            this.index = index;
        }*/

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.ll_item)
            LinearLayout ll_item;
            //@BindView(R.id.tv_zhangchang)
            //TextView tv_zhangchang;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
