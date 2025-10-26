package com.iucosoft.mylinksspringboot.repositories.bridge;

import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.bridge.GroupCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Long> {

    @Query("SELECT gc.category FROM GroupCategory gc WHERE gc.group.id = :groupId")
    Page<Category> findCategoriesByGroupId(final @Param("groupId") Long groupId, Pageable pageable);

    @Query("SELECT gc.group FROM GroupCategory gc WHERE gc.category.id = :categoryId")
    Page<Group> findGroupsByCategoryId(final @Param("categoryId") Long categoryId, Pageable pageable);


    @Query("SELECT gc.category FROM GroupCategory gc WHERE " +
            "gc.category.id = :categoryId AND gc.group.id = :groupId")
    Optional<Category> findCategoryInGroupByCategoryIdAndGroupId(
            final @Param("categoryId") Long categoryId, final @Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupCategory gc WHERE gc.category.id =:categoryId AND gc.group.id=:groupId")
    void deleteCategoryFromGroupByCategoryIdAndGroupId (
            final @Param("categoryId") Long categoryId, final @Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupCategory gc WHERE gc.category.id =:categoryId")
    void deleteByCategoryId (final @Param("categoryId") Long categoryId);

    @Modifying
    @Query("DELETE FROM GroupCategory gc WHERE  gc.group.id=:groupId")
    void deleteByGroupId(final @Param("groupId") Long groupId);

    void deleteAllByCategoryId(final Long categoryId);

    void deleteAllByGroupId(final Long groupId);
}
