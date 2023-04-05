package com.example.demo_spring_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view")
public class ViewController {


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
}
