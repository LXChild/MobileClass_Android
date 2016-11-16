package com.lxchild.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lxchild.bean.SingleChoiceBean;

/**
 * Created by LXChild on 28/10/2016.
 */

public class SingleChoiceTable extends TableHelper<SingleChoiceBean> {

    private final String tb_name = "SingleChoice";
    public static final String column_question_name = "question";
    public static final String column_answer_a_name = "answerA";
    public static final String column_answer_b_name = "answerB";
    public static final String column_answer_c_name = "answerC";
    public static final String column_answer_d_name = "answerD";
    public static final String column_answer_r_name = "answerR";
    private final String createTable = "CREATE TABLE " + tb_name + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + column_question_name + " TEXT DEFAULT \"\" UNIQUE, "
            + column_answer_a_name + " TEXT DEFAULT \"\", "
            + column_answer_b_name + " TEXT DEFAULT \"\", "
            + column_answer_c_name + " TEXT DEFAULT \"\", "
            + column_answer_d_name + " TEXT DEFAULT \"\", "
            + column_answer_r_name + " TEXT DEFAULT \"\")";
    private final String dropTable = "DROP TABLE " + tb_name;

    public SingleChoiceTable(Context context) {
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
    public long insert(SingleChoiceBean bean) {
        SQLiteDatabase db = getWritableDatabase();//获取可写SQLiteDatabase对象
        //ContentValues类似map，存入的是键值对
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_question_name, bean.getQuestion_name());
        contentValues.put(column_answer_a_name, bean.getAnswer_a());
        contentValues.put(column_answer_b_name, bean.getAnswer_b());
        contentValues.put(column_answer_c_name, bean.getAnswer_c());
        contentValues.put(column_answer_d_name, bean.getAnswer_d());
        contentValues.put(column_answer_r_name, bean.getAnswer_r());
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
    public int update(int _id, SingleChoiceBean bean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_question_name, bean.getQuestion_name());
        contentValues.put(column_answer_a_name, bean.getAnswer_a());
        contentValues.put(column_answer_b_name, bean.getAnswer_b());
        contentValues.put(column_answer_c_name, bean.getAnswer_c());
        contentValues.put(column_answer_d_name, bean.getAnswer_d());
        contentValues.put(column_answer_r_name, bean.getAnswer_r());
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
        return db.query(tb_name, new String[]{"_id", column_question_name, column_answer_a_name, column_answer_b_name, column_answer_c_name, column_answer_d_name, column_answer_r_name},
                null, null, null, null, "_id asc");
    }
    /**
     * 查询指定数据
     * */
    @Override
    public Cursor selectWhere(int filter) {
//        SQLiteDatabase db = getReadableDatabase();
//        return db.query(tb_name, new String[]{"_id", column_question_name, column_answer_a_name, column_answer_b_name, column_answer_c_name, column_answer_d_name, column_answer_r_name},
//                filter, null, null, null, "_id asc");
        return null;
    }
}
