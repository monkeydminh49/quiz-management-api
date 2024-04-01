package com.e01.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestOutputDTO {
    private Long id;
    private String code;
    private Long userId;
    private String title;
    private LocalDateTime startTime;
    private List<QuestionOutputDTO> questions;
    private Long duration;
    private Integer numberOfLiveParticipant;

    public List<QuestionOutputDTO> getQuestions() {
        return questions != null ? questions : List.of();
    }
}
