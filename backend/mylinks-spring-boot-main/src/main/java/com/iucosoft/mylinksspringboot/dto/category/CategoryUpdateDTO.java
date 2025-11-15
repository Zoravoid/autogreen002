package com.iucosoft.mylinksspringboot.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryUpdateDTO {
    private Integer id;

    @NotNull(message = "error.category.title.notnull")
    @NotBlank(message = "error.category.title.notblank")
    private String title;

    private String description;
}
