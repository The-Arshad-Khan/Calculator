package com.rianomusicskb.skbchat;

public class UserModel {

    String username;
    String email;
    String uid;
    public UserModel() {
    }


    public UserModel(String uid,String email,String username) {
        this.uid=uid;
        this.email = email;
        this.username=username;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }








    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
