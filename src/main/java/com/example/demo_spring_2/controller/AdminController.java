package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.dto.response.UserResponse;
import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.GroupService;
import com.example.demo_spring_2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
//@Validated
public class AdminController {

    private final GroupService groupService;
    private final UserService userService;

    @GetMapping("/home")
    public String userHome(@AuthenticationPrincipal CurrentUser currentUser,
                           Model model,
                           @RequestParam(value = "groupSaveError", required = false, defaultValue = "") String groupSaveError,
                           @RequestParam(value = "groupPage", required = false, defaultValue = "1") int groupPage,
                           @RequestParam(value = "groupSize", required = false, defaultValue = "4") int groupSize) {
        GroupResponse groupData = groupService.getGroups(groupPage - 1, groupSize);
        model.addAttribute("groups", groupData);
        if (groupData.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, groupData.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("groupPageNumbers", pageNumbers);
        }

        UserResponse users = userService.getUsers(0, 4);
        model.addAttribute("users", users);
        if (users.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, users.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("usersPageNumbers", pageNumbers);
        }
        model.addAttribute("groupSaveError", groupSaveError);
        model.addAttribute("currentUser", currentUser.getUser());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("newStudent", new User());
        return "adminHome";
    }

    @PostMapping("/user")
    public String addUser(@Valid @ModelAttribute User user,
                          RedirectAttributes attr,
                          BindingResult binding,
                          Errors errors) {
        if (errors.hasErrors()) { //todo is not working validation part
            attr.addFlashAttribute("newStudent", user);
            attr.addFlashAttribute("org.springframework.validation.BindingResult.register", binding);
            return "redirect:/admin/home";
        }
        ;
        userService.addUser(user);
        return "redirect:/admin/home";
    }


    @GetMapping("/user-table")
    public String getUsersTableForAdmin(
            @RequestParam(value = "userPage", required = false, defaultValue = "1") int userPage,
            @RequestParam(value = "userSize", required = false, defaultValue = "4") int userSize,
            Model model){
        UserResponse users = userService.getUsers(userPage-1, userSize);
        model.addAttribute("users", users);
        if (users.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, users.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("usersPageNumbers", pageNumbers);
        }
        return "inner/usersTableForAdmin";
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id")int id){
        userService.delete(id);
        return ResponseEntity.ok().body("successfully deleted");
    }


}
