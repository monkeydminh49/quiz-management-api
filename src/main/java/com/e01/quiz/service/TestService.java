package com.e01.quiz.service;

import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.entity.User;
import com.e01.quiz.repository.TestRepository;
import com.e01.quiz.component.Mapper;
import com.e01.quiz.repository.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private Mapper mapper;

    public List<Test> getTests(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        return testRepository.findAllById(user.getTests().stream().map(Test::getId).toList());
    }

    public Test createTest(String username, Test test) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        test.setUser(user);
        testRepository.save(test);
        questionService.saveQuestions(test.getQuestions(), test);

        return test;
    }

    public Test getUserTestById(String username, Long id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));

        if (!user.hasTest(id)) {
            throw new RuntimeException("User " + username + " does not have test with id " + id);
        }
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("test not found"));
    }

    public Test updateTest(String username, Long id, TestDTO testDTO) {
        Test test = getUserTestById(username, id);
        test.setTitle(testDTO.getTitle());
        test.setDuration(testDTO.getDuration());
        // Delete old questions
        questionService.deleteQuestionsByTestId(id);

        // Add new questions
        List<Question> newQuestions = testDTO.getQuestions().stream().map(mapper::toEntity).toList();
        questionService.saveQuestions(newQuestions, test);

        return testRepository.save(test);
    }
}
