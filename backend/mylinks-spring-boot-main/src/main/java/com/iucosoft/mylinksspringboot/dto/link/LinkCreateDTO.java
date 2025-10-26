package com.iucosoft.mylinksspringboot.dto.link;

import com.iucosoft.mylinksspringboot.entities.Tag;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkCreateDTO {

    @NotNull(message = "error.link.title.notnull")
    @NotBlank(message = "error.link.title.notblank")
    private String title;

    @NotNull(message = "error.link.url.notnull")
    @NotBlank(message = "error.link.url.notblank")
    private String url;

    private String description;

    private Set<String> tagTitles;

    private Date dateLastAccessed;

    private Visibility visibility;

    @NotNull(message = "error.link.owner.notnull")
    @NotBlank(message = "error.link.owner.notblank")
    private Long ownerId;

    private List<Long> categoryIds;
}
