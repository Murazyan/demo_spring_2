package com.example.demo_spring_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:foo.properties")
public class AppConfiguration {



    @Bean
    public Test testObject1(){
        return new Test("1");
    }

    @Bean
    public Test testObject2(){
        return new Test("2");
    }
}
