package com.iucosoft.mylinksspringboot.dto;

import lombok.Data;
import java.util.Date;

@Data
public class UserStatusDTO {

    private Long id;

    private String statusName;

    private Boolean restricted;

    private Date lastAccessed;
}
