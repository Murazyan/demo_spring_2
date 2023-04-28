package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.GroupService;
import com.example.demo_spring_2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final GroupService groupService;
    private final UserService userService;

    @GetMapping("/home")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser,

                           Model model,
                           @RequestParam(value = "groupPage", required = false, defaultValue ="1")int groupPage,
                           @RequestParam(value = "groupSaveError", required = false, defaultValue ="")String  groupSaveError,
                           @RequestParam(value = "groupSize", required = false, defaultValue ="4")int groupSize){
        GroupResponse groupData = groupService.getGroups(groupPage-1, groupSize);
        model.addAttribute("groups", groupData);
        if (groupData.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, groupData.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("groupSaveError", groupSaveError);
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("newStudent", new User());
        return "adminHome";
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute @Valid User user,

                          BindingResult result,
                          Errors errors){
        System.out.println(errors.hasErrors());
        System.out.println(result.hasErrors());
        User savedUser = userService.addUser(user);
        return "adminHome";
    }


}
