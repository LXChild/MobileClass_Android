package com.lxchild.classTest.otherTopic.model;

import com.lxchild.bean.OtherTopicBean;

import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public interface ISelectDataCallBack {
    void onSuccessed(List<OtherTopicBean> beens);
    void onFailed(String result);
}
