package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T20:59:40+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_462 (Temurin)"
)
@Component
public class PersonDetailsMapperImpl implements PersonDetailsMapper {

    @Override
    public PersonDetailsDTO toDto(PersonDetails personDetails) {
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

    @Override
    public PersonDetails toEntity(PersonDetailsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails = PersonDetails.builder();

        personDetails.id( dto.getId() );
        personDetails.firstName( dto.getFirstName() );
        personDetails.lastName( dto.getLastName() );
        personDetails.phoneNumber( dto.getPhoneNumber() );
        personDetails.country( dto.getCountry() );
        personDetails.operatingSystem( dto.getOperatingSystem() );
        personDetails.ipAddress( dto.getIpAddress() );
        personDetails.device( dto.getDevice() );

        return personDetails.build();
    }

    @Override
    public PersonDetails personDetailsDTOCreateToPersonDetails(PersonDetailsDTOCreate personDetailsDTOCreate) {
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

    @Override
    public PersonDetails personDetailsToPersonDetailsDTOCreate(PersonDetails personDetails) {
        if ( personDetails == null ) {
            return null;
        }

        PersonDetails.PersonDetailsBuilder<?, ?> personDetails1 = PersonDetails.builder();

        personDetails1.createdDate( personDetails.getCreatedDate() );
        personDetails1.lastModifiedDate( personDetails.getLastModifiedDate() );
        personDetails1.version( personDetails.getVersion() );
        personDetails1.id( personDetails.getId() );
        personDetails1.firstName( personDetails.getFirstName() );
        personDetails1.lastName( personDetails.getLastName() );
        personDetails1.phoneNumber( personDetails.getPhoneNumber() );
        personDetails1.country( personDetails.getCountry() );
        personDetails1.operatingSystem( personDetails.getOperatingSystem() );
        personDetails1.ipAddress( personDetails.getIpAddress() );
        personDetails1.device( personDetails.getDevice() );

        return personDetails1.build();
    }
}
