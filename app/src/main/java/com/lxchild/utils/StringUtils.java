package com.lxchild.utils;

import android.widget.EditText;

/**
 * Created by LXChild on 28/10/2016.
 */

public class StringUtils {

    public static boolean isEmpty(EditText et) {
        return et.getText().toString().trim().equals("");
    }

    public static boolean isValidAnswer(String s) {
        return s.equalsIgnoreCase("A") ||
                s.equalsIgnoreCase("B") ||
                s.equalsIgnoreCase("C") ||
                s.equalsIgnoreCase("D");
    }
}
