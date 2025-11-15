package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.User;
import com.iucosoft.mylinksspringboot.entities.bridge.CategoryOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryOwnerRepository extends JpaRepository<CategoryOwner, Long> {

    @Query("SELECT co.owner FROM CategoryOwner co WHERE co.category.id = :categoryId")
    Page<User> findCategoryOwnersByCategoryId(final @Param("categoryId") Long categoryId, Pageable pageable);

    @Modifying
    @Query("DELETE FROM CategoryOwner co WHERE co.category.id = :categoryId")
    void deleteByCategoryId(final @Param("categoryId") Long categoryId);

    @Modifying
    @Query("DELETE FROM CategoryOwner co WHERE co.owner.id = :ownerId")
    void deleteByOwnerId(final @Param("ownerId") Long ownerId);
}
