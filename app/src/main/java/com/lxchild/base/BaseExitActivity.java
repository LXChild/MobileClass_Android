package com.lxchild.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lxchild.appStart.MyApplication;

/**
 * Created by LXChild on 23/10/2016.
 */

public abstract class BaseExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        MyApplication.getInstance().finishActivity(this);
    }
}
