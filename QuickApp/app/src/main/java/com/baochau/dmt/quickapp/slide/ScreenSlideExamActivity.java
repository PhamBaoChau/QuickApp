package com.baochau.dmt.quickapp.slide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.OOP.ItemQuestion;

import java.util.ArrayList;

public class ScreenSlideExamActivity extends FragmentActivity {
    ArrayList<ItemQuestion> arrayList = new ArrayList<>();
    QuestionHelper db=new QuestionHelper(this,null,null,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_exam);

        Button btnNext = findViewById(R.id.btn_next);
        Button btnFinish = findViewById(R.id.btn_finish);
        Button btnConfirm = findViewById(R.id.btn_confirm);
//        RadioGroup radioGroup = findViewById(R.id.rd_group);
//        RadioButton answer1 = findViewById(R.id.rd_answer1);
//        RadioButton answer2 = findViewById(R.id.rd_answer2);
//        RadioButton answer3 = findViewById(R.id.rd_answer3);

        arrayList =db.getQuestions() ;
    }



}