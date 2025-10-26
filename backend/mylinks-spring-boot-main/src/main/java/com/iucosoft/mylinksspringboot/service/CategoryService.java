package com.iucosoft.mylinksspringboot.service;

import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService extends OperationIntf<Category, Long> {

    Page<Category> getCategoriesByOwnerId(Long ownerId, Pageable pageable);

    List<Long> getCategoriesIdsByOwnerId(Long ownerId);

    User getOwnerByCategoryId(Long categoryId);

    Page<Link> getLinksByCategoryId(Long categoryId, Pageable pageable);

    Page<Group> getGroupsByCategoryId(Long categoryId, Pageable pageable);

    Page<CategoryDTO> findCategoriesByFilterParams(String title, String description, Pageable pageable);
}
