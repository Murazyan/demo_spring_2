package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.models.Group;

public interface GroupService {

    Group save(Group group);

    GroupResponse getGroups(int page, int itemCount);
}
