package com.example.demo_spring_2.dto.response;

import com.example.demo_spring_2.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String text;

    private UserInfoResponse fromUser;
}
