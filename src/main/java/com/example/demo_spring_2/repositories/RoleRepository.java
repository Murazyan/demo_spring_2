package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.Role;
import com.example.demo_spring_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);


}
