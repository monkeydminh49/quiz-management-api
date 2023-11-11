package com.e01.quiz.controller;

import com.e01.quiz.component.Mapper;
import com.e01.quiz.entity.Test;
import com.e01.quiz.service.TestHistoryService;
import com.e01.quiz.service.TestService;
import com.e01.quiz.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContestController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private TestService testService;
    @Autowired
    private Mapper mapper;
    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        System.out.println("helo");
        System.out.println(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/test/{testId}/join")
    @SendTo("/topic/test/{testId}")
    public Message addUser(@Payload Message chatMessage, @DestinationVariable Long testId, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getFrom());
        System.out.println(chatMessage.getFrom());
        Test test = testService.increaseNumberOfLiveParticipantByOne(testId);
        test = testService.saveTest(test);
        System.out.println(test.getTitle());
        chatMessage.setData(test.getNumberOfLiveParticipant());
        System.out.println("Sent to /topic/test/" + testId);
//        this.template.convertAndSend("/topic/public", chatMessage);
        return chatMessage;
    }

}
