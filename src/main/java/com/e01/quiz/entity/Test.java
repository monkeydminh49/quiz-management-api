package com.e01.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Test {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String code;
    private String title;
    private LocalDateTime startTime;
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "test", cascade = { CascadeType.MERGE}, orphanRemoval = true)
    private List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Long duration;
    private Integer numberOfLiveParticipant;

    public void displayTest(){
        System.out.println("Id: " + this.id);
        System.out.println("Test: " + this.title);
        System.out.println("Code: " + this.code);
        System.out.println("Start time: " + this.startTime);
        System.out.println("Duration: " + this.duration);
        System.out.println("Questions: ");
        this.questions.forEach(question -> {
            System.out.println("Id: " + question.getId());
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Type: " + question.getType());
            System.out.println("Choices: ");
            question.getChoices().forEach(choice -> {
                System.out.println("Choice: " + choice);
            });
        });
    }

    public void increaseNumberOfLiveParticipantByOne(){
        if (this.numberOfLiveParticipant == null){
            this.numberOfLiveParticipant = 0;
        }
        this.numberOfLiveParticipant += 1;
    }

    public void decreaseNumberOfLiveParticipantByOne(){
        if (this.numberOfLiveParticipant == null){
            this.numberOfLiveParticipant = 0;
        }
        if (this.numberOfLiveParticipant > 0){
            this.numberOfLiveParticipant -= 1;
        }
    }

    public String toString(){
        return "Test(title=" + this.getTitle() + ")";
    }
}
