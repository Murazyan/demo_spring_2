package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.request.UserRequest;
import com.example.common.models.User;
import com.example.demo_spring_2.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private SimpMessageSendingOperations simpMessagingTemplate;
//    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof CurrentUser) {
                CurrentUser principal = (CurrentUser) authentication.getPrincipal();
                if (principal.getUser().isAdmin()) {
                    return "redirect:/admin/home";
                } else {
                    return "redirect:/user/home";

                }
            }
        }
        modelMap.addAttribute("registerUser", new User());
        modelMap.addAttribute("loginUser", new User());
        return "index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Model modelMap) {

        return "accessDenied";
    }

    @GetMapping("/test")
    public String test(){
        simpMessagingTemplate.convertAndSend("/topic/1202", new UserRequest("poxos", "poxosyan", "sdf", "dsfas"));
        return "redirect:/";
    }
}
