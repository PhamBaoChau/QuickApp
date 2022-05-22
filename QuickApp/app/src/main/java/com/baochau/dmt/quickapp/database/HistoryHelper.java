package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.model.ItemHistory;

import java.util.ArrayList;

public class HistoryHelper extends QuestionHelper {
    private static final String TABLE_NAME = "Histories";
    private static final String ID_COL = "ID";
    private static final String ID_ACCOUNT = "ID_ACCOUNT";
    private static final String NAME = "NAME";
    private static final String RESULT = "RESULT";
    private static final String TIME = "TIME";


    public void addNewHistory(@NonNull int idAccount,@Nullable String name, @NonNull String result, @NonNull String time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_ACCOUNT,idAccount);
        contentValues.put(NAME, name);
        contentValues.put(RESULT, result);
        contentValues.put(TIME, time);
        getCurrentDB().insert(TABLE_NAME, null, contentValues);
        getCurrentDB().close();
    }

    public ArrayList<ItemHistory> getListHistory() {
        ArrayList<ItemHistory> arrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            ItemHistory itemHistory = new ItemHistory(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            arrayList.add(itemHistory);
        }
        return arrayList;
    }

    public HistoryHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        super.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
//        String query2 = "CREATE TABLE  " + TABLE_NAME
//                + " " + "("
//                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + " " + NAME + " " + "NVARCHAR,"
//                + " " + RESULT + " " + "NVARCHAR,"
//                + " " + TIME + " " + "NVARCHAR"
//                + " " + ")";
//        sqLiteDatabase.execSQL(query2);
    }
}
