package com.iucosoft.mylinksspringboot.entities;

import com.iucosoft.mylinksspringboot.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "links")
@Filter(name = "ownerFilter", condition = "owner_id = 2")
public class Link extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "url", length = 2000)
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility")
    private Visibility visibility;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_last_accessed", nullable = true, length = 10)
    private Date dateLastAccessed;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

}
