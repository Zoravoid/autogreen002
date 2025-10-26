package com.iucosoft.mylinksspringboot.dto.personDetails;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetailsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String operatingSystem;
    private String ipAddress;
    private String device;
}
