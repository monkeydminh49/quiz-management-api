package com.e01.quiz.component;

import com.e01.quiz.dto.*;
import com.e01.quiz.entity.*;
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
                .questions(testDTO.getQuestions() != null ? testDTO.getQuestions().stream().map(this::toEntity).toList() : null)
                .duration(testDTO.getDuration())
                .build();
    }
    public QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .testId(question.getTest() != null ? question.getTest().getId(): null)
                .question(question.getQuestion())
                .type(question.getType())
                .choices(question.getChoices() != null ? question.getChoices().stream().map(this::toDTO).toList(): null)
                .build();
    }

    public Question toEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .question(questionDTO.getQuestion())
                .type(questionDTO.getType())
                .choices(questionDTO.getChoices() != null ? questionDTO.getChoices().stream().map(this::toEntity).toList() : null)
                .build();
    }

    public ChoiceDTO toDTO(Choice choice) {
        return ChoiceDTO.builder()
                .id(choice.getId())
                .content(choice.getContent())
                .isCorrect(choice.isCorrect())
                .questionId(choice.getQuestion() != null ? choice.getQuestion().getId() : null)
                .build();
    }

    public Choice toEntity(ChoiceDTO choiceDTO) {
        return Choice.builder()
                .id(choiceDTO.getId())
                .content(choiceDTO.getContent())
                .isCorrect(choiceDTO.getIsCorrect())
                .build();
    }

    public TestHistory toEntity(TestHistoryDTO testHistoryDTO) {
        return TestHistory.builder()
                .id(testHistoryDTO.getId())
                .score(testHistoryDTO.getScore())
                .submitTime(testHistoryDTO.getSubmitTime())
                .build();
    }
    public TestHistoryDTO toDTO(TestHistory testHistory) {
        return TestHistoryDTO.builder()
                .id(testHistory.getId())
                .testId(testHistory.getTestId())
                .code(testHistory.getCode())
                .title(testHistory.getTitle())
                .startTime(testHistory.getStartTime())
                .submitTime(testHistory.getSubmitTime())
                .candidateId(testHistory.getUser() != null ? testHistory.getUser().getId() : null)
                .candidateName(testHistory.getUser() != null ? testHistory.getUser().getName() : null)
                .duration(testHistory.getDuration())
                .score(testHistory.getScore())
                .build();
    }

    public TestHistory testToTestHistory(Test test) {
        return TestHistory.builder()
                .id(test.getId())
                .testId(test.getId())
                .title(test.getTitle())
                .startTime(test.getStartTime())
                .code(test.getCode())
                .duration(test.getDuration())
                .user(test.getUser())
                .score(0)
                .build();
    }
}
