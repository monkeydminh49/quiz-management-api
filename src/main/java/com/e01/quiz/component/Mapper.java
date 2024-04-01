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
                .numberOfLiveParticipant(test.getNumberOfLiveParticipant())
                .build();
    }


    public Test toEntity(TestDTO testOutputDTO) {
        return Test.builder()
                .title(testOutputDTO.getTitle())
                .startTime(testOutputDTO.getStartTime())
                .questions(testOutputDTO.getQuestions() != null ? testOutputDTO.getQuestions().stream().map(this::toEntity).toList() : null)
                .duration(testOutputDTO.getDuration())
                .build();
    }
    public QuestionOutputDTO toDTO(Question question) {
        return QuestionOutputDTO.builder()
                .id(question.getId())
                .testId(question.getTest() != null ? question.getTest().getId(): null)
                .question(question.getQuestion())
                .type(question.getType())
                .choices(question.getChoices() != null ? question.getChoices().stream().map(this::toDTO).toList(): null)
                .build();
    }

    public Question toEntity(QuestionOutputDTO questionOutputDTO) {
        return Question.builder()
                .id(questionOutputDTO.getId())
                .question(questionOutputDTO.getQuestion())
                .type(questionOutputDTO.getType())
                .choices(questionOutputDTO.getChoices() != null ? questionOutputDTO.getChoices().stream().map(this::toEntity).toList() : null)
                .build();
    }

    public ChoiceOutputDTO toDTO(Choice choice) {
        return ChoiceOutputDTO.builder()
                .id(choice.getId())
                .content(choice.getContent())
                .isCorrect(choice.isCorrect())
                .questionId(choice.getQuestion() != null ? choice.getQuestion().getId() : null)
                .build();
    }

    public Choice toEntity(ChoiceOutputDTO choiceOutputDTO) {
        return Choice.builder()
                .id(choiceOutputDTO.getId())
                .content(choiceOutputDTO.getContent())
                .isCorrect(choiceOutputDTO.getIsCorrect())
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
