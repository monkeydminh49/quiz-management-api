package com.e01.quiz.dto;

import com.e01.quiz.util.EQuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionOutputDTO {
    private Long id;
    private Long testId;
    private EQuestionType type;
    private String question;
    private List<ChoiceOutputDTO> choices;

    public List<ChoiceOutputDTO> getChoices() {
        return choices != null ? choices : List.of();
    }

    public EQuestionType getType() {
        return type != null ? type : EQuestionType.SINGLE_CHOICE;
    }
}

