package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.AccountHelper;

public class SignUpActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    EditText edtFullName, edtEmail, edtPass, edtConfirmPass;
    TextView btnSignUp, errConfirm,errNotFull;
    AccountHelper accountHelper = new AccountHelper(this, null, null, 0);

    void init() {
        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassword);
        edtConfirmPass = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        errConfirm=findViewById(R.id.errorConfirm);
        errNotFull=findViewById(R.id.errorNotFull);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        edtFullName.setOnEditorActionListener(this);
        edtEmail.setOnEditorActionListener(this);
        edtPass.setOnEditorActionListener(this);
        edtConfirmPass.setOnEditorActionListener(this);

        btnSignUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    view.setBackground(getDrawable(R.drawable.radius_button_light));
                }
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                    view.setBackground(getDrawable(R.drawable.radius_button_dark));
                    createAAccount();
                }
                return true;
            }
        });

    }

    void createAAccount() {
        if (!getText(edtFullName).isEmpty() &&
                !getText(edtEmail).isEmpty() &&
                !getText(edtPass).isEmpty())
        {
            if (getText(edtPass).equals(getText(edtConfirmPass))){
                accountHelper.addAccount(getText(edtFullName), getText(edtEmail), getText(edtPass));
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                edtPass.setText(null);
                edtConfirmPass.setText(null);
                errConfirm.setVisibility(View.VISIBLE);
            }
        }else {
            edtPass.setText(null);
            edtConfirmPass.setText(null);
            errNotFull.setVisibility(View.VISIBLE);
        }
    }

    String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        errNotFull.setVisibility(View.GONE);
        errConfirm.setVisibility(View.GONE);
        return false;
    }
}