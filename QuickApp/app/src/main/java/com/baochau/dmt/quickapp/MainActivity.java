package com.baochau.dmt.quickapp;

import android.app.Dialog;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.baochau.dmt.quickapp.Activity.CreateExamActivity;
import com.baochau.dmt.quickapp.Activity.HistoryExamActivity;
import com.baochau.dmt.quickapp.Activity.StartExamActivity;
import com.baochau.dmt.quickapp.Activity.TopicExamActivity;
import com.baochau.dmt.quickapp.slide.ExitGameDialogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STATE_TOPIC = "state_topic";
    Button btnCreate, btnJoin, btnHistory, btnListQuestions, btnExit;

    private void init() {
        btnCreate = findViewById(R.id.btnCreateExam);
        btnJoin = findViewById(R.id.btnJoin);
        btnHistory = findViewById(R.id.btnViewHistory);
        btnListQuestions = findViewById(R.id.btnViewQuestions);
        btnExit = findViewById(R.id.btnExit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        btnCreate.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnListQuestions.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view) {
        if (view.getId() == btnCreate.getId()) {
            Intent intent = new Intent(this, CreateExamActivity.class);
            startActivity(intent);
        }
        if (view.getId() == btnJoin.getId()) {
            Intent intent = new Intent(this, TopicExamActivity.class);
            intent.putExtra(STATE_TOPIC, "start");
            startActivity(intent);
        }
        if (view.getId() == btnHistory.getId()) {
            Intent intent = new Intent(this, HistoryExamActivity.class);
            startActivity(intent);
        }
        if (view.getId() == btnListQuestions.getId()) {
            Intent intent = new Intent(this, TopicExamActivity.class);
            intent.putExtra(STATE_TOPIC, "show");
            startActivity(intent);
        }
        if (view.getId() == btnExit.getId()) {
            createDialog();
        }
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
                finishAndRemoveTask();
            }
        });
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroy");
    }
}
