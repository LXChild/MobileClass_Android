package com.lxchild.appStart;

import android.app.Activity;
import android.os.Bundle;

import com.lxchild.sharePreference.AppPref;
import com.lxchild.mobileclass.MainActivity;

/**
 * Created by LXChild on 16/10/2016.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 注意, 这里并没有setContentView, 单纯只是用来跳转到相应的Activity.
        // 目的是减少首屏渲染
        if (AppPref.isFirstRunning(this)) {
            IntroduceActivity.launch(this);
        }
        else {
            MainActivity.launch(this);
        }
        finish();
    }
}
