package com.baochau.dmt.quickapp.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.baochau.dmt.quickapp.FragmentDone;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.questions.Answer;
import com.baochau.dmt.quickapp.questions.ItemQuestion;
import com.baochau.dmt.quickapp.slide.SlidePageFragment;

import java.util.ArrayList;

public class StartExamActivity extends AppCompatActivity {

    public final static String ITEM_QUESTION = "ITEM_QUESTION";
    public final static String MY_ANSWER = "MY_ANSWER";

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

    int currentQuestion = 0;

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
            listAnswer.add(answer);
            currentQuestion++;
            if (currentQuestion < arrayList.size()) {
                setStateButton(Next);
            } else {
                setStateButton(Finish);
            }
        }

    }

    private void nextFunction() {
        setStateButton(Confirm);
        replaceFragment();
    }

    private void finishFunction() {
        fragmentManager = getSupportFragmentManager().beginTransaction();
        FragmentDone fragmentDone = new FragmentDone();
        fragmentManager.replace(fragmentLayout.getId(), fragmentDone);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MY_ANSWER, listAnswer);
        slidePageFragment.setArguments(bundle);
        fragmentManager.addToBackStack(null);
        fragmentManager.commit();
    }

    private void replaceFragment() {
        slidePageFragment = new SlidePageFragment();
        if (currentQuestion < arrayList.size()) {
            fragmentManager = getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(fragmentLayout.getId(), slidePageFragment);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ITEM_QUESTION, arrayList.get(currentQuestion));
            slidePageFragment.setArguments(bundle);
            fragmentManager.addToBackStack(null);
            fragmentManager.commit();
        }
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