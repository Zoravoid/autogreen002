package com.iucosoft.mylinksspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class MessageDTO {

    private String message;

    private Boolean status;


}
