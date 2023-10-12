package com.e01.quiz.dto;


import java.io.Serializable;

public class Answerdto {

    private long Id;
    private String answerContent;
    private boolean correct;
    private Questiondto question;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Questiondto getQuestion() {
        return question;
    }

    public void setQuestion(Questiondto question) {
        this.question = question;
    }
}

