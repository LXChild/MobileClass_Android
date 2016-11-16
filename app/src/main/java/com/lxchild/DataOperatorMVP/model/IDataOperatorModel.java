package com.lxchild.DataOperatorMVP.model;

import com.lxchild.callBack.IListOperatorCallBack;

/**
 * Created by LXChild on 29/10/2016.
 */

public interface IDataOperatorModel<T> {

    boolean insertData(T bean);
    boolean deleteData(int id);
    boolean updateData(int id, T bean);
    void selectData(IListOperatorCallBack<T> callBack);
    int selectData(int id);
}
