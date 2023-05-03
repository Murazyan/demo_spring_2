package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.request.UserRequest;
import com.example.demo_spring_2.dto.response.UserResponse;
import com.example.demo_spring_2.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User register(User user);

    boolean verfy(int userId, String verificationCode);

    User userById(int userId);

    User addUser(User user);

    UserResponse getUsers(int page, int elementCount);

    void delete(int id);

    void updateLockedStatus(int id, boolean locked);

    void update(User user, UserRequest request);

    void saveAvatar(User user, MultipartFile multipartFile);
}
