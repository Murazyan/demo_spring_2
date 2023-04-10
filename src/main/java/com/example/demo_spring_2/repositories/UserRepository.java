package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByNameAndSurname(String name, String surname);


}
