package com.example.demo_spring_2.events;

import com.example.demo_spring_2.models.User;
import org.springframework.context.ApplicationEvent;

public class UserAddEvent extends ApplicationEvent {
    private User user;
    private String password;

    public UserAddEvent(Object source, User user, String password) {
        super(source);
        this.user = user;
        this.password = password;
    }
    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}