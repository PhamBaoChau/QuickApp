package com.baochau.dmt.quickapp.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.CustomSpnAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.model.ItemQuestion;

import java.util.ArrayList;

public class UpdateQuestionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText edtQuestion, edtA, edtB, edtC;
    Spinner spinner;
    CustomSpnAdapter adapter;
    Button btnEditQuestion, btnEditA, btnEditB, btnEditC, btnUpdate, btnFocusSpn;
    ItemQuestion itemQuestion;
    String nameCol;
    InputMethodManager imm;
    ArrayList<String> answers = new ArrayList<>();
    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    String idAnsCorrect;

    void init() {
        edtQuestion = findViewById(R.id.edtQuestion);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        spinner = findViewById(R.id.spnCorrect);
        btnEditQuestion = findViewById(R.id.btnEditQuestion);
        btnEditA = findViewById(R.id.btnEditA);
        btnEditB = findViewById(R.id.btnEditB);
        btnEditC = findViewById(R.id.btnEditC);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnFocusSpn = findViewById(R.id.btnSpinner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        init();
        imm = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        itemQuestion = (ItemQuestion) getIntent().getSerializableExtra(StartExamActivity.ITEM_QUESTION);
        edtQuestion.setText(itemQuestion.question);
        edtA.setText(itemQuestion.answer1.answer);
        edtB.setText(itemQuestion.answer2.answer);
        edtC.setText(itemQuestion.answer3.answer);
        btnEditQuestion.setOnClickListener(this);
        btnEditA.setOnClickListener(this);
        btnEditB.setOnClickListener(this);
        btnEditC.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        idAnsCorrect = itemQuestion.correct.id;
        answers.add(idAnsCorrect);
        adapter = new CustomSpnAdapter(this, R.layout.spinner_selected, answers);
        spinner.setAdapter(adapter);
        btnFocusSpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFocusSpn.setVisibility(View.GONE);
                convertAdapter();
                System.out.println("yes");
            }
        });

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnEditQuestion) {
            edtQuestion.setFocusableInTouchMode(true);

            edtQuestion.requestFocus();
            imm.showSoftInputFromInputMethod(edtQuestion.getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            updateItemQuestion(edtQuestion);
        }
        if (view == btnEditA) {
            edtA.setFocusableInTouchMode(true);

            edtA.requestFocus();
            imm.showSoftInputFromInputMethod(edtA.getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            updateItemQuestion(edtA);
        }
        if (view == btnEditB) {
            edtB.setFocusableInTouchMode(true);

            edtB.requestFocus();
            imm.showSoftInputFromInputMethod(edtA.getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            updateItemQuestion(edtB);
        }
        if (view == btnEditC) {
            edtC.setFocusableInTouchMode(true);

            edtC.requestFocus();
            imm.showSoftInputFromInputMethod(edtA.getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            updateItemQuestion(edtC);
        }
        if (view == btnUpdate) {
            Intent intent = new Intent(UpdateQuestionActivity.this, ListQuestionsActivity.class);
            intent.putExtra(TopicExamActivity.ID_TOPIC, itemQuestion.idTopic);
            startActivity(intent);
        }
    }

    void updateItemQuestion(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    if (!editText.hasFocus()) {
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//                        setDefaultEditText(editText);
                        editText.setFocusable(false);
                    }
                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if (editText.getId() == edtQuestion.getId())
                        nameCol = QuestionHelper.NAME_QUESTION;
                    if (editText.getId() == edtA.getId())
                        nameCol = QuestionHelper.ANSWER1;
                    if (editText.getId() == edtB.getId())
                        nameCol = QuestionHelper.ANSWER2;
                    if (editText.getId() == edtC.getId())
                        nameCol = QuestionHelper.ANSWER3;
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    createDialog(editText);
                }
                return false;
            }
        });
    }

    void createDialog(EditText editText) {
        Dialog dialog = new Dialog(UpdateQuestionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);

        TextView message = dialog.findViewById(R.id.tvTitle);
        Button btnNo = dialog.findViewById(R.id.btnCancel);
        Button btnYes = dialog.findViewById(R.id.btnExit);

        message.setText("Bạn chắc chắn muốn thay đổi?");
        btnYes.setText("Yes");
        btnNo.setText("No");

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                editAnswer(editText);
                editText.setFocusable(false);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                setDefaultEditText(editText);
                editText.setFocusable(false);
            }
        });
        dialog.show();
    }

    void editAnswer(EditText editText) {
        db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, nameCol, getText(editText));
        if (nameCol == QuestionHelper.ANSWER_ID_1) {
            if (itemQuestion.correct.id == itemQuestion.answer1.id) {
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, QuestionHelper.CORRECT_ANSWER, getText(editText));
            }
        }
        if (nameCol == QuestionHelper.ANSWER_ID_2) {
            if (itemQuestion.correct.id == itemQuestion.answer2.id) {
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, QuestionHelper.CORRECT_ANSWER, getText(editText));
            }
        }
        if (nameCol == QuestionHelper.ANSWER_ID_3) {
            if (itemQuestion.correct.id == itemQuestion.answer3.id) {
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, QuestionHelper.CORRECT_ANSWER, getText(editText));
            }
        }
    }

    void setDefaultEditText(EditText editText) {
        if (editText == edtQuestion) {
            edtQuestion.setText(itemQuestion.question);
        }
        if (editText == edtA) {
            edtA.setText(itemQuestion.answer1.answer);
        }
        if (editText == edtB) {
            edtB.setText(itemQuestion.answer2.answer);
        }
        if (editText == edtC) {
            edtC.setText(itemQuestion.answer3.answer);
        }
    }

    void createDialogSpinner(String idCorrect) {
        Dialog dialog = new Dialog(UpdateQuestionActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);

        TextView message = dialog.findViewById(R.id.tvTitle);
        Button btnNo = dialog.findViewById(R.id.btnCancel);
        Button btnYes = dialog.findViewById(R.id.btnExit);

        message.setText("Bạn chắc chắn muốn thay đổi đáp án?");
        btnYes.setText("Yes");
        btnNo.setText("No");

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                String s = null;
                if (idCorrect.equals("A")) {
                    s = getText(edtA);
                }
                if (idCorrect.equals("B")) {
                    s = getText(edtB);
                }
                if (idCorrect.equals("C")) {
                    s = getText(edtC);
                }
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, QuestionHelper.CORRECT_ANSWER_ID, idCorrect);
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, QuestionHelper.CORRECT_ANSWER, s);
                btnFocusSpn.setVisibility(View.VISIBLE);
                idAnsCorrect = idCorrect;
                answers.clear();
                answers.add(idAnsCorrect);
                adapter.notifyDataSetChanged();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                answers.clear();
                answers.add(idAnsCorrect);
                adapter.notifyDataSetChanged();
                btnFocusSpn.setVisibility(View.VISIBLE);
            }
        });
        dialog.show();
    }

    void convertAdapter() {
        if (idAnsCorrect.equals("A")) {
            answers.add(itemQuestion.answer2.id);
            answers.add(itemQuestion.answer3.id);
        }
        if (idAnsCorrect.equals("B")) {
            answers.add(itemQuestion.answer1.id);
            answers.add(itemQuestion.answer3.id);
        }
        if (idAnsCorrect.equals("C")) {
            answers.add(itemQuestion.answer1.id);
            answers.add(itemQuestion.answer2.id);
        }
        adapter.notifyDataSetChanged();
    }

    String getText(EditText edtText) {
        return edtText.getText().toString().trim();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (answers.size() != 1)
            createDialogSpinner(adapter.getItem(i));
        System.out.println(adapter.getItem(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}