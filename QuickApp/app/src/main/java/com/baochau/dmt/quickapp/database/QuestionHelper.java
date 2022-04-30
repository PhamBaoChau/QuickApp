package com.baochau.dmt.quickapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.baochau.dmt.quickapp.questions.Answer;
import com.baochau.dmt.quickapp.questions.ItemQuestion;

import java.util.ArrayList;

public class QuestionHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "QuickQuestions";
    public static int DB_version = 1;
    private static final String TableName = "Questions";
    private static final String ID_COL = "ID";
    private static final String NAME_QUESTION = "NAME_QUESTION";

    private static final String ANSWER_ID_1 = "ANSWER_ID_1";
    private static final String ANSWER1 = "ANSWER1";
    private static final String ANSWER_ID_2 = "ANSWER_ID_2";
    private static final String ANSWER2 = "ANSWER2";
    private static final String ANSWER_ID_3 = "ANSWER_ID_3";
    private static final String ANSWER3 = "ANSWER3";
    private static final String CORRECT_ANSWER_ID = "CORRECT_ANSWER_ID";
    private static final String CORRECT_ANSWER = "CORRECT_ANSWER";

    private SQLiteDatabase getCurrentDB() {
        return this.getWritableDatabase();
    }

    public void addQuestions(ArrayList<ItemQuestion> questions) {
        for (int i = 0; i < questions.size(); i++) {
            final ItemQuestion item = questions.get(i);
            addQuestion(item.question, item.answer1,item.answer2,item.answer3,item.correct);
        }
    }

    public void addQuestion(String nameQuestions, Answer answer1, Answer answer2, Answer answer3, Answer correctAnswer) {
        ContentValues values = new ContentValues();
        values.put(NAME_QUESTION, nameQuestions);
        values.put(ANSWER_ID_1, answer1.id);
        values.put(ANSWER1, answer1.answer);
        values.put(ANSWER_ID_2, answer2.id);
        values.put(ANSWER2, answer2.answer);
        values.put(ANSWER_ID_3, answer3.id);
        values.put(ANSWER3, answer3.answer);
        values.put(CORRECT_ANSWER_ID, correctAnswer.id);
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
            question.answer1 = new Answer(cursor.getInt(2), cursor.getString(3));
            question.answer2 = new Answer(cursor.getInt(4), cursor.getString(5));
            question.answer3 = new Answer(cursor.getInt(6), cursor.getString(7));
            question.correct = new Answer(cursor.getInt(8), cursor.getString(9));
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
