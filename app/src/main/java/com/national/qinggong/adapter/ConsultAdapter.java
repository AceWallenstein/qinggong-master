package com.national.qinggong.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.national.qinggong.R;
import com.national.qinggong.bean.BaseConsultBean;
import com.national.qinggong.bean.ChatConsultBean;
import com.national.qinggong.ui.activity.PersonalDataActivity;
import com.national.qinggong.ui.activity.PhotoViewActivity;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.ToastUtilMsg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultAdapter extends HFAdapter {

    private List<ChatConsultBean.DataBeanX.ListBean.DataBean> mMessageList=new ArrayList<>();



    private final static int MESSAGE_SEND = 0;
    /*
     *
     * 机器人聊天
     * */
    private final static int MESSAGE_ROBOT = 1;
    /*
     *
     * 左边图片
     * */
    private final static int JOB_APPLY = 2;
    String user_id = "";


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<ChatConsultBean.DataBeanX.ListBean.DataBean> getmMessageList() {
        return mMessageList;
    }

    public void setData(List<ChatConsultBean.DataBeanX.ListBean.DataBean> mMessageList){
        Log.i("mesage=", mMessageList.size() + "");
        this.mMessageList.addAll(mMessageList);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolderHF(ViewGroup viewGroup, int type) {

        return new MessageSendViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_text, viewGroup, false));
    }

    @Override
    public void onBindViewHolderHF(RecyclerView.ViewHolder vh, int position) {
        final ChatConsultBean.DataBeanX.ListBean.DataBean aiRobotItemMessage = mMessageList.get(position);
        final MessageSendViewHolder messageSendViewHolder = (MessageSendViewHolder) vh;

        if (user_id.equals(aiRobotItemMessage.getUser_id()+"")) {
            messageSendViewHolder.rel_left.setVisibility(View.VISIBLE);
            messageSendViewHolder.rel_right.setVisibility(View.GONE);
        }else{
            messageSendViewHolder.rel_left.setVisibility(View.GONE);
            messageSendViewHolder.rel_right.setVisibility(View.VISIBLE);
        }
        if (aiRobotItemMessage.getType().equals("text")) {
            messageSendViewHolder.mTvChatcontent.setVisibility(View.VISIBLE);
            messageSendViewHolder.tv_chatcontent_lift.setVisibility(View.VISIBLE);
            messageSendViewHolder.image_lift.setVisibility(View.GONE);
            messageSendViewHolder.image_right.setVisibility(View.GONE);
            messageSendViewHolder.mTvChatcontent.setText(aiRobotItemMessage.getContent().toString().trim());
            messageSendViewHolder.tv_chatcontent_lift.setText(aiRobotItemMessage.getContent().toString().trim());
        }else{
            messageSendViewHolder.mTvChatcontent.setVisibility(View.GONE);
            messageSendViewHolder.tv_chatcontent_lift.setVisibility(View.GONE);

            messageSendViewHolder.image_lift.setVisibility(View.VISIBLE);
            messageSendViewHolder.image_right.setVisibility(View.VISIBLE);

            GlideUtils.loadImageByUrl(aiRobotItemMessage.getContent(), messageSendViewHolder.image_lift);
            GlideUtils.loadImageByUrl(aiRobotItemMessage.getContent(), messageSendViewHolder.image_right);


        }
        messageSendViewHolder.image_lift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.open(messageSendViewHolder.image_lift.getContext(),aiRobotItemMessage.getContent());
            }
        });
        messageSendViewHolder.image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.open(messageSendViewHolder.image_lift.getContext(),aiRobotItemMessage.getContent());
            }
        });
        messageSendViewHolder.img_robort_avart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalDataActivity.open(messageSendViewHolder.img_robort_avart.getContext(),user_id);
            }
        });



        //2020-10-17 17:22
        String times1[]=aiRobotItemMessage.getCreate_time().split(" ");
        String times[]=times1[0].split("-");
        messageSendViewHolder.right_time.setText(times[2] + "-"+times[1]+"-"+times[0]+" "+times1[1]);

        String times2[]=aiRobotItemMessage.getCreate_time().split(" ");
        String time3[]=times2[0].split("-");
        messageSendViewHolder.left_time.setText(time3[2] + "-"+time3[1]+"-"+time3[0]+" "+times2[1]);

        //messageSendViewHolder.right_time.setText(aiRobotItemMessage.getCreate_time());
        //messageSendViewHolder.left_time.setText(aiRobotItemMessage.getCreate_time());


        if (aiRobotItemMessage.getUser_avatar() != null) {
//                头像
//                Bitmap bitmap = BitmapFactory.decodeFile(aiRobotItemMessage.getUser_avatar());
//                messageSendViewHolder.userhead.setImageBitmap(bitmap);
            GlideUtils.loadImageByUrl(aiRobotItemMessage.getUser_avatar(), messageSendViewHolder.userhead);
            GlideUtils.loadImageByUrl(aiRobotItemMessage.getUser_avatar(), messageSendViewHolder.img_robort_avart);
        }
    }

    public class MessageSendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.timestamp)
        TextView mTimestamp;
        @BindView(R.id.tv_chatcontent_lift)
        TextView tv_chatcontent_lift;

        @BindView(R.id.left_time)
        TextView left_time;
        @BindView(R.id.rel_right)
        LinearLayout rel_right;

        @BindView(R.id.img_robort_avart)
        ImageView img_robort_avart;

        @BindView(R.id.tv_chatcontent)
        TextView mTvChatcontent;

        @BindView(R.id.right_time)
        TextView right_time;
        @BindView(R.id.text_lin_lift)
        LinearLayout text_lin_lift;

        @BindView(R.id.rel_left)
        RelativeLayout rel_left;


        @BindView(R.id.userhead)
        ImageView userhead;
        @BindView(R.id.image_lift)
        ImageView image_lift;
        @BindView(R.id.image_right)
        ImageView image_right;

        public MessageSendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public int getItemViewTypeHF(int position) {
        return mMessageList.get(position).getmType();
    }

    @Override
    public int getItemCountHF() {
        return mMessageList.size();
    }
}
