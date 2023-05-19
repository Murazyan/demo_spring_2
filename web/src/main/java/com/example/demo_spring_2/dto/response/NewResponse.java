package com.example.demo_spring_2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewResponse {
    private int id;
    private String title;
    private String content;
    private int groupId;
    
}