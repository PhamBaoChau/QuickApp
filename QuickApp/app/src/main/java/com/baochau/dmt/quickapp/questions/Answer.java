package com.baochau.dmt.quickapp.questions;

import java.io.Serializable;

public class Answer implements Serializable {
    public String id;
    public String answer;

    public Answer() {
    }

    public Answer(String id, String answer) {
        this.id = id;
        this.answer = answer;
    }
}
