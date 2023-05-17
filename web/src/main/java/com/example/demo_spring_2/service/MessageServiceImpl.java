package com.example.demo_spring_2.service;

import com.example.demo_spring_2.dto.request.MessageRequest;
import com.example.common.models.Message;
import com.example.common.models.User;
import com.example.common.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message add(MessageRequest messageRequest, User fromUser) {
        messageRepository.save(Message.builder()
                .fromUser(fromUser)
                .toUser(User.builder().id(messageRequest.getToUserId()).build())
                .text(messageRequest.getMessage())
                .build());
        return null;
    }

    @Override
    public List<Message> messagesByUsers(User currentUser, int participateUserId) {
        return messageRepository.findAllMessagesByUsers(currentUser,
                User.builder().id(participateUserId).build());
    }
}
