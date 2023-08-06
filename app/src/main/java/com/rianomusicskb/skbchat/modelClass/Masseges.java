package com.rianomusicskb.skbchat.modelClass;

public class Masseges {
    String msg;
    String senderId;
    long timestamp;

    public Masseges() {
    }

    public Masseges(String msg, String senderId, long timestamp) {
        this.msg = msg;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}
