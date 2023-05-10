package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.request.MessageRequest;
import com.example.demo_spring_2.dto.request.UserRequest;
import com.example.demo_spring_2.dto.response.MessageResponse;
import com.example.demo_spring_2.dto.response.UserInfoResponse;
import com.example.demo_spring_2.models.Message;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {


    private final MessageService messageService;

    private final SimpMessageSendingOperations simpMessagingTemplate;


    @PostMapping
    @ResponseBody
    public Message addMessage(@AuthenticationPrincipal CurrentUser currentUser,
                              @RequestBody MessageRequest messageRequest) {

        Message savedMessage = messageService.add(messageRequest, currentUser.getUser());
        simpMessagingTemplate.convertAndSend("/topic/" + messageRequest.getToUserId(), new MessageResponse(messageRequest.getMessage(),
               UserInfoResponse.builder()
                       .id( currentUser.getUser().getId())
                       .name( currentUser.getUser().getName())
                       .surname( currentUser.getUser().getSurname())
                       .build()));
        return savedMessage;
    }


    @GetMapping
    public String getHistoryPage(@AuthenticationPrincipal CurrentUser currentUser,
                                 @RequestParam(name = "participateUserId") int participateUserId,
                                 ModelMap modelMap) {
        modelMap.addAttribute("messages", messageService.messagesByUsers(currentUser.getUser(), participateUserId));
        modelMap.addAttribute("currentUser", currentUser.getUser());
        return "inner/userMessages";
    }

}
