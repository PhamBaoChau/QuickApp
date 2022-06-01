package com.baochau.dmt.quickapp.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.EasyEditSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baochau.dmt.quickapp.BaseActivity;
import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.AccountHelper;
import com.baochau.dmt.quickapp.model.Account;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener, View.OnTouchListener, TextView.OnEditorActionListener {
    TextView tvUser, tvPass, tvError;
    TextView tvLogin, tvSignUp, tvForgot;
    EditText edtUser, edtPass;
    ArrayList<Account> accounts;
    Typeface typeface;
    AccountHelper accountHelper = new AccountHelper(this, null, null, 0);

    void init() {
        tvUser = findViewById(R.id.tvUserName);
        tvPass = findViewById(R.id.tvPassword);
        tvError = findViewById(R.id.tvError);
        tvLogin = findViewById(R.id.login);
        tvSignUp = findViewById(R.id.signUp);
        tvForgot = findViewById(R.id.forgot);
        edtUser = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        typeface=ResourcesCompat.getFont(this,R.font.roboto_medium);

        accounts = accountHelper.getAccounts();
        edtUser.setOnFocusChangeListener(this);
        edtPass.setOnFocusChangeListener(this);

        tvForgot.setOnTouchListener(this);
        tvSignUp.setOnTouchListener(this);
        tvLogin.setOnTouchListener(this);

        edtUser.setOnEditorActionListener(this);
        edtPass.setOnEditorActionListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view == edtUser) {
            if (!view.hasFocus()) {
                if (getText(edtUser).isEmpty())
                    tvUser.setVisibility(View.INVISIBLE);
            } else tvUser.setVisibility(View.VISIBLE);
        } else {
            if (!view.hasFocus()) {
                if (getText(edtPass).isEmpty())
                    tvPass.setVisibility(View.INVISIBLE);
            } else tvPass.setVisibility(View.VISIBLE);
        }
    }

    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == tvSignUp) {
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                tvSignUp.setPaintFlags(tvSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                tvSignUp.setTextColor(R.color.purple_700);
            }
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                tvSignUp.getPaint().reset();
//                tvSignUp.setTypeface(typeface);
                tvSignUp.setTextSize(13);
                tvSignUp.setTextColor(R.color.black);
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        }
        if (view == tvForgot) {
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {

                tvForgot.setPaintFlags(tvForgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                tvForgot.setTextColor(R.color.purple_700);
            }
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                tvForgot.getPaint().reset();
//                tvForgot.setTypeface(typeface);
                tvForgot.setTextSize(13);
                tvForgot.setTextColor(R.color.black);
                finishAffinity();
            }
        }
        if (view == tvLogin) {
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                tvLogin.setBackground(getDrawable(R.drawable.radius_button_light));
            }
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                tvLogin.setBackground(getDrawable(R.drawable.radius_button_dark));
                loginAccount();
            }
        }
        return true;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        tvError.setText(null);
        return false;
    }

    void loginAccount() {
        System.out.println(accounts.size());
        if (!getText(edtUser).isEmpty() && !getText(edtPass).isEmpty()) {
            if (accounts.size() > 0) {
                for (int i = 0; i < accounts.size(); i++) {
                    if (accounts.get(i).email.equals(getText(edtUser))) {
                        if (accounts.get(i).password.equals(getText(edtPass))) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.ID_LOGIN, accounts.get(i).id);
                            startActivity(intent);
                            break;
                        } else {
                            edtPass.setText(null);
                            tvError.setText("Sai mật khẩu !");
                        }
                    }
                    if (i == accounts.size() - 1) {
                        edtUser.setText(null);
                        edtPass.setText(null);
                        tvError.setText("Tài khoản không tồn tại !");
                    }
                }
            } else {
                edtUser.setText(null);
                edtPass.setText(null);
                tvError.setText("Tài khoản không tồn tại !");
            }
        }
    }
}