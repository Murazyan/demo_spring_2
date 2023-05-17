package com.example.demo_spring_2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupRequestStatus {

    private int groupId;

    private int userId;

    private boolean status;
}
