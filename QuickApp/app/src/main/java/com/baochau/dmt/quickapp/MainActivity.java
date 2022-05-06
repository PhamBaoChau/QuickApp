package com.baochau.dmt.quickapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baochau.dmt.quickapp.Activity.CreateExamActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreate, btnJoin, btnHistory, btnExit;

    private void init() {
        btnCreate = findViewById(R.id.btnCreateExam);
        btnJoin = findViewById(R.id.btnJoin);
        btnHistory = findViewById(R.id.btnViewHistory);
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
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnCreate.getId()) {
            Intent intent = new Intent(this, CreateExamActivity.class);
            startActivity(intent);
        }
        if (view.getId() == btnJoin.getId()) {

        }
        if (view.getId() == btnHistory.getId()) {

        }
        if (view.getId() == btnExit.getId()) {

        }
    }
}
