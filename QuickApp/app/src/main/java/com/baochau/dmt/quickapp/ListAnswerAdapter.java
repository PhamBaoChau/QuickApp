package com.baochau.dmt.quickapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.questions.Answer;

import java.util.ArrayList;

public class ListAnswerAdapter extends BaseAdapter {
    QuestionHelper db;
    Context context;
    ArrayList<Answer> list;

    public ListAnswerAdapter(Context context, ArrayList<Answer> list) {
        this.context = context;
        this.list = list;
        db = new QuestionHelper(context, null, null, 0);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_answer, null);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvStatus = view.findViewById(R.id.tvStatus);


        tvTitle.setText("Câu " + (i + 1));
        if (list.get(i).id.equals(db.getQuestions().get(i).correct.id)) {
            tvStatus.setText("Đúng");
        } else tvStatus.setText("Sai");

        return view;
    }
}
