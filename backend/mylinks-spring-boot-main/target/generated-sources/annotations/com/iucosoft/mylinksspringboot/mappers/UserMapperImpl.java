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
    date = "2025-12-04T15:51:04+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.email( user.getEmail() );
        userDTO.password( user.getPassword() );
        userDTO.dateLastAccessed( user.getDateLastAccessed() );
        userDTO.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTO.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );

        return userDTO.build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( dto.getId() );
        user.username( dto.getUsername() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        user.dateLastAccessed( dto.getDateLastAccessed() );
        user.dateCreatedAccount( dto.getDateCreatedAccount() );
        user.personDetails( personDetailsDTOToPersonDetails( dto.getPersonDetails() ) );

        return user.build();
    }

    @Override
    public User userDtoCreateToUser(UserDTOCreate userDTOCreate) {
        if ( userDTOCreate == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( userDTOCreate.getUsername() );
        user.email( userDTOCreate.getEmail() );
        user.password( userDTOCreate.getPassword() );
        user.personDetails( personDetailsDTOCreateToPersonDetails( userDTOCreate.getPersonDetails() ) );

        return user.build();
    }

    @Override
    public UserDTOCreate userToUserDTOCreate(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOCreate userDTOCreate = new UserDTOCreate();

        userDTOCreate.setUsername( user.getUsername() );
        userDTOCreate.setEmail( user.getEmail() );
        userDTOCreate.setPassword( user.getPassword() );
        userDTOCreate.setPersonDetails( personDetailsToPersonDetailsDTOCreate( user.getPersonDetails() ) );

        return userDTOCreate;
    }

    @Override
    public User userDTOUpdateToUser(UserDTOUpdate userDTOUpdate) {
        if ( userDTOUpdate == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( userDTOUpdate.getId() );
        user.username( userDTOUpdate.getUsername() );
        user.email( userDTOUpdate.getEmail() );
        user.password( userDTOUpdate.getPassword() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTOUpdate.getPersonDetails() ) );

        return user.build();
    }

    @Override
    public UserDTONew userToUserDTONew(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTONew.UserDTONewBuilder userDTONew = UserDTONew.builder();

        userDTONew.userStatusId( userUserStatusId( user ) );
        userDTONew.id( user.getId() );
        userDTONew.username( user.getUsername() );
        userDTONew.email( user.getEmail() );
        userDTONew.password( user.getPassword() );
        userDTONew.dateLastAccessed( user.getDateLastAccessed() );
        userDTONew.dateCreatedAccount( user.getDateCreatedAccount() );
        userDTONew.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );

        return userDTONew.build();
    }

    @Override
    public UserDTOUpdate userToUserDTOUpdate(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOUpdate.UserDTOUpdateBuilder userDTOUpdate = UserDTOUpdate.builder();

        userDTOUpdate.id( user.getId() );
        userDTOUpdate.username( user.getUsername() );
        userDTOUpdate.email( user.getEmail() );
        userDTOUpdate.password( user.getPassword() );
        userDTOUpdate.personDetails( personDetailsToPersonDetailsDTO( user.getPersonDetails() ) );

        return userDTOUpdate.build();
    }

    protected PersonDetailsDTO personDetailsToPersonDetailsDTO(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTO.PersonDetailsDTOBuilder personDetailsDTO = PersonDetailsDTO.builder();

        personDetailsDTO.id( personDetails.getId() );
        personDetailsDTO.firstName( personDetails.getFirstName() );
        personDetailsDTO.lastName( personDetails.getLastName() );
        personDetailsDTO.phoneNumber( personDetails.getPhoneNumber() );
        personDetailsDTO.country( personDetails.getCountry() );
        personDetailsDTO.operatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTO.ipAddress( personDetails.getIpAddress() );
        personDetailsDTO.device( personDetails.getDevice() );

        return personDetailsDTO.build();
    }

    protected PersonDetails personDetailsDTOToPersonDetails(PersonDetailsDTO personDetailsDTO) {
        if ( personDetailsDTO == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.id( personDetailsDTO.getId() );
        personDetails.firstName( personDetailsDTO.getFirstName() );
        personDetails.lastName( personDetailsDTO.getLastName() );
        personDetails.phoneNumber( personDetailsDTO.getPhoneNumber() );
        personDetails.country( personDetailsDTO.getCountry() );
        personDetails.operatingSystem( personDetailsDTO.getOperatingSystem() );
        personDetails.ipAddress( personDetailsDTO.getIpAddress() );
        personDetails.device( personDetailsDTO.getDevice() );

        return personDetails.build();
    }

    protected PersonDetails personDetailsDTOCreateToPersonDetails(PersonDetailsDTOCreate personDetailsDTOCreate) {
        if ( personDetailsDTOCreate == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.firstName( personDetailsDTOCreate.getFirstName() );
        personDetails.lastName( personDetailsDTOCreate.getLastName() );
        personDetails.phoneNumber( personDetailsDTOCreate.getPhoneNumber() );
        personDetails.country( personDetailsDTOCreate.getCountry() );
        personDetails.operatingSystem( personDetailsDTOCreate.getOperatingSystem() );
        personDetails.ipAddress( personDetailsDTOCreate.getIpAddress() );
        personDetails.device( personDetailsDTOCreate.getDevice() );

        return personDetails.build();
    }

    protected PersonDetailsDTOCreate personDetailsToPersonDetailsDTOCreate(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetailsDTOCreate personDetailsDTOCreate = new PersonDetailsDTOCreate();

        personDetailsDTOCreate.setFirstName( personDetails.getFirstName() );
        personDetailsDTOCreate.setLastName( personDetails.getLastName() );
        personDetailsDTOCreate.setPhoneNumber( personDetails.getPhoneNumber() );
        personDetailsDTOCreate.setCountry( personDetails.getCountry() );
        personDetailsDTOCreate.setOperatingSystem( personDetails.getOperatingSystem() );
        personDetailsDTOCreate.setIpAddress( personDetails.getIpAddress() );
        personDetailsDTOCreate.setDevice( personDetails.getDevice() );

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
