package com.example.demo_spring_2.events;

import com.example.common.models.User;
import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {
    private User user;

    public UserRegisteredEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}