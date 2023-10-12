package com.e01.quiz.dto;

import lombok.Getter;

@Getter
public class AnswerRequest {
    private String answerContent;
    private boolean correct;

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
