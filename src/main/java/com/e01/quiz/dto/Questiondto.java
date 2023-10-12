package com.e01.quiz.dto;

import java.util.Set;

public class Questiondto {
    private long Id;
    private String questionContent;
    private Set<Answerdto> answers;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Set<Answerdto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answerdto> answers) {
        this.answers = answers;
    }
}
