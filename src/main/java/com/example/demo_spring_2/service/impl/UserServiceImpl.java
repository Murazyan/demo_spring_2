package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.events.UserRegisteredEvent;
import com.example.demo_spring_2.models.Role;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.repositories.RoleRepository;
import com.example.demo_spring_2.repositories.UserRepository;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUtil appUtil;
    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public User register(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            return null;
        }else {

            user.setRoles(List.of(roleRepository.findByName("user").get()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setVerificationCode(appUtil.generateRandomString(6));
            user =  userRepository.save(user);
            applicationEventPublisher.publishEvent(new UserRegisteredEvent(this, user));
            return user;
        }
    }

    @Override

    public boolean verfy(int userId, String verificationCode) {
        if(userRepository.existsByIdAndVerificationCode(userId, verificationCode)){
           userRepository.updateVerificationCode(userId, null);
           return true;
        }
        return false;
    }

    @Override
    public User userById(int userId) {
        return userRepository.getReferenceById(userId);
    }
}
