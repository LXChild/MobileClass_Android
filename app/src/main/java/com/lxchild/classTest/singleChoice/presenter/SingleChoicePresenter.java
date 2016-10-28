package com.lxchild.classTest.singleChoice.presenter;

import android.content.Context;

import com.lxchild.bean.SingleChoiceBean;
import com.lxchild.classTest.singleChoice.model.ISingleChoiceModel;
import com.lxchild.classTest.singleChoice.model.SingleChoiceModelImpl;
import com.lxchild.classTest.singleChoice.view.ISingleChoiceView;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoicePresenter {
    private ISingleChoiceModel mSingleChoiceModel;
    private ISingleChoiceView mSingleChoiceView;

    public SingleChoicePresenter(Context context, ISingleChoiceView singleChoiceView) {
        mSingleChoiceView = singleChoiceView;
        mSingleChoiceModel = new SingleChoiceModelImpl(context);
    }

    public void loadData() {
        mSingleChoiceModel.loadData();
    }
    /**
     * 读取指定数据
     * @param filter 过滤器
     * */
    public int loadData(String filter) {
        return mSingleChoiceModel.loadData(filter);
    }

    public boolean insertData(SingleChoiceBean bean) {
        return mSingleChoiceModel.insertData(bean);
    }

    public boolean deleteData(int id) {
        return mSingleChoiceModel.deleteData(id);
    }

    public boolean updateData(int id, SingleChoiceBean bean) {
        return mSingleChoiceModel.updateData(id, bean);
    }
}
