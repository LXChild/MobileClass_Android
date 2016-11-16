package com.lxchild.DataOperatorMVP.view;

import android.content.Context;

import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public interface IDataOperatorView<T> {
    Context getContext();
    void loadDataSucceed(List<T> beans);
    void loadDataFailed(String result);
}
