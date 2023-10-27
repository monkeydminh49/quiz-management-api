package com.e01.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestHistoryDTO {
    private Long id;
    private String code;
    private String title;
    private LocalDateTime startTime;
    private Long duration;
    private int score;
    private Long userId;
    private LocalDateTime submitTime;
}
