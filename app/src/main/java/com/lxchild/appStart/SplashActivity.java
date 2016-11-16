package com.lxchild.appStart;

import android.app.Activity;
import android.os.Bundle;

import com.lxchild.mobileclass.MainActivity;
import com.lxchild.sharePreference.AppPref;
import com.lxchild.sharePreference.SignInPref;

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
         //   IntroduceActivity.launch(this);
            MainActivity.launch(this);
        } else if (SignInPref.isSignIn(this)) {
            MainActivity.launch(this);
        } else {
            MainActivity.launch(this);

        //    SignInActivity.launch(this);
        }
        finish();
    }
}
