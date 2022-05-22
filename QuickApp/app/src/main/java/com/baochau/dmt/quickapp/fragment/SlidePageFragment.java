package com.baochau.dmt.quickapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baochau.dmt.quickapp.Activity.StartExamActivity;
import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.model.Answer;
import com.baochau.dmt.quickapp.model.ItemQuestion;


public class SlidePageFragment extends Fragment {

    TextView title;
    TextView question;
    RadioButton answer1, answer2, answer3;
    RadioGroup radioGroup;
    ImageView imgAns1Correct, imgAns2Correct, imgAns3Correct;
    ImageView imgAns1Wrong, imgAns2Wrong, imgAns3Wrong;
    Answer myAnswer;
    ItemQuestion itemQuestion;

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

        Bundle bundle = getArguments();
        ItemQuestion itemQuestion = (ItemQuestion) bundle.getSerializable(StartExamActivity.ITEM_QUESTION);
        setQuestion(itemQuestion);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setQuestion(ItemQuestion itemQuestion) {
        question.setText(itemQuestion.question);
        answer1.setText(itemQuestion.answer1.answer);
        answer2.setText(itemQuestion.answer2.answer);
        answer3.setText(itemQuestion.answer3.answer);
        this.itemQuestion = itemQuestion;
    }

//    public static SlidePageFragment create(int pageNumber) {
//        SlidePageFragment slidePageFragment = new SlidePageFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt(ARG_PAGE, pageNumber);
//        slidePageFragment.setArguments(bundle);
//        return slidePageFragment;
//    }

    private void showImageView(ImageView image, Boolean visible) {
        if (visible) {
            image.setVisibility(View.VISIBLE);
        }
    }

//    private void showCorrectAnswer(Answer correctAnswer) {
//        if (correctAnswer.answer.equals(answer1.getText())) {
//            showImageView(imgAns1Correct, answer1.isChecked());
//        } else {
//            imgAns1Wrong.setVisibility(View.VISIBLE);
//
//        }
//    }

    private void checkAnswerNSetupLayout() {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (itemQuestion.correct.answer.equals(answer1.getText())) {

                showImageView(imgAns1Correct, answer1.isChecked());
                showImageView(imgAns2Wrong, answer2.isChecked());
                showImageView(imgAns3Wrong, answer3.isChecked());
//                answer1.setChecked(true);
                imgAns1Correct.setVisibility(View.VISIBLE);

            }
            if (itemQuestion.correct.answer.equals(answer2.getText())) {
                showImageView(imgAns2Correct, answer2.isChecked());
                showImageView(imgAns1Wrong, answer1.isChecked());
                showImageView(imgAns3Wrong, answer3.isChecked());
//                answer2.setChecked(true);
                imgAns2Correct.setVisibility(View.VISIBLE);
            }

            if (itemQuestion.correct.answer.equals(answer3.getText())) {

                showImageView(imgAns3Correct, answer3.isChecked());
                showImageView(imgAns1Wrong, answer1.isChecked());
                showImageView(imgAns2Wrong, answer2.isChecked());
//                answer3.setChecked(true);
                imgAns3Correct.setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn đáp án", Toast.LENGTH_SHORT).show();
//            ExitGameDialogFragment exitGameDialogFragment = new ExitGameDialogFragment();
//            exitGameDialogFragment.show(getFragmentManager(), "game");
        }
    }

    public Answer getAnswer() {
        checkAnswerNSetupLayout();
        if (answer1.isChecked()) {
            return myAnswer = itemQuestion.answer1;
        }
        if (answer2.isChecked()) {
            return myAnswer = itemQuestion.answer2;
        }
        if (answer3.isChecked()) {
            return myAnswer = itemQuestion.answer3;
        }
        return null;
    }
}
