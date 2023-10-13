package com.e01.quiz.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(
        name = "QUESTION"
)
public class Question {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String question;

    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    private Test test;
}
