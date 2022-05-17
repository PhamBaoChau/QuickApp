package com.baochau.dmt.quickapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;

import java.util.List;

public class CustomSpnAdapter extends ArrayAdapter<String> {


    public CustomSpnAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_selected,parent,false);
        TextView content=convertView.findViewById(R.id.tv_id);
        String answer=this.getItem(position);
        content.setText(answer);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_selected,parent,false);
        TextView content=convertView.findViewById(R.id.tv_id);
        String answer=this.getItem(position);
        content.setText(answer);
        return convertView;
    }

}
