package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addGroup(@ModelAttribute Group group){
        if(groupService.save(group)==null){//todo
            //վերադարձնել նույն էջ, բայց ցույց տալ տեքստ, որ այդ անունով գրուպա արդեն կա

        }return "redirect:/admin/home";

    }


}
