package com.e01.quiz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1")
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "Welcome to Quiz Manager API!";
    }
}
