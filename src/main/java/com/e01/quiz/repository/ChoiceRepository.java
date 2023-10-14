package com.e01.quiz.repository;

import com.e01.quiz.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    @Query("SELECT c FROM Choice c WHERE c.question.id = ?1")
    List<Choice> findAllByQuestionId(Long questionId);
}
