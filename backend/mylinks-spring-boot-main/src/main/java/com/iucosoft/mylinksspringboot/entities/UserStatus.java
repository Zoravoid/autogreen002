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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "login_user_statuses")
public class UserStatus extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "status_name")
    private String statusName;

    private Boolean restricted;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_accessed", nullable = false)
    private Date dateLastAccessed;

}
