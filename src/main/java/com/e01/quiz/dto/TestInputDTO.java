package com.e01.quiz.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TestInputDTO {
    private String title;
    private LocalDateTime startTime;
    private List<QuestionInputDTO> questions;
    private Long duration;
}
