package com.baochau.dmt.quickapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.model.Topic;
import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;

public class TopicAdapter extends BaseAdapter {
    Context context;
    ArrayList<Topic>arrayList;

    public TopicAdapter(Context context, ArrayList<Topic> arrayList) {
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

    @Nullable
    public View getView(int position, View container, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(context);
            container=inflater.inflate(R.layout.item_topic,null);
            TextView title=container.findViewById(R.id.tvTitle);
            title.setText("Đề "+arrayList.get(position).id+": "+arrayList.get(position).name);
            return container;
    }
}
