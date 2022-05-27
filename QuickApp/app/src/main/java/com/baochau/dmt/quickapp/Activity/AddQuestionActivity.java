package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.CustomSpnAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.model.Answer;

import java.util.ArrayList;
import java.util.Locale;

public class AddQuestionActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {
    EditText edtQuestion, edtA, edtB, edtC;
    Spinner spinner;
    Button btnAdd;
    CustomSpnAdapter adapter;
    ArrayList<Answer> answers = new ArrayList<>();
    ArrayList<String> idAnswers = new ArrayList<>();
    Answer ansCorrect;
    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    int idTopic;

    void init() {
        edtQuestion = findViewById(R.id.edtQuestion);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        spinner = findViewById(R.id.spinnerCorrect);
        btnAdd = findViewById(R.id.btnAdd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        init();
        idTopic = getIntent().getIntExtra(TopicExamActivity.ID_TOPIC, 0);

        edtQuestion.setOnEditorActionListener(this);
        edtA.setOnEditorActionListener(this);
        edtB.setOnEditorActionListener(this);
        edtC.setOnEditorActionListener(this);

        idAnswers.add("Vui lòng chọn đáp án");
        adapter = new CustomSpnAdapter(this, R.layout.spinner_selected, idAnswers);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                createDataAnsCorrect(idAnswers.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAdd.setOnClickListener(this);
    }

    void convertDataSpn() {
        adapter.clear();
        answers.clear();
        idAnswers.add("Vui lòng chọn đáp án");
        createDataAnswer();
        for (Answer item : answers) {
            idAnswers.add(item.id);
        }
        adapter.notifyDataSetChanged();
    }

    void createDataAnsCorrect(String id) {
        ansCorrect = null;
        for (Answer item : answers) {
            if (id.equals(item.id)) {
                ansCorrect = item;
                break;
            }
        }
    }

    void createDataAnswer() {
        if (checkTextFull()==true){
            answers.add(new Answer("A", getText(edtA)));
            answers.add(new Answer("B", getText(edtB)));
            answers.add(new Answer("C", getText(edtC)));
        }
    }

    Boolean checkTextFull() {
        if (!getText(edtQuestion).isEmpty() &&
                !getText(edtA).isEmpty() &&
                !getText(edtB).isEmpty() &&
                !getText(edtC).isEmpty())
            return true;
        return false;
    }

    String getText(EditText edtText) {
        return edtText.getText().toString().trim();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        convertDataSpn();
        if (checkTextFull() == true) {
            edtQuestion.clearFocus();
            edtA.clearFocus();
            edtB.clearFocus();
            edtC.clearFocus();
            convertDataSpn();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (checkTextFull() == true && ansCorrect != null) {
            db.addQuestion(getText(edtQuestion), idTopic, answers.get(0), answers.get(1), answers.get(2), ansCorrect);
            Intent intent = new Intent(AddQuestionActivity.this, ListQuestionsActivity.class);
            intent.putExtra(TopicExamActivity.ID_TOPIC, idTopic);
            startActivity(intent);
            finishAffinity();
        } else
            Toast.makeText(AddQuestionActivity.this, "Vui lòng nhập đủ nội dung!", Toast.LENGTH_SHORT).show();
    }
}