package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.group.GroupCreateDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Group;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-29T15:42:28+0100",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 1.8.0_472 (Private Build)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupDTO toDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTO.GroupDTOBuilder groupDTO = GroupDTO.builder();

        if ( group.getId() != null ) {
            groupDTO.id( group.getId().intValue() );
        }
        groupDTO.title( group.getTitle() );

        return groupDTO.build();
    }

    @Override
    public Group toEntity(GroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Group.GroupBuilder<?, ?> group = Group.builder();

        if ( dto.getId() != null ) {
            group.id( dto.getId().longValue() );
        }
        group.title( dto.getTitle() );

        return group.build();
    }

    @Override
    public GroupUpdateDTO toUpdateDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupUpdateDTO.GroupUpdateDTOBuilder groupUpdateDTO = GroupUpdateDTO.builder();

        if ( group.getId() != null ) {
            groupUpdateDTO.id( group.getId().intValue() );
        }
        groupUpdateDTO.title( group.getTitle() );

        return groupUpdateDTO.build();
    }

    @Override
    public Group groupCreateDTOToGroup(GroupCreateDTO groupCreateDTO) {
        if ( groupCreateDTO == null ) {
            return null;
        }

        Group.GroupBuilder<?, ?> group = Group.builder();

        group.title( groupCreateDTO.getTitle() );

        return group.build();
    }
}
