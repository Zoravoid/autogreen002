package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import com.iucosoft.mylinksspringboot.entities.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-06T19:28:24+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class LinkMapperImpl implements LinkMapper {

    @Override
    public LinkDTO toDto(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkDTO.LinkDTOBuilder linkDTO = LinkDTO.builder();

        linkDTO.id( link.getId() );
        linkDTO.title( link.getTitle() );
        linkDTO.url( link.getUrl() );
        linkDTO.description( link.getDescription() );
        linkDTO.dateLastAccessed( link.getDateLastAccessed() );
        linkDTO.visibility( link.getVisibility() );
        linkDTO.owner( userToUserDTO( link.getOwner() ) );

        return linkDTO.build();
    }

    @Override
    public Link toEntity(LinkDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        link.id( dto.getId() );
        link.title( dto.getTitle() );
        link.url( dto.getUrl() );
        link.description( dto.getDescription() );
        link.visibility( dto.getVisibility() );
        link.dateLastAccessed( dto.getDateLastAccessed() );
        link.owner( userDTOToUser( dto.getOwner() ) );

        return link.build();
    }

    @Override
    public Link toEntity(LinkUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        if ( dto.getId() != null ) {
            link.id( dto.getId().longValue() );
        }
        link.title( dto.getTitle() );
        link.url( dto.getUrl() );
        link.description( dto.getDescription() );
        link.visibility( dto.getVisibility() );
        link.dateLastAccessed( dto.getDateLastAccessed() );

        return link.build();
    }

    @Override
    public LinkUpdateDTO toUpdateDto(Link model) {
        if ( model == null ) {
            return null;
        }

        LinkUpdateDTO.LinkUpdateDTOBuilder linkUpdateDTO = LinkUpdateDTO.builder();

        if ( model.getId() != null ) {
            linkUpdateDTO.id( model.getId().intValue() );
        }
        linkUpdateDTO.title( model.getTitle() );
        linkUpdateDTO.url( model.getUrl() );
        linkUpdateDTO.description( model.getDescription() );
        linkUpdateDTO.visibility( model.getVisibility() );
        linkUpdateDTO.dateLastAccessed( model.getDateLastAccessed() );

        return linkUpdateDTO.build();
    }

    @Override
    public Link toEntity(LinkCreateDTO linkCreateDTO) {
        if ( linkCreateDTO == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        link.title( linkCreateDTO.getTitle() );
        link.url( linkCreateDTO.getUrl() );
        link.description( linkCreateDTO.getDescription() );
        link.visibility( linkCreateDTO.getVisibility() );
        link.dateLastAccessed( linkCreateDTO.getDateLastAccessed() );

        return link.build();
    }

    @Override
    public LinkCreateDTO toCreateDto(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkCreateDTO.LinkCreateDTOBuilder linkCreateDTO = LinkCreateDTO.builder();

        linkCreateDTO.title( link.getTitle() );
        linkCreateDTO.url( link.getUrl() );
        linkCreateDTO.description( link.getDescription() );
        linkCreateDTO.dateLastAccessed( link.getDateLastAccessed() );
        linkCreateDTO.visibility( link.getVisibility() );

        return linkCreateDTO.build();
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

    protected UserDTO userToUserDTO(User user) {
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

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( userDTO.getId() );
        user.username( userDTO.getUsername() );
        user.email( userDTO.getEmail() );
        user.password( userDTO.getPassword() );
        user.dateLastAccessed( userDTO.getDateLastAccessed() );
        user.dateCreatedAccount( userDTO.getDateCreatedAccount() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTO.getPersonDetails() ) );

        return user.build();
    }
}
