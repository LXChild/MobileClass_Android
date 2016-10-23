package com.lxchild.bean;

/**
 * Created by LXChild on 22/10/2016.
 */

public class UserBean {

    public UserBean(String name, String password) {
        mName = name;
        mPassword = password;
    }

    private String mName;
    private String mPassword;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
