package com.e01.quiz.repository;

import com.e01.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long>{
    Optional<Question> findById(Long testId);
}
