package com.lxchild.classTest.otherTopic.presenter;

import com.lxchild.bean.OtherTopicBean;
import com.lxchild.classTest.otherTopic.model.IOtherTopicModel;
import com.lxchild.classTest.otherTopic.model.ISelectDataCallBack;
import com.lxchild.classTest.otherTopic.model.OtherTopicModelImpl;
import com.lxchild.classTest.otherTopic.view.IOtherTopicView;

import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public class OtherTopicPresenter {

    private IOtherTopicModel mOtherTopicModel;
    private IOtherTopicView mOtherTopicView;

    public OtherTopicPresenter(IOtherTopicView otherTopicView) {
        mOtherTopicView = otherTopicView;
        mOtherTopicModel = new OtherTopicModelImpl(mOtherTopicView.getContext());
    }

    public boolean insertData(OtherTopicBean bean) {
        return mOtherTopicModel.insertData(bean);
    }

    public boolean deleteData(int id) {
        return mOtherTopicModel.deleteData(id);
    }

    public boolean updateData(int id, OtherTopicBean bean) {
        return mOtherTopicModel.updateData(id, bean);
    }

    public void selectData() {
        mOtherTopicModel.selectData(new ISelectDataCallBack() {
            @Override
            public void onSuccessed(List<OtherTopicBean> list) {

            }

            @Override
            public void onFailed(String result) {
            }
        });
    }
}
