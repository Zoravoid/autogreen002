package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
    @Query("SELECT u FROM UserStatus u WHERE u.statusName = :userStatus")
    UserStatus findByStatusName(@Param("userStatus") String statusName);
}
