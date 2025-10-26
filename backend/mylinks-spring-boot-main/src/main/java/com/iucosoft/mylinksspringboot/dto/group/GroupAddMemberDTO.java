package com.iucosoft.mylinksspringboot.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupAddMemberDTO {

    private Long groupId;

    private Long memberId;
}
