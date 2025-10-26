package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Notification;
import com.iucosoft.mylinksspringboot.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
