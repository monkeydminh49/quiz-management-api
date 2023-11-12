package com.e01.quiz.entity;


import com.e01.quiz.util.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(
        name = "USERS"
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Test> tests;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TestHistory> testHistories;

    public boolean hasTest(Long id) {
        for (Test test : tests) {
            if (test.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
