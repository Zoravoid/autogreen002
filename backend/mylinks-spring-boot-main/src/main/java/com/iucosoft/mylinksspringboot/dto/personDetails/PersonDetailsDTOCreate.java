package com.iucosoft.mylinksspringboot.dto.personDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetailsDTOCreate {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String operatingSystem;
    private String ipAddress;
    private String device;

}
