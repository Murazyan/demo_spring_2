package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public String register(@ModelAttribute User user){
        return "index";

    }
}
