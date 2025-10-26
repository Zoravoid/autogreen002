package com.iucosoft.mylinksspringboot.mappers;

import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    List<CategoryDTO> toDtos(List<Category> categories);

    Category toEntity(CategoryDTO dto);

    CategoryUpdateDTO toUpdateDto(Category category);

    CategoryCreateDTO toCreateDto(Category category);

    Category toEntity(CategoryCreateDTO categoryCreateDTO);
}
