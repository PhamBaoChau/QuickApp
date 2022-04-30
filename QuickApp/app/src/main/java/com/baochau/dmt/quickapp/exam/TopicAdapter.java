package com.baochau.dmt.quickapp.exam;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;

public class TopicAdapter extends BaseAdapter {
    AnswerFragment fm;
    ArrayList<Topic>arrayList;

    public TopicAdapter(AnswerFragment context, ArrayList<Topic> arrayList) {
        this.fm = context;
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

    @Nullable
    public View getView(int position, View container, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(fm.getContext());
            container=inflater.inflate(R.layout.item_exam,null);
            TextView tvName=container.findViewById(R.id.tv_topic);
            tvName.setText(arrayList.get(position).getName());
            return container;
    }
}
