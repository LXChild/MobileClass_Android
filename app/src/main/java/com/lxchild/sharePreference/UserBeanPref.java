package com.lxchild.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.lxchild.bean.UserBean;

/**
 * Created by LXChild on 26/10/2016.
 */

public class UserBeanPref {

    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_IDENTITY = "user_identity";
    private static final String KEY_USER_BIO = "user_bio";

    private static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("com.lxchild.app_preference.xml", Context.MODE_PRIVATE);
    }

    public static void setUserName(Context context, String name) {
        getPreference(context).edit().putString(KEY_USER_NAME, name).apply();
    }
    public static void setUserIdentity(Context context, int identity) {
        getPreference(context).edit().putInt(KEY_USER_IDENTITY, identity).apply();
    }
    public static void setUserBio(Context context, String bio) {
        getPreference(context).edit().putString(KEY_USER_BIO, bio).apply();
    }

    public static String getUserName(Context context) {
        return getPreference(context).getString(KEY_USER_NAME, null);
    }
    public static int getUserIdentity(Context context) {
        return getPreference(context).getInt(KEY_USER_IDENTITY, -1);
    }
    public static String getUserBio(Context context) {
        return getPreference(context).getString(KEY_USER_BIO, null);
    }

    public static UserBean getSignInUser(Context context) {
        return new UserBean(getUserName(context), UserBean.IntToUserIdentity(getUserIdentity(context)));
    }
}
