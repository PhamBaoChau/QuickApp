package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.baochau.dmt.quickapp.BaseActivity;
import com.baochau.dmt.quickapp.MainActivity;
import com.baochau.dmt.quickapp.model.Topic;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.TopicAdapter;
import com.baochau.dmt.quickapp.database.TopicHelper;

import java.util.ArrayList;

public class TopicExamActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    static final String ID_TOPIC = "id_topic";
    Button btnDeleteTopic, btnUpdateTopic;
    ListView lvTopic;
    ArrayList<Topic> topics;
    TopicAdapter adapter;
    TopicHelper topicHelper = new TopicHelper(this, null, null, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_exam);
        lvTopic = findViewById(R.id.lvTopic);

        topics = topicHelper.getTopics();
        adapter = new TopicAdapter(this, topics);
        lvTopic.setAdapter(adapter);

        lvTopic.setOnItemClickListener(this);
        lvTopic.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Topic topic = (Topic) lvTopic.getItemAtPosition(i);
        int idAccount = getIntent().getIntExtra(MainActivity.ID_LOGIN, 0);
        String state = getIntent().getStringExtra(MainActivity.STATE_TOPIC);
        if (state.equals("start")) {
            Intent intent = new Intent(TopicExamActivity.this, StartExamActivity.class);
            intent.putExtra(MainActivity.ID_LOGIN, idAccount);
            intent.putExtra(ID_TOPIC, topic.id);
            startActivity(intent);
        }
        if (state.equals("show")) {
            Intent intent = new Intent(TopicExamActivity.this, ListQuestionsActivity.class);
            intent.putExtra(ID_TOPIC, topic.id);
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        btnDeleteTopic = view.findViewById(R.id.btnDelete);
        btnDeleteTopic.setVisibility(View.VISIBLE);
        btnDeleteTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicHelper.deleteTopic(topics.get(i).id);
                topicHelper.deleteQuestionFromTopic(topics.get(i).id);
                topics.remove(i);
                btnDeleteTopic.setVisibility(View.GONE);
            }
        });
        return false;
    }
}