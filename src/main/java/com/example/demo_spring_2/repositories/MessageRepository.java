package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.Message;
import com.example.demo_spring_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query(value = "from Message m where (m.fromUser=:user1 and m.toUser=:user2) or (m.fromUser=:user2 and m.toUser=:user1)")
    List<Message> findAllMessagesByUsers(@Param("user1") User user1,
                                         @Param("user2") User user2);


}
