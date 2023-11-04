package com.e01.quiz.entity;


import com.e01.quiz.util.EQuestionType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Data
//@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(
        name = "QUESTION"
)
public class Question {
    public Question(){
        this.type = EQuestionType.SINGLE_CHOICE;
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String question;
    @Enumerated(EnumType.STRING)
    private EQuestionType type;

    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    private Test test;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices;

    public EQuestionType getType() {
        return type != null ? type : EQuestionType.SINGLE_CHOICE;
    }
}
