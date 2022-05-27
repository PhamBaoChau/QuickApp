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
import com.baochau.dmt.quickapp.Activity.LoginActivity;
import com.baochau.dmt.quickapp.Activity.StartExamActivity;
import com.baochau.dmt.quickapp.Activity.TopicExamActivity;
import com.baochau.dmt.quickapp.slide.ExitGameDialogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STATE_TOPIC = "state_topic";
    public static final String ID_LOGIN = "id_login";
    Button btnCreate, btnJoin, btnHistory, btnListQuestions, btnLogin, btnLogOut, btnExit;

    private void init() {
        btnCreate = findViewById(R.id.btnCreateExam);
        btnJoin = findViewById(R.id.btnJoin);
        btnHistory = findViewById(R.id.btnViewHistory);
        btnListQuestions = findViewById(R.id.btnViewQuestions);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogOut = findViewById(R.id.btnLogout);
        btnExit = findViewById(R.id.btnExit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (getIntent().getIntExtra(ID_LOGIN, 0) > 0) {
            btnLogin.setVisibility(View.GONE);
            btnLogOut.setVisibility(View.VISIBLE);
        }
        btnCreate.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnListQuestions.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
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
            intent.putExtra(ID_LOGIN, getIntent().getIntExtra(ID_LOGIN, 0));
            startActivity(intent);
        }
        if (view.getId() == btnHistory.getId()) {
            Intent intent = new Intent(this, HistoryExamActivity.class);
            intent.putExtra(ID_LOGIN, getIntent().getIntExtra(ID_LOGIN, 0));
            startActivity(intent);
        }
        if (view.getId() == btnListQuestions.getId()) {
            Intent intent = new Intent(this, TopicExamActivity.class);
            intent.putExtra(STATE_TOPIC, "show");
            startActivity(intent);
        }
        if (view.getId() == btnLogin.getId()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(ID_LOGIN, getIntent().getIntExtra(ID_LOGIN, 0));
            startActivity(intent);
        }
        if (view.getId() == btnLogOut.getId()) {
            createDialog(btnLogOut);
        }
        if (view.getId() == btnExit.getId()) {
            createDialog(btnExit);
        }
    }

    public void createDialog(Button btnButton) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);

        TextView message = dialog.findViewById(R.id.tvTitle);
        Button btnNo = dialog.findViewById(R.id.btnCancel);
        Button btnYes = dialog.findViewById(R.id.btnExit);

        if (btnButton.getId() == btnLogOut.getId()){
            message.setText("Bạn chắc chắn muốn đăng xuất?");
            btnExit.setText("Log out");
        }
        if (btnButton.getId()==btnExit.getId()){
            message.setText("Bạn chắc chắn muốn thoát?");
        }
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnButton.getId() == btnExit.getId()){
                    finishAffinity();
                }
                if (btnButton.getId()==btnLogOut.getId()){
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra(ID_LOGIN, 0);
                    startActivity(intent);
                }
            }
        });
        dialog.show();
    }
}
