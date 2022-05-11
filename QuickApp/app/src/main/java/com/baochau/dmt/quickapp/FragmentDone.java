package com.baochau.dmt.quickapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.baochau.dmt.quickapp.Activity.StartExamActivity;
import com.baochau.dmt.quickapp.questions.Answer;

import java.util.ArrayList;

public class FragmentDone extends Fragment {
    ArrayList<Answer> listAnswer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_done, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvScore = view.findViewById(R.id.tvScore);
        Switch swtSave = view.findViewById(R.id.swSave);
        ListView listView = view.findViewById(R.id.lvAnswers);
        listAnswer = new ArrayList<>();
        listAnswer.add(new Answer("A","Quoc"));
//        listAnswer = (ArrayList<Answer>) getArguments().getSerializable(StartExamActivity.MY_ANSWER);
        ListAnswerAdapter adapter = new ListAnswerAdapter(getContext(), listAnswer);
        listView.setAdapter(adapter);

    }
}
