package com.baochau.dmt.quickapp.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.baochau.dmt.quickapp.BaseActivity;
import com.baochau.dmt.quickapp.model.ItemQuestion;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.adapter.ShowQuestionAdapter;
import com.baochau.dmt.quickapp.database.QuestionHelper;

import java.util.ArrayList;

public class ListQuestionsActivity extends BaseActivity {
    public static final String ID_QUESTION = "id_question";
    ListView lvQuestions;
    ConstraintLayout layout;
    Button btnAddQuestion;
    ShowQuestionAdapter adapter;
    ArrayList<ItemQuestion> questions = new ArrayList<>();
    QuestionHelper db = new QuestionHelper(this, null, null, 0);
    int idTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        lvQuestions = findViewById(R.id.lvQuestion);
        layout = findViewById(R.id.ltListQuestion);
        idTopic = getIntent().getIntExtra(TopicExamActivity.ID_TOPIC, 0);


        btnAddQuestion = findViewById(R.id.btnAddQuestion);
        for (ItemQuestion item : db.getQuestions()) {
            if ((item.idTopic) == idTopic) {
                questions.add(item);
            }
        }
        adapter = new ShowQuestionAdapter(this, questions);
        lvQuestions.setAdapter(adapter);

        lvQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListQuestionsActivity.this, UpdateQuestionActivity.class);
                intent.putExtra(StartExamActivity.ITEM_QUESTION, questions.get(i));
                startActivity(intent);
            }
        });

        lvQuestions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (ItemQuestion item: questions){
                    if (item.isShowingDeleteBtn) {
                        item.isShowingDeleteBtn = false;
                    }
                }
                Button btnDelete = view.findViewById(R.id.btnDelete);
                btnDelete.setVisibility(View.VISIBLE);
                questions.get(i).isShowingDeleteBtn = true;
                adapter.notifyDataSetChanged();
                System.out.println("Chau: "+i);
                System.out.println("LIST DATA: "+questions.toString());
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.deleteQuestion(questions.get(i).id, questions.get(i).idTopic);
                        questions.remove(i);
                        adapter.notifyDataSetChanged();
                        btnDelete.setVisibility(View.GONE);
                        System.out.println(questions.toString());
                        System.out.println(db.getQuestions());
                    }
                });
                return false;
            }
        });

        btnAddQuestion.setOnClickListener(view -> {
                    Intent intent = new Intent(ListQuestionsActivity.this, AddQuestionActivity.class);
                    intent.putExtra(TopicExamActivity.ID_TOPIC, idTopic);
                    startActivity(intent);
                }
        );

    }
}