package com.baochau.dmt.quickapp.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.slide.ScreenSlideExamActivity;

import java.util.ArrayList;

public class AnswerFragment extends Fragment {
    TopicAdapter adapter;
    GridView gridView;
    ArrayList<Topic> arrayList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exam,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView=view.findViewById(R.id.gv_series);
        arrayList.add(new Topic("Đề 1"));
        arrayList.add(new Topic("Đề 2"));
        arrayList.add(new Topic("Đề 3"));
        arrayList.add(new Topic("Đề 4"));
        arrayList.add(new Topic("Đề 5"));
        arrayList.add(new Topic("Đề 6"));

        adapter=new TopicAdapter(this, arrayList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), ScreenSlideExamActivity.class);
                startActivity(intent);
            }
        });


    }
}
