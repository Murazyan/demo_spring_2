package com.example.common.repositories;

import com.example.common.models.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {


    boolean existsByName(String name);

    Page<Group> findAllByIdNotIn(List<Integer> groupsId, Pageable pageable);


}