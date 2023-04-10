package com.example.demo_spring_2;

import com.example.demo_spring_2.config.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {"org.example", "com.example.demo_spring_2"})
//@SpringBootApplication()
//@ComponentScan
//@EnableAutoConfiguration

public class DemoSpring2Application {


	@Autowired
	@Qualifier("testObject1")
	private Test test;


	public static void main(String[] args) {
		SpringApplication.run(DemoSpring2Application.class, args);
	}

}
