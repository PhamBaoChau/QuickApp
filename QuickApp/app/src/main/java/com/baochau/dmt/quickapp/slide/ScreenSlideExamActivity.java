package com.baochau.dmt.quickapp.slide;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baochau.dmt.quickapp.R;
import com.baochau.dmt.quickapp.questions.Answer;
import com.baochau.dmt.quickapp.questions.FirstFragment;
import com.baochau.dmt.quickapp.questions.ItemQuestion;

import java.util.ArrayList;
import java.util.Arrays;

public class ScreenSlideExamActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    ArrayList<ItemQuestion> arrayList = new ArrayList<>();
    ArrayList<Answer> answers = new ArrayList<>();
    SlidePageFragment fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_exam);
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        fragment1 = (SlidePageFragment) pagerAdapter.getItem(0);
        Button btnNext = findViewById(R.id.btn_next);
        Button btnFinish = findViewById(R.id.btn_finish);
//        RadioGroup radioGroup = findViewById(R.id.rd_group);
//        RadioButton answer1 = findViewById(R.id.rd_answer1);
//        RadioButton answer2 = findViewById(R.id.rd_answer2);
//        RadioButton answer3 = findViewById(R.id.rd_answer3);
        arrayList = SlidePageFragment.arrayList;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                btnConfirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        btnConfirm.setVisibility(View.GONE);
//                        btnNext.setVisibility(View.VISIBLE);
////                        System.out.println(arrayList.get(i).correct);
//                        System.out.println(answer1.getText());
//                    }
//                });
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fragment1.getAnswer() != null) {
                            answers.add(fragment1.getAnswer());
                        }
                        viewPager.arrowScroll(View.FOCUS_RIGHT);
                    }
                });
            }

            @Override
            public void onPageSelected(int i) {
//                btnConfirm.setVisibility(View.VISIBLE);
                if (i == viewPager.getAdapter().getCount() - 1) {
                    btnFinish.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }



}