package com.lxchild.classTest.paperList.model;

import android.content.Context;
import android.database.Cursor;

import com.lxchild.bean.PaperBean;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.dataMVP.IDataContract;
import com.lxchild.database.PaperTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListModel implements IDataContract.IDataModel<PaperBean> {

    private PaperTable mTable;
    private List<PaperBean> list;
    public PaperListModel(Context context) {
        mTable = new PaperTable(context);
        list = new ArrayList<>();
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
    public void loadData(ILoadDataCallBack<PaperBean> callBack) {
        Cursor c = mTable.select();
        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                list.clear();
                do {
                    String title = c.getString(c.getColumnIndex(PaperTable.column_name));
                    String remark = c.getString(c.getColumnIndex(PaperTable.column_remark));
                    String date = c.getString(c.getColumnIndex(PaperTable.column_date));
                    if (!title.trim().equals("")) {
                        PaperBean bean = new PaperBean(title, remark, date);
                        list.add(bean);
                    }

                } while (c.moveToNext());
            }
            callBack.onSucceed(list);
        } else {
            callBack.onFailed("数据为空");
        }
    }

    public int getID(int position) {
        Cursor c = mTable.select();
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i = 0; i < position; i++) {
                c.moveToNext();
            }
            return c.getInt(c.getColumnIndex("_id"));
        }
        return -1;
    }
}
