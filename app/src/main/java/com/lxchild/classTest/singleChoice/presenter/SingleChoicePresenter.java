package com.lxchild.classTest.singleChoice.presenter;

import com.lxchild.DataOperatorMVP.presenter.DataOperatorPresenter;
import com.lxchild.DataOperatorMVP.view.IDataOperatorView;
import com.lxchild.bean.SingleChoiceBean;
import com.lxchild.classTest.singleChoice.model.SingleChoiceModel;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoicePresenter extends DataOperatorPresenter<SingleChoiceBean> {

    public SingleChoicePresenter(IDataOperatorView<SingleChoiceBean> dataOperatorView) {
        super(dataOperatorView);
        mIDataOperatorModel = new SingleChoiceModel(dataOperatorView.getContext());
    }
}
