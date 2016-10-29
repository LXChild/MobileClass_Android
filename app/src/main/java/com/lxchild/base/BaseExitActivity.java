package com.lxchild.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lxchild.appStart.MyApplication;

/**
 * Created by LXChild on 23/10/2016.
 */

public abstract class BaseExitActivity extends AppCompatActivity {

    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0x01;
    public static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 0x02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        MyApplication.getInstance().addActivity(this);
        checkPermission();
    }

    protected void checkPermission() {};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    protected void doNext(int requestCode, int[] grantResults){}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        MyApplication.getInstance().finishActivity(this);
    }
}
