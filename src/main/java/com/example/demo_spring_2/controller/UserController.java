package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import jakarta.servlet.Filter;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping
    public String register(@ModelAttribute User user, Model model){
        User savedUser =  userService.register(user);
        if(savedUser==null){
            return "index"; //todo
        }else {
            return "registerSuccess";
        }
    }

    @GetMapping("/verify")
    public String verify(@RequestParam (name = "id")int userId,
                         @RequestParam(name = "verificationCode")String verificationCode,
                         Model model){
        boolean isVerified =  userService.verfy(userId, verificationCode);
        if(isVerified){
            model.addAttribute("currentUser", userService.userById(userId));
            return "userHome";
        }else {
            return "verifyError";
        }
    }
}
