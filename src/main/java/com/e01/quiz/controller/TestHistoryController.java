package com.e01.quiz.controller;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.dto.TestHistoryDTO;
import com.e01.quiz.entity.TestHistory;
import com.e01.quiz.service.TestHistoryService;
import com.e01.quiz.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/test-history")
@Slf4j
public class TestHistoryController {
    @Autowired
    private TestHistoryService testHistoryService;
    @Autowired
    private TestService testService;
    @Autowired
    private Mapper mapper;

    @PostMapping
    public TestHistoryDTO submitTest(Principal principal, @RequestBody TestHistoryDTO testHistoryDTO) {
        String username = principal.getName();
        TestHistory testHistory = mapper.toEntity(testHistoryDTO);

        testHistory = testHistoryService.submitTest(username, testHistory);
        testHistoryDTO = mapper.toDTO(testHistory);

        return testHistoryDTO;
    }
    @GetMapping
    public List<TestHistoryDTO> getTestHistories(Principal principal) {
        String username = principal.getName();
        List<TestHistory> testHistories = testHistoryService.getTestHistories(username);
        return testHistories.stream().map(mapper::toDTO).toList();
    }
}
