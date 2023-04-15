package com.example.demo_spring_2.service.impl;

import com.example.demo_spring_2.events.UserRegisteredEvent;
import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.repositories.UserRepository;
import com.example.demo_spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUtil appUtil;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public User register(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            return null;
        }else {
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
