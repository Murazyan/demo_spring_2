package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.dto.request.UserGroupRequestStatus;
import com.example.demo_spring_2.dto.response.GroupResponse;
import com.example.demo_spring_2.dto.response.UserGroupResponse;
import com.example.common.models.Group;
import com.example.common.models.User;
import com.example.common.models.UserGroup;
import com.example.common.models.enums.UserGroupState;
import com.example.common.repositories.GroupRepository;
import com.example.common.repositories.UserGroupRepository;
import com.example.demo_spring_2.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    private final GroupRepository groupRepository;


    @Override
    public GroupResponse getgroupsByUserAndState(User user, UserGroupState state, int page, int elementCount) {
        Page<UserGroup> data = userGroupRepository.findAllByUserAndState(user, state, PageRequest.of(page, elementCount));
        return new GroupResponse(data.getTotalPages(),
                data.getContent().stream().map(UserGroup::getGroup).collect(Collectors.toList()));
    }

    @Override
    public GroupResponse getAvailableGroups(User user, int page, int elementCount) {
        List<Integer> participatedGroupsId = userGroupRepository.findAllByUser(user)
                .stream().map(ug -> ug.getGroup().getId()).collect(Collectors.toList());
        Page<Group> data =participatedGroupsId.isEmpty() ?
                groupRepository.findAll(PageRequest.of(page, elementCount, Sort.by("id").ascending())):
                groupRepository.findAllByIdNotIn(participatedGroupsId,
                PageRequest.of(page, elementCount,Sort.by("id").ascending()));
        return new GroupResponse(data.getTotalPages(), data.getContent());
    }

    @Override
    public void add(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroupResponse waitingGroupRequestsForAdmin(int page, int elementCount) {
        Page<UserGroup> data = userGroupRepository.findAllByState(UserGroupState.WAITING,
                PageRequest.of(page, elementCount, Sort.by("id").ascending()));
        return new UserGroupResponse(data.getTotalPages(), data.getContent());
    }

    @Override
    public void updateState(UserGroupRequestStatus request) {
        if(request.isStatus()){
        userGroupRepository.updateState(request.getGroupId(), request.getUserId(), UserGroupState.APPROVED);
        }else {
            userGroupRepository.deleteByUserAndGroup(request.getUserId(), request.getGroupId());
        }
    }

    @Override
    public List<Group> getGroupsByUserAndState(User user, UserGroupState state) {
        return userGroupRepository.findAllByUserAndState(user, state).stream().map(UserGroup::getGroup).collect(Collectors.toList());
    }

    @Override
    public Set<User> getParticipatedUsers(List<Group> groups) {
        return userGroupRepository.findAllByGroupInAndState(groups, UserGroupState.APPROVED)
                .stream().map(UserGroup::getUser).collect(Collectors.toSet());
    }
}