package com.lxchild.intrface;

import android.support.annotation.UiThread;

/**
 * Created by LXChild on 22/10/2016.
 */

public interface ILoadView {

    @UiThread
    void showLoading();

    @UiThread
    void dismissLoading();

    @UiThread
    void error(Throwable e);
}
