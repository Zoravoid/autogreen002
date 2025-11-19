package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.entities.UserStatus;
import com.iucosoft.mylinksspringboot.repositories.UserStatusRepository;
import com.iucosoft.mylinksspringboot.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserStatusServiceImpl extends AbstractServiceImpl<UserStatus, Long> implements UserStatusService{
    private final UserStatusRepository userStatusRepository;

    @Autowired
    public UserStatusServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }


    @Override
    protected JpaRepository<UserStatus, Long> getRepository() {
        return userStatusRepository;
    }

    @Override
    public UserStatus findByStatusName(String statusName) {
        return  userStatusRepository.findByStatusName(statusName);
    }
}
