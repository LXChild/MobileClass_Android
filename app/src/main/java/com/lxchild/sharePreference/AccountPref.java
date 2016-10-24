package com.lxchild.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.lxchild.bean.UserBean;

/**
 * Created by LXChild on 16/10/2016.
 */

public class AccountPref {
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_PWD = "user_password";

    private static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("com.lxchild.app_preference.xml", Context.MODE_PRIVATE);
    }

    public static void setUserName(Context context, String userName) {
        getPreference(context).edit().putString(KEY_USER_NAME, userName).apply();
    }
    public static void setPassword(Context context, String password) {
        getPreference(context).edit().putString(KEY_USER_PWD, password).apply();
    }

    public static String getUserName(Context context) {
        return getPreference(context).getString(KEY_USER_NAME, "");
    }
    public static String getPassword(Context context) {
        return getPreference(context).getString(KEY_USER_PWD, "");
    }

    public static UserBean getSignInUser(Context context) {
        return new UserBean(getUserName(context), getPassword(context));
    }
}
