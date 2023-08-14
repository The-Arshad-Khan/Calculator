package com.rianomusicskb.skbchat.modelClass;

public class Masseges {
    String image_url;
    String msg;
    String senderId;
    long timestamp;

    public Masseges() {
    }

    public Masseges(String image_url ,String msg, String senderId, long timestamp) {
        this.image_url=image_url;
        this.msg = msg;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
