package com.example.demo_spring_2.controller;

import com.example.demo_spring_2.dto.request.NewRequest;
import com.example.demo_spring_2.dto.response.NewResponse;
import com.example.demo_spring_2.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewService newService;

    @PostMapping
    @ResponseBody
    public NewResponse addNew(@RequestPart(name = "file")MultipartFile multipartFile,
                              @RequestBody NewRequest newRequest){
        return newService.addNew(newRequest);
    }
}
