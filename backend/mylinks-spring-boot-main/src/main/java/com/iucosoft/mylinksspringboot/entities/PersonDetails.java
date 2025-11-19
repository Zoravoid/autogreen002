package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_details")
public class PersonDetails extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "country", length = 30)
    private String country;

    @Column(name = "operating_system", length = 30)
    private String operatingSystem;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "device")
    private String device;


    public PersonDetails(Long id, String phoneNumber, String operatingSystem, String ipAddress, String device) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.operatingSystem = operatingSystem;
        this.ipAddress = ipAddress;
        this.device = device;
    }
}
