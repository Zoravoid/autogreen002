package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.bridge.NotificationUserReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationUserReceiverRepository extends JpaRepository<NotificationUserReceiver, Long> {
}
