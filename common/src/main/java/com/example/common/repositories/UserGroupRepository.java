package com.example.common.repositories;


import com.example.common.models.Group;
import com.example.common.models.User;
import com.example.common.models.UserGroup;
import com.example.common.models.enums.UserGroupState;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

    Page<UserGroup> findAllByUserAndState(User user, UserGroupState state, Pageable pageable);
    List<UserGroup> findAllByUserAndState(User user, UserGroupState state);
    Page<UserGroup> findAllByState(UserGroupState state, Pageable pageable);
    List<UserGroup> findAllByUser(User user);

    List<UserGroup> findAllByGroupInAndState(List<Group> groups, UserGroupState state);

    @Modifying
    @Transactional
    @Query(value = "update UserGroup u set u.state=:state where u.group.id=:groupId and u.user.id=:userId")
    void updateState(@Param("groupId") int groupId,
                     @Param("userId") int userId,
                     @Param("state") UserGroupState state);

    @Transactional
    public void deleteByUser_IdAndGroup_Id(int userId, int groupId);

    public default void deleteByUserAndGroup(int userId, int groupId){
        deleteByUser_IdAndGroup_Id(userId, groupId);
    }

}
