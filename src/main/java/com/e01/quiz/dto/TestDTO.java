package com.e01.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDTO {
    private Long id;
    private String title;
    private List<QuestionDTO> questions;

    public List<QuestionDTO> getQuestions() {
        return questions != null ? questions : List.of();
    }
}
