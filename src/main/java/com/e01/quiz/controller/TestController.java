package com.e01.quiz.controller;

import com.e01.quiz.dto.MappingResponse;
import com.e01.quiz.dto.TestDTO;
import com.e01.quiz.entity.Question;
import com.e01.quiz.entity.Test;
import com.e01.quiz.service.TestService;
import com.e01.quiz.component.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Mapper mapper;

    @PostMapping("/test")
    public MappingResponse createTest(@RequestBody TestDTO testDTO) {
        Test test = mapper.toEntity(testDTO);
        Test createdTest = testService.createTest(test);
        return MappingResponse.builder()
                .code(1)
                .body(mapper.toDTO(createdTest))
                .message("Create test successfully")
                .build();
    }

    @GetMapping("/test")
    public List<TestDTO> getTests() {
        List<Test> tests = testService.getTests();

        return tests.stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/test/{id}")
    public TestDTO getTestById(@PathVariable Long id) {
        Test test = testService.getTestById(id);
        return mapper.toDTO(test);
    }

    @PutMapping("/test/{id}")
    public TestDTO updateTest(@PathVariable Long id, @RequestBody TestDTO testDTO) {
        Test test = testService.updateTest(id, testDTO);
        return mapper.toDTO(test);
    }
}
