package com.e01.quiz.service;


import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repository;

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Question getQuestionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Question "));
    }

    public Question getTestIdById(Long testId) {
        return repository.findById(testId).orElseThrow(() -> new RuntimeException("Invalid Test"));
    }


    public void saveQuestions(List<Question> questions, Test test) {
        questions.forEach(question -> question.setTest(test));
        repository.saveAll(questions);
    }

    public void deleteQuestions(List<Question> oldQuestions) {
        repository.deleteAll(oldQuestions);
    }

    public void deleteQuestionsByTestId(Long testId) {
        List<Question> questions =  repository.findAllByTestId(testId);
        repository.deleteAll(questions);
    }
}