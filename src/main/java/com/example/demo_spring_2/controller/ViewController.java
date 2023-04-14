package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@Profile("dev")
@RequestMapping("/view")
public class ViewController implements InitializingBean {

    private UserService userService;
    @Autowired
    public ViewController( @Qualifier("userServiceImpl") UserService userService){
        this.userService = userService;
        System.out.println("---- ViewController: constructor");
    }

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

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----- viewController:afterPropertiesSet method ");
    }

    @PostConstruct
    public void  postConstruct(){
        System.out.println("----- postConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("---- destroy");
    }
}
