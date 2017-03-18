package com.lxchild.classTest.paperList.presenter;

import com.lxchild.dataMVP.IDataContract;
import com.lxchild.bean.PaperBean;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.classTest.paperList.model.PaperListModel;

import java.util.List;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListPresenter implements IDataContract.IDataPresenter<PaperBean> {

    private IDataContract.IDataView<PaperBean> mView;
    private IDataContract.IDataModel<PaperBean> mModel;
    public PaperListPresenter(IDataContract.IDataView<PaperBean> view) {
        mView = view;
        mModel = new PaperListModel(view.getContext());
        mView.setPresenter(this);
    }

    @Override
    public boolean insertData(PaperBean bean) {
        return mModel.insertData(bean);
    }

    @Override
    public boolean deleteData(int position) {
        return mModel.deleteData(position);
    }

    @Override
    public boolean updateData(int position, PaperBean bean) {
        return mModel.updateData(position, bean);
    }

    @Override
    public void start() {
        openTask();
    }

    private void openTask() {
        mView.setLoadingIndicator(true);
        mModel.loadData(new ILoadDataCallBack<PaperBean>() {
            @Override
            public void onSucceed(List<PaperBean> list) {
                mView.setLoadingIndicator(false);
                mView.loadDataSucceed(list);
            }

            @Override
            public void onFailed(String result) {
                mView.setLoadingIndicator(false);
                mView.loadDataFailed(result);
            }
        });
    }

    public int getID(int position) {
        PaperListModel model = (PaperListModel) mModel;
        return model.getID(position);
    }
}
