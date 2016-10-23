package com.lxchild.sharePreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LXChild on 23/10/2016.
 */

public class SignInPref{

    private static final String KEY_IS_SIGN_IN = "is_sign_in";

    public static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("com.lxchild.app_preference.xml", Context.MODE_PRIVATE);
    }

    public static void setAlreadySignIn(Context context) {
        getPreference(context).edit().putBoolean(KEY_IS_SIGN_IN, true).apply();
    }

    public static void setAlreadySignOut(Context context) {
        getPreference(context).edit().putBoolean(KEY_IS_SIGN_IN, false).apply();
    }

    public static boolean isIsSignIn(Context context) {
        return getPreference(context).getBoolean(KEY_IS_SIGN_IN, false);
    }
}
