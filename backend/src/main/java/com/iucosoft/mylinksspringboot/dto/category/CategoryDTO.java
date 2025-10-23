package com.iucosoft.mylinksspringboot.dto.category;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;
    private String title;
    private String description;
    private UserDTO owner;
}
