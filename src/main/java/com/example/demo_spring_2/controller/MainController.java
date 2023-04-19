package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model modelMap){
        modelMap.addAttribute("registerUser", new User());
        modelMap.addAttribute("loginUser", new User());
        return "index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Model modelMap){

        return "accessDenied";
    }
}
