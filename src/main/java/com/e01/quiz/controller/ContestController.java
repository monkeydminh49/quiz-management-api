package com.e01.quiz.controller;

import com.e01.quiz.util.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ContestController {

    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        System.out.println("helo");
        System.out.println(chatMessage);
        return chatMessage;
    }

//    @MessageMapping("/chat")
//    @SendTo("/topic/public")
//    public String addUser(@Payload String chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", "MinhDunk");
//        return chatMessage;
//    }

}
