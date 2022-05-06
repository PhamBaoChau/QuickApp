package com.baochau.dmt.quickapp.slide;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.database.QuestionHelper;
import com.baochau.dmt.quickapp.questions.Answer;
import com.baochau.dmt.quickapp.questions.ItemQuestion;

import java.util.ArrayList;


public class SlidePageFragment extends Fragment {
    public static final String ARG_PAGE = "page";
    int numPage;
    public static ArrayList<ItemQuestion> arrayList = new ArrayList<>();
    QuestionHelper db;
    TextView title;
    TextView question;
    RadioButton answer1, answer2, answer3;
    RadioGroup radioGroup;
    ImageView imgAns1Correct, imgAns2Correct, imgAns3Correct;
    ImageView imgAns1Wrong, imgAns2Wrong, imgAns3Wrong;
    Answer myAnswer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_slide_page_exam, container, false);
        title = rootView.findViewById(R.id.lb_title);
        question = rootView.findViewById(R.id.tv_question);
        radioGroup = rootView.findViewById(R.id.rd_group);
        answer1 = rootView.findViewById(R.id.rd_answer1);
        answer2 = rootView.findViewById(R.id.rd_answer2);
        answer3 = rootView.findViewById(R.id.rd_answer3);
        imgAns1Correct = rootView.findViewById(R.id.icon_answer1_correct);
        imgAns2Correct = rootView.findViewById(R.id.icon_answer2_correct);
        imgAns3Correct = rootView.findViewById(R.id.icon_answer3_correct);
        imgAns1Wrong = rootView.findViewById(R.id.icon_answer1_wrong);
        imgAns2Wrong = rootView.findViewById(R.id.icon_answer2_wrong);
        imgAns3Wrong = rootView.findViewById(R.id.icon_answer3_wrong);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        numPage = getArguments().getInt(ARG_PAGE);
        db = new QuestionHelper(getContext(), null, null, 0);
        arrayList = db.getQuestions();
        title.setText("Câu " + (numPage + 1) + ": ");
        question.setText(arrayList.get(numPage).question);
        answer1.setText(arrayList.get(numPage).answer1.answer);
        answer2.setText(arrayList.get(numPage).answer2.answer);
        answer3.setText(arrayList.get(numPage).answer3.answer);
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Check(numPage);
//                btnConfirm.setVisibility(view.GONE);
//            }
//        });
    }

    public static SlidePageFragment create(int pageNumber) {
        SlidePageFragment slidePageFragment = new SlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, pageNumber);
        slidePageFragment.setArguments(bundle);
        return slidePageFragment;
    }

    private void showImageView(ImageView image, Boolean visible) {
        if (visible) {
            image.setVisibility(View.VISIBLE);
        }
    }

    public void Check(int i) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (arrayList.get(i).correct.answer.equals(answer1.getText())) {

                showImageView(imgAns1Correct, answer1.isChecked());

                showImageView(imgAns2Wrong, answer2.isChecked());

                showImageView(imgAns3Wrong, answer3.isChecked());
//                answer1.setChecked(true);

            } else if (arrayList.get(i).correct.answer.equals(answer2.getText())) {
                showImageView(imgAns2Correct, answer2.isChecked());

                showImageView(imgAns1Wrong, answer1.isChecked());

                showImageView(imgAns3Wrong, answer3.isChecked());
//                answer2.setChecked(true);
            } else if (arrayList.get(i).correct.answer.equals(answer3.getText())) {

                showImageView(imgAns3Correct, answer3.isChecked());

                showImageView(imgAns1Wrong, answer1.isChecked());

                showImageView(imgAns2Wrong, answer2.isChecked());
//                answer3.setChecked(true);
            }
        }
        else {
            ExitGameDialogFragment exitGameDialogFragment = new ExitGameDialogFragment();
            exitGameDialogFragment.show(getFragmentManager(), "game");
        }
    }

    public Answer getAnswer() {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (radioGroup.getCheckedRadioButtonId() == answer1.getId()) {
                myAnswer= arrayList.get(numPage).answer1;
            }
            if (radioGroup.getCheckedRadioButtonId() == answer2.getId()) {
                myAnswer = arrayList.get(numPage).answer2;
            }
            if (radioGroup.getCheckedRadioButtonId() == answer3.getId()) {
                myAnswer = arrayList.get(numPage).answer3;
            }
        }
        if (myAnswer != null) {
            return myAnswer;
        }
        return null;
    }
}
