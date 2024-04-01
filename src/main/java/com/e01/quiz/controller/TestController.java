package com.e01.quiz.controller;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.dto.MappingResponse;
import com.e01.quiz.dto.TestOutputDTO;
import com.e01.quiz.entity.Test;
import com.e01.quiz.mapper.TestMapper;
import com.e01.quiz.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/test")
@Slf4j
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private Mapper mapper;

    @Autowired
    private TestMapper testMapper;


    @PostMapping
    public MappingResponse createTest(Principal principal, @RequestBody TestInputDTO testInputDTO) {
        String username = principal.getName();
        Test createdTest = testService.createTest(username, test);
        return MappingResponse.builder()
                .code(1)
                .body(testMapper.getTestOutputDtoFromTest(createdTest))
                .message("Create test successfully")
                .build();
    }

    @GetMapping("/all")
    public List<TestOutputDTO> getTests(Principal principal) {
        String username = principal.getName();
        List<Test> tests = testService.getTests(username);

        return testMapper.getTestOutputDtosFromTests(tests);
    }
    @GetMapping()
    public TestOutputDTO getTestByCode(@RequestParam String code) {
        Test test = testService.getTestByCode(code);
        return testMapper.getTestOutputDtoFromTest(test);
    }

    @GetMapping("/{id}")
    public TestOutputDTO getUserTestById(Principal principal, @PathVariable Long id) {
        String username = principal.getName();
        Test test = testService.getUserTestById(username, id).get();
        return mapper.toDTO(test);
    }

    @PutMapping("/{id}")
    public MappingResponse updateTest(Principal principal, @PathVariable Long id, @RequestBody TestDTO testOutputDTO) {
        String username = principal.getName();
        Test test = testService.updateTest(username, id, testOutputDTO);

        return MappingResponse.builder()
                .code(1)
                .body(mapper.toDTO(test))
                .message("Update test successfully")
                .build();
    }
    @DeleteMapping("/{id}")
    public MappingResponse deleteTestById(Principal principal, @PathVariable Long id) {
        String username = principal.getName();
        testService.deleteTestById(username, id);
        return MappingResponse.builder()
                .code(1)
                .message("Delete test successfully")
                .build();
    }
}
