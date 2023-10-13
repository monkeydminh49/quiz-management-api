package com.e01.quiz.controller;

import com.e01.quiz.dto.QuestionDTO;
import com.e01.quiz.entity.Question;
import com.e01.quiz.service.QuestionService;
import com.e01.quiz.util.Mapper;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@Builder
@RequestMapping("/api/v1")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private Mapper mapper;

    @GetMapping("/question")
    public List<QuestionDTO> getQuestion() {
        List<Question> questions = questionService.getAllQuestions();

        return questions.stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/question/{id}")
    public QuestionDTO getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);

        return mapper.toDTO(question);
    }
}

