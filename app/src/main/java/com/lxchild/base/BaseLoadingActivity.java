package com.lxchild.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.lxchild.widget.LoadingDialog;

/**
 * Created by LXChild on 22/10/2016.
 */

public abstract class BaseLoadingActivity extends BaseExitActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoadingDialog() {
        LoadingDialog.show(this);
    }

    public void dismissLoadingDialog() {
        LoadingDialog.dismiss(this);
    }

    public void error(Throwable e) {
        Snackbar.make(getWindow().getDecorView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
