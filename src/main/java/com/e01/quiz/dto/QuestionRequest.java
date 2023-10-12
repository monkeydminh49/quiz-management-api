package com.e01.quiz.dto;
import java.util.Set;

public class QuestionRequest {

    private String questionContent;
    private Set<AnswerRequest> answers; // set for unique values

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Set<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerRequest> answers) {
        this.answers = answers;
    }
}
