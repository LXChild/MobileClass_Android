package com.lxchild.classTest.singleChoice.model;

import android.content.Context;
import android.database.Cursor;

import com.lxchild.DataOperatorMVP.model.IDataOperatorModel;
import com.lxchild.bean.SingleChoiceBean;
import com.lxchild.callBack.IListOperatorCallBack;
import com.lxchild.database.SingleChoiceTable;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoiceModel implements IDataOperatorModel<SingleChoiceBean> {
    private SingleChoiceTable tb_singleChoice;

    public SingleChoiceModel(Context context) {
        tb_singleChoice = new SingleChoiceTable(context);
    }

    @Override
    public boolean insertData(SingleChoiceBean bean) {
        return tb_singleChoice.insert(bean) != -1;
    }

    @Override
    public boolean deleteData(int id) {
        return tb_singleChoice.delete(id) == id;
    }

    @Override
    public boolean updateData(int id, SingleChoiceBean bean) {
        return tb_singleChoice.update(id, bean) == id;
    }

    @Override
    public void selectData(IListOperatorCallBack<SingleChoiceBean> callBack) {

        Cursor c = tb_singleChoice.select();
        if (c.getCount() != 0) {
            List<SingleChoiceBean> list = new ArrayList<>();

            if (c.moveToFirst()) {
                do {
                    String questionName = c.getString(c.getColumnIndex(SingleChoiceTable.column_question_name));
                    String answerA = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_a_name));
                    String answerB = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_b_name));
                    String answerC = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_c_name));
                    String answerD = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_d_name));
                    String answerR = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_r_name));
                    if (!questionName.trim().equals("")) {
                        SingleChoiceBean singleChoiceBean = new SingleChoiceBean(questionName, answerA, answerB, answerC, answerD, answerR);
                        list.add(singleChoiceBean);
                        Logger.d(singleChoiceBean);
//                        adapter.notifyDataSetChanged();
                    }

                } while (c.moveToNext());
            }
            callBack.onSucceed(list);
        }
    }

    @Override
    public int selectData(int id) {
        Cursor c = tb_singleChoice.selectWhere(id);
        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                do {
                    String questionName = c.getString(c.getColumnIndex(SingleChoiceTable.column_question_name));
                    String answerA = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_a_name));
                    String answerB = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_b_name));
                    String answerC = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_c_name));
                    String answerD = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_d_name));
                    String answerR = c.getString(c.getColumnIndex(SingleChoiceTable.column_answer_r_name));
                    if (!questionName.trim().equals("")) {
                        SingleChoiceBean bean = new SingleChoiceBean(questionName, answerA, answerB, answerC, answerD, answerR);
                        Logger.d(bean.getQuestion_name() + " , " + bean.getAnswer_a());
                    }

                } while (c.moveToNext());
            }
            return c.getCount();
        }
        return -1;
    }
}
