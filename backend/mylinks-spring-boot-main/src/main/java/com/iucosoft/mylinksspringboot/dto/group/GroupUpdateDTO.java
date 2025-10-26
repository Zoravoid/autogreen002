package com.iucosoft.mylinksspringboot.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupUpdateDTO {

    private Integer id;

    private String title;

}
