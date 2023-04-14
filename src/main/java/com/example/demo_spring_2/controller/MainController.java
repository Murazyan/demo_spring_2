package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model modelMap){
        modelMap.addAttribute("registerUser", new User());
        return "index";
    }
}
