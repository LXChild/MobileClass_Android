package com.lxchild.classTest.partList;

import com.lxchild.bean.PaperPart;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.dataMVP.IDataContract;

import java.util.List;

/**
 * Created by LXChild on 20/11/2016.
 */

public class PartListPresenter implements IDataContract.IDataPresenter<PaperPart> {
    private IDataContract.IDataView<PaperPart> mView;
    private IDataContract.IDataModel<PaperPart> mModel;

    public PartListPresenter(IDataContract.IDataView<PaperPart> view) {
        mView = view;
        mModel = new PartListModel(view.getContext());
        mView.setPresenter(this);
    }

    @Override
    public boolean insertData(PaperPart bean) {
        return mModel.insertData(bean);
    }

    @Override
    public boolean deleteData(int position) {
        return mModel.deleteData(position);
    }

    @Override
    public boolean updateData(int position, PaperPart bean) {
        return mModel.updateData(position, bean);
    }

    @Override
    public void start() {
        openTask();
    }

    private void openTask() {
        mView.setLoadingIndicator(true);
        mModel.loadData(new ILoadDataCallBack<PaperPart>() {
            @Override
            public void onSucceed(List<PaperPart> list) {
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

    public PaperPart selectWhere(int id) {
        PartListModel model = (PartListModel) mModel;
        return model.selectWhere(id);
    }
}
