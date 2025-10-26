package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTO;
import com.iucosoft.mylinksspringboot.dto.personDetails.PersonDetailsDTOCreate;
import com.iucosoft.mylinksspringboot.entities.PersonDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDetailsMapper {

    PersonDetailsDTO toDto(PersonDetails personDetails);

    PersonDetails toEntity(PersonDetailsDTO dto);

    PersonDetails personDetailsDTOCreateToPersonDetails(PersonDetailsDTOCreate personDetailsDTOCreate);

    PersonDetails personDetailsToPersonDetailsDTOCreate(PersonDetails personDetails);
}
