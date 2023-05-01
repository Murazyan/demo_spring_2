package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.models.UserGroup;
import com.example.demo_spring_2.models.enums.UserGroupState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

    Page<UserGroup> findAllByUserAndState(User user, UserGroupState state, Pageable pageable);
    List<UserGroup> findAllByUser(User user);
}
