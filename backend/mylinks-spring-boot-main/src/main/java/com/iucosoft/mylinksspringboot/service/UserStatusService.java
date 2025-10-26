package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.entities.UserStatus;

public interface UserStatusService extends OperationIntf<UserStatus, Long>{
    UserStatus findByStatusName(String statusName);
}
