package com.lxchild.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LXChild on 06/11/2016.
 */

abstract class TableHelper<T> extends SQLiteOpenHelper {
    private static final String DB_NAME = "MobileClass";

    TableHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public abstract long insert(T bean);

    public abstract int delete(int id);

    public abstract int update(int id, T bean);

    public abstract Cursor select();

    public abstract Cursor selectWhere(int id);
}