package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.model.Topic;

import java.util.ArrayList;

public class TopicHelper extends QuestionHelper{
    static final String TABLE_NAME="Topics";
    static final String ID_COL="ID";
    static  final String NAME="NAME";
    public TopicHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, @Nullable int version) {
        super(context, DB_NAME, factory, DB_version);
    }
    public void addATopic(String name){
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        getCurrentDB().insert(TABLE_NAME,null,contentValues);
        getCurrentDB().close();
    }

    public ArrayList<Topic> getTopics(){
        ArrayList<Topic> topics=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=this.getReadableDatabase().rawQuery(query,null);
        while (cursor.moveToNext()){
            Topic topic=new Topic(cursor.getInt(0),cursor.getString(1));
            topics.add(topic);
        }
       return topics;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        super.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
//        String query3 = "CREATE TABLE  " + TABLE_NAME
//                + " " + "("
//                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + " " + NAME + " " + "NVARCHAR"
//                + " " + ")";
//        sqLiteDatabase.execSQL(query3);
    }
}
