package com.iucosoft.mylinksspringboot.mappers.custom;

import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Link;

public interface CustomLinkMapper {

    LinkDTO toDto(Link link);

    Link toEntity(LinkDTO dto);

    Link toEntity(LinkUpdateDTO dto);

    LinkUpdateDTO toUpdateDto(Link link);

    Link toEntity(LinkCreateDTO linkCreateDTO);

    LinkCreateDTO toCreateDto(Link link);
}
