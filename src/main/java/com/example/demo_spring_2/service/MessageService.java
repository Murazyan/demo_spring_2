package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.request.MessageRequest;
import com.example.demo_spring_2.models.Message;
import com.example.demo_spring_2.models.User;

import java.util.List;

public interface MessageService {

    Message add(MessageRequest messageRequest, User fromUser);

    List<Message> messagesByUsers(User currentUser, int participateUserId);
}
