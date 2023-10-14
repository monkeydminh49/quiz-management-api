package com.e01.quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(
        name = "CHOICE"
)
public class Choice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String choice;
    private boolean isCorrect;
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
}
