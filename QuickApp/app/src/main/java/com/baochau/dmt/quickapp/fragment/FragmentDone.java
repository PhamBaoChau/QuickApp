package com.baochau.dmt.quickapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.baochau.dmt.quickapp.Activity.StartExamActivity;
import com.baochau.dmt.quickapp.adapter.ListAnswerAdapter;
import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.HistoryHelper;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.model.Answer;
import com.baochau.dmt.quickapp.model.ItemQuestion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentDone extends Fragment {
    ArrayList<Answer> listAnswer;
    ArrayList<ItemQuestion> questions;
    QuestionHelper db;
    int score = 0;
    HistoryHelper dbHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_done, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new QuestionHelper(getContext(), null, null, 0);
        dbHistory = new HistoryHelper(getContext(), null, null, 0);
        questions = db.getQuestions();

        TextView tvScore = view.findViewById(R.id.tvScore);
        Switch swtSave = view.findViewById(R.id.swSave);
        ListView listView = view.findViewById(R.id.lvAnswers);
        EditText edtName = view.findViewById(R.id.edtName);
        Button btnDone = view.findViewById(R.id.btnDone);

        listAnswer = (ArrayList<Answer>) getArguments().getSerializable(StartExamActivity.MY_ANSWER);
        ListAnswerAdapter adapter = new ListAnswerAdapter(getContext(), listAnswer);
        listView.setAdapter(adapter);

        for (int i = 0; i < listAnswer.size(); i++) {
            if (listAnswer.get(i).id.equals(questions.get(i).correct.id)) {
                score++;
            }
        }
        tvScore.setText(score + " / " + listAnswer.size());

        swtSave.setOnClickListener(view1 -> {
            if (swtSave.isChecked()) {
                edtName.setVisibility(View.VISIBLE);
            } else {
                edtName.setVisibility(View.GONE);
            }
        });


        btnDone.setOnClickListener(view12 -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm dd/MM/yy");
            String time = simpleDateFormat.format(Calendar.getInstance().getTime());
            int idLogin = getArguments().getInt(MainActivity.ID_LOGIN);
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.putExtra(MainActivity.ID_LOGIN,idLogin);
            System.out.println(idLogin);
            if (swtSave.isChecked()) {
                dbHistory.addNewHistory(idLogin,getText(edtName),tvScore.getText().toString().trim(),time);
            } else
                dbHistory.addNewHistory(idLogin, null, tvScore.getText().toString().trim(), time);
            startActivity(intent);
            getActivity().finishAffinity();
        });
    }

    String getText(EditText edtText) {
        return edtText.getText().toString().trim();
    }
}
