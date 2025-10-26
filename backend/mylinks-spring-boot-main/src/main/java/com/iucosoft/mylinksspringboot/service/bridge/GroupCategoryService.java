package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupCategory;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupCategoryService extends OperationIntf<GroupCategory, Long> {

    Page<Category> getCategoriesByGroupId(Long groupId, Pageable pageable);

    Category getCategoryFromGroup(Long categoryId, Long groupId);

    void deleteByCategoryId(Long categoryId);
    void deleteByGroupId(Long groupId);
    void deleteCategoryFromGroup(Long categoryId, Long groupId);

    void deleteAllByCategoryId(final Long categoryId);

    void deleteAllByGroupId(final Long groupId);
}
