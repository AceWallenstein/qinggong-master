package com.national.qinggong.bean;

public class ChatMessageEvent {
    private int new_message_num;

    public ChatMessageEvent(int new_message_num) {
        this.new_message_num = new_message_num;
    }

    public int getNew_message_num() {
        return new_message_num;
    }

    public void setNew_message_num(int new_message_num) {
        this.new_message_num = new_message_num;
    }
}
