package com.iucosoft.mylinksspringboot.service.bridge;

import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.CategoryOwner;
import com.iucosoft.mylinksspringboot.service.OperationIntf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryOwnerService extends OperationIntf<CategoryOwner, Long> {

    Page<User> getCategoryOwnersByCategoryId(Long categoryId, Pageable pageable);

    void deleteByCategoryId(Long categoryId);
    void deleteByOwnerId(Long ownerId);
}
