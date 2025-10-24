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
    date = "2025-10-24T14:00:14+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class LinkMapperImpl implements LinkMapper {

    @Override
    public LinkDTO toDto(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkDTO.LinkDTOBuilder linkDTO = LinkDTO.builder();

        linkDTO.dateLastAccessed( link.getDateLastAccessed() );
        linkDTO.description( link.getDescription() );
        linkDTO.id( link.getId() );
        linkDTO.owner( userToUserDTO( link.getOwner() ) );
        linkDTO.title( link.getTitle() );
        linkDTO.url( link.getUrl() );
        linkDTO.visibility( link.getVisibility() );

        return linkDTO.build();
    }

    @Override
    public Link toEntity(LinkDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        link.dateLastAccessed( dto.getDateLastAccessed() );
        link.description( dto.getDescription() );
        link.id( dto.getId() );
        link.owner( userDTOToUser( dto.getOwner() ) );
        link.title( dto.getTitle() );
        link.url( dto.getUrl() );
        link.visibility( dto.getVisibility() );

        return link.build();
    }

    @Override
    public Link toEntity(LinkUpdateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        link.dateLastAccessed( dto.getDateLastAccessed() );
        link.description( dto.getDescription() );
        if ( dto.getId() != null ) {
            link.id( dto.getId().longValue() );
        }
        link.title( dto.getTitle() );
        link.url( dto.getUrl() );
        link.visibility( dto.getVisibility() );

        return link.build();
    }

    @Override
    public LinkUpdateDTO toUpdateDto(Link model) {
        if ( model == null ) {
            return null;
        }

        LinkUpdateDTO.LinkUpdateDTOBuilder linkUpdateDTO = LinkUpdateDTO.builder();

        linkUpdateDTO.dateLastAccessed( model.getDateLastAccessed() );
        linkUpdateDTO.description( model.getDescription() );
        if ( model.getId() != null ) {
            linkUpdateDTO.id( model.getId().intValue() );
        }
        linkUpdateDTO.title( model.getTitle() );
        linkUpdateDTO.url( model.getUrl() );
        linkUpdateDTO.visibility( model.getVisibility() );

        return linkUpdateDTO.build();
    }

    @Override
    public Link toEntity(LinkCreateDTO linkCreateDTO) {
        if ( linkCreateDTO == null ) {
            return null;
        }

        Link.LinkBuilder<?, ?> link = Link.builder();

        link.dateLastAccessed( linkCreateDTO.getDateLastAccessed() );
        link.description( linkCreateDTO.getDescription() );
        link.title( linkCreateDTO.getTitle() );
        link.url( linkCreateDTO.getUrl() );
        link.visibility( linkCreateDTO.getVisibility() );

        return link.build();
    }

    @Override
    public LinkCreateDTO toCreateDto(Link link) {
        if ( link == null ) {
            return null;
        }

        LinkCreateDTO.LinkCreateDTOBuilder linkCreateDTO = LinkCreateDTO.builder();

        linkCreateDTO.dateLastAccessed( link.getDateLastAccessed() );
        linkCreateDTO.description( link.getDescription() );
        linkCreateDTO.title( link.getTitle() );
        linkCreateDTO.url( link.getUrl() );
        linkCreateDTO.visibility( link.getVisibility() );

        return linkCreateDTO.build();
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

    protected UserDTO userToUserDTO(User user) {
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

    protected User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.dateCreatedAccount( userDTO.getDateCreatedAccount() );
        user.dateLastAccessed( userDTO.getDateLastAccessed() );
        user.email( userDTO.getEmail() );
        user.id( userDTO.getId() );
        user.password( userDTO.getPassword() );
        user.personDetails( personDetailsDTOToPersonDetails( userDTO.getPersonDetails() ) );
        user.username( userDTO.getUsername() );

        return user.build();
    }
}
