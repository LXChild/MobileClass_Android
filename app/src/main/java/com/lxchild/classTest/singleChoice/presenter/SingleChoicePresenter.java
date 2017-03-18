package com.lxchild.classTest.singleChoice.presenter;

import com.lxchild.dataMVP.IDataContract;
import com.lxchild.bean.SingleChoiceBean;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.classTest.singleChoice.model.SingleChoiceModel;

import java.util.List;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoicePresenter implements IDataContract.IDataPresenter<SingleChoiceBean> {

    private IDataContract.IDataView<SingleChoiceBean> mView;
    private IDataContract.IDataModel<SingleChoiceBean> mModel;

    public SingleChoicePresenter(IDataContract.IDataView<SingleChoiceBean> view) {
        mView = view;
        mModel = new SingleChoiceModel(view.getContext());
        view.setPresenter(this);
    }

    @Override
    public boolean insertData(SingleChoiceBean bean) {
        return mModel.insertData(bean);
    }

    @Override
    public boolean deleteData(int position) {
        return mModel.deleteData(position);
    }

    @Override
    public boolean updateData(int position, SingleChoiceBean bean) {
        return mModel.updateData(position, bean);
    }

    @Override
    public void start() {
        openTask();
    }

    private void openTask() {
        mView.setLoadingIndicator(true);
        mModel.loadData(new ILoadDataCallBack<SingleChoiceBean>() {
            @Override
            public void onSucceed(List<SingleChoiceBean> list) {
                mView.setLoadingIndicator(true);
                mView.loadDataSucceed(list);
            }

            @Override
            public void onFailed(String result) {
                mView.setLoadingIndicator(true);
                mView.loadDataFailed(result);
            }
        });
    }
}
