package com.baochau.dmt.quickapp.questions;

import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class ItemQuestion implements Serializable {
    public int id;
    public String question;
    public Answer answer1;
    public Answer answer2;
    public Answer answer3;
    public Answer correct;

    public ItemQuestion() {}
    public ItemQuestion(int id, String question, Answer answer1, Answer answer2, Answer answer3, Answer correct) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correct = correct;
    }

}

