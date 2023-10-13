package com.e01.quiz.service;

import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.repository.TestRepository;
import com.e01.quiz.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private Mapper mapper;

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public Test createTest(TestDTO testDTO) {
        List<Question> questions = testDTO.getQuestions().stream().map(mapper::toEntity).toList();

        Test test = testRepository.save(Test.builder()
                .title(testDTO.getTitle())
                .questions(questions)
                .build());

        questionService.saveQuestions(questions, test);

        return test;
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("test not found"));
    }

    public Test addQuestions(Long id, List<Question> questions) {
        Test test = getTestById(id);
        List<Question> newQuestions = new ArrayList<>(test.getQuestions());
        newQuestions.addAll(questions);
        test.setQuestions(newQuestions);
        return testRepository.save(test);
    }

    public Test updateTest(Long id, TestDTO testDTO) {
        Test test = getTestById(id);
        test.setTitle(testDTO.getTitle());

        // Delete old questions
        questionService.deleteQuestionsByTestId(id);

        // Add new questions
        List<Question> newQuestions = testDTO.getQuestions().stream().map(mapper::toEntity).toList();
        questionService.saveQuestions(newQuestions, test);

        return testRepository.save(test);
    }
}
