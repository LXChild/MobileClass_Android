package com.lxchild.classTest.otherTopic.model;

import android.content.Context;
import android.database.Cursor;

import com.lxchild.bean.OtherTopicBean;
import com.lxchild.database.OtherTopicTable;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 29/10/2016.
 */

public class OtherTopicModelImpl implements IOtherTopicModel {
    private OtherTopicTable mOtherTopicTable;

    public OtherTopicModelImpl(Context context) {
        mOtherTopicTable = new OtherTopicTable(context);
    }

    @Override
    public boolean insertData(OtherTopicBean bean) {
        return mOtherTopicTable.insert(bean) != -1;
    }

    @Override
    public boolean deleteData(int id) {
        return mOtherTopicTable.delete(id) == id;
    }

    @Override
    public boolean updateData(int id, OtherTopicBean bean) {
        return mOtherTopicTable.update(id, bean) == id;
    }

    @Override
    public void selectData(ISelectDataCallBack callBack) {
        Cursor c = mOtherTopicTable.select();
        if (c.getCount() != 0) {
            List<OtherTopicBean> list = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    String questionName = c.getString(c.getColumnIndex(OtherTopicTable.column_question_name));
                    String answer = c.getString(c.getColumnIndex(OtherTopicTable.column_answer_name));
                    if (!questionName.trim().equals("")) {
                        OtherTopicBean otherTopicBean = new OtherTopicBean(questionName, answer);
                        list.add(otherTopicBean);
                        Logger.d(otherTopicBean.getQuestion() + " , " + otherTopicBean.getAnswer());
//                        itemList.add(page);
//                        adapter.notifyDataSetChanged();
                    }

                } while (c.moveToNext());
            }
            callBack.onSuccessed(list);
        }
    }

    @Override
    public int selectData(int id) {
        return 0;
    }
}
