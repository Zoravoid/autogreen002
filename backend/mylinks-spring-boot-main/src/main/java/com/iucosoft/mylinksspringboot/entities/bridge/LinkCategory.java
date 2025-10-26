package com.iucosoft.mylinksspringboot.entities.bridge;

import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.Category;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="link_categories")
public class LinkCategory extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "link_id", nullable = false)
    private Link link;
}
