package com.baochau.dmt.quickapp.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Process;
import android.renderscript.Sampler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.fragment.FragmentDone;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.OOP.Answer;
import com.baochau.dmt.quickapp.OOP.ItemQuestion;
import com.baochau.dmt.quickapp.fragment.SlidePageFragment;
import com.baochau.dmt.quickapp.slide.ExitGameDialogFragment;

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
    FrameLayout fragmentDoneFrameLayout;
    LinearLayout overView;

    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    ArrayList<ItemQuestion> arrayList = new ArrayList<>();
    ArrayList<Answer> listAnswer = new ArrayList<>();
    SlidePageFragment slidePageFragment;

    int currentQuestion = 0;

    void init() {
        overView = findViewById(R.id.overView);
        fragmentDoneFrameLayout = findViewById(R.id.fragmentDone);
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
        getQuestions(getIntent().getIntExtra(TopicExamActivity.ID_TOPIC,DEFAULT_KEYS_SHORTCUT));
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

    @Override
    public void onBackPressed() {
        createDialog();
    }

    public void createDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);

        TextView message = dialog.findViewById(R.id.tvTitle);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnExit = dialog.findViewById(R.id.btnExit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartExamActivity.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();

            }
        });
        dialog.show();
    }

    private void getQuestions(int id) {
        for (ItemQuestion item: db.getQuestions()) {
            if (item.idTopic==id)
            {
                arrayList.add(item);
            }
        }
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
        overView.setVisibility(View.GONE);
        fragmentDoneFrameLayout.setVisibility(View.VISIBLE);

        fragmentManager = getSupportFragmentManager().beginTransaction();
        FragmentDone fragmentDone = new FragmentDone();
        fragmentManager.replace(fragmentDoneFrameLayout.getId(), fragmentDone);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MY_ANSWER, listAnswer);
        fragmentDone.setArguments(bundle);
        fragmentManager.addToBackStack(null);
        fragmentManager.commit();
        btnFinish.setVisibility(View.GONE);
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