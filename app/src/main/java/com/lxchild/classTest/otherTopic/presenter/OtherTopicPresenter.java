package com.lxchild.classTest.otherTopic.presenter;

import com.lxchild.dataMVP.IDataContract;
import com.lxchild.bean.OtherTopicBean;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.classTest.otherTopic.model.OtherTopicModel;

import java.util.List;

/**
 * Created by LXChild on 06/11/2016.
 */

public class OtherTopicPresenter implements IDataContract.IDataPresenter<OtherTopicBean> {

    private IDataContract.IDataView<OtherTopicBean> mView;
    private IDataContract.IDataModel<OtherTopicBean> mModel;
    public OtherTopicPresenter(IDataContract.IDataView<OtherTopicBean> view) {
        mView = view;
        mModel = new OtherTopicModel(view.getContext());
        mView.setPresenter(this);
    }

    @Override
    public boolean insertData(OtherTopicBean bean) {
        return mModel.insertData(bean);
    }

    @Override
    public boolean deleteData(int position) {
        return mModel.deleteData(position);
    }

    @Override
    public boolean updateData(int position, OtherTopicBean bean) {
        return mModel.updateData(position, bean);
    }

    @Override
    public void start() {
        openTask();
    }

    private void openTask() {
        mView.setLoadingIndicator(true);
        mModel.loadData(new ILoadDataCallBack<OtherTopicBean>() {
            @Override
            public void onSucceed(List<OtherTopicBean> list) {
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
