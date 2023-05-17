package com.example.demo_spring_2.config;

import com.example.common.models.Role;
import com.example.common.models.User;
import com.example.common.models.enums.Gender;
import com.example.common.repositories.RoleRepository;
import com.example.common.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DbInit {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDb() {
        log.info("starting db initialization");
        if (roleRepository.findByName("admin").isEmpty()) {
            roleRepository.saveAll(Arrays.asList(new Role("admin"),
                    new Role("user")));
        }

        if(!userRepository.existsByEmail("admin@gmail.com")){
            userRepository.save(User.builder()
                            .name("Admin")
                            .surname("Admin")
                            .email("admin@gmail.com")
                            .password(passwordEncoder.encode("123"))
                            .gender(Gender.MALE)
                            .roles(Arrays.asList(roleRepository.findByName("admin").get()))
                    .build());
        }
        log.info("end db initialization");

    }
}
