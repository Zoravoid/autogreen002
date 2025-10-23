package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.RoleUser;
import com.iucosoft.mylinksspringboot.repositories.bridge.RoleUserRepository;
import com.iucosoft.mylinksspringboot.service.bridge.RoleUserService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl extends AbstractServiceImpl<RoleUser, Long> implements RoleUserService {

    private final RoleUserRepository roleUserRepository;

    @Autowired
    public RoleUserServiceImpl(RoleUserRepository roleUserRepository) {
        this.roleUserRepository = roleUserRepository;
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return roleUserRepository.findUsersByRoleId(roleId);
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleUserRepository.findRolesByUserId(userId);
    }

    @Override
    public void deleteRolesByUserId(Long id) {
        roleUserRepository.deleteRolesByUserId(id);
    }

    @Override
    protected JpaRepository<RoleUser, Long> getRepository() {
        return roleUserRepository;
    }
}
