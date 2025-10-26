package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_groups")
@Filter(name = "ownerFilter", condition = "owner_id = :ownerIdParam")
public class Group extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", length = 25)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;


}
