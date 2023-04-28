package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public String register(@ModelAttribute User user, Model model) {
        User savedUser = userService.register(user);
        if (savedUser == null) {
            return "index"; //todo
        } else {
            return "registerSuccess";
        }
    }

    @GetMapping("/verify")
    public String verify(@RequestParam(name = "id") int userId,
                         @RequestParam(name = "verificationCode") String verificationCode,
                         Model model) {
        boolean isVerified = userService.verfy(userId, verificationCode);
        if (isVerified) {
            User currentUser = userService.userById(userId);
            model.addAttribute("currentUser", currentUser);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(currentUser.getEmail(), currentUser.getPassword()));
            return "redirect:/user/home";
        } else {
            return "verifyError";
        }
    }

    @GetMapping("/home")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser,
                           Model model) {
        if (currentUser.getUser().isAdmin()) {
            return "redirect:/admin/home";
        }
        model.addAttribute("currentUser", currentUser.getUser());
        return "userHome";
    }
}
