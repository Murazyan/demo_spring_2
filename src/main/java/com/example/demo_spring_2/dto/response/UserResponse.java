package com.example.demo_spring_2.dto.response;

import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {

    private int page;

    private List<User> users;
}
