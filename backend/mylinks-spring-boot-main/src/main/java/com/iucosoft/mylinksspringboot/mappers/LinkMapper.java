package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Link;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    LinkDTO toDto(Link link);

    Link toEntity(LinkDTO dto);

    Link toEntity(LinkUpdateDTO dto);

    LinkUpdateDTO toUpdateDto(Link model);

    Link toEntity(LinkCreateDTO linkCreateDTO);

    LinkCreateDTO toCreateDto(Link link);
}
