package com.example.demo_spring_2.dto.response;

import com.example.common.models.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor

public class GroupResponse {

    private int page;

    private List<Group> groups;
}
