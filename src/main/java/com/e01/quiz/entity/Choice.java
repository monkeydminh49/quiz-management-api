package com.e01.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Choice {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private boolean isCorrect;
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;

    public String toString() {
        return "Choice(id=" + this.getId() + ", content=" + this.getContent() + ", isCorrect=" + this.isCorrect() + ")";
    }
}
