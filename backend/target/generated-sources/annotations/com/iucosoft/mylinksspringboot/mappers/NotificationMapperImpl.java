package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.NotificationDTO;
import com.iucosoft.mylinksspringboot.entities.Notification;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T19:58:37+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setDateNotificationRead( notification.getDateNotificationRead() );
        notificationDTO.setDateNotificationSent( notification.getDateNotificationSent() );
        if ( notification.getId() != null ) {
            notificationDTO.setId( notification.getId().intValue() );
        }
        notificationDTO.setMessage( notification.getMessage() );
        notificationDTO.setPriority( notification.getPriority() );

        return notificationDTO;
    }

    @Override
    public Notification toEntity(NotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setDateNotificationRead( dto.getDateNotificationRead() );
        notification.setDateNotificationSent( dto.getDateNotificationSent() );
        if ( dto.getId() != null ) {
            notification.setId( dto.getId().longValue() );
        }
        notification.setMessage( dto.getMessage() );
        notification.setPriority( dto.getPriority() );

        return notification;
    }
}
