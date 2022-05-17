package com.baochau.dmt.quickapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.OOP.ItemQuestion;
import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;

public class ShowQuestionAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemQuestion>arrayList;

    public ShowQuestionAdapter(Context context, ArrayList<ItemQuestion> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.item_show_question,null);

        TextView lbQuestion=view.findViewById(R.id.lbQuestion);
        TextView tvQuestion=view.findViewById(R.id.tvQuestion);
        TextView tvA=view.findViewById(R.id.tvA);
        TextView tvB=view.findViewById(R.id.tvB);
        TextView tvC=view.findViewById(R.id.tvC);
        TextView tvCorrect=view.findViewById(R.id.tvCorrect);

        lbQuestion.setText("Câu "+arrayList.get(i).id+": ");
        tvQuestion.setText(arrayList.get(i).question);
        tvA.setText(arrayList.get(i).answer1.answer);
        tvB.setText(arrayList.get(i).answer2.answer);
        tvC.setText(arrayList.get(i).answer3.answer);
        tvCorrect.setText(arrayList.get(i).correct.id);
        return view;
    }
}
