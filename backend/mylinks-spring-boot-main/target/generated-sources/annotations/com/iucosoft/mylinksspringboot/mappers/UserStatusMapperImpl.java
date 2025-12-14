package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.UserStatusDTO;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2025-12-13T21:15:35+0100",
=======
    date = "2025-11-30T18:57:18+0100",
>>>>>>> 7c168b0 (Merge branch 'main' into raspberri-pi)
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
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
        userStatusDTO.setStatusName( userStatus.getStatusName() );
        userStatusDTO.setRestricted( userStatus.getRestricted() );

        return userStatusDTO;
    }

    @Override
    public UserStatus toEntity(UserStatusDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserStatus.UserStatusBuilder<?, ?> userStatus = UserStatus.builder();

        userStatus.id( dto.getId() );
        userStatus.statusName( dto.getStatusName() );
        userStatus.restricted( dto.getRestricted() );

        return userStatus.build();
    }
}
