package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOCreate;
import com.iucosoft.mylinksspringboot.dto.user.UserDTONew;
import com.iucosoft.mylinksspringboot.dto.user.UserDTOUpdate;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.UserStatus;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T14:00:14+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTO.dateLastAccessed( user.getDateLastAccessed() );
        userDTO.email( user.getEmail() );
        userDTO.id( user.getId() );
        userDTO.password( user.getPassword() );
        userDTO.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );
        userDTO.username( user.getUsername() );

        return userDTO.build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.dateCreatedAccount( dto.getDateCreatedAccount() );
        user.dateLastAccessed( dto.getDateLastAccessed() );
        user.email( dto.getEmail() );
        user.id( dto.getId() );
        user.password( dto.getPassword() );
        user.personDetails( personDetailsDTOToPersonDetails( dto.getPersonDetails() ) );
        user.username( dto.getUsername() );

        return user.build();
    }

    @Override
    public User userDtoCreateToUser(UserDTOCreate userDTOCreate) {
        if ( userDTOCreate == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.email( userDTOCreate.getEmail() );
        user.password( userDTOCreate.getPassword() );
        user.personDetails( personDetailsDTOCreateToPersonDetails( userDTOCreate.getPersonDetails() ) );
        user.username( userDTOCreate.getUsername() );

        return user.build();
    }

    @Override
    public UserDTOCreate userToUserDTOCreate(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOCreate userDTOCreate = new UserDTOCreate();

        userDTOCreate.setEmail( user.getEmail() );
        userDTOCreate.setPassword( user.getPassword() );
        userDTOCreate.setPersonDetails( personDetailsToPersonDetailsDTOCreate( user.getPersonDetails() ) );
        userDTOCreate.setUsername( user.getUsername() );

        return userDTOCreate;
    }

    @Override
    public User userDTOUpdateToUser(UserDTOUpdate userDTOUpdate) {
        if ( userDTOUpdate == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.email( userDTOUpdate.getEmail() );
        user.id( userDTOUpdate.getId() );
        user.password( userDTOUpdate.getPassword() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTOUpdate.getPersonDetails() ) );
        user.username( userDTOUpdate.getUsername() );

        return user.build();
    }

    @Override
    public UserDTONew userToUserDTONew(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTONew.UserDTONewBuilder userDTONew = UserDTONew.builder();

        userDTONew.userStatusId( userUserStatusId( user ) );
        userDTONew.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTONew.dateLastAccessed( user.getDateLastAccessed() );
        userDTONew.email( user.getEmail() );
        userDTONew.id( user.getId() );
        userDTONew.password( user.getPassword() );
        userDTONew.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );
        userDTONew.username( user.getUsername() );

        return userDTONew.build();
    }

    @Override
    public UserDTOUpdate userToUserDTOUpdate(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOUpdate.UserDTOUpdateBuilder userDTOUpdate = UserDTOUpdate.builder();

        userDTOUpdate.email( user.getEmail() );
        userDTOUpdate.id( user.getId() );
        userDTOUpdate.password( user.getPassword() );
        userDTOUpdate.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );
        userDTOUpdate.username( user.getUsername() );

        return userDTOUpdate.build();
    }

    protected PersonDetailsDTO personDetailsToPersonDetailsDTO(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTO.PersonDetailsDTOBuilder personDetailsDTO = PersonDetailsDTO.builder();

        personDetailsDTO.country( personDetails.getCountry() );
        personDetailsDTO.device( personDetails.getDevice() );
        personDetailsDTO.firstName( personDetails.getFirstName() );
        personDetailsDTO.id( personDetails.getId() );
        personDetailsDTO.ipAddress( personDetails.getIpAddress() );
        personDetailsDTO.lastName( personDetails.getLastName() );
        personDetailsDTO.operatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTO.phoneNumber( personDetails.getPhoneNumber() );

        return personDetailsDTO.build();
    }

    protected PersonDetails personDetailsDTOToPersonDetails(PersonDetailsDTO personDetailsDTO) {
        if ( personDetailsDTO == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.country( personDetailsDTO.getCountry() );
        personDetails.device( personDetailsDTO.getDevice() );
        personDetails.firstName( personDetailsDTO.getFirstName() );
        personDetails.id( personDetailsDTO.getId() );
        personDetails.ipAddress( personDetailsDTO.getIpAddress() );
        personDetails.lastName( personDetailsDTO.getLastName() );
        personDetails.operatingSystem( personDetailsDTO.getOperatingSystem() );
        personDetails.phoneNumber( personDetailsDTO.getPhoneNumber() );

        return personDetails.build();
    }

    protected PersonDetails personDetailsDTOCreateToPersonDetails(PersonDetailsDTOCreate personDetailsDTOCreate) {
        if ( personDetailsDTOCreate == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.country( personDetailsDTOCreate.getCountry() );
        personDetails.device( personDetailsDTOCreate.getDevice() );
        personDetails.firstName( personDetailsDTOCreate.getFirstName() );
        personDetails.ipAddress( personDetailsDTOCreate.getIpAddress() );
        personDetails.lastName( personDetailsDTOCreate.getLastName() );
        personDetails.operatingSystem( personDetailsDTOCreate.getOperatingSystem() );
        personDetails.phoneNumber( personDetailsDTOCreate.getPhoneNumber() );

        return personDetails.build();
    }

    protected PersonDetailsDTOCreate personDetailsToPersonDetailsDTOCreate(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTOCreate personDetailsDTOCreate = new PersonDetailsDTOCreate();

        personDetailsDTOCreate.setCountry( personDetails.getCountry() );
        personDetailsDTOCreate.setDevice( personDetails.getDevice() );
        personDetailsDTOCreate.setFirstName( personDetails.getFirstName() );
        personDetailsDTOCreate.setIpAddress( personDetails.getIpAddress() );
        personDetailsDTOCreate.setLastName( personDetails.getLastName() );
        personDetailsDTOCreate.setOperatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTOCreate.setPhoneNumber( personDetails.getPhoneNumber() );

        return personDetailsDTOCreate;
    }

    private Long userUserStatusId(User user) {
        if ( user == null ) {
            return null;
        }
        UserStatus userStatus = user.getUserStatus();
        if ( userStatus == null ) {
            return null;
        }
        Long id = userStatus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
