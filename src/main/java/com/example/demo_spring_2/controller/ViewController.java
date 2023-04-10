package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class ViewController {



    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/home")
    public String homePage(@RequestParam(value = "name", required = false, defaultValue = "Petros") String name) {
        return "index";
    }

    @GetMapping("/user-home/id/{id}")
    public String userHome(@RequestParam(value = "name", required = false, defaultValue = "Petros") String name,
                           @PathVariable("id")int id,
                           ModelMap modelMap) {

        modelMap.addAttribute("userName", name);
        modelMap.addAttribute("id", id);
        return "userHome";
    }


    @GetMapping
    @ResponseBody
    public User getUsers(){
        return null;
    }
}
