package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.NotificationDTO;
import com.iucosoft.mylinksspringboot.entities.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDto(Notification notification);
    Notification toEntity(NotificationDTO dto);
}

