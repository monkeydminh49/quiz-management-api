package com.e01.quiz.controller;

import com.e01.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/v1")
public class TestController {
    @Autowired
    UserService userService;
    @GetMapping("/hello")
    public String hello() {
        return "Welcome to Quiz Manager API!";
    }

    @GetMapping("admin")
    public String welcomeAdmin(){
        return "Welcome " + userService.getUserByUsername("admin@gmail.com").getUsername();
    }
}
