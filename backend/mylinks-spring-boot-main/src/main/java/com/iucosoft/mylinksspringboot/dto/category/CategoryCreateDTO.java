package com.iucosoft.mylinksspringboot.dto.category;

import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDTO {
    @NotNull(message = "error.category.title.notnull")
    @NotBlank(message = "error.category.title.notblank")
    private String title;

    private String description;

    @NotNull(message = "error.category.owner.notnull")
    private Long ownerId;
}
