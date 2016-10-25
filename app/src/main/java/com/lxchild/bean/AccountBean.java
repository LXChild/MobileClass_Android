package com.lxchild.bean;

/**
 * Created by LXChild on 22/10/2016.
 */

public class AccountBean {

    private String mID;
    private String mPassword;

    public AccountBean(String id, String password) {
        mID = id;
        mPassword = password;
    }

    public String getID() {
        return mID;
    }

    public void setID(String name) {
        mID = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
