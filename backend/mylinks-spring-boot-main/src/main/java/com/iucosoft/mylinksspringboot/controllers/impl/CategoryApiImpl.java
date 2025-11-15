package com.iucosoft.mylinksspringboot.controllers.impl;

import com.iucosoft.mylinksspringboot.controllers.AbstractExceptionHandler;
import com.iucosoft.mylinksspringboot.controllers.CategoryApi;
import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupCategory;
import com.iucosoft.mylinksspringboot.mappers.CategoryMapper;
import com.iucosoft.mylinksspringboot.mappers.GroupMapper;
import com.iucosoft.mylinksspringboot.mappers.UserMapper;
import com.iucosoft.mylinksspringboot.mappers.custom.CustomCategoryMapper;
import com.iucosoft.mylinksspringboot.service.CategoryService;
import com.iucosoft.mylinksspringboot.service.GroupService;
import com.iucosoft.mylinksspringboot.service.UserService;
import com.iucosoft.mylinksspringboot.service.bridge.GroupCategoryService;
import com.iucosoft.mylinksspringboot.service.impl.LinkServiceImpl;
import com.iucosoft.mylinksspringboot.util.AppConstants;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import com.iucosoft.mylinksspringboot.util.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CategoryApiImpl extends AbstractExceptionHandler implements CategoryApi {
    private final CategoryService categoryService;
    private final GroupCategoryService groupCategoryService;
    private final UserService userService;
    private final GroupService groupService;
    private final CustomCategoryMapper customCategoryMapper;
    private final UserMapper userMapper;
    private final LinkServiceImpl linkService;
    private final GroupMapper groupMapper;
    private final JWTUtil jwtUtil;

    public CategoryApiImpl(CategoryService categoryService, GroupCategoryService groupCategoryService,
                           UserService userService, GroupService groupService, CustomCategoryMapper customCategoryMapper,
                           UserMapper userMapper, LinkServiceImpl linkService, GroupMapper groupMapper, JWTUtil jwtUtil) {
        this.categoryService = categoryService;
        this.groupCategoryService = groupCategoryService;
        this.userService = userService;
        this.groupService = groupService;
        this.customCategoryMapper = customCategoryMapper;
        this.userMapper = userMapper;
        this.linkService = linkService;
        this.groupMapper = groupMapper;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<Page<CategoryDTO>> getListOfAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.findAllPaginated(pageable);
        return ResponseEntity.ok(categories.map(customCategoryMapper::toDto));
    }

    @Override
    public ResponseEntity<Page<CategoryDTO>> getListOfCategoriesFiltered(String title, String description, Pageable pageable) {
        Page<CategoryDTO> categories = categoryService.findCategoriesByFilterParams(title, description, pageable);
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<CategoryDTO> createCategory(CategoryCreateDTO categoryCreateDTO, String authorizationHeader) {

        //TODO de luat userId din context de sters String authorizationHeader
        final String jwtToken = authorizationHeader.replace(AuthConstants.BEARER, "").trim();
        final Integer userId = jwtUtil.extractUserId(jwtToken);

        final User categoryOwner = userService.findById(Long.valueOf(userId));
        // de schimbat pe o metodă din mapper
        final Category categoryToSave = Category.builder().title(categoryCreateDTO.getTitle())
                                                          .description(categoryCreateDTO.getDescription())
                                                          .owner(categoryOwner).build();
        final Category savedCategory = categoryService.save(categoryToSave);
        final CategoryDTO savedCategoryDTO = customCategoryMapper.toDto(savedCategory);
        savedCategoryDTO.setOwner(userMapper.toDto(categoryOwner));
        return ResponseEntity.ok(savedCategoryDTO);
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategoryById(Long categoryId, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryService.findById(categoryId);
        category.setTitle(categoryUpdateDTO.getTitle());
        category.setDescription(categoryUpdateDTO.getDescription());
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok(customCategoryMapper.toDto(savedCategory));
    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        log.debug("Category: {}", category);
        CategoryDTO categoryDTO = customCategoryMapper.toDto(category);
        log.debug("CategoryDTO: {}", categoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @Override
    public ResponseEntity<MessageDTO> deleteCategoryById(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        categoryService.delete(category);
        return ResponseEntity.ok().body(new MessageDTO(String.format("Category with id = %s has been deleted", categoryId), true));
    }

    @Override
    public ResponseEntity<Page<CategoryDTO>> getCategoriesByOwnerId(Long userId, Pageable pageable) {
        Page<Category> categoryPage = userService.getCategoriesByOwnerId(userId, pageable);
        Page<CategoryDTO> categoryDTOPage = categoryPage.map(customCategoryMapper::toDto);
        return ResponseEntity.ok(categoryDTOPage);
    }

    @Override
    public ResponseEntity<Page<CategoryDTO>> getCategoriesByLinkId(Long linkId, Pageable pageable) {
        final Page<Category> pageCategories = linkService.getCategoriesByLinkId(linkId, pageable);
        Page<CategoryDTO> categoryDTOPage = pageCategories.map(customCategoryMapper::toDto);
        return ResponseEntity.ok().body(categoryDTOPage);
    }

    @Override
    public ResponseEntity<Page<CategoryDTO>> getCategoriesByGroupId(Long groupId, Pageable pageable) {
        return ResponseEntity.ok(groupService.getCategoriesByGroupId(groupId, pageable));
    }

    @Override
    public ResponseEntity<GroupDTO> addCategoryToGroup(Long groupId, CategoryDTO categoryDTO) {
        final Category category = categoryService.findById(categoryDTO.getId());
        final Group group = groupService.findById(groupId);
        groupCategoryService.save(GroupCategory.builder().category(category).group(group).build());
        return ResponseEntity.ok().body(groupMapper.toDto(groupService.findById(groupId)));
    }

    @Override
    public ResponseEntity<GroupDTO> editCategoriesInGroup(Long groupId, List<Long> categoryIds) {
        final Group group = groupService.findById(groupId);
        groupCategoryService.deleteAllByGroupId(groupId);
        final List<GroupCategory> groupCategories = categoryIds.stream().map(id -> GroupCategory.builder().group(group).category(categoryService.findById(id)).build()).collect(Collectors.toList());
        groupCategoryService.save(groupCategories);
        return ResponseEntity.ok().body(groupMapper.toDto(groupService.findById(groupId)));
    }

    @Override
    public ResponseEntity<MessageDTO> removeCategoryFromGroup(Long groupId, CategoryDTO categoryDTO) {
        groupCategoryService.deleteCategoryFromGroup(categoryDTO.getId(), groupId);
        return ResponseEntity.ok().body(new MessageDTO(AppConstants.Messages.CATEGORY_REMOVED_SUCCESSFULLY_FROM_GROUP, true));
    }

}
