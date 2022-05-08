package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.questions.Answer;

import java.util.ArrayList;

public class CreateExamActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    QuestionHelper db = new QuestionHelper(this, null, null, 0);

    EditText edtQuestion, edtA, edtB, edtC;
    Spinner spnCorrect;
    Button btnCreate;
    ConstraintLayout parentView;

    ArrayAdapter spinnerAdapter;

    Answer answerA, answerB, answerC, ansCorrect;
    ArrayList<Answer> answers;
    ArrayList<String> dataSpinner = new ArrayList<>();

    void init() {
        parentView = findViewById(R.id.parentView);
        edtQuestion = findViewById(R.id.edtQuestion);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);

        spnCorrect = findViewById(R.id.spinnerCorrect);

        btnCreate = findViewById(R.id.btnCreate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        init();

        parentView.setOnClickListener((View.OnClickListener) this);

        answers = new ArrayList<>();
//        ChangedText(edtA);
//        ChangedText(edtB);
//        ChangedText(edtC);
        endEditText(edtA);
        endEditText(edtB);
        endEditText(edtC);
//        handleDoneButton(edtA);
//        handleDoneButton(edtB);
//        handleDoneButton(edtC);
        dataSpinner.add("Vui lòng chọn đáp án");
        spnCorrect.setOnItemSelectedListener(this);
        spinnerAdapter = new CustomSpnAdapter(this,R.layout.spinner_selected,dataSpinner);
//        ArrayAdapter adapter = new ArrayAdapter(CreateExamActivity.this, android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spnCorrect.setAdapter(spinnerAdapter);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInputField() && ansCorrect != null ) {
                    String sQuestion = getText(edtQuestion);
                    db.addQuestion(sQuestion, answers.get(0), answers.get(1), answers.get(2), ansCorrect);

                    Intent intent = new Intent(CreateExamActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateExamActivity.this, "Vui lòng nhập đầy đủ.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void convertSpnAdapter()
    {
        dataSpinner.clear();
        dataSpinner.add("Vui lòng chọn đáp án");
        for (Answer item:answers) {
            dataSpinner.add(item.id);
        }
        spinnerAdapter.notifyDataSetChanged();
    }

    boolean checkInputField() {
        if (!getText(edtQuestion).isEmpty() &&
                !getText(edtA).isEmpty() &&
                !getText(edtB).isEmpty() &&
                !getText(edtC).isEmpty()) {
            return true;
        }
        return false;
    }

    void clearFocus() {
        edtQuestion.clearFocus();
        edtA.clearFocus();
        edtB.clearFocus();
        edtC.clearFocus();
    }

    void endEditText(EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // When focus is lost check that the text field has valid values.
                if (!hasFocus) {
//                    createData(editText);
                }
            }
        });
    }

    private void createData(EditText editText) {
        Answer answer = new Answer();
        answer.answer = getText(editText);

        if (editText == edtA) {
            answer.id = "A";
//            updateData(answer);
        } else if (editText == edtB) {
            answer.id = "B";
//            updateData(answer);
        } else if (editText == edtC) {
            answer.id = "C";
//            updateData(answer);
        }
        answers.add(answer);
    }

//    private void updateData(Answer answer) {
//        if (answers.size() == 3) {
//            for (int i = 0; i < answers.size(); i++) {
//                if (answers.get(i).id.equals(answer.id)) {
//                    answers.set(i, answer);
//                }
//            }
//        } else {
//            answers.add(answer);
//        }
//    }

    private String getText(EditText edt) {
        return edt.getText().toString().trim();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String id = dataSpinner.get(i);
        ansCorrect = null;
        for (Answer answer: answers) {
            if (answer.id.equals(id)) {
                ansCorrect = answer;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        this.clearFocus();
        dataSpinner.clear();
        answers.clear();
        if (checkInputField()) {
            createData(edtA);
            createData(edtB);
            createData(edtC);
            convertSpnAdapter();
        }
    }
}
