package com.example.common.repositories;


import com.example.common.models.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<New, Integer> {


}
