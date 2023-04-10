package com.example.demo_spring_2.controller;


import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class ResController {

    private final UserRepository userRepository;

    @Value("${hello.txt}")
    private String hello;

    @Autowired
    public ResController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/id/{id}")
    public User getById(@PathVariable("id") int id){
       return userRepository.findById(id).orElse(new User());
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public String getHello(){
        return hello;
    }
}
