package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.RoleUser;
import com.iucosoft.mylinksspringboot.service.OperationIntf;

import java.util.List;

public interface RoleUserService extends OperationIntf<RoleUser, Long> {
    List<User> getUsersByRoleId(Long roleId);
    List<Role> getRolesByUserId(Long userId);

    void deleteRolesByUserId(Long id);
}
