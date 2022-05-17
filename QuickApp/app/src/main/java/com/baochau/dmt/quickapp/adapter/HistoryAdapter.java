package com.baochau.dmt.quickapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.OOP.ItemHistory;
import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemHistory> arr;

    public HistoryAdapter(Context context, ArrayList<ItemHistory> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view =layoutInflater.inflate(R.layout.item_history,null);

        TextView id=view.findViewById(R.id.tvId);
        TextView name=view.findViewById(R.id.tvName);
        TextView result=view.findViewById(R.id.tvResult);
        TextView timer=view.findViewById(R.id.tvTime);

        id.setText(String.valueOf(arr.get(i).id));
        name.setText(arr.get(i).name);
        result.setText(arr.get(i).result);
        timer.setText(arr.get(i).time);
        return view;
    }
}
