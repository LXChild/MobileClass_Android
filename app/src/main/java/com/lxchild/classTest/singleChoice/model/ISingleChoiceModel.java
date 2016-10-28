package com.lxchild.classTest.singleChoice.model;

import com.lxchild.bean.SingleChoiceBean;

/**
 * Created by LXChild on 28/10/2016.
 */

public interface ISingleChoiceModel {


    boolean insertData(SingleChoiceBean bean);
    boolean deleteData(int id);
    boolean updateData(int id, SingleChoiceBean bean);
    void loadData();
    int loadData(String filter);
}
