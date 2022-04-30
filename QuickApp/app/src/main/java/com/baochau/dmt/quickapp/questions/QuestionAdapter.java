package com.baochau.dmt.quickapp.questions;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter {
    ArrayList<ItemQuestion> lvQuestion;
    FirstFragment fm;

    public QuestionAdapter(ArrayList lvQuestion, FirstFragment fm) {
        this.lvQuestion = lvQuestion;
        this.fm=fm;
    }


    @Override
    public int getCount() {
        return lvQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return lvQuestion.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private class ViewHolder{
        TextView question;
        TextView answer1;
        TextView answer2;
        TextView answer3;
        TextView correct;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(fm.getContext());
            view=inflater.inflate(R.layout.item_question,null);

            viewHolder.question=view.findViewById(R.id.question_content);
            viewHolder.answer1=view.findViewById(R.id.answer_content1);
            viewHolder.answer2=view.findViewById(R.id.answer_content2);
            viewHolder.answer3=view.findViewById(R.id.answer_content3);
            viewHolder.correct=view.findViewById(R.id.correct_answer);
        view.setTag(viewHolder);
        viewHolder.question.setText(lvQuestion.get(i).question);
        viewHolder.answer1.setText(lvQuestion.get(i).answer1.answer);
        viewHolder.answer2.setText(lvQuestion.get(i).answer2.answer);
        viewHolder.answer3.setText(lvQuestion.get(i).answer3.answer);
        viewHolder.correct.setText(lvQuestion.get(i).correct.answer);
        return view;
    }
}
