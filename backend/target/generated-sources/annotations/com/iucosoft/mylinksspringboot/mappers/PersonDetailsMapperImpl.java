package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-22T19:58:37+0200",
    comments = "version: 1.5.4.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PersonDetailsMapperImpl implements PersonDetailsMapper {

    @Override
    public PersonDetailsDTO toDto(PersonDetails personDetails) {
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

    @Override
    public PersonDetails toEntity(PersonDetailsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.country( dto.getCountry() );
        personDetails.device( dto.getDevice() );
        personDetails.firstName( dto.getFirstName() );
        personDetails.id( dto.getId() );
        personDetails.ipAddress( dto.getIpAddress() );
        personDetails.lastName( dto.getLastName() );
        personDetails.operatingSystem( dto.getOperatingSystem() );
        personDetails.phoneNumber( dto.getPhoneNumber() );

        return personDetails.build();
    }

    @Override
    public PersonDetails personDetailsDTOCreateToPersonDetails(PersonDetailsDTOCreate personDetailsDTOCreate) {
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

    @Override
    public PersonDetails personDetailsToPersonDetailsDTOCreate(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails1 = PersonDetails.builder();

        personDetails1.createdDate( personDetails.getCreatedDate() );
        personDetails1.lastModifiedDate( personDetails.getLastModifiedDate() );
        personDetails1.version( personDetails.getVersion() );
        personDetails1.country( personDetails.getCountry() );
        personDetails1.device( personDetails.getDevice() );
        personDetails1.firstName( personDetails.getFirstName() );
        personDetails1.id( personDetails.getId() );
        personDetails1.ipAddress( personDetails.getIpAddress() );
        personDetails1.lastName( personDetails.getLastName() );
        personDetails1.operatingSystem( personDetails.getOperatingSystem() );
        personDetails1.phoneNumber( personDetails.getPhoneNumber() );

        return personDetails1.build();
    }
}
