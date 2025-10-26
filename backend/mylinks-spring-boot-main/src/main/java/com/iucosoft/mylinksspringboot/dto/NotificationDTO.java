package com.iucosoft.mylinksspringboot.dto;

import com.iucosoft.mylinksspringboot.entities.Priority;
import lombok.Data;
import java.util.Date;

@Data
public class NotificationDTO {

    private Integer id;

    private String message;

    private Date dateNotificationSent;

    private Date dateNotificationRead;

    private Priority priority;
}
