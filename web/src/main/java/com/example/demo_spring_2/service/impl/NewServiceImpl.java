package com.example.demo_spring_2.service.impl;

import com.example.common.repositories.NewRepository;
import com.example.demo_spring_2.dto.request.NewRequest;
import com.example.demo_spring_2.dto.response.NewResponse;
import com.example.demo_spring_2.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewService {
    private final NewRepository newRepository;


    @Override
    public NewResponse addNew(NewRequest newRequest) {
        return null;
    }
}
