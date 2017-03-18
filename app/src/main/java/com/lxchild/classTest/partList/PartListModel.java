package com.lxchild.classTest.partList;

import android.content.Context;
import android.database.Cursor;

import com.lxchild.bean.PaperPart;
import com.lxchild.callBack.ILoadDataCallBack;
import com.lxchild.dataMVP.IDataContract;
import com.lxchild.database.PartTable;

import java.util.ArrayList;
import java.util.List;

import static com.lxchild.database.PartTable.column_paper_id;
import static com.lxchild.database.PartTable.column_part_a;
import static com.lxchild.database.PartTable.column_part_b;
import static com.lxchild.database.PartTable.column_part_c;
import static com.lxchild.database.PartTable.column_part_d;
import static com.lxchild.database.PartTable.column_part_e;
import static com.lxchild.database.PartTable.column_type_a;
import static com.lxchild.database.PartTable.column_type_b;
import static com.lxchild.database.PartTable.column_type_c;
import static com.lxchild.database.PartTable.column_type_d;
import static com.lxchild.database.PartTable.column_type_e;

/**
 * Created by LXChild on 20/11/2016.
 */

public class PartListModel implements IDataContract.IDataModel<PaperPart> {

    private PartTable mTable;
    private List<PaperPart> mParts;

    public PartListModel(Context context) {
        mTable = new PartTable(context);
        mParts = new ArrayList<>();
    }

    @Override
    public boolean insertData(PaperPart bean) {
        return mTable.insert(bean) != -1;
    }

    @Override
    public boolean deleteData(int position) {
        return mTable.delete(position) == position;
    }

    @Override
    public boolean updateData(int position, PaperPart bean) {
        return mTable.update(position, bean) == 1;
    }

    @Override
    public void loadData(ILoadDataCallBack<PaperPart> callBack) {
        Cursor c = mTable.select();
        if (c.getCount() != 0) {
            if (c.moveToFirst()) {
                mParts.clear();
                do {
                    int paperID = c.getInt(c.getColumnIndex(column_paper_id));
                    String partA = c.getString(c.getColumnIndex(column_part_a));
                    String partB = c.getString(c.getColumnIndex(column_part_b));
                    String partC = c.getString(c.getColumnIndex(column_part_c));
                    String partD = c.getString(c.getColumnIndex(column_part_d));
                    String partE = c.getString(c.getColumnIndex(column_part_e));
                    int typeA = c.getInt(c.getColumnIndex(column_type_a));
                    int typeB = c.getInt(c.getColumnIndex(column_type_b));
                    int typeC = c.getInt(c.getColumnIndex(column_type_c));
                    int typeD = c.getInt(c.getColumnIndex(column_type_d));
                    int typeE = c.getInt(c.getColumnIndex(column_type_e));

                    PaperPart bean = new PaperPart(paperID, partA, typeA);
                    bean.setPartB(partB);
                    bean.setPartC(partC);
                    bean.setPartD(partD);
                    bean.setPartE(partE);

                    bean.setTypeB(typeB);
                    bean.setTypeC(typeC);
                    bean.setTypeD(typeD);
                    bean.setTypeE(typeE);

                    mParts.add(bean);
                } while (c.moveToNext());
            }
            callBack.onSucceed(mParts);
        }
    }

    public PaperPart selectWhere(int paperID) {
        Cursor cursor = mTable.selectWhere(paperID);
        PaperPart part = null;
        if (cursor != null && cursor.moveToFirst()) {
            part = new PaperPart(cursor.getInt(cursor.getColumnIndex(column_paper_id)),
                    cursor.getString(cursor.getColumnIndex(column_part_a)),
                    cursor.getInt(cursor.getColumnIndex(column_type_a)));

            String partB = cursor.getString(cursor.getColumnIndex(column_part_b));
            String partC = cursor.getString(cursor.getColumnIndex(column_part_c));
            String partD = cursor.getString(cursor.getColumnIndex(column_part_d));
            String partE = cursor.getString(cursor.getColumnIndex(column_part_e));

            int typeB = cursor.getInt(cursor.getColumnIndex(column_type_b));
            int typeC = cursor.getInt(cursor.getColumnIndex(column_type_c));
            int typeD = cursor.getInt(cursor.getColumnIndex(column_type_d));
            int typeE = cursor.getInt(cursor.getColumnIndex(column_type_e));

            part.setPartB(partB);
            part.setPartC(partC);
            part.setPartD(partD);
            part.setPartE(partE);

            part.setTypeB(typeB);
            part.setTypeC(typeC);
            part.setTypeD(typeD);
            part.setTypeE(typeE);
        }
        return part;
    }
}
