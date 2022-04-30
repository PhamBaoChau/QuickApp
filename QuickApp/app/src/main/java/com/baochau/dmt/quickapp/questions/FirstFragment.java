package com.baochau.dmt.quickapp.questions;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import androidx.navigation.fragment.NavHostFragment;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
    public static ArrayList<ItemQuestion> listQuestion = new ArrayList<>();
    ListView listView;
    QuestionAdapter adapter;
    QuestionHelper db;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
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
//        System.out.println("Q_DEBUG: onResume");
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Tao database
        db = new QuestionHelper(getContext(), null, null, 0);
        listQuestion = db.getQuestions();
        //Tao table
        listView = view.findViewById(R.id.lv_question);

        if (getArguments() != null) {
            //Them du lieu
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_MainActivity);
        }
        adapter = new QuestionAdapter(listQuestion, this);
        listView.setAdapter(adapter);

        view.findViewById(R.id.fad_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }
}