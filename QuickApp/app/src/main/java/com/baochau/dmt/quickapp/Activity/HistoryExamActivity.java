package com.baochau.dmt.quickapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.model.ItemHistory;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.HistoryAdapter;
import com.baochau.dmt.quickapp.database.HistoryHelper;

import java.util.ArrayList;

public class HistoryExamActivity extends AppCompatActivity {

    ListView lvHistory;
    HistoryAdapter adapter;
    ArrayList<ItemHistory> arrayList;
    HistoryHelper historyHelper = new HistoryHelper(this, null, null, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_exam);
        lvHistory = findViewById(R.id.lvHistory);
        if (getIntent().getIntExtra(MainActivity.ID_LOGIN, 0) > 0) {
            arrayList = new ArrayList<>();
            for (ItemHistory item : historyHelper.getListHistory()) {
                if (item.idAccount == getIntent().getIntExtra(MainActivity.ID_LOGIN, 0)) {
                    arrayList.add(item);
                }
            }
        } else
            arrayList = historyHelper.getListHistory();
        adapter = new HistoryAdapter(this, arrayList);
        lvHistory.setAdapter(adapter);
    }
}