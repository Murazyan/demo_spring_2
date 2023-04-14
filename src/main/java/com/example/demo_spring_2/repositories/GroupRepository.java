package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.Group;
import com.example.demo_spring_2.models.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {


}
