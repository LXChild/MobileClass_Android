package com.lxchild.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lxchild.bean.PaperBean;

/**
 * Created by LXChild on 06/11/2016.
 */

public class PaperListTable extends TableHelper<PaperBean> {

    private final String tb_name = "PaperList";
    public static final String column_title = "paper";
    public static final String column_remark = "remark";
    public static final String column_date = "date";
    private final String createTable = "CREATE TABLE " + tb_name + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + column_title + " TEXT DEFAULT \"\" UNIQUE, "
            + column_remark + " TEXT DEFAULT \"\", "
            + column_date + " TEXT DEFAULT \"\")";
    private final String dropTable = "DROP TABLE " + tb_name;

    public PaperListTable(Context context) {
        super(context);
    }

    @Override
    public long insert(PaperBean bean) {
        SQLiteDatabase db = getWritableDatabase();//获取可写SQLiteDatabase对象
        //ContentValues类似map，存入的是键值对
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_title, bean.getTitle());
        contentValues.put(column_remark, bean.getRemark());
        contentValues.put(column_date, bean.getDate());
        return db.insert(tb_name, null, contentValues);
    }

    @Override
    public int delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(id)};
        return db.delete(tb_name, "_id=?", args);
    }

    @Override
    public int update(int id, PaperBean bean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_title, bean.getTitle());
        contentValues.put(column_remark, bean.getRemark());
        contentValues.put(column_date, bean.getDate());
        String[] args = new String[]{String.valueOf(id)};
        return db.update(tb_name, contentValues, "_id=?", args);
    }

    @Override
    public Cursor select() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tb_name, new String[]{"_id", column_title, column_remark, column_date},
                null, null, null, null, "_id asc");
    }

    @Override
    public Cursor selectWhere(int id) {
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTable);
        onCreate(db);
    }
}
