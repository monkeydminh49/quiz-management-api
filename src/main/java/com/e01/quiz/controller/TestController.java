package com.e01.quiz.controller;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.dto.MappingResponse;
import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Test;
import com.e01.quiz.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Mapper mapper;

    @PostMapping("/test")
    public MappingResponse createTest(Principal principal, @RequestBody TestDTO testDTO) {
        String username = principal.getName();
        Test test = mapper.toEntity(testDTO);
        Test createdTest = testService.createTest(username, test);
        return MappingResponse.builder()
                .code(1)
                .body(mapper.toDTO(createdTest))
                .message("Create test successfully")
                .build();
    }

    @GetMapping("/test")
    public List<TestDTO> getTests(Principal principal) {
        String username = principal.getName();
        List<Test> tests = testService.getTests(username);

        return tests.stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/test/{id}")
    public TestDTO getUserTestById(Principal principal, @PathVariable Long id) {
        String username = principal.getName();
        Test test = testService.getUserTestById(username, id);
        return mapper.toDTO(test);
    }

    @PutMapping("/test/{id}")
    public MappingResponse updateTest(Principal principal, @PathVariable Long id, @RequestBody TestDTO testDTO) {
        String username = principal.getName();
        Test test = testService.updateTest(username, id, testDTO);
        return MappingResponse.builder()
                .code(1)
                .body(mapper.toDTO(test))
                .message("Update test successfully")
                .build();
    }
}
