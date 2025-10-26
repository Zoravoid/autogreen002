package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupCategory;
import com.iucosoft.mylinksspringboot.exceptions.ResourceNotFoundException;
import com.iucosoft.mylinksspringboot.repositories.bridge.GroupCategoryRepository;
import com.iucosoft.mylinksspringboot.service.bridge.GroupCategoryService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupCategoryServiceImpl extends AbstractServiceImpl<GroupCategory, Long> implements GroupCategoryService {
    private final GroupCategoryRepository groupCategoryRepository;

    @Autowired
    public GroupCategoryServiceImpl(GroupCategoryRepository groupCategoryRepository) {
        this.groupCategoryRepository = groupCategoryRepository;
    }

    @Override
    protected JpaRepository<GroupCategory, Long> getRepository() {
        return groupCategoryRepository;
    }

    @Override
    public Page<Category> getCategoriesByGroupId(Long groupId, Pageable pageable) {
        return groupCategoryRepository.findCategoriesByGroupId(groupId, pageable);
    }

    @Override
    public Category getCategoryFromGroup(Long categoryId, Long groupId) {
        return groupCategoryRepository.findCategoryInGroupByCategoryIdAndGroupId(categoryId, groupId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no such link in this group"));
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        groupCategoryRepository.deleteByCategoryId(categoryId);
    }

    @Override
    public void deleteByGroupId(Long groupId) {
        groupCategoryRepository.deleteByGroupId(groupId);
    }

    @Override
    public void deleteCategoryFromGroup(Long categoryId, Long groupId) {
        groupCategoryRepository.findCategoryInGroupByCategoryIdAndGroupId(categoryId, groupId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no such link in this group"));
        groupCategoryRepository.deleteCategoryFromGroupByCategoryIdAndGroupId(categoryId, groupId);
    }

    @Override
    public void deleteAllByCategoryId(Long categoryId) {
        groupCategoryRepository.deleteAllByCategoryId(categoryId);
    }

    @Override
    public void deleteAllByGroupId(Long groupId) {
        groupCategoryRepository.deleteAllByGroupId(groupId);
    }
}
