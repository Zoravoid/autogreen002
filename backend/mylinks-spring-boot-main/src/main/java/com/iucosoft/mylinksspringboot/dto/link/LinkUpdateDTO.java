package com.iucosoft.mylinksspringboot.dto.link;

import com.iucosoft.mylinksspringboot.entities.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkUpdateDTO {

    private Integer id;

    @NotNull(message = "error.link.title.notnull")
    @NotBlank(message = "error.link.title.notblank")
    private String title;

    @NotNull(message = "error.link.url.notnull")
    @NotBlank(message = "error.link.url.notblank")
    private String url;

    private String description;

    private Set<String> tagTitles;

    private Visibility visibility;

    private Date dateLastAccessed;

    private List<Long> categoryIds;
}
