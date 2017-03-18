package com.lxchild.dataMVP;

import android.content.Context;

import com.lxchild.base.MVP.IBasePresenter;
import com.lxchild.base.MVP.IBaseView;
import com.lxchild.callBack.ILoadDataCallBack;

import java.util.List;

/**
 * Created by LXChild on 15/11/2016.
 */

public interface IDataContract {
    interface IDataView<T> extends IBaseView<IDataPresenter> {
        void setLoadingIndicator(boolean active);

        Context getContext();
        void loadDataSucceed(List<T> beans);
        void loadDataFailed(String result);
    }

    interface IDataPresenter<T> extends IBasePresenter {
        boolean insertData(T bean);
        boolean deleteData(int position);
        boolean updateData(int position, T bean);
    }

    interface IDataModel<T> {
        boolean insertData(T bean);
        boolean deleteData(int position);
        boolean updateData(int position, T bean);
        void loadData(ILoadDataCallBack<T> callBack);
    }
}
