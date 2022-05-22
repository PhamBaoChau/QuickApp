package com.baochau.dmt.quickapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.baochau.dmt.quickapp.model.ItemQuestion;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.ShowQuestionAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;

import java.util.ArrayList;

public class ListQuestionsActivity extends AppCompatActivity {
    ListView lvQuestions;
    ShowQuestionAdapter adapter;
    ArrayList<ItemQuestion> questions=new ArrayList<>();
    QuestionHelper db=new QuestionHelper(this,null,null,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        lvQuestions=findViewById(R.id.lvQuestion);

        for (ItemQuestion item: db.getQuestions()) {
            if ((item.idTopic)==getIntent().getIntExtra(TopicExamActivity.ID_TOPIC,DEFAULT_KEYS_SHORTCUT)){
                questions.add(item);
            }
        }
        adapter=new ShowQuestionAdapter(this,questions);
        lvQuestions.setAdapter(adapter);
    }
}