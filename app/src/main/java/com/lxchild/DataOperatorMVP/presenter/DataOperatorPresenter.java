package com.lxchild.DataOperatorMVP.presenter;

import com.lxchild.DataOperatorMVP.model.IDataOperatorModel;
import com.lxchild.DataOperatorMVP.view.IDataOperatorView;
import com.lxchild.callBack.IListOperatorCallBack;

import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public abstract class DataOperatorPresenter<T> {

    public IDataOperatorModel<T> mIDataOperatorModel;
    public IDataOperatorView<T> mIDataOperatorView;

    public DataOperatorPresenter(IDataOperatorView<T> view) {
        this.mIDataOperatorView = view;
    }

    public boolean insertData(T bean) {
        return mIDataOperatorModel.insertData(bean);
    }

    public boolean deleteData(int id) {
        return mIDataOperatorModel.deleteData(id);
    }

    public boolean updateData(int id, T bean) {
        return mIDataOperatorModel.updateData(id, bean);
    }

    public void selectData() {
        mIDataOperatorModel.selectData(new IListOperatorCallBack<T>() {
            @Override
            public void onSucceed(List<T> list) {
                mIDataOperatorView.loadDataSucceed(list);
            }

            @Override
            public void onFailed(String result) {
                mIDataOperatorView.loadDataFailed(result);
            }
        });
    }
}
