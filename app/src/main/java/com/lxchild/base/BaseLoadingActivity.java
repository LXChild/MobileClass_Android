package com.lxchild.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.lxchild.intrface.LoadView;
import com.lxchild.widget.LoadingView;

/**
 * Created by LXChild on 22/10/2016.
 */

public class BaseLoadingActivity extends AppCompatActivity implements LoadView{

    private LoadingView mLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingView = new LoadingView(this);
    }
    @Override
    public void showLoading() {
        mLoadingView.show();
    }

    @Override
    public void dismissLoading() {
        mLoadingView.dismiss();
    }

    @Override
    public void error(Throwable e) {
        Snackbar.make(getWindow().getDecorView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
    }
}
