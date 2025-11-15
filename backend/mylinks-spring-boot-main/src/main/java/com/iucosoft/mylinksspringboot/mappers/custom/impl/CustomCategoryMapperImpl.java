package com.iucosoft.mylinksspringboot.mappers.custom.impl;

import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.mappers.CategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomCategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomUserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomCategoryMapperImpl implements CustomCategoryMapper {

    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    private final CustomUserMapper customUserMapper;

    public CustomCategoryMapperImpl(CustomUserMapper customUserMapper, UserMapper userMapper) {
        this.customUserMapper = customUserMapper;
    }

    @Override
    public CategoryDTO toDto(Category category) {
        CategoryDTO categoryDTO = categoryMapper.toDto(category);
        UserDTO owner = customUserMapper.toDto(category.getOwner());
        categoryDTO.setOwner(owner);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toDtos(List<Category> categories) {
        List<CategoryDTO> categoryDTOList = categoryMapper.toDtos(categories);
        for (int i = 0; i < categories.size(); i++) {
            UserDTO owner = customUserMapper.toDto(categories.get(i).getOwner());
            categoryDTOList.get(i).setOwner(owner);
        }
        return categoryDTOList;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return categoryMapper.toEntity(dto);
    }

    @Override
    public CategoryUpdateDTO toUpdateDto(Category category) {
        return categoryMapper.toUpdateDto(category);
    }

    @Override
    public CategoryCreateDTO toCreateDto(Category category) {
        return categoryMapper.toCreateDto(category);
    }

    @Override
    public Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        return categoryMapper.toEntity(categoryCreateDTO);
    }
}
