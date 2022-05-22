package com.baochau.dmt.quickapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baochau.dmt.quickapp.model.Topic;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.TopicHelper;

import java.util.ArrayList;

public class FragmentCreateTopic extends Fragment {
    EditText edtTopic, edtNumQuestion;
    Button btnNext;
    Topic topic;
    TopicHelper topicHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_topic_exam, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topicHelper = new TopicHelper(getContext(), null, null, 0);
        edtTopic = view.findViewById(R.id.edtTopic);
        edtNumQuestion = view.findViewById(R.id.edtNumQuestion);


    }

    public void createATopic(){
        topicHelper.addATopic(edtTopic.getText().toString().trim());
    }

    public String getNumQuestion() {
        return edtNumQuestion.getText().toString().trim();
    }

    public String getNameTopic() {
        return edtNumQuestion.getText().toString().trim();
    }

    public int getIdTopic() {
        int id = 0;
        ArrayList<Topic> topics= topicHelper.getTopics();
        for (Topic item:topics)
        {
            if ((edtTopic.getText().toString().trim()).equals(item.name)){
                id=item.id;
            }
        }
        return id;
    }
}
