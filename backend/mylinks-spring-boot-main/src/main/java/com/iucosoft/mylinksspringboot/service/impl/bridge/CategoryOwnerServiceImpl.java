package com.iucosoft.mylinksspringboot.service.impl.bridge;

import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.CategoryOwner;
import com.iucosoft.mylinksspringboot.repositories.bridge.CategoryOwnerRepository;
import com.iucosoft.mylinksspringboot.service.bridge.CategoryOwnerService;
import com.iucosoft.mylinksspringboot.service.impl.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryOwnerServiceImpl extends AbstractServiceImpl<CategoryOwner, Long> implements CategoryOwnerService {
    private final CategoryOwnerRepository categoryOwnerRepository;

    @Autowired
    public CategoryOwnerServiceImpl(CategoryOwnerRepository categoryOwnerRepository) {
        this.categoryOwnerRepository = categoryOwnerRepository;
    }

    @Override
    protected JpaRepository<CategoryOwner, Long> getRepository() {
        return categoryOwnerRepository;
    }

    @Override
    public Page<User> getCategoryOwnersByCategoryId(Long categoryId, Pageable pageable) {
        return categoryOwnerRepository.findCategoryOwnersByCategoryId(categoryId, pageable);
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        categoryOwnerRepository.deleteByCategoryId(categoryId);
    }

    @Override
    public void deleteByOwnerId(Long ownerId) {
        categoryOwnerRepository.deleteByOwnerId(ownerId);
    }
}
