package com.iucosoft.mylinksspringboot.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMembersDTO {

    private Integer id;

    private String title;

    private List<Long> membersIds;
}
