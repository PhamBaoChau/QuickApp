package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.model.Account;

import java.util.ArrayList;

public class AccountHelper extends QuestionHelper{
    private static final String TABLE_NAME="Accounts";
    private static final String NAME="NAME";
    private static final String EMAIL="EMAIL";
    private static final String PASSWORD="PASSWORD";
    public AccountHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, @Nullable int version) {
        super(context, DB_NAME, factory, DB_version);
    }

    public void addAccount(@NonNull String name,@NonNull String email,@NonNull String password){
        ContentValues values=new ContentValues();
        values.put(NAME,name);
        values.put(EMAIL,email);
        values.put(PASSWORD,password);
        getCurrentDB().insert(TABLE_NAME,null,values);
        getCurrentDB().close();
    }

    public ArrayList<Account>getAccounts(){
        ArrayList<Account>arrayList=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=this.getReadableDatabase().rawQuery(query,null);
        while (cursor.moveToNext()) {
            arrayList.add(new Account(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
        }
        return arrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        super.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
