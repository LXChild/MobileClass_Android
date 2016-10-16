package com.lxchild.mobileclass;

import android.app.Application;

/**
 * Created by LXChild on 16/10/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializeService.start(this);
    }
}
