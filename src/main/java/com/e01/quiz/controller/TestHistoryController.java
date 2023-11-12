package com.e01.quiz.controller;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.dto.TestHistoryDTO;
import com.e01.quiz.entity.TestHistory;
import com.e01.quiz.service.TestHistoryService;
import com.e01.quiz.service.TestService;
import com.e01.quiz.util.EMessageType;
import com.e01.quiz.util.EQuestionType;
import com.e01.quiz.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static java.util.Arrays.stream;

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

    @Autowired
    private SimpMessageSendingOperations template;

    @PostMapping
    public TestHistoryDTO submitTest(Principal principal, @RequestBody TestHistoryDTO testHistoryDTO) {
        String username = principal.getName();
        TestHistory testHistory = mapper.toEntity(testHistoryDTO);
        testHistory = testHistoryService.submitTest(username, testHistory);
        System.out.println("Destination: /topic/test/" + testHistory.getTestId());
        testHistoryDTO = mapper.toDTO(testHistory);
        template.convertAndSend("/topic/test/" + testHistory.getTestId(), new Message(username, testHistoryDTO, EMessageType.TEST_HISTORY));

        return testHistoryDTO;
    }
    @GetMapping
    public List<TestHistoryDTO> getTestHistories(Principal principal) {
        String username = principal.getName();
        List<TestHistory> testHistories = testHistoryService.getTestHistories(username);
        return testHistories.stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public List<TestHistoryDTO> getTestHistoryByTestId(Principal principal, @PathVariable Long id) {
        String username = principal.getName();
        List<TestHistory> testHistories = testHistoryService.getTestHistoriesByTestId(username, id);
        return testHistories.stream().map(mapper::toDTO).toList();
    }

}
