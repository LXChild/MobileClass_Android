package com.lxchild.classTest.otherTopic.presenter;

import com.lxchild.DataOperatorMVP.presenter.DataOperatorPresenter;
import com.lxchild.DataOperatorMVP.view.IDataOperatorView;
import com.lxchild.bean.OtherTopicBean;
import com.lxchild.classTest.otherTopic.model.OtherTopicModel;

/**
 * Created by LXChild on 06/11/2016.
 */

public class OtherTopicPresenter extends DataOperatorPresenter<OtherTopicBean> {

    public OtherTopicPresenter(IDataOperatorView dataOperatorView) {
        super(dataOperatorView);
        mIDataOperatorModel = new OtherTopicModel(dataOperatorView.getContext());
    }
}
