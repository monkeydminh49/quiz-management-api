package com.e01.quiz.entity;


import com.e01.quiz.util.EQuestionType;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "question", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private List<Choice> choices;

    public EQuestionType getType() {
        return type != null ? type : EQuestionType.SINGLE_CHOICE;
    }
}
