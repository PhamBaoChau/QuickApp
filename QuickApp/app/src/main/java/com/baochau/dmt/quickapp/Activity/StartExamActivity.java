package com.baochau.dmt.quickapp.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.questions.Answer;
import com.baochau.dmt.quickapp.questions.ItemQuestion;
import com.baochau.dmt.quickapp.slide.SlidePageFragment;

import java.util.ArrayList;

public class StartExamActivity extends AppCompatActivity {

    public final static String ITEM_QUESTION = "ITEM_QUESTION";

    int Next = 1;
    int Confirm = 2;
    int Finish = 3;

    int currentButtonState = Confirm;
    FragmentTransaction fragmentManager;

    Button btnNext, btnConfirm, btnFinish;
    FrameLayout fragmentLayout;

    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    ArrayList<ItemQuestion> arrayList = new ArrayList<>();
    ArrayList<Answer> listAnswer = new ArrayList<>();
    SlidePageFragment slidePageFragment;

    void init() {
        fragmentLayout = findViewById(R.id.fragment);
        btnNext = findViewById(R.id.btn_next);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnFinish = findViewById(R.id.btn_finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        init();
        getQuestions();
        replaceFragment();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmFunction();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextFunction();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishFunction();
            }
        });
    }

    private void getQuestions() {
        arrayList = db.getQuestions();
    }

    private void confirmFunction() {
        Answer answer = slidePageFragment.getAnswer();
        if (answer != null) {
            setStateButton(Next);
            listAnswer.add(answer);
        }
    }

    private void nextFunction() {
        setStateButton(Confirm);
        replaceFragment();
    }

    private void finishFunction() {

    }

    private void replaceFragment() {
        slidePageFragment = new SlidePageFragment();
        fragmentManager = getSupportFragmentManager().beginTransaction();
        fragmentManager.replace(fragmentLayout.getId(), slidePageFragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEM_QUESTION, arrayList.get(0));
        slidePageFragment.setArguments(bundle);
        fragmentManager.commit();
    }

    private void setStateButton(int state) {
        if (state == Next) {
            btnNext.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.GONE);
            btnFinish.setVisibility(View.GONE);

        } else if (state == Confirm) {
            btnNext.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);

        } else {
            btnNext.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        }
        currentButtonState = state;
    }
}