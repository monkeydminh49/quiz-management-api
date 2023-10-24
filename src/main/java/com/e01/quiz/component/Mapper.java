package com.e01.quiz.component;

import com.e01.quiz.dto.ChoiceDTO;
import com.e01.quiz.dto.QuestionDTO;
import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.dto.UserResponse;
import com.e01.quiz.entity.Choice;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserResponse toDTO(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
    public TestDTO toDTO(Test test) {
        return TestDTO.builder()
                .id(test.getId())
                .code(test.getCode())
                .title(test.getTitle())
                .startTime(test.getStartTime())
                .userId(test.getUser().getId())
                .questions(test.getQuestions().stream().map(this::toDTO).toList())
                .duration(test.getDuration())
                .build();
    }


    public Test toEntity(TestDTO testDTO) {
        return Test.builder()
                .title(testDTO.getTitle())
                .startTime(testDTO.getStartTime())
                .questions(testDTO.getQuestions().stream().map(this::toEntity).toList())
                .duration(testDTO.getDuration())
                .build();
    }
    public QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .testId(question.getTest().getId())
                .question(question.getQuestion())
                .choices(question.getChoices().stream().map(this::toDTO).toList())
                .build();
    }

    public Question toEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .question(questionDTO.getQuestion())
                .choices(questionDTO.getChoices().stream().map(this::toEntity).toList())
                .build();
    }

    public ChoiceDTO toDTO(Choice choice) {
        return ChoiceDTO.builder()
                .id(choice.getId())
                .content(choice.getContent())
                .isCorrect(choice.isCorrect())
                .questionId(choice.getQuestion().getId())
                .build();
    }

    public Choice toEntity(ChoiceDTO choiceDTO) {
        return Choice.builder()
                .content(choiceDTO.getContent())
                .isCorrect(choiceDTO.getIsCorrect())
                .build();
    }
}
