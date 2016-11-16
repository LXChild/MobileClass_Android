package com.lxchild.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LXChild on 06/11/2016.
 */

public class TimeUtils {

    public static String getCurrentTime() {
        long current = System.currentTimeMillis();
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
    }
}
