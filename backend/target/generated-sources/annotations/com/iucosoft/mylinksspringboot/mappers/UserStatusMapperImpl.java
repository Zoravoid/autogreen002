package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T19:58:37+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserStatusMapperImpl implements UserStatusMapper {

    @Override
    public UserStatusDTO toDto(UserStatus userStatus) {
        if ( userStatus == null ) {
            return null;
        }

        UserStatusDTO userStatusDTO = new UserStatusDTO();

        userStatusDTO.setId( userStatus.getId() );
        userStatusDTO.setRestricted( userStatus.getRestricted() );
        userStatusDTO.setStatusName( userStatus.getStatusName() );

        return userStatusDTO;
    }

    @Override
    public UserStatus toEntity(UserStatusDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserStatus.UserStatusBuilder<?, ?> userStatus = UserStatus.builder();

        userStatus.id( dto.getId() );
        userStatus.restricted( dto.getRestricted() );
        userStatus.statusName( dto.getStatusName() );

        return userStatus.build();
    }
}
