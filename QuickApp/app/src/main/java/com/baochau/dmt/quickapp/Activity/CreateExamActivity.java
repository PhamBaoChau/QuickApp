package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baochau.dmt.quickapp.BaseActivity;
import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.fragment.FragmentCreateQuestions;
import com.baochau.dmt.quickapp.fragment.FragmentCreateTopic;

public class CreateExamActivity extends BaseActivity implements View.OnClickListener {
    public static FrameLayout fragment;
    Button btnNext;
    FragmentTransaction fragmentManager;
    FragmentCreateTopic fmCreateTopic;
    FragmentCreateQuestions createQuestions;
    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fmCreateTopic = new FragmentCreateTopic();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        fragment = findViewById(R.id.fragment);
        fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(fragment.getId(), fmCreateTopic);
        fragmentManager.commit();

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!fmCreateTopic.getNameTopic().equals("") &&
                !fmCreateTopic.getNumQuestion().equals("")) {
            int numQuestion = Integer.parseInt(fmCreateTopic.getNumQuestion());
            if (currentQuestion > 0) {
                createQuestions.createAQuestion(db, fmCreateTopic.getIdTopic());
            } else {
                fmCreateTopic.createATopic();
            }

            if (currentQuestion < numQuestion) {
                createQuestions = new FragmentCreateQuestions();
                fragmentManager = getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(CreateExamActivity.fragment.getId(), createQuestions);
                fragmentManager.commit();

                if (currentQuestion == numQuestion - 1) {
                    btnNext.setText("Finish");
                }
                currentQuestion++;
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Vui long nhập nội dung đầy đủ", Toast.LENGTH_SHORT).show();
        }
    }
}
