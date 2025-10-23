package com.iucosoft.mylinksspringboot.repositories;

import com.iucosoft.mylinksspringboot.entities.Category;
import com.iucosoft.mylinksspringboot.entities.Group;
import com.iucosoft.mylinksspringboot.entities.Link;
import com.iucosoft.mylinksspringboot.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.owner.id =:ownerId")
    Page<Category> findCategoriesPageByOwnerId(final @Param("ownerId") Long ownerId, Pageable pageable);

    @Query("SELECT c FROM Category c JOIN LinkCategory lc ON c.id = lc.category.id JOIN Link l ON l.id = lc.link.id WHERE l.id = :linkId")
    List<Category> findCategoriesByLinkId(final @Param("linkId") Long linkId);

    @Query("SELECT c FROM Category c JOIN LinkCategory lc ON c.id = lc.category.id JOIN Link l ON l.id = lc.link.id WHERE c.owner.id = :linkId")
    List<Category> findCategoriesByOwnerId(final @Param("linkId") Long linkId);

    @Query("SELECT co.owner FROM CategoryOwner co WHERE co.category.id = :categoryId")
    User getOwnerByCategoryId(final @Param("categoryId") Long categoryId);

    @Query("SELECT lc.link FROM LinkCategory lc WHERE lc.category.id = :categoryId")
    Page<Link> getLinksByCategoryId(final @Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT gc.group FROM GroupCategory gc WHERE gc.category.id = :categoryId")
    Page<Group> getGroupsByCategoryId(final @Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE (:title IS NULL OR c.title LIKE CONCAT('%', :title, '%'))" +
            " AND (:description IS NULL OR c.description LIKE CONCAT('%', :description, '%'))")
    Page<Category> findCategoriesByFilterParams(final @Param("title") String title,
                                                final @Param("description") String description,
                                                Pageable pageable);
}
