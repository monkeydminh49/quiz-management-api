package com.e01.quiz.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ContestController {

    @MessageMapping("/chat")
    @SendTo("/chat")
    public String sendMessage(@Payload String chatMessage) {
        return chatMessage;
    }

//    @MessageMapping("/chat")
//    @SendTo("/topic/public")
//    public String addUser(@Payload String chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", "MinhDunk");
//        return chatMessage;
//    }

}
