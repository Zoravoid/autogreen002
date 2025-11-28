package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.NotificationDTO;
import com.iucosoft.mylinksspringboot.entities.Notification;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-28T14:38:20+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationDTO toDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        if ( notification.getId() != null ) {
            notificationDTO.setId( notification.getId().intValue() );
        }
        notificationDTO.setMessage( notification.getMessage() );
        notificationDTO.setDateNotificationSent( notification.getDateNotificationSent() );
        notificationDTO.setDateNotificationRead( notification.getDateNotificationRead() );
        notificationDTO.setPriority( notification.getPriority() );

        return notificationDTO;
    }

    @Override
    public Notification toEntity(NotificationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        if ( dto.getId() != null ) {
            notification.setId( dto.getId().longValue() );
        }
        notification.setMessage( dto.getMessage() );
        notification.setDateNotificationSent( dto.getDateNotificationSent() );
        notification.setDateNotificationRead( dto.getDateNotificationRead() );
        notification.setPriority( dto.getPriority() );

        return notification;
    }
}
