package com.lxchild.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lxchild.bean.OtherTopicBean;

/**
 * Created by LXChild on 29/10/2016.
 */

public class OtherTopicTable extends TableHelper<OtherTopicBean> {

    private final String tb_name = "OtherTopic";
    public static final String column_question_name = "question";
    public static final String column_answer_name = "answer";
    private final String createTable = "CREATE TABLE " + tb_name + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + column_question_name + " TEXT DEFAULT \"\" UNIQUE, "
            + column_answer_name + " TEXT DEFAULT \"\", "
            + ")";
    private final String dropTable = "DROP TABLE " + tb_name;

    public OtherTopicTable(Context context) {
        super(context);
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

    /**
     * 插入数据
     * @param bean 问题详情
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    @Override
    public long insert(OtherTopicBean bean) {
        SQLiteDatabase db = getWritableDatabase();//获取可写SQLiteDatabase对象
        //ContentValues类似map，存入的是键值对
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_question_name, bean.getQuestion());
        contentValues.put(column_answer_name, bean.getAnswer());
        return db.insert(tb_name, null, contentValues);
    }

    /**
     * 删除记录
     *
     * @param _id 列表条目
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    @Override
    public int delete(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(_id)};
        return db.delete(tb_name, "_id=?", args);
    }

    /**
     * 更新记录
     *
     * @param _id     列表条目
     * @param bean 问题详情
     * @return the number of rows affected
     */
    @Override
    public int update(int _id, OtherTopicBean bean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_question_name, bean.getQuestion());
        contentValues.put(column_answer_name, bean.getAnswer());
        String[] args = new String[]{String.valueOf(_id)};
        return db.update(tb_name, contentValues, "_id=?", args);
    }

    /**
     * 查询所有数据
     *
     * @return Cursor
     */
    @Override
    public Cursor select() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tb_name, new String[]{"_id", column_question_name, column_answer_name},
                null, null, null, null, "_id asc");
    }
    /**
     * 查询指定数据
     * */
    @Override
    public Cursor selectWhere(int filter) {
//        SQLiteDatabase db = getReadableDatabase();
//        return db.query(tb_name, new String[]{"_id", column_question_name, column_answer_name},
//                filter, null, null, null, "_id asc");
        return null;
    }
}
