//package com.example.demo_spring_2.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class MvcConfig implements WebMvcConfigurer {
//
////    @Value("${user.image.path}")
////    private String userImagesFolder;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
////        registry
////                .addResourceHandler("/user/image/**")
////                .addResourceLocations("file:C:\\Users\\Admin\\Desktop\\spring_image\\");
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//
//    }
//
//
//}