package com.example.demo_spring_2.repositories;

import com.example.demo_spring_2.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByIdAndVerificationCode(int id, String verificationCode);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.verificationCode=:verifyCode where u.id=:id")
    void updateVerificationCode(@Param("id") int id,
                                @Param("verifyCode") String verificationCode);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.locked=:locked where u.id=:id")
    void updateLockedStatus(@Param("id") int id,
                            @Param("locked") boolean locked);

    Optional<User> findByEmail(String email);

}
