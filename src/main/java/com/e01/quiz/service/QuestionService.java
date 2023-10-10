package com.e01.quiz.service;


import com.e01.quiz.entity.Question;
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


}