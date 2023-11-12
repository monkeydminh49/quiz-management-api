package com.e01.quiz.repository;

import com.e01.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long>{
    @Query("SELECT q FROM Question q WHERE q.test.id = ?1")
    List<Question> findAllByTestId(Long testId);
}
