package com.example.demo_spring_2.service;


import com.example.demo_spring_2.dto.request.NewRequest;
import com.example.demo_spring_2.dto.response.NewResponse;

public interface NewService {
    NewResponse addNew(NewRequest newRequest);
}
