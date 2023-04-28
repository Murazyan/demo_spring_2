package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public RedirectView addGroup(@ModelAttribute Group group,
                           RedirectAttributes attributes){
        if(groupService.save(group)==null){
            attributes.addAttribute("groupSaveError", "Group already exist");

        }return new RedirectView("/admin/home");

    }



}
