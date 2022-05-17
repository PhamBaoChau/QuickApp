package com.baochau.dmt.quickapp.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baochau.dmt.quickapp.OOP.ItemHistory;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.HistoryAdapter;
import com.baochau.dmt.quickapp.database.HistoryHelper;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    HistoryHelper db;
    ArrayList<ItemHistory>arrayList=new ArrayList<>();
    ListView lvHistory;
    HistoryAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_history,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db=new HistoryHelper(getContext(),null,null,0);
        arrayList=db.getListHistory();

//        adapter=new HistoryAdapter(arrayList);
//        lvHistory=view.findViewById(R.id.lv_history);
//        lvHistory.setAdapter(adapter);

    }
}
