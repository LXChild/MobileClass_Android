package com.lxchild.intrface;

import android.support.annotation.UiThread;

/**
 * Created by LXChild on 22/10/2016.
 */

public interface ILoadView {

    @UiThread
    public void showLoading();

    @UiThread
    public void dismissLoading();

    @UiThread
    public void error(Throwable e);
}
