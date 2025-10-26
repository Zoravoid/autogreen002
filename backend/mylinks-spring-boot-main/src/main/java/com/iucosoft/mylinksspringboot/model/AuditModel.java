package com.iucosoft.mylinksspringboot.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FilterDef(name = "ownerFilter", parameters = @ParamDef(name = "ownerIdParam", type = "long"))
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuditModel implements Serializable {

    @Column(name = "created_date", updatable = false, nullable = false)
    @CreatedDate
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Version
    private Long version;
}
