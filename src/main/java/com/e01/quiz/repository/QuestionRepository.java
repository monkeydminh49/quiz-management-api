package com.e01.quiz.repository;

import com.e01.quiz.entity.Answer;
import com.e01.quiz.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findById(long id);
    Question findByQuestionContent(String questionContent);
    Answer findByAnswers(String answerContent);
}
