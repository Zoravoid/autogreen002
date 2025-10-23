package com.iucosoft.mylinksspringboot.mappers.custom;

import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import org.mapstruct.Mapping;

import java.util.List;

public interface CustomCategoryMapper {

    CategoryDTO toDto(Category category);

    List<CategoryDTO> toDtos(List<Category> categories);

    Category toEntity(CategoryDTO dto);

    CategoryUpdateDTO toUpdateDto(Category category);

    CategoryCreateDTO toCreateDto(Category category);

    Category toEntity(CategoryCreateDTO categoryCreateDTO);
}
