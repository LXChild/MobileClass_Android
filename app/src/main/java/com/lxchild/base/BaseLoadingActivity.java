package com.lxchild.base;

import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.lxchild.intrface.ILoadView;
import com.lxchild.widget.LoadingView;

/**
 * Created by LXChild on 22/10/2016.
 */

public class BaseLoadingActivity extends BaseExitActivity implements ILoadView {

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
