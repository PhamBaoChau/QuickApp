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

import com.baochau.dmt.quickapp.BaseActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.CustomSpnAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.model.ItemQuestion;

import java.util.ArrayList;

public class UpdateQuestionActivity extends BaseActivity implements TextView.OnEditorActionListener, AdapterView.OnItemSelectedListener {
    EditText edtQuestion, edtA, edtB, edtC;
    Spinner spinner;
    CustomSpnAdapter adapter;
    Button btnEditQuestion, btnUpdate;
    ItemQuestion itemQuestion;
    ArrayList<String> answers = new ArrayList<>();
    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    String idAnsCorrect;

    void init() {
        edtQuestion = findViewById(R.id.edtQuestion);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        spinner = findViewById(R.id.spnCorrect);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        init();
        itemQuestion = (ItemQuestion) getIntent().getSerializableExtra(StartExamActivity.ITEM_QUESTION);
        setDataQuestion();

        idAnsCorrect = itemQuestion.correct.id;
        answers.add(itemQuestion.answer1.id);
        answers.add(itemQuestion.answer2.id);
        answers.add(itemQuestion.answer3.id);
        adapter = new CustomSpnAdapter(this, R.layout.spinner_selected, answers);
        spinner.setAdapter(adapter);
        spinner.setSelection(getPosDefaultSpn(itemQuestion.correct.id));
        spinner.setOnItemSelectedListener(this);
        System.out.println("Chau"+getPosDefaultSpn(itemQuestion.correct.id));

        edtA.setOnEditorActionListener(this);
        edtB.setOnEditorActionListener(this);
        edtC.setOnEditorActionListener(this);
        edtQuestion.setOnEditorActionListener(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }

    void createDialog() {
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
                db.updateQuestion(itemQuestion.id, itemQuestion.idTopic, getText(edtQuestion), getText(edtA), getText(edtB), getText(edtC), idAnsCorrect, getAnswerCorrect(idAnsCorrect));
                Intent intent = new Intent(UpdateQuestionActivity.this, ListQuestionsActivity.class);
                intent.putExtra(TopicExamActivity.ID_TOPIC, itemQuestion.idTopic);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataQuestion();
                dialog.cancel();
            }
        });
        dialog.show();
    }

    String getAnswerCorrect(String idCorrect) {
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
        return s;
    }

    void setDataQuestion() {
        edtQuestion.setText(itemQuestion.question);
        edtA.setText(itemQuestion.answer1.answer);
        edtB.setText(itemQuestion.answer2.answer);
        edtC.setText(itemQuestion.answer3.answer);
    }

    void convertAdapter() {
        answers.clear();
        answers.add(itemQuestion.answer1.id);
        answers.add(itemQuestion.answer2.id);
        answers.add(itemQuestion.answer3.id);
        adapter.notifyDataSetChanged();
    }

    int getPosDefaultSpn(String id){
        int pos=-1;
        for(int i=0;i<answers.size();i++){
            if (id.equals(answers.get(i))){
                pos=i;
                break;
            }
        }
        return pos;
    }

    String getText(EditText edtText) {
        return edtText.getText().toString().trim();
    }

    Boolean checkFull() {
        if (!getText(edtQuestion).isEmpty() &&
                !getText(edtA).isEmpty() &&
                !getText(edtB).isEmpty() &&
                !getText(edtC).isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (checkFull())
            convertAdapter();
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        idAnsCorrect = answers.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}