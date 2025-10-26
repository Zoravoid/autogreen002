package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.bridge.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRoleRepository extends JpaRepository<PermissionRole, Long> {

    @Query("SELECT pr.permission FROM PermissionRole pr WHERE pr.role.id = :roleId")
    List<Permission> findAllPermissionsByRoleId(final @Param("roleId") Long roleId);

    @Query("SELECT pr FROM PermissionRole pr WHERE pr.permission.id = :permissionId AND pr.role.id = :roleId")
    Optional<PermissionRole> findByPermissionIdAndRoleId(final @Param("permissionId") Long permissionId, final @Param("roleId") Long roleId);

    @Modifying
    @Query("DELETE FROM PermissionRole pr WHERE pr.role.id = :roleId")
    void deleteByRoleId(final @Param("roleId") Long roleId);

    @Modifying
    @Query("DELETE FROM PermissionRole pr WHERE pr.permission.id = :permissionId")
    void deleteByPermissionId(final @Param("permissionId") Long permissionId);
}
