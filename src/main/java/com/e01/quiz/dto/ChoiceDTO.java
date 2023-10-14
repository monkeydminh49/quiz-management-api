package com.e01.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ChoiceDTO {
    private Long id;
    private String content;
    private Boolean isCorrect;
    private Long questionId;

    public Boolean getIsCorrect() {
        return isCorrect != null ? isCorrect : false;
    }
}
