package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.history.ItemHistory;

import java.util.ArrayList;
import java.util.Date;

public class HistoryHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "HISTORY";
    private static final String ID_COL = "ID";
    private static final String RESULT = "RESULT";
    private static final String TIME = "TIME";

    public HistoryHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, QuestionHelper.DB_NAME, null, QuestionHelper.DB_version);
    }

    private SQLiteDatabase getCurrentDB() {
        return this.getWritableDatabase();
    }

    public void addNewHistory(@NonNull String result, @NonNull Date time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESULT, result);
        contentValues.put(TIME, time.toString());
        getCurrentDB().insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<ItemHistory> getListHistory() {
        ArrayList<ItemHistory> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            ItemHistory itemHistory = new ItemHistory(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            arrayList.add(itemHistory);
        }
        return arrayList;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME+" "
                + " " + "("
                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + RESULT + " " + "VARCHAR NOT NULL,"
                + " " + TIME + " " + "DATETIME"
                + " " + ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
