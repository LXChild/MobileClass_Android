package com.lxchild.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lxchild.bean.PaperPart;

import static com.lxchild.database.OtherTopicTable.column_answer_name;
import static com.lxchild.database.OtherTopicTable.column_question_name;

/**
 * Created by LXChild on 20/11/2016.
 */

public class PartTable extends TableHelper<PaperPart> {

    private final String tb_name = "OtherTopic";

    public static final String column_paper_id = "paperID";
    public static final String column_part_a = "partA";
    public static final String column_part_b = "partB";
    public static final String column_part_c = "partC";
    public static final String column_part_d = "partD";
    public static final String column_part_e = "partE";
    public static final String column_type_a = "typeA";
    public static final String column_type_b = "typeB";
    public static final String column_type_c = "typeC";
    public static final String column_type_d = "typeD";
    public static final String column_type_e = "typeE";

    private final String createTable = "CREATE TABLE " + tb_name + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + column_question_name + " TEXT DEFAULT \"\" UNIQUE, "
            + column_answer_name + " TEXT DEFAULT \"\", "
            + ")";
    private final String dropTable = "DROP TABLE " + tb_name;

    public PartTable(Context context) {
        super(context);
    }

    @Override
    public long insert(PaperPart bean) {
        SQLiteDatabase db = getWritableDatabase();//获取可写SQLiteDatabase对象
        //ContentValues类似map，存入的是键值对
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_paper_id, bean.getPaperID());
        contentValues.put(column_part_a, bean.getPartA());
        contentValues.put(column_type_a, bean.getTypeA());
        contentValues.put(column_part_b, bean.getPartB());
        contentValues.put(column_type_b, bean.getTypeB());
        contentValues.put(column_part_c, bean.getPartC());
        contentValues.put(column_type_c, bean.getTypeC());
        contentValues.put(column_part_d, bean.getPartD());
        contentValues.put(column_type_d, bean.getTypeD());
        contentValues.put(column_part_e, bean.getPartE());
        contentValues.put(column_type_e, bean.getTypeE());
        return db.insert(tb_name, null, contentValues);
    }

    @Override
    public int delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = new String[]{String.valueOf(id)};
        return db.delete(tb_name, column_paper_id + "=?", args);
    }

    @Override
    public int update(int id, PaperPart bean) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_paper_id, bean.getPaperID());
        contentValues.put(column_part_a, bean.getPartA());
        contentValues.put(column_type_a, bean.getTypeA());
        contentValues.put(column_part_b, bean.getPartB());
        contentValues.put(column_type_b, bean.getTypeB());
        contentValues.put(column_part_c, bean.getPartC());
        contentValues.put(column_type_c, bean.getTypeC());
        contentValues.put(column_part_d, bean.getPartD());
        contentValues.put(column_type_d, bean.getTypeD());
        contentValues.put(column_part_e, bean.getPartE());
        contentValues.put(column_type_e, bean.getTypeE());
        String[] args = new String[]{String.valueOf(id)};
        return db.update(tb_name, contentValues, column_paper_id + "=?", args);
    }

    @Override
    public Cursor select() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(tb_name, new String[]{column_paper_id,
                        column_part_a, column_type_a,
                        column_part_b, column_type_b,
                        column_part_c, column_type_c,
                        column_part_d, column_type_d,
                        column_part_e, column_type_e},
                null, null, null, null, column_paper_id + " asc");
    }

    @Override
    public Cursor selectWhere(int id) {

        SQLiteDatabase db = getReadableDatabase();
        return db.query(tb_name, null,
                column_paper_id + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);
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
