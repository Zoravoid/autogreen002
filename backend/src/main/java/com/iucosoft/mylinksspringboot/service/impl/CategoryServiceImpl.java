package com.iucosoft.mylinksspringboot.service.impl;

import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.exceptions.BadRequestException;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.mappers.CategoryMapper;
import com.iucosoft.mylinksspringboot.repositories.CategoryRepository;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.CategoryOwnerService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupCategoryService;
import com.iucosoft.mylinksspringboot.service.bridge.LinkCategoryService;
import com.iucosoft.mylinksspringboot.util.AppConstants;
import com.iucosoft.mylinksspringboot.util.SortingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl extends AbstractServiceImpl<Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryOwnerService categoryOwnerService;
    private final GroupCategoryService groupCategoryService;
    private final LinkCategoryService linkCategoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryOwnerService categoryOwnerService,
                               GroupCategoryService groupCategoryService,
                               LinkCategoryService linkCategoryService,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryOwnerService = categoryOwnerService;
        this.groupCategoryService = groupCategoryService;
        this.linkCategoryService = linkCategoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> getCategoriesByOwnerId(Long ownerId, Pageable pageable) {
        Page<Category> categories = categoryRepository.findCategoriesPageByOwnerId(ownerId, pageable);
        return categories;
    }

    @Override
    public List<Long> getCategoriesIdsByOwnerId(Long ownerId) {
        return categoryRepository.findCategoriesByOwnerId(ownerId).stream().map(category -> category.getId()).collect(Collectors.toList());
    }

    @Override
    public User getOwnerByCategoryId(Long categoryId) {
        return categoryRepository.getOwnerByCategoryId(categoryId);
    }

    @Override
    public Page<Link> getLinksByCategoryId(Long categoryId, Pageable pageable) {
        return categoryRepository.getLinksByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Group> getGroupsByCategoryId(Long categoryId, Pageable pageable) {
        return categoryRepository.getGroupsByCategoryId(categoryId, pageable);
    }


    private void deleteAllRelationshipWithCategory(Category category) {
        categoryOwnerService.deleteByCategoryId(category.getId());
        groupCategoryService.deleteByCategoryId(category.getId());
        linkCategoryService.deleteByCategoryId(category.getId());

    }

    @Override
    public void delete(Category entity) {
        deleteAllRelationshipWithCategory(entity);
        categoryRepository.delete(entity);
    }

    @Override
    public Category findById(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("The category id must be not null");
        }

        return categoryRepository.findAllById(Collections.singleton(id)).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Category could not be found!"));
    }

    @Override
    public Page<CategoryDTO> findCategoriesByFilterParams(final String title, final String description, Pageable pageable) {
        pageable = SortingUtil.applyDefaultSorting(pageable, Link.class, AppConstants.Fields.TITLE);
        Page<Category> categories = categoryRepository.findCategoriesByFilterParams(title, description, pageable);
        return categories.map(categoryMapper::toDto);
    }
}
