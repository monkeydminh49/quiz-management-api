package com.e01.quiz.component;

import com.e01.quiz.dto.QuestionDTO;
import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    public TestDTO toDTO(Test test) {
        return TestDTO.builder()
                .id(test.getId())
                .title(test.getTitle())
                .questions(test.getQuestions().stream().map(this::toDTO).toList())
                .build();
    }

    public Test toEntity(TestDTO testDTO) {
        return Test.builder()
                .title(testDTO.getTitle())
                .questions(testDTO.getQuestions().stream().map(this::toEntity).toList())
                .build();
    }
    public QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .testId(question.getTest().getId())
                .build();
    }

    public Question toEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .question(questionDTO.getQuestion())
                .build();
    }
}
