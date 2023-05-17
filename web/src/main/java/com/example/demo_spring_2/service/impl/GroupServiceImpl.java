package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.common.models.Group;
import com.example.common.repositories.GroupRepository;
import com.example.demo_spring_2.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    @Override
    public Group save(Group group) {
        if(groupRepository.existsByName(group.getName()))
            return null;
        else
            return groupRepository.save(group);
    }

    @Override
    public GroupResponse getGroups(int page, int itemCount) {
        Page<Group> pageOfGroups = groupRepository.findAll(PageRequest.of(page, itemCount, Sort.by("id").ascending()));
        List<Group> content = pageOfGroups.getContent();
        int totalPages = pageOfGroups.getTotalPages();
        return new GroupResponse(totalPages, content);
    }


}
