package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.group.GroupCreateDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO toDto(Group group);

    Group toEntity(GroupDTO dto);

    GroupUpdateDTO toUpdateDto(Group group);

    Group groupCreateDTOToGroup(GroupCreateDTO groupCreateDTO);


}
