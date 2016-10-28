package com.lxchild.bean;

/**
 * Created by LXChild on 26/10/2016.
 */

public class UserBean {

    private String mName;
    private UserIdentity mIdentity;
    private String mBio;

    public UserBean(String name, UserIdentity identity) {
        mName = name;
        mIdentity = identity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public UserIdentity getIdentity() {
        return mIdentity;
    }

    public void setIdentity(UserIdentity identity) {
        mIdentity = identity;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public enum UserIdentity {
        administrator, teacher, student
    }

    public static int UserIdentityToInt(UserBean.UserIdentity identity) {
        if (identity.equals(UserIdentity.administrator)) {
            return 0;
        } else if (identity.equals(UserIdentity.teacher)) {
            return 1;
        }  else if (identity.equals(UserIdentity.student)) {
            return 2;
        }
        return -1;
    }

    public static UserIdentity IntToUserIdentity(int identity) {
        switch (identity) {
            case 0:
                return UserIdentity.administrator;
            case 1:
                return UserIdentity.teacher;
            case 2:
                return UserIdentity.student;
            default:
                break;
        }
        return null;
    }
}
