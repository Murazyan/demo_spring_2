package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.dto.request.UserRequest;
import com.example.demo_spring_2.dto.response.UserResponse;
import com.example.demo_spring_2.events.UserAddEvent;
import com.example.demo_spring_2.events.UserRegisteredEvent;
import com.example.common.models.Group;
import com.example.common.models.User;
import com.example.common.models.enums.UserGroupState;
import com.example.common.repositories.RoleRepository;
import com.example.common.repositories.UserRepository;
import com.example.demo_spring_2.service.UserGroupService;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUtil appUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final UserGroupService userGroupService;

    @Value("${app.student.avatar}")
    private String studentAvatar;

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null;
        } else {

            user.setRoles(List.of(roleRepository.findByName("user").get()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVerificationCode(appUtil.generateRandomString(6));
            user = userRepository.save(user);
            applicationEventPublisher.publishEvent(new UserRegisteredEvent(this, user));
            return user;
        }
    }

    @Override

    public boolean verfy(int userId, String verificationCode) {
        if (userRepository.existsByIdAndVerificationCode(userId, verificationCode)) {
            userRepository.updateVerificationCode(userId, null);
            return true;
        }
        return false;
    }

    @Override
    public User userById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null;
        } else {

            String password = appUtil.generateRandomString(6);
            user.setRoles(List.of(roleRepository.findByName("user").get()));
            user.setPassword(passwordEncoder.encode(password));
            user = userRepository.save(user);
            applicationEventPublisher.publishEvent(new UserAddEvent(this, user, password));
            return user;
        }
    }

    @Override
    public UserResponse getUsers(int page, int elementCount) {
        Page<User> pageOfGroups = userRepository.findAll(PageRequest.of(page, elementCount, Sort.by("id").ascending()));
        List<User> content = pageOfGroups.getContent();
        int totalPages = pageOfGroups.getTotalPages();
        return new UserResponse(totalPages, content);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateLockedStatus(int id, boolean locked) {
        userRepository.updateLockedStatus(id, locked);
    }

    @Override
    public void update(User user, UserRequest request) {
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void saveAvatar(User user, MultipartFile multipartFile) {
        String fileName = "" + System.currentTimeMillis()+multipartFile.getOriginalFilename();
        new File(studentAvatar + "\\" + user.getAvatar()).delete();
        multipartFile.transferTo(new File(studentAvatar + "\\" + fileName));
        user.setAvatar(fileName);
        userRepository.save(user);
    }

    @Override
    public Set<User> getFriends(User user) {
        List<Group> joinedGroupsForUser = userGroupService.getGroupsByUserAndState(user, UserGroupState.APPROVED);
        Set<User> participatedUsers = userGroupService.getParticipatedUsers(joinedGroupsForUser);
        participatedUsers.remove(user);
        return participatedUsers;

    }

}
