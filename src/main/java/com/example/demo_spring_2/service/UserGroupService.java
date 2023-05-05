package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.request.UserGroupRequestStatus;
import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.dto.response.UserGroupResponse;
import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.models.UserGroup;
import com.example.demo_spring_2.models.enums.UserGroupState;

import java.util.List;
import java.util.Set;

public interface UserGroupService {
    GroupResponse getgroupsByUserAndState(User user, UserGroupState state, int page, int elementCount);

    GroupResponse getAvailableGroups(User user, int page, int elementCount);

    void add(UserGroup userGroup);

    UserGroupResponse waitingGroupRequestsForAdmin(int page, int elementCount);

    void updateState(UserGroupRequestStatus request);

    List<Group> getGroupsByUserAndState(User user, UserGroupState state);

    Set<User> getParticipatedUsers(List<Group> joinedGroupsForUser);
}
