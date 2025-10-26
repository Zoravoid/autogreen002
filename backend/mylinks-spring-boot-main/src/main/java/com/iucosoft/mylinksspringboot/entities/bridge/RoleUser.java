package com.iucosoft.mylinksspringboot.entities.bridge;

import com.iucosoft.mylinksspringboot.entities.Role;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@SuperBuilder
@Table(name="role_users")
public class RoleUser extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public RoleUser() {
    }

    public RoleUser(Role role, User user) {
        this.role = role;
        this.user = user;
    }
}
