package com.iucosoft.mylinksspringboot.dto.link;


import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {

    private Long id;

    private String title;

    private String url;

    private String description;

    private Set<String> tagTitles;

    private Date dateLastAccessed;

    private Visibility visibility;

    private UserDTO owner;

    private List<CategoryDTO> categories;
}
