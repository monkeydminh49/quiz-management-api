package com.e01.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(
        name = "TEST"
)
public class Test {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String code;
    private String title;
    private LocalDateTime startTime;
    @OneToMany(mappedBy = "test")
    private List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Long duration;
}
