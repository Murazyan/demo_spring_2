package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.security.CurrentUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser,
                           Model model){
        model.addAttribute("currentUser", currentUser.getUser());
        return "adminHome";
    }
}
