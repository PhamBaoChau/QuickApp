package com.baochau.dmt.quickapp.model;

import java.io.Serializable;

public class ItemQuestion implements Serializable {
    public int id;
    public String question;
    public int idTopic;
    public Answer answer1;
    public Answer answer2;
    public Answer answer3;
    public Answer correct;

    public Boolean isShowingDeleteBtn = false;
    public ItemQuestion() {
    }

    public ItemQuestion(int id, String question, int idTopic, Answer answer1, Answer answer2, Answer answer3, Answer correct) {
        this.id = id;
        this.question = question;
        this.idTopic = idTopic;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "ItemQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", idTopic=" + idTopic +
                ", answer1=" + answer1 +
                ", answer2=" + answer2 +
                ", answer3=" + answer3 +
                ", correct=" + correct +
                ", isShowingDeleteBtn=" + isShowingDeleteBtn +
                '}';
    }
}

