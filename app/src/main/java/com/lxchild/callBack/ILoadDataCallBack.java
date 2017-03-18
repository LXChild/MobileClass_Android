package com.lxchild.callBack;


import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public interface ILoadDataCallBack<T> {
    void onSucceed(List<T> beans);
    void onFailed(String result);
}
