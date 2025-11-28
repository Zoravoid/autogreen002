package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "humidity")
public class Humidity extends AuditModel implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "device_id", unique = false, nullable = false)
    private Long deviceId;

    @Column(name = "humidity_val", nullable = false, length = 20)
    private Float humidityVal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_stamp", nullable = false, length = 10)
    private Date timeStamp;

}
