package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_accessed", nullable = false, length = 10)
    private Date dateLastAccessed;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_created_account", nullable = false, length = 10)
    private Date dateCreatedAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "person_details_id", referencedColumnName = "id")
    private PersonDetails personDetails;

    @ManyToOne
    @JoinColumn(name = "user_status_id", referencedColumnName = "id")
    private UserStatus userStatus;

    //TODO de verificat daca userul este online prin websocket
    @Column(name = "online", nullable = false)
    private boolean online;
}
