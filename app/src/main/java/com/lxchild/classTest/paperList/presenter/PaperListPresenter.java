package com.lxchild.classTest.paperList.presenter;

import com.lxchild.DataOperatorMVP.presenter.DataOperatorPresenter;
import com.lxchild.DataOperatorMVP.view.IDataOperatorView;
import com.lxchild.bean.PaperBean;
import com.lxchild.classTest.paperList.model.PaperListModel;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListPresenter extends DataOperatorPresenter<PaperBean> {

    public PaperListPresenter(IDataOperatorView<PaperBean> dataOperatorView) {
        super(dataOperatorView);
        mIDataOperatorModel = new PaperListModel(dataOperatorView.getContext());
    }
}
