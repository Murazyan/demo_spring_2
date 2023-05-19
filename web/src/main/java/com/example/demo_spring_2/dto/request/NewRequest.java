package com.example.demo_spring_2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRequest {
    private String title;
    private String content;
    private int groupId;

}
