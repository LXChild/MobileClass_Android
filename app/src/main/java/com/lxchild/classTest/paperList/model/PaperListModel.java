package com.lxchild.classTest.paperList.model;

import android.content.Context;
import android.database.Cursor;

import com.lxchild.DataOperatorMVP.model.IDataOperatorModel;
import com.lxchild.bean.PaperBean;
import com.lxchild.callBack.IListOperatorCallBack;
import com.lxchild.database.PaperListTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListModel implements IDataOperatorModel<PaperBean> {

    private PaperListTable mTable;
    private List<PaperBean> list = new ArrayList<>();
    public PaperListModel(Context context) {

        mTable = new PaperListTable(context);
    }

    @Override
    public boolean insertData(PaperBean bean) {
        return mTable.insert(bean) != -1;
    }

    @Override
    public boolean deleteData(int id) {
        int _id = getID(id);
        return _id != -1 && mTable.delete(_id) == id;
    }

    @Override
    public boolean updateData(int id, PaperBean bean) {
        int _id = getID(id);
        return _id != -1 && mTable.update(_id, bean) == 1;
    }

    @Override
    public void selectData(IListOperatorCallBack<PaperBean> callBack) {
        Cursor c = mTable.select();
        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                list.clear();
                do {
                    String title = c.getString(c.getColumnIndex(PaperListTable.column_title));
                    String remark = c.getString(c.getColumnIndex(PaperListTable.column_remark));
                    String date = c.getString(c.getColumnIndex(PaperListTable.column_date));
                    if (!title.trim().equals("")) {
                        PaperBean bean = new PaperBean(title, remark, date);
                        list.add(bean);
                    }

                } while (c.moveToNext());
            }
            callBack.onSucceed(list);
        }
    }

    @Override
    public int selectData(int id) {
        return 0;
    }

    private int getID(int id) {
        Cursor c = mTable.select();
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < id; i++) {
                c.moveToNext();
            }
            int _id = c.getInt(c.getColumnIndex("_id"));
            return _id;
        }
        return -1;
    }
}
