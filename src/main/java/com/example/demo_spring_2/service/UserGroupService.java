package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.models.enums.UserGroupState;

public interface UserGroupService {
    GroupResponse getgroupsByUserAndState(User user, UserGroupState state, int page, int elementCount);

    GroupResponse getAvailableGroups(User user, int page, int elementCount);
}
