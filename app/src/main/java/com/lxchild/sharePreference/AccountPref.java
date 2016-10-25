package com.lxchild.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.lxchild.bean.AccountBean;

/**
 * Created by LXChild on 16/10/2016.
 */

public class AccountPref {
    private static final String KEY_ID = "user_id";
    private static final String KEY_PWD = "user_password";

    private static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("com.lxchild.app_preference.xml", Context.MODE_PRIVATE);
    }

    public static void setUserID(Context context, String userID) {
        getPreference(context).edit().putString(KEY_ID, userID).apply();
    }
    public static void setPassword(Context context, String password) {
        getPreference(context).edit().putString(KEY_PWD, password).apply();
    }

    private static String getUserID(Context context) {
        return getPreference(context).getString(KEY_ID, "");
    }
    private static String getPassword(Context context) {
        return getPreference(context).getString(KEY_PWD, "");
    }

    public static AccountBean getSignInAccount(Context context) {
        return new AccountBean(getUserID(context), getPassword(context));
    }
}
