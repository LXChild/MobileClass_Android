package com.lxchild.utils;

import java.io.File;

/**
 * Created by LXChild on 22/10/2016.
 */

public class FileUtils {

    public static boolean createFilePathIfNotExist(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
