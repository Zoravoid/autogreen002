package com.iucosoft.mylinksspringboot.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateDTO {

    private String title;

    private Long ownerId;
}
