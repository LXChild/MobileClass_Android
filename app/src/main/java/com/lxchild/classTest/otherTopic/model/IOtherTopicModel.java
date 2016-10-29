package com.lxchild.classTest.otherTopic.model;

import com.lxchild.bean.OtherTopicBean;

/**
 * Created by LXChild on 29/10/2016.
 */

public interface IOtherTopicModel {

    boolean insertData(OtherTopicBean bean);
    boolean deleteData(int id);
    boolean updateData(int id, OtherTopicBean bean);
    void selectData(ISelectDataCallBack callBack);
    int selectData(int id);
}
