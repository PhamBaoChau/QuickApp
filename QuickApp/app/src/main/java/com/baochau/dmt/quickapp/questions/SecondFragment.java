package com.baochau.dmt.quickapp.questions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;


public class SecondFragment extends Fragment {

    EditText etQuestion;
    EditText etAnswer1;
    EditText etAnswer2;
    EditText etAnswer3;
    EditText etCorrect;
    QuestionHelper db;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        System.out.println("Q_DEBUG: onAttach");
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        System.out.println("Q_DEBUG: onCreate");
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        System.out.println("Q_DEBUG: onActivityCreated");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        System.out.println("Q_DEBUG: onStart");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        System.out.println("Q_DEBUG: onStart");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        System.out.println("Q_DEBUG: onPause");
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        System.out.println("Q_DEBUG: onStop");
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        System.out.println("Q_DEBUG: onDestroyView");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.out.println("Q_DEBUG: onDestroy");
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        System.out.println("Q_DEBUG: onDetach");
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Q_DEBUG: onCreateView");
        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new QuestionHelper(getContext(), null, null, 0);

        Bundle bundle = new Bundle();
        Button btnCreate = view.findViewById(R.id.button_second);
        etQuestion = view.findViewById(R.id.question_content);
        etAnswer1 = view.findViewById(R.id.answer_content1);
        etAnswer2 = view.findViewById(R.id.answer_content2);
        etAnswer3 = view.findViewById(R.id.answer_content3);
        etCorrect = view.findViewById(R.id.correct_answer);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getText(etQuestion).isEmpty()
                        && !getText(etAnswer1).isEmpty()
                        && !getText(etAnswer2).isEmpty()
                        && !getText(etAnswer3).isEmpty()
                        && !getText(etCorrect).isEmpty()
                ) {
                    Answer answer1 = new Answer(0,getText(etAnswer1));
                    Answer answer2 = new Answer(1,getText(etAnswer2));
                    Answer answer3 = new Answer(2,getText(etAnswer3));
                    Answer correct = new Answer(3,getText(etCorrect));
                    db.addQuestion(getText(etQuestion), answer1,answer2,answer3,correct);
//                    bundle.putString("Question", String.valueOf(etQuestion.getText()));
//                    bundle.putString("Answer1", String.valueOf(etAnswer1.getText()));
//                    bundle.putString("Answer2", String.valueOf(etAnswer2.getText()));
//                    bundle.putString("Answer3", String.valueOf(etAnswer3.getText()));
//                    bundle.putString("Correct", String.valueOf(etCorrect.getText()));
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment, bundle);
                } else {
                    Snackbar.make(view, "Nhap noi dung day du", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    String getText(EditText edt) {
        return edt.getText().toString().trim();
    }
}