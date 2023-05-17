package com.example.demo_spring_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication()
@ComponentScan(basePackages = {"com.example.common.*", "com.example.demo_spring_2"})
@EntityScan("com.example.common.*")
@EnableJpaRepositories("com.example.common.*")
public class DemoSpring2Application {


	public static void main(String[] args)  {
		SpringApplication.run(DemoSpring2Application.class, args);
	}

}
