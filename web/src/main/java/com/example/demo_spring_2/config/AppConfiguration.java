package com.example.demo_spring_2.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

@EnableWebMvc
@Configuration
@Slf4j
public class AppConfiguration {

    @Value("${app.student.avatar}")
    private String studentAvatar;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void postConstruct(){
        if(new File(studentAvatar).mkdirs()){
            log.info("Created student avatars folder");
        };

    }

}
