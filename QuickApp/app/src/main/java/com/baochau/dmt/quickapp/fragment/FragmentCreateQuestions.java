package com.baochau.dmt.quickapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.CustomSpnAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.model.Answer;
import com.baochau.dmt.quickapp.database.TopicHelper;

import java.util.ArrayList;

public class FragmentCreateQuestions extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner spnCorrect;
    ConstraintLayout parentView;
    public FrameLayout fragment;
    Button btnNext, btnCreate;
    FragmentTransaction fragmentManager;
    ArrayAdapter spinnerAdapter;

    Answer answerA, answerB, answerC, ansCorrect;
    ArrayList<Answer> answers;
    ArrayList<String> dataSpinner = new ArrayList<>();
    QuestionHelper db = new QuestionHelper(getContext(), null, null, 0);
    TopicHelper topicHelper=new TopicHelper(getContext(),null,null,0);
    EditText edtQuestion, edtA, edtB, edtC;
    String getQuestion;
    int currentQuestion = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnCorrect = view.findViewById(R.id.spinnerCorrect);
        parentView = view.findViewById(R.id.parentView);
        fragment = view.findViewById(R.id.fragment_item_create_question);
        btnNext = view.findViewById(R.id.btnNext);
        btnCreate = view.findViewById(R.id.btnCreateExam);
        edtQuestion = view.findViewById(R.id.edtQuestion);
        edtA = view.findViewById(R.id.edtA);
        edtB = view.findViewById(R.id.edtB);
        edtC = view.findViewById(R.id.edtC);

        answers = new ArrayList<>();

        parentView.setOnClickListener(this);
        dataSpinner.add("Vui lòng chọn đáp án");
        spnCorrect.setOnItemSelectedListener(this);
        spinnerAdapter = new CustomSpnAdapter(getContext(), R.layout.spinner_selected, dataSpinner);
        spnCorrect.setAdapter(spinnerAdapter);
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (checkInputField() && ansCorrect != null) {
//                    db.addQuestion(getQuestion, answers.get(0), answers.get(1), answers.get(2), ansCorrect);
//                }
//            }
//        });
//        btnCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    void convertSpnAdapter() {
        dataSpinner.clear();
        dataSpinner.add("Vui lòng chọn đáp án");
        for (Answer item : answers) {
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

    String getText(EditText edtText) {
        return edtText.getText().toString().trim();
    }

    void createData(EditText edtText) {
        Answer answer = new Answer();
        answer.answer = getText(edtText);
        if (edtText == edtA) {
            answer.id = "A";
        }
        if (edtText == edtB) {
            answer.id = "B";
        }
        if (edtText == edtC) {
            answer.id = "C";
        }

        answers.add(answer);
    }

    void clearFocus() {
        edtQuestion.clearFocus();
        edtA.clearFocus();
        edtB.clearFocus();
        edtC.clearFocus();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String id = dataSpinner.get(i);
        ansCorrect = null;
        for (Answer answer : answers) {
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

    public void createAQuestion(QuestionHelper db,int idTopPic) {
        if (checkInputField() && ansCorrect != null) {
            db.addQuestion(getText(edtQuestion),idTopPic, answers.get(0), answers.get(1), answers.get(2), ansCorrect);
        }
    }
}
