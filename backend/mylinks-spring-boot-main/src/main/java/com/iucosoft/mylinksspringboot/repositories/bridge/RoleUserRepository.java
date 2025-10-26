package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long> {

    @Query("SELECT ru.user FROM RoleUser ru WHERE ru.role.id = :roleId")
    List<User> findUsersByRoleId(final @Param("roleId") Long roleId);

    @Query("SELECT ru.role FROM RoleUser ru WHERE ru.user.id = :userId")
    List<Role> findRolesByUserId(final @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM RoleUser rU WHERE rU.user.id = :userId")
    void deleteRolesByUserId(@Param("userId") Long userId);
}
