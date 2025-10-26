package com.iucosoft.mylinksspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

    private Long id;

    private String resource;

    private String typeOfPermission;

    public PermissionDTO(String resource, String typeOfPermission) {
        this.resource = resource;
        this.typeOfPermission = typeOfPermission;
    }
}


