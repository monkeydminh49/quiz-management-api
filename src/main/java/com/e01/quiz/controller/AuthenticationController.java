package com.e01.quiz.controller;

import com.e01.quiz.dto.*;
import com.e01.quiz.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/hello")
    public HelloDTO hello() {
        return HelloDTO.builder()
                .message("Hello vux")
                .build();
    }

    @PostMapping("/register")
    public MappingResponse registerUser(@RequestBody RegisterRequest request) {
        UserResponse userResponse = authenticationService.register(request);
        return MappingResponse.builder()
                .code(1)
                .body(userResponse)
                .message("Register successfully")
                .build();
    }

    @PostMapping("/login")
    public MappingResponse login(@RequestBody LoginRequest request) {
        UserResponse userResponse = authenticationService.login(request);
        return MappingResponse.builder()
                .code(1)
                .body(userResponse)
                .message("Login successfully")
                .build();
    }

//    @PostMapping("/refresh-token")
//    public MappingResponse refreshToken(@RequestBody RefreshTokenRequest request) {
//        JwtResponse token =  authenticationService.refreshToken(request);
//        return MappingResponse.builder()
//                .status("ok")
//                .body(token)
//                .message("Refresh token successfully")
//                .build();
//    }
}

