package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.repositories.GroupRepository;
import com.example.demo_spring_2.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
