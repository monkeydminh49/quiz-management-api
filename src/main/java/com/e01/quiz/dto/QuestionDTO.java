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
public class QuestionDTO {
    private Long id;
    private Long testId;
    private String question;
    private List<ChoiceDTO> choices;

    public List<ChoiceDTO> getChoices() {
        return choices != null ? choices : List.of();
    }
}

