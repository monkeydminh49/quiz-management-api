package com.e01.quiz.service;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.entity.Test;
import com.e01.quiz.entity.TestHistory;
import com.e01.quiz.entity.User;
import com.e01.quiz.repository.TestHistoryRepository;
import com.e01.quiz.repository.TestRepository;
import com.e01.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestHistoryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private TestHistoryRepository testHistoryRepository;

    public TestHistory submitTest(String username, TestHistory testHistory) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        Test test = testRepository.findById(testHistory.getId()).orElseThrow(() -> new RuntimeException("test not found"));
        TestHistory testHistory1 = mapper.testToTestHistory(test);
        testHistory1.setUser(user);
        testHistory1.setScore(testHistory.getScore());
        testHistory1.setSubmitTime(LocalDateTime.now());
        testHistoryRepository.save(testHistory1);
        return testHistory1;
    }

    public List<TestHistory> getTestHistories(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("user not found"));
        return testHistoryRepository.findAllById(user.getTestHistories().stream().map(TestHistory::getId).toList());
    }
}
