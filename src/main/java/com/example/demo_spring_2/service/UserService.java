package com.example.demo_spring_2.service;

import com.example.demo_spring_2.models.User;

public interface UserService {
    User register(User user);

    boolean verfy(int userId, String verificationCode);

    User userById(int userId);

    User addUser(User user);
}
