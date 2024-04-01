package com.e01.quiz.dto;

import com.e01.quiz.util.EQuestionType;

import java.util.List;

public class QuestionInputDTO {
    private EQuestionType type;
    private String question;
    private List<ChoiceInputDTO> choices;
}