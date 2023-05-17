package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.request.UserRequest;
import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.common.models.User;
import com.example.common.models.enums.UserGroupState;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.UserGroupService;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserGroupService userGroupService;

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

        GroupResponse participatedGroups = userGroupService.getgroupsByUserAndState(currentUser.getUser(), UserGroupState.APPROVED, 0, 4);
        model.addAttribute("participatedGroups", participatedGroups);
        if (participatedGroups.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, participatedGroups.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("participatedGroupsPageNumbers", pageNumbers);
        }

        GroupResponse available = userGroupService.getAvailableGroups(currentUser.getUser(), 0, 4);
        model.addAttribute("availableGroups", available);
        if (available.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, available.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("availableGroupsPageNumbers", pageNumbers);
        }

        model.addAttribute("chatUsers", userService.getFriends(currentUser.getUser()));
        return "userHome";
    }


    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser,
                              Model model) {
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("profile", new UserRequest());
        return "userProfile";
    }


    @PostMapping("/profile")
    public String userProfile(@AuthenticationPrincipal CurrentUser currentUser,
                              @ModelAttribute UserRequest request,
                              @RequestPart(value = "avatar", required = false) MultipartFile multipartFile,
                              Model model) {

        userService.update(currentUser.getUser(), request);
        if (multipartFile != null && !multipartFile.isEmpty())
            userService.saveAvatar(currentUser.getUser(), multipartFile);
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("profile", new UserRequest());
        return "userProfile";
    }

}
