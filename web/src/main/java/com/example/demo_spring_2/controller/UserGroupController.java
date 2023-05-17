package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.request.UserGroupJoinRequest;
import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.common.models.Group;
import com.example.common.models.UserGroup;
import com.example.demo_spring_2.security.CurrentUser;
import com.example.demo_spring_2.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user-group")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Void> addUserGroup(@AuthenticationPrincipal CurrentUser currentUser,
                                             @RequestBody UserGroupJoinRequest request) {

        userGroupService.add(UserGroup.builder()
                .user(currentUser.getUser())
                .group(Group.builder().id(request.getGroupId()).build())
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    @PreAuthorize("hasAuthority('USER')")
    public String availableGroups(@AuthenticationPrincipal CurrentUser currentUser,
                                  @RequestParam(value = "groupPage", required = false, defaultValue = "1") int groupPage,
                                  @RequestParam(value = "groupSize", required = false, defaultValue = "4") int groupSize,
                                  Model model) {

        GroupResponse available = userGroupService.getAvailableGroups(currentUser.getUser(), groupPage-1, groupSize);
        model.addAttribute("availableGroups", available);
        if (available.getPage() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, available.getPage())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("availableGroupsPageNumbers", pageNumbers);
        }
        return "inner/availableGroupsTableForUser";

    }


}
