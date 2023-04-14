package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.New;
import com.example.demo_spring_2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<New, Integer> {


}
