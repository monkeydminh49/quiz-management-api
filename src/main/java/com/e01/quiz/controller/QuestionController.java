package com.e01.quiz.controller;

import com.e01.quiz.dto.QuestionResponse;

import com.e01.quiz.entity.Question;

import com.e01.quiz.service.QuestionService;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@Builder
@RequestMapping("/api/v1")
public class QuestionController {
    @Autowired
    private QuestionService questionService;


}

