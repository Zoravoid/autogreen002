package com.iucosoft.mylinksspringboot.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "message", length = 45)
    private String message;

    @Column(name = "date_notification_sent", length = 45)
    private Date dateNotificationSent;

    @Column(name = "date_notification_read")
    private Date dateNotificationRead;

    private Priority priority;

}
