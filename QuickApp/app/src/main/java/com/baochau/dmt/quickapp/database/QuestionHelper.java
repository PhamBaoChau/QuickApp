package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.model.Answer;
import com.baochau.dmt.quickapp.model.ItemQuestion;

import java.util.ArrayList;

public class QuestionHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "QuickApp";
    public static int DB_version = 1;
    public static final String TableName = "Questions";
    public static final String ID_COL = "ID";
    public static final String ID_TOPIC = "ID_TOPIC";
    public static final String NAME_QUESTION = "NAME_QUESTION";

    public static final String ANSWER_ID_1 = "ANSWER_ID_1";
    public static final String ANSWER1 = "ANSWER1";
    public static final String ANSWER_ID_2 = "ANSWER_ID_2";
    public static final String ANSWER2 = "ANSWER2";
    public static final String ANSWER_ID_3 = "ANSWER_ID_3";
    public static final String ANSWER3 = "ANSWER3";
    public static final String CORRECT_ANSWER_ID = "CORRECT_ANSWER_ID";
    public static final String CORRECT_ANSWER = "CORRECT_ANSWER";

    public SQLiteDatabase getCurrentDB() {
        return this.getWritableDatabase();
    }

    //    public void addQuestions(ArrayList<ItemQuestion> questions) {
//        for (int i = 0; i < questions.size(); i++) {
//            final ItemQuestion item = questions.get(i);
//            addQuestion(item.question, item.answer1,item.answer2,item.answer3,item.correct);
//        }
//    }
    public void updateQuestion(@NonNull int idQuestion, @NonNull int idTopic, @NonNull String nameCol, @NonNull String text) {
        ContentValues values = new ContentValues();
        values.put(nameCol, text);
        String query = "(" + ID_COL + "=?) AND (" + ID_TOPIC + "=?)";
        getCurrentDB().update(TableName, values, query,new String[]{String.valueOf(idQuestion), String.valueOf(idTopic)});
    }

    public void deleteQuestionFromTopic(@NonNull int idTopic){
        String query =ID_TOPIC + "=?";
        getCurrentDB().delete(TableName,query,new String[]{String.valueOf(idTopic)});
    }

    public void deleteQuestion(@NonNull int idQuestion,@NonNull int idTopic){
        String query = "(" + ID_COL + "=?) AND (" + ID_TOPIC + "=?)";
        getCurrentDB().delete(TableName,query,new String[]{String.valueOf(idQuestion), String.valueOf(idTopic)});
    }

    public void addQuestion(@NonNull String nameQuestions,@NonNull int idTopic,@NonNull Answer answer1,@NonNull Answer answer2,@NonNull Answer answer3,@NonNull Answer correctAnswer) {
        ContentValues values = new ContentValues();
        values.put(NAME_QUESTION, nameQuestions);
        values.put(ID_TOPIC, idTopic);
        values.put(ANSWER_ID_1, (String) answer1.id);
        values.put(ANSWER1, answer1.answer);
        values.put(ANSWER_ID_2, (String) answer2.id);
        values.put(ANSWER2, answer2.answer);
        values.put(ANSWER_ID_3, (String) answer3.id);
        values.put(ANSWER3, answer3.answer);
        values.put(CORRECT_ANSWER_ID, (String) correctAnswer.id);
        values.put(CORRECT_ANSWER, correctAnswer.answer);

        getCurrentDB().insert(TableName, null, values);
        getCurrentDB().close();
    }

//    public void removeQuestion(String nameQuestions, Answer answer1, Answer answer2, Answer answer3, Answer correctAnswer) {
//
//    }
//
//    public void updateQuestion(String nameQuestions, Answer answer1, Answer answer2, Answer answer3, Answer correctAnswer) {
//
//    }

    public ArrayList<ItemQuestion> getQuestions() {
        ArrayList<ItemQuestion> questions = new ArrayList<>();
        String query = "SELECT * FROM" + " " + TableName;
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            ItemQuestion question = new ItemQuestion();
            question.id = cursor.getInt(0);
            question.question = cursor.getString(1);
            question.idTopic = cursor.getInt(2);
            question.answer1 = new Answer(cursor.getString(3), cursor.getString(4));
            question.answer2 = new Answer(cursor.getString(5), cursor.getString(6));
            question.answer3 = new Answer(cursor.getString(7), cursor.getString(8));
            question.correct = new Answer(cursor.getString(9), cursor.getString(10));
            questions.add(question);
        }
        return questions;
    }

//    public Answer getAQuestion(){
//        Answer answer = new Answer();
//        return answer;
//    }

    public QuestionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, @Nullable int version) {
        super(context, DB_NAME, factory, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE  " + TableName
                + " " + "("
                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"

                + " " + NAME_QUESTION + " " + "NVARCHAR,"
                + " " + ID_TOPIC + " " + "INTEGER,"
                + " " + ANSWER_ID_1 + " " + "INTEGER,"
                + " " + ANSWER1 + " " + "NVARCHAR,"
                + " " + ANSWER_ID_2 + " " + "INTEGER,"
                + " " + ANSWER2 + " " + "NVARCHAR,"
                + " " + ANSWER_ID_3 + " " + "INTEGER,"
                + " " + ANSWER3 + " " + "NVARCHAR,"
                + " " + CORRECT_ANSWER_ID + " " + "INTEGER,"
                + " " + CORRECT_ANSWER + " " + "NVARCHAR"
                + " " + ")";
        sqLiteDatabase.execSQL(query);

        String query2 = "CREATE TABLE  " + "Histories"
                + " " + "("
                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + "ID_ACCOUNT" + " " + "INTEGER,"
                + " " + "NAME" + " " + "NVARCHAR,"
                + " " + "RESULT" + " " + "NVARCHAR,"
                + " " + "TIME" + " " + "NVARCHAR"
                + " " + ")";
        sqLiteDatabase.execSQL(query2);

        String query3 = "CREATE TABLE  " + "Topics"
                + " " + "("
                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + "NAME" + " " + "NVARCHAR"
                + " " + ")";
        sqLiteDatabase.execSQL(query3);

        String query4 = "CREATE TABLE  " + "Accounts"
                + " " + "("
                + " " + ID_COL + " " + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " " + "NAME" + " " + "NVARCHAR,"
                + " " + "EMAIL" + " " + "NVARCHAR,"
                + " " + "PASSWORD" + " " + "NVARCHAR"
                + " " + ")";
        sqLiteDatabase.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        String drop_students_table = "DROP TABLE IF EXISTS" + " " + TableName;
//        sqLiteDatabase.execSQL(drop_students_table);
//        String drop_students_table2 = "DROP TABLE IF EXISTS" + " " + "History";
//        sqLiteDatabase.execSQL(drop_students_table2);
//        onCreate(sqLiteDatabase);
    }
}
