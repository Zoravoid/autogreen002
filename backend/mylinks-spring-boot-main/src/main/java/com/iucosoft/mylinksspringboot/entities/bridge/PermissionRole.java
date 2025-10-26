package com.iucosoft.mylinksspringboot.entities.bridge;


import com.iucosoft.mylinksspringboot.entities.Permission;
import com.iucosoft.mylinksspringboot.entities.Role;
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

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="permissions_roles")
public class PermissionRole extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;


    public PermissionRole(Permission permission, Role role) {
        this.permission = permission;
        this.role = role;
    }
}
