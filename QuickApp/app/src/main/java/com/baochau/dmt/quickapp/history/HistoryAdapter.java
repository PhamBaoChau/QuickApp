package com.baochau.dmt.quickapp.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HistoryAdapter extends BaseAdapter {
    ArrayList<ItemHistory> arr;

    public HistoryAdapter(ArrayList<ItemHistory> arr) {
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
        LayoutInflater layoutInflater=LayoutInflater.from(view.getContext());
        view =layoutInflater.inflate(R.layout.item_history,null);

        TextView idGame=view.findViewById(R.id.tv_id);
        TextView result=view.findViewById(R.id.tv_result);
        TextView timer=view.findViewById(R.id.tv_time);

        idGame.setText(arr.get(i).id);
        result.setText(arr.get(i).result);
        result.setText(arr.get(i).time);
        return view;
    }
}
